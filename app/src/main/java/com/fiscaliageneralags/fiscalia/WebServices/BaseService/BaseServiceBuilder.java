package com.fiscaliageneralags.fiscalia.WebServices.BaseService;

import com.fiscaliageneralags.fiscalia.Interceptors.Base64DecodeInterceptor;
import com.fiscaliageneralags.fiscalia.Interceptors.BasicAuthInterceptor;
import com.fiscaliageneralags.fiscalia.Interceptors.ContentTypeInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by ERodriguezF on 12/01/2018.
 * @author ERodriguezF
 * @version 1.3
 */

public class BaseServiceBuilder {

    /**
     * Build a Retrofit with {@link BasicAuthInterceptor#BasicAuthInterceptor(String, String)} interceptor
     * and {@link ContentTypeInterceptor#ContentTypeInterceptor(String)} interceptor,
     * using JacksonConverterFactory and {@link ServiceConstantsConfig#BASE_PATH} as base URL.
     * @param email User email for Authorization Header.
     * @param password User email for Authorization Header.
     * @return Retrofit instance with the specified credentials for web api consumption.
     */
    public static Retrofit BuildWithCredentials(String email, String password){
        OkHttpClient cliente =  new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(email,password))
                .addInterceptor(new ContentTypeInterceptor(ServiceConstantsConfig.CONTENT_TYPE))
                .addInterceptor(new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(HttpLoggingInterceptor.Level.BASIC))
                .readTimeout(60*6, TimeUnit.SECONDS)
                .connectTimeout(60*6, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder().baseUrl(ServiceConstantsConfig.BASE_PATH).client(cliente).addConverterFactory(JacksonConverterFactory.create()).build();
    }

    /**
     * Build a Retrofit with {@link ContentTypeInterceptor#ContentTypeInterceptor(String)} interceptor,
     * using JacksonConverterFactory and {@link ServiceConstantsConfig#BASE_PATH} as base URL.
     * @return Retorfit instance for web api consumption.
     */
    public static Retrofit Build(){
        OkHttpClient cliente =  new OkHttpClient.Builder()
                .addInterceptor(new ContentTypeInterceptor(ServiceConstantsConfig.CONTENT_TYPE))
                .addInterceptor(new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(HttpLoggingInterceptor.Level.BASIC))
                .readTimeout(60*6, TimeUnit.SECONDS)
                .connectTimeout(60*6, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder().baseUrl(ServiceConstantsConfig.BASE_PATH).client(cliente).addConverterFactory(JacksonConverterFactory.create()).build();
    }

    /**
     * Build a OKHttpCleint.Builder with
     * {@link BasicAuthInterceptor#BasicAuthInterceptor(String, String)} interceptor,
     * {@link ContentTypeInterceptor#ContentTypeInterceptor(String)} interceptor and
     * {@link Base64DecodeInterceptor#Base64DecodeInterceptor()} interceptor.
     * @param email User email for Authorization Header.
     * @param password User email for Authorization Header.
     * @return OKHttpCleint.Builder instance with the specified credentials for Piccasso Builder.
     */
    public static OkHttpClient.Builder BuilderClientForPicasso(String email,String password){
        OkHttpClient.Builder cliente =  new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(email,password))
                .addInterceptor(new ContentTypeInterceptor(ServiceConstantsConfig.CONTENT_TYPE))
                .readTimeout(60*6, TimeUnit.SECONDS)
                .connectTimeout(60*6, TimeUnit.SECONDS)
                .addInterceptor(new Base64DecodeInterceptor());
                //.build();
        return cliente;
    }

    /**
     * Get the URL for the requested Image
     * @param id The User Profile Image Id
     * @return String with the requested URL With the id
     */
    public static String getImageURLForUsuarioAppId(int id){
        return new StringBuilder().append(ServiceConstantsConfig.BASE_PATH).append(ServiceConstantsConfig.IMAGE_USUARIO_APP).append(id).toString();
    }
}
