package com.newmantech.appdistribuidor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Pedido> items = new ArrayList();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FillPedidos();

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new PedidosAdapter(items);
        recycler.setAdapter(adapter);
    }
    private void FillPedidos(){
        items.add(new Pedido(R.drawable.face01, "Marlon Leandro", "Av. Chimu 412 Urb. Zarate", "SJL", "Aniversario","Pendiente","-12.02456","-77.00057"));
        items.add(new Pedido(R.drawable.face02, "Juan Perez", "Av. Chimu 413 Urb. Zarate", "SJL", "Cumpleaños","Pendiente","-12.070118","-77.029274"));
        items.add(new Pedido(R.drawable.face03, "Carlos Gonzales", "Av. Chimu 414 Urb. Zarate", "SJL", "Boda","Pendiente","-12.058746","-77.12736"));
        items.add(new Pedido(R.drawable.face04, "Pedro Miranda", "Av. Chimu 415 Urb. Zarate", "SJL", "Aniversario","Pendiente","-11.986429","-77.090034"));
        items.add(new Pedido(R.drawable.face05, "Mateo Montero", "Av. Chimu 416 Urb. Zarate", "SJL", "Dia del Padre","Pendiente","-11.9229765","-77.04199"));
        items.add(new Pedido(R.drawable.face06, "Hugo Santana", "Av. Chimu 417 Urb. Zarate", "SJL", "Dia de la empresa","Pendiente","-12.05393","-76.97475"));
        items.add(new Pedido(R.drawable.face07, "Pedro Quijandria", "Av. Chimu 418 Urb. Zarate", "SJL", "Boda","Pendiente","-12.067931","-77.01171"));
        items.add(new Pedido(R.drawable.face08, "Ruben cartagena", "Av. Chimu 419 Urb. Zarate", "SJL", "Aniversario","Pendiente","-12.003887","-77.06022"));
    }
}
