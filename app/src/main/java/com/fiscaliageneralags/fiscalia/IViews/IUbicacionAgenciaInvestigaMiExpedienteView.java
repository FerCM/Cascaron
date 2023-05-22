package com.fiscaliageneralags.fiscalia.IViews;

import com.fiscaliageneralags.fiscalia.Models.AgenciaDeExpediente;


/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IUbicacionAgenciaInvestigaMiExpedienteView {

    //Response From Services;
    void onGetAgenciaIntegradoraDeExpedienteSuccess(AgenciaDeExpediente agenciaDeExpediente);

    //General Errors
    void onConecctionError();

}
