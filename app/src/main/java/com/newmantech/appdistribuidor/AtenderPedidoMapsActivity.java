package com.newmantech.appdistribuidor;

import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.newmantech.appdistribuidor.utils.Utilitario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AtenderPedidoMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public Button btnAtender;
    public Button btnFinalizar;
    public Button btnIncidente;
    private GoogleMap mMap;
    public Dialog dlgFinalizarPedido;
    public Dialog dlgRegistrarIndicencia;
    public TextView idpedido;
    public EditText edtObservaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atender_pedido_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        idpedido = (TextView) findViewById(R.id.idpedido);
        idpedido.setText(getIntent().getExtras().getString("nidpedido"));
        //String nidpedido = getIntent().getExtras().getString("nidpedido");

        btnAtender = (Button) findViewById(R.id.btnAtender);
        btnAtender.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                AtenderPedido();
            }
        });
        //btnAtender.setVisibility(View.GONE);

        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
        btnFinalizar.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                FinalizarPedido();
            }
        });
        btnFinalizar.setVisibility(View.INVISIBLE);
        dlgFinalizarPedido = new Dialog(this);

        btnIncidente = (Button) findViewById(R.id.btnIncidente);
        btnIncidente.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Incidente();
            }
        });
        btnIncidente.setVisibility(View.INVISIBLE);

        dlgRegistrarIndicencia = new Dialog(this);
    }

    public void ShowFinalizarPopup(){
        TextView txtclose;
        Button btnFinalizarPedido;
        dlgFinalizarPedido.setContentView(R.layout.finalizarpopup);
        txtclose = (TextView)  dlgFinalizarPedido.findViewById(R.id.txtclose);
        edtObservaciones = (EditText) dlgFinalizarPedido.findViewById(R.id.edtObservaciones);
        btnFinalizarPedido = (Button) dlgFinalizarPedido.findViewById(R.id.btnFinalizarPedido);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgFinalizarPedido.dismiss();
            }
        });

        btnFinalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Agregar Guardado de finalizacion de Atencion
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    DistribucionService distribucionService = retrofit.create(DistribucionService.class);
                    PedidoPost pedidoTemP = new PedidoPost();
                    pedidoTemP.setIdPedido(Integer.valueOf(idpedido.getText().toString()));
                    pedidoTemP.setObservacion(edtObservaciones.getText().toString());

                    Call<Integer> resultado = distribucionService.finalizarPedido(pedidoTemP);
                    resultado.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            Log.i("finalizarPedido ", "onResponse: "+response.code());
                            Log.i("finalizarPedido message", "onResponse: "+response.message());

                            if(response.isSuccessful()) {
                                Log.i("FINALIZA_PEDIDO", "onResponse: " + response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Log.e("onFaillure chamado ", t.getMessage());
                        }
                    });



                dlgFinalizarPedido.dismiss();
                Toast.makeText(AtenderPedidoMapsActivity.this,"El pedido fue finalizado con exito",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(AtenderPedidoMapsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        dlgFinalizarPedido.show();
    }

    public void ShowInicdenciaPopup(){
        TextView txtclose;
        Button btnGuardarIncidente;
        dlgRegistrarIndicencia.setContentView(R.layout.incidenciapopup);
        txtclose = (TextView)  dlgRegistrarIndicencia.findViewById(R.id.txtclose);
        edtObservaciones = (EditText) dlgRegistrarIndicencia.findViewById(R.id.edtObservaciones);
        btnGuardarIncidente = (Button) dlgRegistrarIndicencia.findViewById(R.id.btnGuardarIncidente);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgRegistrarIndicencia.dismiss();
            }
        });
        btnGuardarIncidente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Agregar Guardado de incidente de Atencion

                Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                DistribucionService distribucionService = retrofit.create(DistribucionService.class);
                PedidoPost pedidoTemP = new PedidoPost();
                pedidoTemP.setIdPedido(Integer.valueOf(idpedido.getText().toString()));
                pedidoTemP.setObservacion(edtObservaciones.getText().toString());

                Call<Integer> resultado = distribucionService.registrarIncidencia(pedidoTemP);
                resultado.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        Log.i("registrarIncidencia ", "onResponse: "+response.code());
                        Log.i("Incidencia message", "onResponse: "+response.message());

                        if(response.isSuccessful()) {
                            Log.i("INCIDENCIA", "onResponse: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("onFaillure chamado ", t.getMessage());
                    }
                });


                dlgRegistrarIndicencia.dismiss();
                Toast.makeText(AtenderPedidoMapsActivity.this,"La incidencia fue registrada con exito",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(AtenderPedidoMapsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        dlgRegistrarIndicencia.show();
    }

    private void AtenderPedido(){
        btnAtender.setVisibility(View.INVISIBLE);
        btnFinalizar.setVisibility(View.VISIBLE);
        btnIncidente.setVisibility(View.VISIBLE);
        //Agregar Inicio de Atencion
    }

    private void FinalizarPedido(){
        ShowFinalizarPopup();
    }

    private void Incidente(){
        ShowInicdenciaPopup();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings mapUtils = mMap.getUiSettings();
        //Habilita el zoom
        mapUtils.setZoomControlsEnabled(true);
        //Habilita la brujula
        mapUtils.setCompassEnabled(true);
        //Habilita el boton "Mi Ubicacion"
        mapUtils.setMyLocationButtonEnabled(true);
        //Habilita la barra de herramientas del mapa
        mapUtils.setMapToolbarEnabled(true);

        double nLatitud = getIntent().getExtras().getDouble("nLatitud");
        double nLongitud = getIntent().getExtras().getDouble("nLongitud");

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(nLatitud, nLongitud);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Estoy aquí"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,18));
/*
        mMap.setMinZoomPreference(20);
        mMap.setMaxZoomPreference(20);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng sjl = new LatLng(-12.003887, -77.06022);
        mMap.addMarker(new MarkerOptions().position(sjl).title("SJL").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sjl));

        CameraPosition camera = new CameraPosition.Builder()
                .target(sjl)
                .zoom(18)
                .bearing(0)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(AtenderPedidoMapsActivity.this,"Click on: \n" +
                "Latitud:" + latLng.latitude + "\n" +
                "Longitud" + latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(AtenderPedidoMapsActivity.this,"Click on: \n" +
                        "Latitud:" + latLng.latitude + "\n" +
                        "Longitud" + latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(AtenderPedidoMapsActivity.this,"Click on: \n" +
                        "Latitud:" + marker.getPosition().latitude + "\n" +
                        "Longitud" + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();

            }
        });


        */
        }
    }

