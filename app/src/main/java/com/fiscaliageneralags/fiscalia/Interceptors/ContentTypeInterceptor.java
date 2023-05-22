package com.fiscaliageneralags.fiscalia.Interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ERodriguezF on 15/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class ContentTypeInterceptor implements Interceptor {

    private String ContentType;

    /**
     * Creates a content type interceptor to add content type header to the request.
     * @param contentType
     */
    public ContentTypeInterceptor(String contentType){
        this.ContentType = contentType;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request contentTypeRequest = request.newBuilder()
                .header("ContentType", this.ContentType).build();
        return chain.proceed(contentTypeRequest);
    }
}
