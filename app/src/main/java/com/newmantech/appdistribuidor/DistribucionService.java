package com.newmantech.appdistribuidor;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DistribucionService {

    @GET("distribucion/listar/{usuario}")//34
    Call<List<Distribucion>> getListadoDistribucion(@Path("usuario") Integer usuario);

    @POST("pedido/actualizarEstadoPedido")
    Call<Integer> actualizarPedido(@Body PedidoPost pedido);

    //@POST("pedido/actualizarEstadoPedido")
    //Call<Integer> registrarIncidencia(@Body PedidoPost pedido);

}
