package com.josevalenzuela.prospectosconcreditoapp.Interactor;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {
    private static ApiRetrofit instance = null;
    public static final String BASE_URL = "http://192.168.0.2:8090/prospectos/api/";

    private JsonHolderApi holderApi;

    public static ApiRetrofit getInstance() {
        if (instance == null) {
            instance = new ApiRetrofit();
        }

        return instance;
    }

    private ApiRetrofit() {
        initRetrofit();
    }

    private void initRetrofit() {
        ConnectionPool pool = new ConnectionPool(5, 10000, TimeUnit.MILLISECONDS);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(pool)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.holderApi = retrofit.create(JsonHolderApi.class);
    }



    public JsonHolderApi getHolderApi() {
        return holderApi;
    }
}
