package com.newmantech.appdistribuidor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PedidosPendientesActivity extends AppCompatActivity {

    private List<String> names;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoudManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_pendientes);

        names = this.getAllNames();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoudManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                Toast.makeText(PedidosPendientesActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<String> getAllNames(){
        return new ArrayList<String>(){{
            add("Alejandro");
            add("Antonio");
            add("Jose");
        }
        };
    }
}
