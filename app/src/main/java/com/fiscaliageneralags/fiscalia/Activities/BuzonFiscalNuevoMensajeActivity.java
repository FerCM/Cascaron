package com.fiscaliageneralags.fiscalia.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fiscaliageneralags.fiscalia.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ERodriguezF on 27/12/2017.
 * @author ERodriguezF
 * @version 1.18
 */

public class BuzonFiscalNuevoMensajeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.buzon_fiscal_tipo_better_spinner)
    MaterialBetterSpinner tipoSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_nuevo_fiscal_buzon);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, new String[]{"Queja","Sugerencia"});
        tipoSpinner.setAdapter(adapter);
    }

    @OnClick(R.id.toolbar_back_btn)
    public void backToolbarButton(){
        super.onBackPressed();
    }
}
