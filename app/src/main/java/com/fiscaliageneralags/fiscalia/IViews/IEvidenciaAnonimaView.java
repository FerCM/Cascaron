package com.fiscaliageneralags.fiscalia.IViews;

import com.fiscaliageneralags.fiscalia.Models.Municipios;


/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IEvidenciaAnonimaView {

    // Response From Services
    void onPostEvidenciaAnonimaSuccess();
    void onPostEvidenciaAnonimaError();
    void onGetMunicipiosSuccess(Municipios[] municipios);

    //General Errors
    void onConecctionError();

}
