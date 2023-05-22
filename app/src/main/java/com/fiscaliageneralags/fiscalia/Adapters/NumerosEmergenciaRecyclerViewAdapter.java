package com.fiscaliageneralags.fiscalia.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.fiscaliageneralags.fiscalia.Models.NumeroEmergenciaItem;
import com.fiscaliageneralags.fiscalia.R;
import com.fiscaliageneralags.fiscalia.Utils.Interfaces.INumeroEmergenciaItemOnClickListener;
import com.fiscaliageneralags.fiscalia.ViewHolders.NumerosEmergenciaViewHolder;

import java.util.List;


/**
 * Created by ERodriguezF on 18/12/2017.
 * @author ERodriguezF
 * @version 1.18
 */

public class NumerosEmergenciaRecyclerViewAdapter extends RecyclerView.Adapter<NumerosEmergenciaViewHolder>{

    private List<NumeroEmergenciaItem> items;
    private Context context;
    private INumeroEmergenciaItemOnClickListener listener;

    public NumerosEmergenciaRecyclerViewAdapter(List<NumeroEmergenciaItem> items, INumeroEmergenciaItemOnClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public NumerosEmergenciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NumerosEmergenciaViewHolder numerosEmergenciaViewHolder = new NumerosEmergenciaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_module_emergencia_numeros,parent,false));
        context = parent.getContext();
        return numerosEmergenciaViewHolder;
    }

    /**
     * Change holder information.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(NumerosEmergenciaViewHolder holder, int position) {
        final NumeroEmergenciaItem item =  items.get(position);
        holder.getIconImageView().setImageDrawable(item.getIcon());
        holder.getDescripcionTextView().setText(item.getDescripcion());
        holder.getNumeroEmergenciaTextView().setText(item.getNumeroEmergencia());
        switch ((position)%4){
            case 0:
                holder.getCardView().setCardBackgroundColor(context.getResources().getColor(R.color.num_emerg_card_background_1));
                break;
            case 1:
                holder.getCardView().setCardBackgroundColor(context.getResources().getColor(R.color.num_emerg_card_background_2));
                break;
            case 2:
                holder.getCardView().setCardBackgroundColor(context.getResources().getColor(R.color.num_emerg_card_background_3));
                break;
            case 3:
                holder.getCardView().setCardBackgroundColor(context.getResources().getColor(R.color.num_emerg_card_background_4));
                break;
            default:
                holder.getCardView().setCardBackgroundColor(context.getResources().getColor(R.color.num_emerg_card_background_1));
                break;
        }
        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(context,item);
            }
        });
    }



    @Override
    public int getItemCount() {
        return items.size();
    }
}
