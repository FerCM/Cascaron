package com.fiscaliageneralags.fiscalia.IViews;

/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IBuzonFiscalMainModuleView {

    // Response From Services
    void onPostSugerenciaAlFiscalSuccess();
    void onPostSugerenciaAlFiscalError();

    //General Errors
    void onConecctionError();

}
