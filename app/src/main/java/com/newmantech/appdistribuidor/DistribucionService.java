package com.newmantech.appdistribuidor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DistribucionService {

    @GET("distribucion/listar/1")
    Call<List<Distribucion>> getPacientes();

}
