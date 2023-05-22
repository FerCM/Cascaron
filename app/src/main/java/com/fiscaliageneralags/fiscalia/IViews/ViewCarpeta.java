package com.fiscaliageneralags.fiscalia.IViews;

public interface ViewCarpeta {

    void onCarpetaSucess(String carpeta);
    void onCarpetaError();

    void onAmberSucess(String amber);
    void onAmberError();
    //General Errors
    void onConecctionError();
}
