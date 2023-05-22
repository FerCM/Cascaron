package com.fiscaliageneralags.fiscalia.IViews;

/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IPreDenunciaView {

    // Response From Services
    void onPreDenunciaSucesss(String folio);
    void onPreDenunciaError();

    //General Errors
    void onConecctionError();

}
