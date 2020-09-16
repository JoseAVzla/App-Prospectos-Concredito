package com.josevalenzuela.prospectosconcreditoapp.Interactor;

import com.josevalenzuela.prospectosconcreditoapp.contracts.ListadoProspectosContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoProspectosInteractor implements ListadoProspectosContract.Interactor {
    private ListadoProspectosContract.CompleteListener listener;
    private List<Prospecto> prospectosList;

    public ListadoProspectosInteractor(ListadoProspectosContract.CompleteListener listener) {
        this.listener = listener;
    }

    @Override
    public void obtenerTodosProspectos() {

        prospectosList = new ArrayList<>();

        ConnectionPool pool = new ConnectionPool(5, 10000, TimeUnit.MILLISECONDS);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(pool)
                .build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.8:8090/prospectos/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonHolderApi holderApi = retrofit.create(JsonHolderApi.class);

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
