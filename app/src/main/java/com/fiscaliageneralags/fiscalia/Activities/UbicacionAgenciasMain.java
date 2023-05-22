package com.fiscaliageneralags.fiscalia.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.fiscaliageneralags.fiscalia.FiscApplication.FiscApp;
import com.fiscaliageneralags.fiscalia.IPresenters.IUbicacionAgenciasModulosParaDenunciaPresenter;
import com.fiscaliageneralags.fiscalia.IViews.IUbicacionAgenciasModulosParaDenunciaView;
import com.fiscaliageneralags.fiscalia.Models.AuxiliarEdificios;
import com.fiscaliageneralags.fiscalia.Models.EdificiosParaDenuncia;
import com.fiscaliageneralags.fiscalia.Models.Municipios;
import com.fiscaliageneralags.fiscalia.Presenters.UbicacionAgenciasModulosParaDenunciaPresenter;
import com.fiscaliageneralags.fiscalia.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.objectbox.Box;

public class UbicacionAgenciasMain extends AppCompatActivity implements IUbicacionAgenciasModulosParaDenunciaView {
    private Unbinder unbinder;
    private IUbicacionAgenciasModulosParaDenunciaPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_agencias_main);
        unbinder = ButterKnife.bind(this);
        presenter =  new UbicacionAgenciasModulosParaDenunciaPresenter(this,this);
        presenter.getTodasLasAgencias();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.ubicacion_agencias_main_btn_en_estado)
    public void startEnElEstado(){
        Intent intent = new Intent(this, UbicacionAgenciasModulosParaDenunciaActivity.class);
        intent.putExtra("map", 1);
        startActivity(intent);
    }

    @OnClick(R.id.ubicacion_agencias_main_btn_modulo_denuncias)
    public void startModulosParaDenunciar(){
        Intent intent = new Intent(this, UbicacionAgenciasModulosParaDenunciaActivity.class);
        intent.putExtra("map", 2);
        startActivity(intent);
    }
    @OnClick(R.id.menu_agencias)
    public void startAgenciasList(){
        Intent intent = new Intent(this, UbicacionAgenciasLista.class);
        startActivity(intent);
    }

    @Override
    public void onGetEdificiosParaDenunciar(EdificiosParaDenuncia[] edificiosParaDenuncias) {
        Box<EdificiosParaDenuncia> numeroListaAgenciasBox = ((FiscApp) this.getApplication()).getBoxStore().boxFor(EdificiosParaDenuncia.class);
        for (EdificiosParaDenuncia edificioParaDenuncia : edificiosParaDenuncias) {
            numeroListaAgenciasBox.put(edificioParaDenuncia);
        }
    }

    @Override
    public void onGetMunicipiosSuccess(Municipios[] municipios) {

    }

    @Override
    public void onGetTodasLasAgenciasSuccess(EdificiosParaDenuncia[] edificiosParaDenuncias) {
        System.out.println("Por que vergas no entro?");
        Box<AuxiliarEdificios> numeroListaAgenciasBox = ((FiscApp) this.getApplication()).getBoxStore().boxFor(AuxiliarEdificios.class);
        numeroListaAgenciasBox.removeAll();
        for (EdificiosParaDenuncia edificioParaDenuncia : edificiosParaDenuncias) {
            System.out.println("Edificio? " + edificioParaDenuncia);
            AuxiliarEdificios aux = new AuxiliarEdificios(
                    edificioParaDenuncia.Telefono,
                    edificioParaDenuncia.Edificio,
                    edificioParaDenuncia.Horario_Denuncia,
                    edificioParaDenuncia.Cve_Mun,
                    edificioParaDenuncia.Domicilio);
            System.out.println("Insertar? " + aux);
            numeroListaAgenciasBox.put(aux);
        }
        System.out.println("Ya sali? " + numeroListaAgenciasBox);
    }

    @Override
    public void onConecctionError() {
        System.out.println("Ubo error?");
    }
    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
          super.onBackPressed();
    }
}
