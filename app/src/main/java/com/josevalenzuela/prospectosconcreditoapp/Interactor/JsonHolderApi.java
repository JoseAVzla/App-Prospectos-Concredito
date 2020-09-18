package com.josevalenzuela.prospectosconcreditoapp.Interactor;

import com.josevalenzuela.prospectosconcreditoapp.DTO.ProspectoRequestDTO;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonHolderApi {

    @GET("prospectos/all")
    Call<List<Prospecto>> getProspectos();

    @POST("prospectos/save")
    Call<Prospecto> guardarProspecto(@Body ProspectoRequestDTO prospecto);
}
