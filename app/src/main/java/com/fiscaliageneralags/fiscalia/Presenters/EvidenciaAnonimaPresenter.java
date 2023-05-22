package com.fiscaliageneralags.fiscalia.Presenters;

import android.content.Context;
import android.location.Address;

import com.fiscaliageneralags.fiscalia.IInteractors.IEvidenciaAnonimaInteractor;
import com.fiscaliageneralags.fiscalia.IPresenters.IEvidenciaAnonimaPresenter;
import com.fiscaliageneralags.fiscalia.IViews.IEvidenciaAnonimaView;
import com.fiscaliageneralags.fiscalia.Interactors.EvidenciaAnonimaInteractor;
import com.fiscaliageneralags.fiscalia.Models.Municipios;

import java.io.File;


/**
 * Created by ERodriguezF on 03/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class EvidenciaAnonimaPresenter implements IEvidenciaAnonimaPresenter {

    private IEvidenciaAnonimaInteractor interactor;
    private IEvidenciaAnonimaView view;

    public EvidenciaAnonimaPresenter(Context context, IEvidenciaAnonimaView view){
        interactor =  new EvidenciaAnonimaInteractor(context,this);
        this.view =  view;
    }

    @Override
    public void getMunicipios() {
        interactor.getMunicipios();
    }

    @Override
    public void postEvidenciaAnonima(File evidencia,
                                     String correo,
                                     String nombre,
                                     Address localizacion,
                                     String descripcionHechos,
                                     String calle,
                                     String colonia,
                                     String numInt,
                                     String numExt,
                                     int municipio,
                                     String municipioForaneo,
                                     String estado,
                                     String celular,
                                     String latitudCelular,
                                     String longitudCelular) {
        interactor.postSendEvidenciaAnonima(
                evidencia,
                correo,
                nombre,
                localizacion,
                descripcionHechos,
                calle,
                colonia,
                numInt,
                numExt,
                municipio,
                municipioForaneo,
                estado,
                celular,
                latitudCelular,
                longitudCelular
        );
    }

    @Override
    public void onPostEvidenciaAnonimaSuccess() {
        view.onPostEvidenciaAnonimaSuccess();
    }

    @Override
    public void onPostEvidenciaAnonimaError() {
        view.onPostEvidenciaAnonimaError();
    }

    @Override
    public void onGetMunicipiosSuccess(Municipios[] municipios) {
        view.onGetMunicipiosSuccess(municipios);
    }

    @Override
    public void onConecctionError() {
        view.onConecctionError();
    }
}
