package com.fiscaliageneralags.fiscalia.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.fiscaliageneralags.fiscalia.Models.MensajeAlFiscalItem;
import com.fiscaliageneralags.fiscalia.R;
import com.fiscaliageneralags.fiscalia.Utils.Interfaces.IMensajeBuzonFiscalOnClickListener;
import com.fiscaliageneralags.fiscalia.ViewHolders.MensajeAlFiscalViewHolder;

import java.util.List;

/**
 * Created by ERodriguezF on 27/12/2017.
 * @author ERodriguezF
 * @version 1.18
 */

public class MensajeAlFiscalRecyclerViewAdapter extends RecyclerView.Adapter<MensajeAlFiscalViewHolder> {

    private List<MensajeAlFiscalItem> items;
    private Context context;
    private IMensajeBuzonFiscalOnClickListener messageClickListener;

    public MensajeAlFiscalRecyclerViewAdapter(List<MensajeAlFiscalItem> items, IMensajeBuzonFiscalOnClickListener messageClickListener) {
        this.items = items;
        this.messageClickListener = messageClickListener;
    }

    @Override
    public MensajeAlFiscalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MensajeAlFiscalViewHolder viewHolder =  new MensajeAlFiscalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_module_buzon_fiscal,parent,false));
        context = parent.getContext();
        return viewHolder;
    }

    /**
     * Change holder information.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MensajeAlFiscalViewHolder holder, int position) {
        final MensajeAlFiscalItem item = items.get(position);
        holder.getAsuntoTextView().setText(item.getAsunto());
        holder.getEstadoTextView().setText(item.getHolderEstado());
        holder.getFechaTextView().setText(item.getHolderFecha());
        holder.getTipoTextView().setText(item.getHolderTipo());
        holder.getTipoTextView().setTextColor(
                context.getResources().getColor(
                        item.getTipo() == MensajeAlFiscalItem.Tipo.QUEJA ?
                                R.color.buz_fisc_quejas :
                                R.color.buz_fisc_sugerencias
                )
        );
        holder.getEstadoTextView().setTextColor(
                context.getResources().getColor(
                        item.getEstado() == MensajeAlFiscalItem.Estado.RESPONDIDA ?
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
