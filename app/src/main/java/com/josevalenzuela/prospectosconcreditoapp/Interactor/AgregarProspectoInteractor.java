package com.josevalenzuela.prospectosconcreditoapp.Interactor;

import com.josevalenzuela.prospectosconcreditoapp.DTO.ProspectoRequestDTO;
import com.josevalenzuela.prospectosconcreditoapp.contracts.AgregarProspectoContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarProspectoInteractor implements AgregarProspectoContract.Interactor {
    private Prospecto prospectoRespuesta;
    private AgregarProspectoContract.CompleteListener listener;
    private List<String> documentos;

    public AgregarProspectoInteractor(AgregarProspectoContract.CompleteListener listener) {
        this.listener = listener;
    }

    @Override
    public void guardarProspecto(ProspectoRequestDTO prospecto) {
        this.documentos = documentos;
        JsonHolderApi holderApi = ApiRetrofit.getInstance().getHolderApi();

        Call<Prospecto> callAgregarProspecto = holderApi.guardarProspecto(prospecto);
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
