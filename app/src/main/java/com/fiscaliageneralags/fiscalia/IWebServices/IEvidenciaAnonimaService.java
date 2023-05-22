package com.fiscaliageneralags.fiscalia.IWebServices;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IEvidenciaAnonimaService {

    @Multipart
    @POST("EvidenciaAnonima/upload")
    Call<Object> postEvidenciaAnonima(@Part MultipartBody.Part filePart,
                                      @Part("lat") String latitud,
                                      @Part("long") String lognitud,
                                      @Part("description") String desc,
                                      @Part("mpio") String municipio,
                                      @Part("calle") String calle,
                                      @Part("colonia") String colonia,
                                      @Part("numExt") String numExt,
                                      @Part("numInt") String numInt,

                                      //Not Required Fields
                                      @Part("correo") String correo,
                                      @Part("nombre") String nombre,
                                      @Part("mpioForaneo") String municipioForaneo,
                                      @Part("estadoForaneo") String estado,

                                      @Part("cel_env") String celular,
                                      @Part("lat_cel") String latitudCelular,
                                      @Part("long_cel") String longitudCelular
    );


}
