package com.fiscaliageneralags.fiscalia.IInteractors;

import android.location.Address;

import java.io.File;

/**
 * Created by ERodriguezF on 22/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IEvidenciaAnonimaInteractor {


    void getMunicipios();
    void postSendEvidenciaAnonima(File evidencia,
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
                                  String longitudCelular
    );

}
