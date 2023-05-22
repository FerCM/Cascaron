package com.fiscaliageneralags.fiscalia.WebServices;


import com.fiscaliageneralags.fiscalia.IWebServices.CI;
import com.fiscaliageneralags.fiscalia.WebServices.BaseService.BaseServiceBuilder;

public class Builder {
    private static Builder ServiceBuilder;

    /**
     * Instance of the services to use.
     */
    private CI Service;

    /**
     * Create a new instance of the PreDenunciaServiceBuilder
     * to consume the services for the image using {@link BaseServiceBuilder#Build()} method.
     */
    private Builder(){
        Service = BaseServiceBuilder.Build().create(CI.class);
    }

    /**
     * Gets and validate de corresponding credentials for the instance
     * that is going to return.
     * @return The service for the web api.
     */
    public static CI Build(){
        if(ServiceBuilder ==null){
            ServiceBuilder =  new Builder();
        }
        return ServiceBuilder.Service;
    }
}
