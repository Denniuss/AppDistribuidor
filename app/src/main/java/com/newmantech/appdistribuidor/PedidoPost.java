package com.newmantech.appdistribuidor;

public class PedidoPost {
    private int idPedido;
    private String observacion;

    public PedidoPost() {

    }

    public PedidoPost(int idPedido, String observacion)
    {
        this.idPedido = idPedido;
        this.observacion = observacion;
    }

    public int getIdPedido(){return idPedido;}
    public void setIdPedido(int idPedido){this.idPedido = idPedido;}
    public String getObservacion(){ return observacion;}
    public void setObservacion(String observacion){ this.observacion = observacion;}

}
