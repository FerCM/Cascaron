package com.fiscaliageneralags.fiscalia.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fiscaliageneralags.fiscalia.R;


/**
 * Created by ERodriguezF on 27/12/2017.
 * @author ERodriguezF
 * @version 1.3
 */

public class MensajeAlFiscalViewHolder extends RecyclerView.ViewHolder {

    /**
     * Type Text View instance of {@link R.layout#card_view_module_buzon_fiscal} layout.
     */
    private TextView tipoTextView;

    /**
     * Affair Text View instance of {@link R.layout#card_view_module_buzon_fiscal} layout.
     */
    private TextView asuntoTextView;

    /**
     * Date Text View instance of {@link R.layout#card_view_module_buzon_fiscal} layout.
     */
    private TextView fechaTextView;

    /**
     * State Text View instance of {@link R.layout#card_view_module_buzon_fiscal} layout.
     */
    private TextView estadoTextView;

    /**
     * Root Card View instance of {@link R.layout#card_view_module_buzon_fiscal} layout.
     */
    private CardView cardView;

    /**
     * Create a View Holder For the Instance of {@link R.layout#card_view_module_buzon_fiscal}.
     * @param itemView The instance of {@link R.layout#card_view_module_buzon_fiscal} layout of the view holder.
     */
    public MensajeAlFiscalViewHolder(View itemView) {
        super(itemView);

        tipoTextView = itemView.findViewById(R.id.lista_negra_card_view_text_activo);
        asuntoTextView = itemView.findViewById(R.id.buzon_fiscal_card_view_text_asunto);
        fechaTextView = itemView.findViewById(R.id.buzon_fiscal_card_view_text_fecha);
        estadoTextView = itemView.findViewById(R.id.buzon_fiscal_card_view_text_estado);
        cardView = itemView.findViewById(R.id.buzon_fiscal_card_view);
    }

    /**
     *
     * @return The Type {@link TextView} instance of the instance of {@link R.layout#card_view_module_buzon_fiscal} layout.
     */
    public TextView getTipoTextView() {
        return tipoTextView;
    }

    /**
     *
     * @return The Affair {@link TextView} instance of the instance of {@link R.layout#card_view_module_buzon_fiscal} layout.
     */
    public TextView getAsuntoTextView() {
        return asuntoTextView;
    }

    /**
     *
     * @return The Date {@link TextView} instance of the instance of {@link R.layout#card_view_module_buzon_fiscal} layout.
     */
    public TextView getFechaTextView() {
        return fechaTextView;
    }

    /**
     *
     * @return The State {@link TextView} instance of the instance of {@link R.layout#card_view_module_buzon_fiscal} layout.
     */
    public TextView getEstadoTextView() {
        return estadoTextView;
    }

    /**
     *
     * @return The Root {@link CardView} instance of the instance of {@link R.layout#card_view_module_buzon_fiscal} layout.
     */
    public CardView getCardView() { return cardView; }

}
