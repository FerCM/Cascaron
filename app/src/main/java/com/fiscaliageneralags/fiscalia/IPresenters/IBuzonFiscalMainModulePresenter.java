package com.fiscaliageneralags.fiscalia.IPresenters;

import com.fiscaliageneralags.fiscalia.Models.SugerenciaAlFiscal;


/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IBuzonFiscalMainModulePresenter {

    // Services
    void postSugerenciaAlFiscal(SugerenciaAlFiscal sugerenciaAlFiscal);


    // Response From Services
    void onPostSugerenciaAlFiscalSuccess();
    void onPostSugerenciaAlFiscalError();


    //General Errors
    void onConecctionError();

}
