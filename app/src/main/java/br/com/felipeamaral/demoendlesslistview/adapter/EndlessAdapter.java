package br.com.felipeamaral.demoendlesslistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.felipeamaral.demoendlesslistview.R;

public class EndlessAdapter extends ArrayAdapter<String> {

    private List<String> lista;
    private Context context;
    private int layoutId;

    public EndlessAdapter(Context context, List<String> lista, int layoutId) {
        super(context, layoutId, lista);
        this.context = context;
        this.lista = lista;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public String getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  inflater.inflate(layoutId, parent, false);
        }
        TextView tvTexto = (TextView) convertView.findViewById(R.id.tvTexto);
        tvTexto.setText(lista.get(position));

        return convertView;
    }

}
