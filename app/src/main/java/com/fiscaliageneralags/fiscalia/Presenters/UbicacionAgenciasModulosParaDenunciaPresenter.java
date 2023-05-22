package com.fiscaliageneralags.fiscalia.Presenters;

import android.content.Context;

import com.fiscaliageneralags.fiscalia.IInteractors.IUbicacionAgenciasModulosParaDenunciaInteractor;
import com.fiscaliageneralags.fiscalia.IPresenters.IUbicacionAgenciasModulosParaDenunciaPresenter;
import com.fiscaliageneralags.fiscalia.IViews.IUbicacionAgenciasModulosParaDenunciaView;
import com.fiscaliageneralags.fiscalia.Interactors.UbicacionAgenciasModulosParaDenunciaInteractor;
import com.fiscaliageneralags.fiscalia.Models.EdificiosParaDenuncia;
import com.fiscaliageneralags.fiscalia.Models.Municipios;


/**
 * Created by ERodriguezF on 05/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class UbicacionAgenciasModulosParaDenunciaPresenter implements IUbicacionAgenciasModulosParaDenunciaPresenter {

    private IUbicacionAgenciasModulosParaDenunciaInteractor interactor;
    private IUbicacionAgenciasModulosParaDenunciaView view;

    public UbicacionAgenciasModulosParaDenunciaPresenter(Context context, IUbicacionAgenciasModulosParaDenunciaView view){
        interactor =  new UbicacionAgenciasModulosParaDenunciaInteractor(context,this);
        this.view =  view;
    }

    @Override
    public void getEdificiosParaDenunciar() {
        interactor.getEdificiosParaDenunciar();
    }
    @Override
    public void getTodasLasAgencias() {
        interactor.getTodasLasAgencias();
    }
    @Override
    public void getMunicipios() {
        interactor.getMunicipios();
    }

    @Override
    public void onGetEdificiosParaDenunciar(EdificiosParaDenuncia[] edificiosParaDenuncias) {
        view.onGetEdificiosParaDenunciar(edificiosParaDenuncias);
    }

    @Override
    public void onGetMunicipiosSuccess(Municipios[] municipios) {
        view.onGetMunicipiosSuccess(municipios);
    }
    @Override
    public void onGetTodasLasAgenciasSuccess(EdificiosParaDenuncia[] edificiosParaDenuncias) {
        view.onGetTodasLasAgenciasSuccess(edificiosParaDenuncias);
    }


    @Override
    public void onConecctionError() {
        view.onConecctionError();
    }
}
