package com.fiscaliageneralags.fiscalia.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fiscaliageneralags.fiscalia.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UbicacionAgenciasLista extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_agencias_lista);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
        super.onBackPressed();
    }
}


