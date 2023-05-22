package com.fiscaliageneralags.fiscalia.Presenters;

import com.fiscaliageneralags.fiscalia.IInteractors.Interactor;
import com.fiscaliageneralags.fiscalia.IPresenters.Presenter;
import com.fiscaliageneralags.fiscalia.IViews.ViewCarpeta;
import com.fiscaliageneralags.fiscalia.Interactors.CarpetaServicio;

public class CarpetaPresenter implements Presenter {

    private ViewCarpeta carpetaView;
    private Interactor carpeta;

    public CarpetaPresenter(ViewCarpeta carpetaView){
        this.carpetaView = carpetaView;
        this.carpeta =  new CarpetaServicio(this);
    }

    @Override
    public void getCarpetaI(String carpeta, int num) {
        this.carpeta.getCarpetaI(carpeta, num);
    }
    @Override
    public void onCarpetaSucess(String carpeta) {
        this.carpetaView.onCarpetaSucess(carpeta);
    }

    @Override
    public void onCarpetaError() {
        this.carpetaView.onCarpetaError();
    }

    @Override
    public void getAlertaAmber(int estatus) {
        this.carpeta.getAlertaAmber(estatus);
    }
   @Override
    public void onAmberSucess(String amber) {
        carpetaView.onAmberSucess(amber);
    }

    @Override
    public void onAmberError() {
        this.carpetaView.onCarpetaError();
    }

    @Override
    public void onConecctionError() {
        carpetaView.onConecctionError();
    }
}
