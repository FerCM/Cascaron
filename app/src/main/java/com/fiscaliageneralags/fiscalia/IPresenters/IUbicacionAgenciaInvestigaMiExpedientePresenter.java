package com.fiscaliageneralags.fiscalia.IPresenters;

import com.fiscaliageneralags.fiscalia.Models.AgenciaDeExpediente;


/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IUbicacionAgenciaInvestigaMiExpedientePresenter {

    //Services
    void getAgenciaIntegradoraDeExpediente(String numExpendiente, String password);

    //Response From Services;
    void onGetAgenciaIntegradoraDeExpedienteSuccess(AgenciaDeExpediente agenciaDeExpediente);

    //General Errors
    void onConecctionError();

}
