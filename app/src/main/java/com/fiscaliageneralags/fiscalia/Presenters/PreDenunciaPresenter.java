package com.fiscaliageneralags.fiscalia.Presenters;


import com.fiscaliageneralags.fiscalia.IInteractors.IPreDenunciaInteractor;
import com.fiscaliageneralags.fiscalia.IPresenters.IPreDenunciaPresenter;
import com.fiscaliageneralags.fiscalia.IViews.IPreDenunciaView;
import com.fiscaliageneralags.fiscalia.Interactors.PreDenunciaInteractor;
import com.fiscaliageneralags.fiscalia.Models.PreDenuncia;

/**
 * Created by ERodriguezF on 23/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class PreDenunciaPresenter implements IPreDenunciaPresenter {

    private IPreDenunciaView preDenunciaView;
    private IPreDenunciaInteractor preDenunciaInteractor;

    public PreDenunciaPresenter(IPreDenunciaView preDenunciaView){
        this.preDenunciaView = preDenunciaView;
        this.preDenunciaInteractor =  new PreDenunciaInteractor(this);
    }

    @Override
    public void postPreDenuncia(PreDenuncia preDenuncia) {
        this.preDenunciaInteractor.postPreDenuncia(preDenuncia);
    }

    @Override
    public void onPreDenunciaSucesss(String folio) {
        this.preDenunciaView.onPreDenunciaSucesss(folio);
    }

    @Override
    public void onPreDenunciaError() {
        this.preDenunciaView.onPreDenunciaError();
    }

    @Override
    public void onConecctionError() {
        this.preDenunciaView.onConecctionError();
    }
}
