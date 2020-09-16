package com.josevalenzuela.prospectosconcreditoapp.Interactor;

import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonHolderApi {

    @GET("prospectos/all")
    Call<List<Prospecto>> getProspectos();
}
