package com.fiscaliageneralags.fiscalia.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fiscaliageneralags.fiscalia.R;


/**
 * Created by ERodriguezF on 18/12/2017.
 * @author ERodriguezF
 * @version 1.3
 */

public class NumerosEmergenciaViewHolder extends RecyclerView.ViewHolder{

    /**
     * Root Card View instance of {@link R.layout#card_view_module_emergencia_numeros} layout.
     */
    private CardView cardView;

    /**
     * Icon Image View instance of {@link R.layout#card_view_module_emergencia_numeros} layout.
     */
    private ImageView iconImageView;

    /**
     * Description Text View instance of {@link R.layout#card_view_module_emergencia_numeros} layout.
     */
    private TextView descripcionTextView;

    /**
     * Phone Text View instance of {@link R.layout#card_view_module_emergencia_numeros} layout.
     */
    private TextView numeroEmergenciaTextView;

    /**
     * Create a View Holder For the Instance of {@link R.layout#card_view_module_emergencia_numeros}.
     * @param itemView The instance of {@link R.layout#card_view_module_emergencia_numeros} layout of the view holder.
     */
    public NumerosEmergenciaViewHolder(View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.numeros_emergencia_card_view);
        iconImageView = itemView.findViewById(R.id.numeros_emergencia_card_view_icon);
        descripcionTextView = itemView.findViewById(R.id.numeros_emergencia_card_view_descripcion);
        numeroEmergenciaTextView = itemView.findViewById(R.id.numeros_emergencia_card_view_numero_emergencia);
    }

    /**
     *
     * @return The Root {@link CardView} instance of the instance of {@link R.layout#card_view_module_emergencia_numeros} layout.
     */
    public CardView getCardView() {
        return cardView;
    }

    /**
     *
     * @return The Icon {@link ImageView} instance of the instance of {@link R.layout#card_view_module_emergencia_numeros} layout.
     */
    public ImageView getIconImageView() {
        return iconImageView;
    }

    /**
     *
     * @return The Description {@link TextView} instance of the instance of {@link R.layout#card_view_module_emergencia_numeros} layout.
     */
    public TextView getDescripcionTextView() {
        return descripcionTextView;
    }

    /**
     *
     * @return The Phone {@link TextView} instance of the instance of {@link R.layout#card_view_module_emergencia_numeros} layout.
     */
    public TextView getNumeroEmergenciaTextView() {
        return numeroEmergenciaTextView;
    }

}
