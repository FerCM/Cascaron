package com.fiscaliageneralags.fiscalia.Presenters;

import android.content.Context;

import com.fiscaliageneralags.fiscalia.IInteractors.IUbicacionAgenciaInvestigaMiExpedienteInteractor;
import com.fiscaliageneralags.fiscalia.IPresenters.IUbicacionAgenciaInvestigaMiExpedientePresenter;
import com.fiscaliageneralags.fiscalia.IViews.IUbicacionAgenciaInvestigaMiExpedienteView;
import com.fiscaliageneralags.fiscalia.Interactors.UbicacionAgenciaInvestigaMiExpedienteInteractor;
import com.fiscaliageneralags.fiscalia.Models.AgenciaDeExpediente;


/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class UbicacionAgenciaInvestigaMiExpedientePresenter implements IUbicacionAgenciaInvestigaMiExpedientePresenter {

    private IUbicacionAgenciaInvestigaMiExpedienteInteractor interactor;
    private IUbicacionAgenciaInvestigaMiExpedienteView view;

    public UbicacionAgenciaInvestigaMiExpedientePresenter(Context context, IUbicacionAgenciaInvestigaMiExpedienteView view){
        interactor =  new UbicacionAgenciaInvestigaMiExpedienteInteractor(context,this);
        this.view =  view;
    }

    @Override
    public void getAgenciaIntegradoraDeExpediente(String numExpendiente, String password) {
        interactor.getAgenciaIntegradoraDeExpediente(numExpendiente, password);
    }

    @Override
    public void onGetAgenciaIntegradoraDeExpedienteSuccess(AgenciaDeExpediente agenciaDeExpediente) {
        view.onGetAgenciaIntegradoraDeExpedienteSuccess(agenciaDeExpediente);
    }

    @Override
    public void onConecctionError() {
        view.onConecctionError();
    }
}
