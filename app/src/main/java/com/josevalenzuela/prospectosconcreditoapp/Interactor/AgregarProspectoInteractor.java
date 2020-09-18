package com.josevalenzuela.prospectosconcreditoapp.Interactor;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.josevalenzuela.prospectosconcreditoapp.DTO.ProspectoRequestDTO;
import com.josevalenzuela.prospectosconcreditoapp.contracts.AgregarProspectoContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarProspectoInteractor implements AgregarProspectoContract.Interactor {
    private Prospecto prospectoRespuesta;
    private AgregarProspectoContract.CompleteListener listener;
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private StorageReference Folder = firebaseStorage.getReference();


    public AgregarProspectoInteractor(AgregarProspectoContract.CompleteListener listener) {
        this.listener = listener;
    }


    public ProspectoRequestDTO saveDocsOnFirestore(List<Bitmap> docs, ProspectoRequestDTO prospectoRequestDTO){
        List<String> urls = new ArrayList<>();
        int index = 0;

        int docsGuardadosContador = 0;
        int numeroDocs = docs.size();

        for (Bitmap b: docs) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();
            String fileName = prospectoRequestDTO.getRfc() + index + ".jpg";
            StorageReference ref = Folder.child(fileName );
            urls.add(fileName);
            UploadTask uploadTask = ref.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });

            index++;
        }
        System.out.println(urls.size());
        prospectoRequestDTO.setDocumentosUrl(urls);
        return prospectoRequestDTO;
    }

    @Override
    public void guardarProspecto(ProspectoRequestDTO prospecto, List<Bitmap> docs) {
        JsonHolderApi holderApi = ApiRetrofit.getInstance().getHolderApi();
        ProspectoRequestDTO prospectoAGuardar = saveDocsOnFirestore(docs, prospecto);
        Call<Prospecto> callAgregarProspecto = holderApi.guardarProspecto(prospectoAGuardar);
        callAgregarProspecto.enqueue(new Callback<Prospecto>() {
            @Override
            public void onResponse(Call<Prospecto> call, Response<Prospecto> response) {
                if (response.isSuccessful()) {
                    prospectoRespuesta = response.body();
                    listener.onSucces(prospectoRespuesta);
                }
            }

            @Override
            public void onFailure(Call<Prospecto> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }
}
