package com.fiscaliageneralags.fiscalia.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fiscaliageneralags.fiscalia.EvidenciaAnonimaMainModule;
import com.fiscaliageneralags.fiscalia.IPresenters.IBuzonFiscalMainModulePresenter;
import com.fiscaliageneralags.fiscalia.IViews.IBuzonFiscalMainModuleView;
import com.fiscaliageneralags.fiscalia.MainActivity;
import com.fiscaliageneralags.fiscalia.Models.SugerenciaAlFiscal;
import com.fiscaliageneralags.fiscalia.Presenters.BuzonFiscalMainModulePresenter;
import com.fiscaliageneralags.fiscalia.R;
import com.fiscaliageneralags.fiscalia.Utils.AppConstants;
import com.fiscaliageneralags.fiscalia.Utils.UCUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BuzonFiscalMain extends AppCompatActivity implements IBuzonFiscalMainModuleView {
    @BindView(R.id.buzon_fiscal_edt_asunto)
    MaterialEditText asuntoEditText;
    @BindView(R.id.buzon_fiscal_edt_correo) MaterialEditText correoEditText;
    @BindView(R.id.buzon_fiscal_edt_mensaje) MaterialEditText mensajeEditText;
    @BindView(R.id.buzon_fiscal_edt_nombre) MaterialEditText nombreEditText;
    Context context;
    private Unbinder unbinder;

    private IBuzonFiscalMainModulePresenter presenter;

    private MaterialDialog processDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzon_fiscal_main);
        unbinder = ButterKnife.bind(this);
        addEditTextValidators();
        context = this;
        processDialog = new MaterialDialog.Builder(this)
                .content("Enviando Mensaje ...")
                .progress(true, 0)
                .build();
        processDialog.setCanceledOnTouchOutside(false);
        presenter = new BuzonFiscalMainModulePresenter(this,this);
        SharedPreferences sp = this.getSharedPreferences(AppConstants.AppPersistantKey, 0);
        int userId = sp.getInt(AppConstants.UCUserId,-1);
        if( userId > 0) {
            UCUtil ucUtil = new UCUtil(this);
            correoEditText.setText(ucUtil.getUCEmail());
        }
    }
    public void addEditTextValidators(){
        nombreEditText.addValidator(new METValidator("Este campo es requerido.") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return text.length()>0;
            }
        });
        correoEditText.addValidator(new METValidator("Ingrese un correo electrónico valido.") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return Patterns.EMAIL_ADDRESS.matcher(text).matches();
            }
        });
        asuntoEditText.addValidator(new METValidator("Este campo es requerido.") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return text.length() > 0;
            }
        });
        mensajeEditText.addValidator(new METValidator("Este campo es requerido.") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return text.length() > 0;
            }
        });
    }

    private SugerenciaAlFiscal getSugerenciaAlFiscal(){
        SugerenciaAlFiscal sugerencia = new SugerenciaAlFiscal();
        sugerencia.asunto = asuntoEditText.getText().toString();
        sugerencia.correo = correoEditText.getText().toString();
        sugerencia.nombre = nombreEditText.getText().toString();
        sugerencia.sugerencia = mensajeEditText.getText().toString();
        return sugerencia;
    }

    @OnClick(R.id.buzon_fiscal_enviar)
    public void onSugerenciaAlFiscal(){
        if( !(nombreEditText.validate() && correoEditText.validate() &&
                asuntoEditText.validate() && mensajeEditText.validate()) ){
            Toast.makeText(this,"Por favor llene todos los campos.",Toast.LENGTH_LONG).show();
        }
        else{
            System.out.println("K pedo? " + getSugerenciaAlFiscal().nombre + getSugerenciaAlFiscal().correo + getSugerenciaAlFiscal().asunto + getSugerenciaAlFiscal().sugerencia);
            presenter.postSugerenciaAlFiscal(getSugerenciaAlFiscal());
            processDialog.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onPostSugerenciaAlFiscalSuccess() {
        processDialog.dismiss();
        Toast.makeText(this,"Tu mensaje al Fiscal fue enviado exitosamente.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(BuzonFiscalMain.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPostSugerenciaAlFiscalError() {
        processDialog.dismiss();
        Toast.makeText(this,"Lo sentimos ha ocurrido un error, por favor inténtelo nuevamente.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConecctionError() {
        processDialog.dismiss();
        Toast.makeText(this,"Lo sentimos ha ocurrido un error, por favor inténtelo nuevamente.",Toast.LENGTH_LONG).show();
    }
    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
        System.out.println("Prrakola");
        super.onBackPressed();
    }
}
