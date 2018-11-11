package com.newmantech.appdistribuidor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distribucion {

    @SerializedName("id_pedido")
    @Expose
    private Integer idPedido;
    @SerializedName("cliente")
    @Expose
    private String cliente;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("referencia")
    @Expose
    private Object referencia;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("cantidad")
    @Expose
    private Object cantidad;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Object getReferencia() {
        return referencia;
    }

    public void setReferencia(Object referencia) {
        this.referencia = referencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Object getCantidad() {
        return cantidad;
    }

    public void setCantidad(Object cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}