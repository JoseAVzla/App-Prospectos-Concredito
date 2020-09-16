package com.josevalenzuela.prospectosconcreditoapp.Interactor;

import com.josevalenzuela.prospectosconcreditoapp.contracts.ApiTestContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestInteractor implements ApiTestContract.Interactor {
    private ApiTestContract.CompleteListener listener;
    private List<Prospecto> prospectosList;

    public TestInteractor(ApiTestContract.CompleteListener listener) {
        this.listener = listener;
    }

    @Override
    public void testingApi() {
        prospectosList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.8:8090/prospectos/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonProspectoHolderApi holderApi = retrofit.create(JsonProspectoHolderApi.class);

        Call<List<Prospecto>> call = holderApi.getProspectos();

        call.enqueue(new Callback<List<Prospecto>>() {
            @Override
            public void onResponse(Call<List<Prospecto>> call, Response<List<Prospecto>> response) {
                if (!response.isSuccessful()){
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
