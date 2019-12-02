package com.newmantech.appdistribuidor;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.newmantech.appdistribuidor.utils.Utilitario;

public class DetalleActivity extends AppCompatActivity {
    public ImageView imagen;
    public TextView cliente;
    public TextView direccion;
    public TextView distrito;
    public TextView estado;
    public TextView latitud;
    public TextView longitud;
    public TextView idpedido;
    public TextView idpedidoLabel;
    public Button btnMapa;

    Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

/*
        currentFragment = new DetallePedidoFragment();
        changeFragment(currentFragment);

        Toolbar toolbar = (Toolbar) currentFragment.getView().findViewById(R.id.toolbar);
        ((AppCompatActivity)currentFragment.getActivity()).setSupportActionBar(toolbar);
 */
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        cliente = (TextView) findViewById(R.id.cliente);
        direccion = (TextView) findViewById(R.id.direccion);
        distrito = (TextView) findViewById(R.id.distrito);
        estado = (TextView) findViewById(R.id.estado);
        imagen = (ImageView) findViewById(R.id.imagen);
        idpedido = (TextView) findViewById(R.id.idpedido);
        latitud = (TextView) findViewById(R.id.latitud);
        longitud = (TextView) findViewById(R.id.longitud);
        btnMapa = (Button) findViewById(R.id.btnMapa);
        idpedidoLabel = (TextView) findViewById(R.id.idpedidoLabel);



        cliente.setText(getIntent().getExtras().getString("curCliente"));
        direccion.setText("Direccion: " + getIntent().getExtras().getString("curDireccion"));
        distrito.setText("Distrito: " + getIntent().getExtras().getString("curDistrito"));
        estado.setText("Estado: " + getIntent().getExtras().getString("curEstado"));
        imagen.setImageResource(getIntent().getExtras().getInt("curImagen"));
        idpedido.setText(String.valueOf(getIntent().getExtras().getInt("curIdpedido")));
        latitud.setText(getIntent().getExtras().getString("curLatitud"));
        longitud.setText(getIntent().getExtras().getString("curLongitud"));
        idpedidoLabel.setText("Pedido: " +String.valueOf(getIntent().getExtras().getString("curIdpedidoLabel")));


        btnMapa.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                MostrarMapa();
            }
        });

        //Picasso.with(imagen.getContext())
          //      .load(getIntent().getExtras().getString("curImagen")).into(imagen);
    }

    private void MostrarMapa(){
        if(latitud.getText().toString().isEmpty()||longitud.getText().toString().isEmpty()){
            Toast.makeText(this,getString(R.string.geo_entercords),Toast.LENGTH_SHORT).show();
        }else{
            String pLatitud = latitud.getText().toString();
            String pLongitud = longitud.getText().toString();
            String pidpedido = idpedido.getText().toString();


            if(!Utilitario.isNumeric(pLatitud)&&!Utilitario.isNumeric(pLongitud)){
                Toast.makeText(this,"Ingrese valores numericos",Toast.LENGTH_SHORT).show();
            }else{

                double nLatitud = Double.parseDouble(pLatitud);
                double nLongitud= Double.parseDouble(pLongitud);
                String nidpedido = pidpedido;

                Bundle bundle = new Bundle();
                bundle.putDouble("nLatitud",nLatitud);
                bundle.putDouble("nLongitud",nLongitud);
                bundle.putString("nidpedido",nidpedido);
                Intent newActivity = new Intent(this,AtenderPedidoMapsActivity.class);
                newActivity.putExtras(bundle);
                this.startActivity(newActivity);
            }
        }
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_detalle:
                currentFragment = new DetallePedidoFragment();
                break;
            case R.id.menu_maps:
                currentFragment = new PedidoMapFragment();
                break;
        }
        changeFragment(currentFragment);
        return super.onOptionsItemSelected(item);
    }
     private  void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment).commit();
     }
     */
}
