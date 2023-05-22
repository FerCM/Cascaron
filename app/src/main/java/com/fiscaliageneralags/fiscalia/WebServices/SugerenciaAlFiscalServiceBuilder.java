package com.fiscaliageneralags.fiscalia.WebServices;


import com.fiscaliageneralags.fiscalia.IWebServices.ISugerenciaAlFiscalService;
import com.fiscaliageneralags.fiscalia.WebServices.BaseService.BaseServiceBuilder;

public class SugerenciaAlFiscalServiceBuilder {

    /**
     * Instance of self for singleton pattern.
     */
    private static SugerenciaAlFiscalServiceBuilder serviceBuilder;

    /**
     * Instance of the services to use.
     */
    public ISugerenciaAlFiscalService service;

    /**
     * Create a new instance of the ResetPasswordServiceBuilder
     * to consume the services for the image using {@link BaseServiceBuilder#Build()} method.
     */
    private SugerenciaAlFiscalServiceBuilder(){
        service = BaseServiceBuilder.Build().create(ISugerenciaAlFiscalService.class);
    }

    /**
     * Gets and validate de corresponding credentials for the instance
     * that is going to return.
     * @return The service for the web api.
     */
    public static ISugerenciaAlFiscalService Build(){
        if(serviceBuilder ==null){
            serviceBuilder =  new SugerenciaAlFiscalServiceBuilder();
        }
        return serviceBuilder.service;
    }

}
