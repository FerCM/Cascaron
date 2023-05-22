package com.fiscaliageneralags.fiscalia.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fiscaliageneralags.fiscalia.Models.Municipios;


/**
 * Created by ERodriguezF on 22/12/2017.
 * @author ERodriguezF
 * @version 1.18
 */

public class SearchLocationArrayAdapter extends ArrayAdapter<Municipios> {

    private final int mResource;

    public SearchLocationArrayAdapter(@NonNull Context context, int resource, @NonNull Municipios[] objects) {
        super(context, resource, objects);
        mResource = resource;
    }

    /**
     * Inflate and customize the view to return.
     * @param position
     * @param convertView
     * @param parent
     * @return The Customized view
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(mResource,parent,false);
        TextView etiqueta = rowView.findViewById(android.R.id.text1);
        Municipios item = getItem(position);
        etiqueta.setText(item.toString());
        return rowView;
    }
}
