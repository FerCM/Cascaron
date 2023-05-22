package com.fiscaliageneralags.fiscalia.Interactors;

import com.fiscaliageneralags.fiscalia.IInteractors.IPreDenunciaInteractor;
import com.fiscaliageneralags.fiscalia.IPresenters.IPreDenunciaPresenter;
import com.fiscaliageneralags.fiscalia.IWebServices.IPreDenunciaService;
import com.fiscaliageneralags.fiscalia.Models.PreDenuncia;
import com.fiscaliageneralags.fiscalia.WebServices.PreDenunciaServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ERodriguezF on 22/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class PreDenunciaInteractor implements IPreDenunciaInteractor {

    private IPreDenunciaPresenter presenter;

    public PreDenunciaInteractor(IPreDenunciaPresenter preDenunciaPresenter){
        this.presenter = preDenunciaPresenter;
    }

    @Override
    public void postPreDenuncia(PreDenuncia newPreDenuncia) {

        IPreDenunciaService services = PreDenunciaServiceBuilder.Build();
        Call<String> preDenunciaService = services.postNewPreDenuncia(newPreDenuncia);
        preDenunciaService.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    presenter.onPreDenunciaSucesss(response.body());
                }
                else{
                    System.out.println("ERRROOOOOOOOOOOOOOOOORrekrekroek : " + response);
                    presenter.onPreDenunciaError();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERRROOOOOOOOOOOOOOOOORaki : " + t.getMessage());
                presenter.onPreDenunciaError();
            }
        });
    }
}
