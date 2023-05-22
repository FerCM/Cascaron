package com.fiscaliageneralags.fiscalia.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fiscaliageneralags.fiscalia.Models.QuejaItem;
import com.fiscaliageneralags.fiscalia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ERodriguezF on 03/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class QuejaDetalleMensajeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.quejas_detalle_text_funcionario) TextView funcionarioView;
    @BindView(R.id.quejas_detalle_text_asunto) TextView asuntoTextView;
    @BindView(R.id.quejas_detalle_text_mensaje) TextView mensajeTextView;
    @BindView(R.id.quejas_detalle_text_estado) TextView estadoTextView;
    @BindView(R.id.quejas_detalle_text_respuesta) TextView respuestaTextView;
    @BindView(R.id.quejas_detalle_label_respuesta) TextView respuestaLabelTextView;
    @BindView(R.id.quejas_detalle_frame_respuesta) FrameLayout respuestaFrameTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_detalle_quejas);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        QuejaItem queja = (QuejaItem) getIntent().getSerializableExtra("QuejaItem");
        fillMessageDetail(queja);
    }

    public void fillMessageDetail(QuejaItem queja){
        asuntoTextView.setText(queja.getAsunto());
        mensajeTextView.setText(queja.getMensaje());
        estadoTextView.setText(queja.getHolderEstado());
        respuestaTextView.setText(queja.getRespuesta());
        funcionarioView.setText(queja.getHolderFuncionario());
        estadoTextView.setTextColor(getResources().getColor(
                queja.getEstado() == QuejaItem.Estado.RESPONDIDA ?
                        R.color.quejas_respodida :
                        R.color.quejas_en_espera
        ));
        int visibilityState = queja.getEstado() == QuejaItem.Estado.RESPONDIDA ? View.VISIBLE : View.GONE;
        respuestaLabelTextView.setVisibility(visibilityState);
        respuestaTextView.setVisibility(visibilityState);
        respuestaFrameTextView.setVisibility(visibilityState);
    }

    @OnClick(R.id.toolbar_back_btn)
    public void backToolbarButton(){
        super.onBackPressed();
    }

    @OnClick(R.id.toolbar_delete_text_view)
    public void deleteToolbarButton(){ finish(); }

}
