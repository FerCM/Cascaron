package com.fiscaliageneralags.fiscalia.IPresenters;

import android.location.Address;

import com.fiscaliageneralags.fiscalia.Models.Municipios;

import java.io.File;

/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IEvidenciaAnonimaPresenter {

    // Services
    void getMunicipios();

    void postEvidenciaAnonima(File evidencia,
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
                              String longitudCelular);


    // Response From Services
    void onPostEvidenciaAnonimaSuccess();
    void onPostEvidenciaAnonimaError();
    void onGetMunicipiosSuccess(Municipios[] municipios);


    //General Errors
    void onConecctionError();


}
