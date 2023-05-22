package com.fiscaliageneralags.fiscalia.Interactors;

import android.content.Context;

import com.fiscaliageneralags.fiscalia.IInteractors.IUbicacionAgenciaInvestigaMiExpedienteInteractor;
import com.fiscaliageneralags.fiscalia.IPresenters.IUbicacionAgenciaInvestigaMiExpedientePresenter;
import com.fiscaliageneralags.fiscalia.IWebServices.IUbicacionAgenciaService;
import com.fiscaliageneralags.fiscalia.Models.AgenciaDeExpediente;
import com.fiscaliageneralags.fiscalia.Models.CaseFileCredentials;
import com.fiscaliageneralags.fiscalia.WebServices.UbicacionAgenciaServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class UbicacionAgenciaInvestigaMiExpedienteInteractor implements IUbicacionAgenciaInvestigaMiExpedienteInteractor {

    private Context context;

    private IUbicacionAgenciaInvestigaMiExpedientePresenter presenter;

    public UbicacionAgenciaInvestigaMiExpedienteInteractor(Context context, IUbicacionAgenciaInvestigaMiExpedientePresenter presenter){
        this.context = context;
        this.presenter =  presenter;
    }

    @Override
    public void getAgenciaIntegradoraDeExpediente(String numExpendiente, String password) {
        IUbicacionAgenciaService services = UbicacionAgenciaServiceBuilder.Build();
        CaseFileCredentials caseFileCredentials = new CaseFileCredentials();
        caseFileCredentials.caseFileCode = numExpendiente;
        caseFileCredentials.passGuid = password;
        Call<AgenciaDeExpediente> getAgenciaDeExpediente = services.getAgenciaEnExpediente(caseFileCredentials);
        getAgenciaDeExpediente.enqueue(new Callback<AgenciaDeExpediente>() {
            @Override
            public void onResponse(Call<AgenciaDeExpediente> call, Response<AgenciaDeExpediente> response) {
                if(response.isSuccessful()){
                    presenter.onGetAgenciaIntegradoraDeExpedienteSuccess(response.body());
                }
                else{
                    presenter.onConecctionError();
                }
            }

            @Override
            public void onFailure(Call<AgenciaDeExpediente> call, Throwable t) {
                presenter.onConecctionError();
            }
        });
    }
}
