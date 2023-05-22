package com.fiscaliageneralags.fiscalia.WebServices;


import com.fiscaliageneralags.fiscalia.IWebServices.IPreDenunciaService;
import com.fiscaliageneralags.fiscalia.WebServices.BaseService.BaseServiceBuilder;

/**
 * Created by ERodriguezF on 22/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class PreDenunciaServiceBuilder {

    /**
     * Instance of self for singleton pattern.
     */
    private static PreDenunciaServiceBuilder preDenunciaServiceBuilder;

    /**
     * Instance of the services to use.
     */
    private IPreDenunciaService preDenunciaService;


    /**
     * Create a new instance of the PreDenunciaServiceBuilder
     * to consume the services for the image using {@link BaseServiceBuilder#Build()} method.
     */
    private PreDenunciaServiceBuilder(){
        preDenunciaService = BaseServiceBuilder.Build().create(IPreDenunciaService.class);
    }

    /**
     * Gets and validate de corresponding credentials for the instance
     * that is going to return.
     * @return The service for the web api.
     */
    public static IPreDenunciaService Build(){
        if(preDenunciaServiceBuilder ==null){
            preDenunciaServiceBuilder =  new PreDenunciaServiceBuilder();
        }
        return preDenunciaServiceBuilder.preDenunciaService;
    }

}
