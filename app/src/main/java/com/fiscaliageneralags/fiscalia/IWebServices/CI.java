package com.fiscaliageneralags.fiscalia.IWebServices;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CI {
    @GET("api/APPMP/{carpetai}/{numc}")
    Call<String> getCarpetaI(@Path("carpetai") String carpeta, @Path("numc") int num);

    @GET("api/ObtenerAlertasAmber/{estatus}")
    Call<String> getAlertaAmber(@Path("estatus") int estatus);
}

