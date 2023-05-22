package com.fiscaliageneralags.fiscalia.Interceptors;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ERodriguezF on 12/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class BasicAuthInterceptor implements Interceptor {

    private String credentials;

    /**
     * Creates a Basic authentication interceptor to add the headers authentication to the request
     * @param user
     * @param password
     */
    public BasicAuthInterceptor(String user, String password) {
        this.credentials = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }
}
