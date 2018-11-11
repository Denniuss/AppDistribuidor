package com.newmantech.appdistribuidor;

public class Pedido {
    private String cliente;
    private String direccion;
    private String distrito;
    private String descripcion;
    private String estado;
    //private String imagenurl;
    private int imagen;
    private String latitud;
    private String longitud;

    public Pedido(/*String imagenurl*/ int imagen,String cliente, String direccion, String distrito, String descripcion, String estado, String latitud, String longitud)
    {
        this.cliente = cliente;
        this.direccion = direccion;
        this.distrito = distrito;
        this.descripcion = descripcion;
        this.estado = estado;
        this.imagen = imagen;
        //this.imagenurl = imagenurl;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    /*
    public String getImagenurl() {
        return imagenurl;
    }
    public void setImagenurl(String imagenurl) {
        this.imagenurl = imagenurl;
    }*/
    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
    public String getCliente(){ return cliente; }
    public void setCliente(String cliente){ this.cliente = cliente;}
    public String getDireccion(){ return direccion; }
    public void setDireccion(String direccion){ this.direccion = direccion;}
    public String getDistrito(){ return distrito; }
    public void setDistrito(String distrito){ this.distrito = distrito;}
    public String getDescripcion(){ return descripcion; }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion;}
    public String getEstado(){ return estado; }
    public void setEstado(String estado){ this.estado = estado;}
    public String getLatitud(){ return latitud;}
    public void setLatitud(String latitud){ this.latitud = latitud;}
    public String getLongitud(){ return longitud;}
    public void setLongitud(String longitud){ this.longitud = longitud;}
}
