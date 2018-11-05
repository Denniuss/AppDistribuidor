package com.newmantech.appdistribuidor;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DetallePedidoAdapter extends BaseAdapter {

private Context context;
private int layout;
private List<String> names;

public DetallePedidoAdapter(Context context, int layout, List<String> names)
{
    this.context = context;
    this.layout = layout;
    this.names = names;

}

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //View Holder Pattern
        ViewHolder holder;

        if(convertView==null)
        {
            //inflamos la vista desde nuestro layout personalizado
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.list_pedido_item,null);

            holder = new ViewHolder();
            //Referenciamos el elemento a modificar y lo rellenamos
            holder.nameTextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        // nos traemos el valor actual dependiente de la posicion
        String currentName = names.get(position);
        //currentName = (String)getItem(position);
        //Referenciamos el elemento a modificar y lo rellenamos

        holder.nameTextView.setText(currentName);
        //devolvemos la vista inflada y modificada con nuestros datos
        return convertView;
    }

    static class ViewHolder{
    private TextView nameTextView;

    }
}
