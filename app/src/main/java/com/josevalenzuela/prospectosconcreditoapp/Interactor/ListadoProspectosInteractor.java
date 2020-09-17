package com.josevalenzuela.prospectosconcreditoapp.Interactor;

import com.josevalenzuela.prospectosconcreditoapp.contracts.ListadoProspectosContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListadoProspectosInteractor implements ListadoProspectosContract.Interactor {
    private ListadoProspectosContract.CompleteListener listener;
    private List<Prospecto> prospectosList;

    public ListadoProspectosInteractor(ListadoProspectosContract.CompleteListener listener) {
        this.listener = listener;

    }

    @Override
    public void obtenerTodosProspectos() {

        prospectosList = new ArrayList<>();

        JsonHolderApi holderApi = ApiRetrofit.getInstance().getHolderApi();

        Call<List<Prospecto>> call = holderApi.getProspectos();

        call.enqueue(new Callback<List<Prospecto>>() {
            @Override
            public void onResponse(Call<List<Prospecto>> call, Response<List<Prospecto>> response) {
                if (!response.isSuccessful()){
                    listener.onSucces(prospectosList);
                    return;
                }
                prospectosList = new ArrayList<>();
                List<Prospecto> prospectos = response.body();
                for (Prospecto prospecto: prospectos) {
                    prospectosList.add(prospecto);
                }

                listener.onSucces(prospectosList);
            }

            @Override
            public void onFailure(Call<List<Prospecto>> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }
}
