package com.fiscaliageneralags.fiscalia.IWebServices;

import com.fiscaliageneralags.fiscalia.Models.PreDenuncia;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IPreDenunciaService {
    @POST("PreDenuncia")
    Call<String> postNewPreDenuncia(@Body PreDenuncia newPreDenuncia);
}
