package com.josevalenzuela.prospectosconcreditoapp.Interactor;

import com.josevalenzuela.prospectosconcreditoapp.DTO.ProspectoRequestDTO;
import com.josevalenzuela.prospectosconcreditoapp.contracts.EvaluarProspectoContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluarProspectoInteractor implements EvaluarProspectoContract.Interactor {
    private EvaluarProspectoContract.CompleteListener listener;
    private Prospecto prospectoRespuesta;

    public EvaluarProspectoInteractor(EvaluarProspectoContract.CompleteListener listener) {
        this.listener = listener;
    }

    @Override
    public void actualizarEstadoProspecto(ProspectoRequestDTO prospecto) {
        JsonHolderApi holderApi = ApiRetrofit.getInstance().getHolderApi();

        Call<Prospecto> callAgregarProspecto = holderApi.actualizarProspecto(prospecto);
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
