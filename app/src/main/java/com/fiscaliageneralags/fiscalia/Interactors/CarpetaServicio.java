package com.fiscaliageneralags.fiscalia.Interactors;

import com.fiscaliageneralags.fiscalia.IInteractors.Interactor;
import com.fiscaliageneralags.fiscalia.IPresenters.Presenter;
import com.fiscaliageneralags.fiscalia.IWebServices.CI;
import com.fiscaliageneralags.fiscalia.WebServices.Builder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarpetaServicio implements Interactor {
    private Presenter presenter;

    public CarpetaServicio(Presenter carpetaPresenter){
        this.presenter = carpetaPresenter;
    }


    @Override
    public void getCarpetaI(String carpeta, int num) {
        CI services = Builder.Build();
        Call<String> preDenunciaService = services.getCarpetaI(carpeta, num);
        preDenunciaService.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    presenter.onCarpetaSucess(response.body());
                }
                else{
                    System.out.println("ERRROOOOOOOOOOOOOOOOORrekrekroek : " + response);
                    presenter.onCarpetaError();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERRROOOOOOOOOOOOOOOOORaki : " + t.getMessage());
                presenter.onCarpetaError();
            }
        });
    }
    @Override
    public void getAlertaAmber(int estatus) {
        CI services = Builder.Build();
        Call<String> preDenunciaService = services.getAlertaAmber(estatus);
        preDenunciaService.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    presenter.onCarpetaSucess(response.body());
                }
                else{
                    System.out.println("ERRROOOOOOOOOOOOOOOOORrekrekroek : " + response);
                    presenter.onCarpetaError();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERRROOOOOOOOOOOOOOOOORaki : " + t.getMessage());
                presenter.onCarpetaError();
            }
        });
    }
}
