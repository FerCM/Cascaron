package com.fiscaliageneralags.fiscalia.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fiscaliageneralags.fiscalia.Models.MensajeAlFiscalItem;
import com.fiscaliageneralags.fiscalia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by ERodriguezF on 28/12/2017.
 * @author ERodriguezF
 * @version 1.18
 */

public class BuzonFiscalDetalleMensajeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.buzon_fiscal_detalle_text_titulo) TextView tituloTextView;
    @BindView(R.id.buzon_fiscal_detalle_text_asunto) TextView asuntoTextView;
    @BindView(R.id.buzon_fiscal_detalle_text_mensaje) TextView mensajeTextView;
    @BindView(R.id.buzon_fiscal_detalle_text_estado) TextView estadoTextView;
    @BindView(R.id.buzon_fiscal_detalle_text_respuesta) TextView respuestaTextView;
    @BindView(R.id.buzon_fiscal_detalle_label_respuesta) TextView respuestaLabelTextView;
    @BindView(R.id.buzon_fiscal_detalle_frame_respuesta) FrameLayout respuestaFrameTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_detalle_fiscal_buzon);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        MensajeAlFiscalItem mensaje = (MensajeAlFiscalItem) getIntent().getSerializableExtra("MensajeAlFiscalItem");
        fillMessageDetail(mensaje);
    }

    public void fillMessageDetail(MensajeAlFiscalItem mensaje){
        tituloTextView.setText(mensaje.getHolderTipo());
        asuntoTextView.setText(mensaje.getAsunto());
        mensajeTextView.setText(mensaje.getMensaje());
        estadoTextView.setText(mensaje.getHolderEstado());
        respuestaTextView.setText(mensaje.getRespuesta());
        tituloTextView.setTextColor(getResources().getColor(
                mensaje.getTipo() == MensajeAlFiscalItem.Tipo.QUEJA ?
                        R.color.buz_fisc_quejas :
                        R.color.buz_fisc_sugerencias
        ));
        estadoTextView.setTextColor(getResources().getColor(
                mensaje.getEstado() == MensajeAlFiscalItem.Estado.RESPONDIDA ?
                        R.color.buz_fisc_respodida :
                        R.color.buz_fisc_en_espera
        ));
        int visibilityState = mensaje.getEstado() == MensajeAlFiscalItem.Estado.RESPONDIDA ? View.VISIBLE : View.GONE;
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
