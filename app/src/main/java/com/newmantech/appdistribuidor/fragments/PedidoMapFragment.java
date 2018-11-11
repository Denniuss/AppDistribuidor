package com.newmantech.appdistribuidor.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newmantech.appdistribuidor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedidoMapFragment extends Fragment {


    public PedidoMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pedido_map, container, false);
    }

}
