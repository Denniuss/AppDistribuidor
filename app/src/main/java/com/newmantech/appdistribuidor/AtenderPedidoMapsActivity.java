package com.newmantech.appdistribuidor;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AtenderPedidoMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public Button btnAtender;
    public Button btnFinalizar;
    public Button btnIncidente;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atender_pedido_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnAtender = (Button) findViewById(R.id.btnAtender);
        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
        btnIncidente = (Button) findViewById(R.id.btnIncidente);

        btnAtender.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                AtenderPedido();
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                FinalizarPedido();
            }
        });

        btnIncidente.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Incidente();
            }
        });
    }

    private void AtenderPedido(){

    }

    private void FinalizarPedido(){

    }

    private void Incidente(){

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

