package com.fiscaliageneralags.fiscalia.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fiscaliageneralags.fiscalia.Filters.QuejaFilter;
import com.fiscaliageneralags.fiscalia.Models.Funcionario;


/**
 * Created by ERodriguezF on 03/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class FuncionarioArrayAdapter extends ArrayAdapter<Funcionario> {

    private final int mResource;
    private final QuejaFilter filter;
    private Funcionario[] items;

    public FuncionarioArrayAdapter(@NonNull Context context, int resource, @NonNull Funcionario[] objects) {
        super(context, resource, objects);
        mResource = resource;
        filter =  new QuejaFilter(objects,this);
        items =  objects;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    public void setItems(Funcionario[] items) {
        this.items = items;
    }

    @Override
    public Funcionario getItem(int position) {
        return items[position];
    }

    /**
     * Inflate the Costom view and fill it with the specific item.
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(mResource,parent,false);
        TextView etiqueta = rowView.findViewById(android.R.id.text1);
        Funcionario item = getItem(position);
        etiqueta.setText(item.getNombreCompleto());
        return rowView;
    }

    @NonNull
    @Override
    public QuejaFilter getFilter() {
        return filter;
    }
}
