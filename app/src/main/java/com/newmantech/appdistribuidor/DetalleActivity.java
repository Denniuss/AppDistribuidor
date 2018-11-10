package com.newmantech.appdistribuidor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {
    public ImageView imagen;
    public TextView cliente;
    public TextView direccion;
    public TextView distrito;
    public TextView estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cliente = (TextView) findViewById(R.id.cliente);
        direccion = (TextView) findViewById(R.id.direccion);
        distrito = (TextView) findViewById(R.id.distrito);
        estado = (TextView) findViewById(R.id.estado);
        imagen = (ImageView) findViewById(R.id.imagen);

        cliente.setText(getIntent().getExtras().getString("curCliente"));
        direccion.setText("Direccion: " + getIntent().getExtras().getString("curDireccion"));
        distrito.setText("Distrito: " + getIntent().getExtras().getString("curDistrito"));
        estado.setText("Estado: " + getIntent().getExtras().getString("curEstado"));

        Picasso.with(imagen.getContext())
                .load(getIntent().getExtras().getString("curImagen")).into(imagen);
    }
}
