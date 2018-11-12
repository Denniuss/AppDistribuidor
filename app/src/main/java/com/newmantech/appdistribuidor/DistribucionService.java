package com.newmantech.appdistribuidor;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DistribucionService {

    @GET("distribucion/listar/1")
    Call<List<Distribucion>> getListadoDistribucion();

    @POST("distribucion/finalizarPedido")
    Call<Integer> finalizarPedido(@Body PedidoPost pedido);

    @POST("distribucion/registrarIncidencia")
    Call<Integer> registrarIncidencia(@Body PedidoPost pedido);

}
