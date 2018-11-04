package com.newmantech.appdistribuidor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidosActivity extends AppCompatActivity {

    private List<String> names;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        listView = (ListView)findViewById(R.id.listView);

        names = new ArrayList<String>();
        names.add("Jose");
        names.add("Manuel");
        names.add("Maria");
        names.add("Raquel");
        names.add("Indurama");
        names.add("Talma");
        //Adaptador, la forma visual en que mostraremos nuestros datos
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);

        //Enlazamos el adaptador con nuestro List View
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ListaPedidosActivity.this, "Clicked: "+names.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }
}
