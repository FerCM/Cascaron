package com.fiscaliageneralags.fiscalia.WebServices;

import com.fiscaliageneralags.fiscalia.IWebServices.IEvidenciaAnonimaService;
import com.fiscaliageneralags.fiscalia.WebServices.BaseService.BaseServiceBuilder;

/**
 * Created by ERodriguezF on 22/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class EvidenciaAnonimaServiceBuilder {

    /**
     * Instance of self for singleton pattern.
     */
    private static EvidenciaAnonimaServiceBuilder serviceBuilder;

    /**
     * Instance of the services to use.
     */
    public IEvidenciaAnonimaService service;

    /**
     * Create a new instance of the ResetPasswordServiceBuilder
     * to consume the services for the image using {@link BaseServiceBuilder#Build()} method.
     */
    private EvidenciaAnonimaServiceBuilder(){
        service = BaseServiceBuilder.Build().create(IEvidenciaAnonimaService.class);
    }

    /**
     * Gets and validate de corresponding credentials for the instance
     * that is going to return.
     * @return The service for the web api.
     */
    public static IEvidenciaAnonimaService Build(){
        if(serviceBuilder ==null){
            serviceBuilder =  new EvidenciaAnonimaServiceBuilder();
        }
        return serviceBuilder.service;
    }

}
