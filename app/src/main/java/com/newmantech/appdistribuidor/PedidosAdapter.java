package com.newmantech.appdistribuidor;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.squareup.picasso.Picasso;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.PedidoViewHolder> {

    private List<Pedido> items;

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {

        public CardView pedidoCardView;
        public ImageView imagen;
        public TextView cliente;
        public TextView direccion;
        public TextView distrito;
        public TextView estado;
        public TextView idpedidoLabel;
        public TextView idpedido;

        public PedidoViewHolder(View v) {

            super(v);
            pedidoCardView = (CardView) v.findViewById(R.id.pedido_card);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            cliente = (TextView) v.findViewById(R.id.cliente);
            direccion = (TextView) v.findViewById(R.id.direccion);
            distrito = (TextView) v.findViewById(R.id.distrito);
            estado = (TextView) v.findViewById(R.id.estado);
            idpedido = (TextView) v.findViewById(R.id.idpedido);
            idpedidoLabel = (TextView) v.findViewById(R.id.idpedidoLabel);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public PedidosAdapter(List<Pedido> items) {
        this.items = items;
    }

    public List<Pedido> getItems(){
        return this.items;
    }

    @Override
    public PedidoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pedido_card, viewGroup, false);
        return new PedidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PedidoViewHolder viewHolder, final int i) {
        viewHolder.imagen.setImageResource(items.get(i).getImagen());
        //Picasso.with(viewHolder.imagen.getContext())
               // .load(items.get(i).getImagenurl()).into(viewHolder.imagen);

        viewHolder.cliente.setText(items.get(i).getCliente());
        viewHolder.direccion.setText("Dirección: " + items.get(i).getDireccion());
        viewHolder.distrito.setText("Distrito: " + String.valueOf(items.get(i).getDistrito()));
        viewHolder.estado.setText("Estado: " + String.valueOf(items.get(i).getNombreEstado()));
        viewHolder.idpedido.setText(String.valueOf(items.get(i).getIdPedido()));
        viewHolder.idpedidoLabel.setText("Pedido: " + String.valueOf(items.get(i).getIdPedido()));

        viewHolder.pedidoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("curImagen", items.get(i).getImagen());
                //bundle.putString("curImagen", items.get(i).getImagenurl());
                bundle.putString("curCliente", items.get(i).getCliente());
                bundle.putString("curDireccion", items.get(i).getDireccion());
                bundle.putString("curDistrito", items.get(i).getDistrito());
                bundle.putString("curEstado", items.get(i).getNombreEstado());
                bundle.putInt("curIdpedido", items.get(i).getIdPedido());
                bundle.putString("curLatitud", items.get(i).getLatitud());
                bundle.putString("curLongitud", items.get(i).getLongitud());
                bundle.putString("curIdpedidoLabel", items.get(i).getIdPedido()+"");

                Intent iconIntent = new Intent(view.getContext(), DetalleActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);
            }
        });
    }

}
