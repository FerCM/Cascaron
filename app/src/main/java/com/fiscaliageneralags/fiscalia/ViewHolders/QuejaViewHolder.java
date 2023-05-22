package com.fiscaliageneralags.fiscalia.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fiscaliageneralags.fiscalia.R;


/**
 * Created by ERodriguezF on 03/01/2018.
 * @author ERodriguezF
 * @version 1.3
 */

public class QuejaViewHolder extends RecyclerView.ViewHolder {

    /**
     * Affair Text View instance of {@link R.layout#card_view_module_quejas} layout.
     */
    private TextView asuntoTextView;

    /**
     * Date Text View instance of {@link R.layout#card_view_module_quejas} layout.
     */
    private TextView fechaTextView;

    /**
     * State Text View instance of {@link R.layout#card_view_module_quejas} layout.
     */
    private TextView estadoTextView;

    /**
     * Root Text View instance of {@link R.layout#card_view_module_quejas} layout.
     */
    private CardView cardView;

    /**
     * Create a View Holder For the Instance of {@link R.layout#card_view_module_quejas}.
     * @param itemView The instance of {@link R.layout#card_view_module_quejas} layout of the view holder.
     */
    public QuejaViewHolder(View itemView) {
        super(itemView);

        asuntoTextView = itemView.findViewById(R.id.quejas_card_view_text_asunto);
        fechaTextView = itemView.findViewById(R.id.quejas_card_view_text_fecha);
        estadoTextView = itemView.findViewById(R.id.quejas_card_view_text_estado);
        cardView = itemView.findViewById(R.id.quejas_card_view);
    }

    /**
     *
     * @return The Affair {@link TextView} instance of the instance of {@link R.layout#card_view_module_quejas} layout.
     */
    public TextView getAsuntoTextView() {
        return asuntoTextView;
    }

    /**
     *
     * @return The Date {@link TextView} instance of the instance of {@link R.layout#card_view_module_quejas} layout.
     */
    public TextView getFechaTextView() {
        return fechaTextView;
    }

    /**
     *
     * @return The State {@link TextView} instance of the instance of {@link R.layout#card_view_module_quejas} layout.
     */
    public TextView getEstadoTextView() {
        return estadoTextView;
    }

    /**
     *
     * @return The Root {@link CardView} instance of the instance of {@link R.layout#card_view_module_quejas} layout.
     */
    public CardView getCardView() { return cardView; }

}
