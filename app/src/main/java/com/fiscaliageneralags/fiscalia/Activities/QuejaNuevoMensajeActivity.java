package com.fiscaliageneralags.fiscalia.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fiscaliageneralags.fiscalia.Adapters.FuncionarioArrayAdapter;
import com.fiscaliageneralags.fiscalia.Models.Funcionario;
import com.fiscaliageneralags.fiscalia.R;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ERodriguezF on 03/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class QuejaNuevoMensajeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.quejas_funcionario_autocomplete)
    MaterialAutoCompleteTextView funcionariosAutoComplete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_nuevo_quejas);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        FuncionarioArrayAdapter adapter =  new FuncionarioArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line,getFuncionarios());
        funcionariosAutoComplete.setAdapter(adapter);
    }

    private Funcionario[] getFuncionarios(){
        ArrayList<Funcionario> items = new ArrayList<>();
        items.add(new Funcionario("Eduardo","González","Rios"));
        items.add(new Funcionario("Juan","Rodríguez","Figueroa"));
        items.add(new Funcionario("Paco","Alonso","de Leon"));
        items.add(new Funcionario("Pedro","Tinoco","Figueroa"));
        items.add(new Funcionario("Rodrigo","Ariza","Figueroa"));
        items.add(new Funcionario("Luis","Solis","Olvera"));
        items.add(new Funcionario("Vicente","Manjarrez","Valenzuela"));
        return items.toArray(new Funcionario[0]);
    }

    @OnClick(R.id.toolbar_back_btn)
    public void backToolbarButton(){
        super.onBackPressed();
    }
}
