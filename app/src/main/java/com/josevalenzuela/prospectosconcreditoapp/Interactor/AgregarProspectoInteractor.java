package com.josevalenzuela.prospectosconcreditoapp.Interactor;

import com.josevalenzuela.prospectosconcreditoapp.contracts.AgregarProspectoContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarProspectoInteractor implements AgregarProspectoContract.Interactor{
    private Prospecto prospectoRespuesta;
    private AgregarProspectoContract.CompleteListener listener;


    public AgregarProspectoInteractor(AgregarProspectoContract.CompleteListener listener) {
        this.listener = listener;
    }

    @Override
    public void guardarProspecto(Prospecto prospecto) {


        JsonHolderApi holderApi = ApiRetrofit.getInstance().getHolderApi();

        Call<Prospecto> callAgregarProspecto = holderApi.guardarProspecto(prospecto);
        callAgregarProspecto.enqueue(new Callback<Prospecto>() {
            @Override
            public void onResponse(Call<Prospecto> call, Response<Prospecto> response) {
                if (response.isSuccessful()){
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
