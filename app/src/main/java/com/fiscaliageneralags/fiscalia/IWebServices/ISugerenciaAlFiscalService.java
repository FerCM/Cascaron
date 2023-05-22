package com.fiscaliageneralags.fiscalia.IWebServices;

import com.fiscaliageneralags.fiscalia.Models.SugerenciaAlFiscal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface ISugerenciaAlFiscalService {

    @POST("SugerenciaAlFiscal/Sugerencia")
    Call<Object> PostSugerencia(@Body SugerenciaAlFiscal sugerencia);

}
