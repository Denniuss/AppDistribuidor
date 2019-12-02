package com.newmantech.appdistribuidor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.newmantech.appdistribuidor.utils.TaskLoadedCallback;
import com.newmantech.appdistribuidor.utils.Utilitario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.newmantech.appdistribuidor.utils.FetchURL;

import java.security.Policy;

public class AtenderPedidoMapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {
    public Button btnAtender;
    public Button btnFinalizar;
    public Button btnIncidente;
    public Button btnRuta;
    private GoogleMap mMap;
    public Dialog dlgFinalizarPedido;
    public Dialog dlgRegistrarIndicencia;
    public TextView idpedido;
    public EditText edtObservaciones;
    MarkerOptions place1, place2;
    Polyline currentPolyline;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atender_pedido_maps);

        btnRuta = (Button) findViewById(R.id.btnRuta);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        idpedido = (TextView) findViewById(R.id.idpedido);
        idpedido.setText(getIntent().getExtras().getString("nidpedido"));
        //String nidpedido = getIntent().getExtras().getString("nidpedido");

        btnAtender = (Button) findViewById(R.id.btnAtender);
        btnAtender.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                AtenderPedido();

                //Agregar Guardado Atencion

                Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                DistribucionService distribucionService = retrofit.create(DistribucionService.class);
                PedidoPost pedidoTemP = new PedidoPost();
                pedidoTemP.setEstado(Utilitario.ESTADO_PEDIDO_SIMPLE.DISTRIBUIDO_INICIADO_PEDIDO.getCodigo());
                pedidoTemP.setIdUsuario(Utilitario.idDistriduidor);
                pedidoTemP.setIdPedido(Integer.valueOf(idpedido.getText().toString()));
                //pedidoTemP.setObservacion(edtObservaciones.getText().toString());

                Call<Integer> resultado = distribucionService.actualizarPedido(pedidoTemP);
                resultado.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        Log.i("Atender ", "onResponse: "+response.code());
                        Log.i("Atender message", "onResponse: "+response.message());

                        if(response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Se inicio la atención del pedido",Toast.LENGTH_SHORT).show();

                            Log.i("Atender", "onResponse: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("onFaillure chamado ", t.getMessage());
                    }
                });


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

        double nLatitud = getIntent().getExtras().getDouble("nLatitud");
        double nLongitud = getIntent().getExtras().getDouble("nLongitud");


        /* Obtener posicion actual
            FusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            lt = String.valueOf(location.getLatitude());
                            ln = String.valueOf((location.getLongitude()));
                            LatLng Ubica = new LatLng(Float.parseFloat(lt), Float.parseFloat(ln));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Ubica, 15));
                            mMap.addMarker(new MarkerOptions().position(new LatLng(Float.parseFloat(lt), Float.parseFloat(ln))));
                        }
                    }
                });
        * */

        //place1 = new MarkerOptions().position(new LatLng(-12.0746749,-77.056573)).title("almacén");
        place1 = new MarkerOptions().position(new LatLng(-12.0874807,-77.0501289)).title("UPC");
        place2 = new MarkerOptions().position(new LatLng(nLatitud, nLongitud)).title("pedido");

        btnRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getUrl(place1.getPosition(),place2.getPosition(),"driving");
                new FetchURL(AtenderPedidoMapsActivity.this).execute(url,"driving");

            }
        });

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
                    pedidoTemP.setEstado(Utilitario.ESTADO_PEDIDO_SIMPLE.DISTRIBUIDO_FINALIZADO_PEDIDO.getCodigo());
                    pedidoTemP.setIdUsuario(Utilitario.idDistriduidor);
                    pedidoTemP.setObservacion(edtObservaciones.getText().toString());

                    Call<Integer> resultado = distribucionService.actualizarPedido(pedidoTemP);
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
                pedidoTemP.setEstado(Utilitario.ESTADO_PEDIDO_SIMPLE.CON_INCIDENCIA_PEDIDO.getCodigo());
                pedidoTemP.setIdUsuario(Utilitario.idDistriduidor);
                pedidoTemP.setIdPedido(Integer.valueOf(idpedido.getText().toString()));
                pedidoTemP.setObservacion(edtObservaciones.getText().toString());

                Call<Integer> resultado = distribucionService.actualizarPedido(pedidoTemP);
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

        //place1 = new MarkerOptions().position(new LatLng(-12.0746749,-77.056573)).title("almacén");
        place1 = new MarkerOptions().position(new LatLng(-12.0874807,-77.0501289)).title("UPC");
        place2 = new MarkerOptions().position(new LatLng(nLatitud, nLongitud)).title("pedido");

        // Add a marker in Sydney and move the camera
        LatLng pedido = new LatLng(nLatitud, nLongitud);
        LatLng almacen = new LatLng(-12.0746749,-77.056573);
/*        mMap.addMarker(new MarkerOptions().position(pedido).title("pedido"));
        mMap.addMarker(new MarkerOptions().position(pedido).title("pedido"));*/
        mMap.addMarker(place1);
        mMap.addMarker(place2);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(almacen));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(almacen,18));

    }
    private String getUrl(LatLng origin, LatLng destino, String direcctionMode){
            String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
            String str_dest = "destination=" + destino.latitude + "," + destino.longitude;
            String mode = "mode=" + direcctionMode;
            String parameters = str_origin + "&" + str_dest + "&" + mode;
            String output = "json";
            String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
            return url;
    }


    @Override
    public void onTaskDone(Object... values) {
        if(currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);

    }
}

