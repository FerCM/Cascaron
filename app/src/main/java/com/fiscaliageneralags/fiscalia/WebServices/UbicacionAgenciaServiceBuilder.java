package com.fiscaliageneralags.fiscalia.WebServices;

import com.fiscaliageneralags.fiscalia.IWebServices.IUbicacionAgenciaService;
import com.fiscaliageneralags.fiscalia.WebServices.BaseService.BaseServiceBuilder;

/**
 * Created by ERodriguezF on 26/02/2018.
 * @author ERodriguezF
 * @version 1.3
 */

public class UbicacionAgenciaServiceBuilder {

    /**
     * Instance of self for singleton pattern.
     */
    private static UbicacionAgenciaServiceBuilder serviceBuilder;

    /**
     * Instance of the services to use.
     */
    private IUbicacionAgenciaService service;

    /**
     * Create a new instance of the UbicacionAgenciaServiceBuilder
     * to consume the services for the image using {@link BaseServiceBuilder#Build()} method.
     */
    private UbicacionAgenciaServiceBuilder(){
        service = BaseServiceBuilder.Build().create(IUbicacionAgenciaService.class);
    }

    /**
     * Gets and validate de corresponding credentials for the instance
     * that is going to return.
     * @return The service for the web api.
     */
    public static IUbicacionAgenciaService Build(){
        if(serviceBuilder ==null){
            serviceBuilder =  new UbicacionAgenciaServiceBuilder();
        }
        return serviceBuilder.service;
    }


}