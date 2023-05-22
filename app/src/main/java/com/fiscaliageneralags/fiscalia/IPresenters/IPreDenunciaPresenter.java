package com.fiscaliageneralags.fiscalia.IPresenters;

import com.fiscaliageneralags.fiscalia.Models.PreDenuncia;

/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IPreDenunciaPresenter {
    // Services
    void postPreDenuncia(PreDenuncia preDenuncia);

    // Response From Services
    void onPreDenunciaSucesss(String folio);
    void onPreDenunciaError();

    //General Errors
    void onConecctionError();
}
