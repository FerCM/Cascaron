package com.fiscaliageneralags.fiscalia.Filters;

import android.widget.Filter;

import com.fiscaliageneralags.fiscalia.Adapters.FuncionarioArrayAdapter;
import com.fiscaliageneralags.fiscalia.Models.Funcionario;

import java.util.ArrayList;
import java.util.List;


public class QuejaFilter extends Filter {

    private Funcionario[] items;
    private FuncionarioArrayAdapter adapter;

    public QuejaFilter(Funcionario[] items, FuncionarioArrayAdapter adapter) {
        this.items = items;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        if(constraint != null) {
            ArrayList<Funcionario> sugerencias = new ArrayList<>();
            for (Funcionario item : items) {
                if(item.getNombreCompleto().toLowerCase().contains(constraint.toString().toLowerCase()))
                    sugerencias.add(item);
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = sugerencias;
            filterResults.count =  sugerencias.size();
            return filterResults;
        } else {
            return new FilterResults();
        }
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if( results != null && results.count > 0 ){
            adapter.setItems( ( (List<Funcionario>)results.values).toArray(new Funcionario[0]) );
            adapter.notifyDataSetChanged();
        }
    }
}
