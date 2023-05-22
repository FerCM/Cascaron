package com.fiscaliageneralags.fiscalia.IPresenters;

public interface Presenter {
    // Services
    void getCarpetaI(String carpeta, int num);
    void getAlertaAmber(int estatus);
    // Response From Services
    void onCarpetaSucess(String carpeta);
    void onCarpetaError();

    void onAmberSucess(String amber);
    void onAmberError();
    //General Errors
    void onConecctionError();
}
