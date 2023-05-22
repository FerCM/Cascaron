package com.fiscaliageneralags.fiscalia.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.fiscaliageneralags.fiscalia.Models.AuxiliarEdificios;
import com.fiscaliageneralags.fiscalia.R;
import com.fiscaliageneralags.fiscalia.ViewHolders.ListaAgenciasViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ListaAgenciasViewAdapter extends RecyclerView.Adapter<ListaAgenciasViewHolder> {

    private ArrayList<AuxiliarEdificios> items =  new ArrayList<>();
    private Context context;

    public ListaAgenciasViewAdapter(List<AuxiliarEdificios> items, Context context) {
        System.out.println("Aki?");
        this.setItems(items);
        this.context = context;
     }

    @Override
    public ListaAgenciasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListaAgenciasViewHolder viewHolder = new ListaAgenciasViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_module_agencias_lista,parent,false));
        return viewHolder;
    }
    /**
     * Changes the view holder information with the items information for the given position.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ListaAgenciasViewHolder holder, int position) {
        final AuxiliarEdificios item = getItems().get(position);
         String phone;
         holder.getAgenciaTextView().setText(item.Edificio);
         holder.getDireccionTextView().setText(item.Domicilio);
         holder.getHorarioTextView().setText(item.Horario_Denuncia);

    }

    @Override
    public int getItemCount() { return getItems().size(); }


    public List<AuxiliarEdificios> getItems() {
        return items;
    }

    public void setItems(List<AuxiliarEdificios> items) {
        System.out.println("Items?" + items.isEmpty());
        this.items.clear();
        if(items != null && !items.isEmpty()){
            System.out.println("Item? " + items);
            this.items.addAll(items);
        }
    }
}
