package com.fiscaliageneralags.fiscalia.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.fiscaliageneralags.fiscalia.Models.QuejaItem;
import com.fiscaliageneralags.fiscalia.R;
import com.fiscaliageneralags.fiscalia.Utils.Interfaces.IQuejaOnClickListener;
import com.fiscaliageneralags.fiscalia.ViewHolders.QuejaViewHolder;

import java.util.List;

/**
 * Created by ERodriguezF on 03/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class QuejasRecyclerViewAdapter extends RecyclerView.Adapter<QuejaViewHolder> {

    private List<QuejaItem> items;
    private Context context;
    private IQuejaOnClickListener messageClickListener;

    public QuejasRecyclerViewAdapter(List<QuejaItem> items, IQuejaOnClickListener messageClickListener) {
        this.items = items;
        this.messageClickListener = messageClickListener;
    }

    @Override
    public QuejaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        QuejaViewHolder viewHolder =  new QuejaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_module_quejas,parent,false));
        context = parent.getContext();
        return viewHolder;
    }

    /**
     * Change holder information.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(QuejaViewHolder holder, int position) {
        final QuejaItem item = items.get(position);
        holder.getAsuntoTextView().setText(item.getAsunto());
        holder.getEstadoTextView().setText(item.getHolderEstado());
        holder.getFechaTextView().setText(item.getHolderFecha());
        holder.getEstadoTextView().setTextColor(
                context.getResources().getColor(
                        item.getEstado() == QuejaItem.Estado.RESPONDIDA ?
                                R.color.buz_fisc_respodida :
                                R.color.buz_fisc_en_espera
                )
        );
        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageClickListener.onClick(context,item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
