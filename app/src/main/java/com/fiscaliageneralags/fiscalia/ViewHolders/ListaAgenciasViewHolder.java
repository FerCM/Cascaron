package com.fiscaliageneralags.fiscalia.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fiscaliageneralags.fiscalia.R;


public class ListaAgenciasViewHolder extends RecyclerView.ViewHolder {

    /**
     * References of the instances of the Layout to modify.
     */
    private CardView cardView;
    private TextView agenciaTextView;
    private TextView telefonoTextView;
    private TextView direTextView;
    private TextView horarioTextView;


    public ListaAgenciasViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.lista_negra_card_view);
            agenciaTextView = itemView.findViewById(R.id.lista_negra_card_view_text_agencia);
            telefonoTextView = itemView.findViewById(R.id.lista_negra_card_view_text_telefono);
            direTextView = itemView.findViewById(R.id.lista_negra_card_view_text_direccion);
            horarioTextView= itemView.findViewById(R.id.lista_negra_card_view_text_horario);

            }
    public CardView getCardView() {
            return cardView;
            }

    public TextView getAgenciaTextView() {
            return agenciaTextView;
            }
    public TextView getDireccionTextView() {
        return direTextView;
    }
    public TextView getTelefonoTextView() {
            return telefonoTextView;
            }
    public TextView getHorarioTextView() {
        return horarioTextView;
    }
    }
