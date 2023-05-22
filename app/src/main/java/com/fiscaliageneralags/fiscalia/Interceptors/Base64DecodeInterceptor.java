package com.fiscaliageneralags.fiscalia.Interceptors;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ERodriguezF on 22/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class Base64DecodeInterceptor implements Interceptor{

    /**
     * Creates a Base 64 Decoder Interceptor to handle the response image
     */
    public Base64DecodeInterceptor(){}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request().newBuilder()
                .build();
        Response response = chain.proceed(newRequest);

        String base64Image = response.body().string();
        if( base64Image != null && !base64Image.isEmpty() ){
            base64Image.replace("\"","");
            byte[] decodedImage = Base64.decode(base64Image,Base64.DEFAULT);
            ResponseBody body = ResponseBody.create( MediaType.parse("application/octet-stream; charset=utf-8"), decodedImage);
            response =  response.newBuilder().body(body).build();
        }

        return response;
    }
}
