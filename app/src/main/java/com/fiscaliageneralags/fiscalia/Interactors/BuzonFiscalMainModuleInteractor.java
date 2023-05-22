package com.fiscaliageneralags.fiscalia.Interactors;

import android.content.Context;

import com.fiscaliageneralags.fiscalia.IInteractors.IBuzonFiscalMainModuleInteractor;
import com.fiscaliageneralags.fiscalia.IPresenters.IBuzonFiscalMainModulePresenter;
import com.fiscaliageneralags.fiscalia.IWebServices.ISugerenciaAlFiscalService;
import com.fiscaliageneralags.fiscalia.Models.SugerenciaAlFiscal;
import com.fiscaliageneralags.fiscalia.Utils.UCUtil;
import com.fiscaliageneralags.fiscalia.WebServices.SugerenciaAlFiscalServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ERodriguezF on 22/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class BuzonFiscalMainModuleInteractor implements IBuzonFiscalMainModuleInteractor {

    private Context context;
    private UCUtil userCredentials;
    private IBuzonFiscalMainModulePresenter presenter;

    public BuzonFiscalMainModuleInteractor(Context context, IBuzonFiscalMainModulePresenter presenter){
        this.context = context;
        this.userCredentials = new UCUtil(context);
        this.presenter =  presenter;
    }

    @Override
    public void postSugerenciaAlFiscal(SugerenciaAlFiscal sugerenciaAlFiscal) {
        ISugerenciaAlFiscalService service = SugerenciaAlFiscalServiceBuilder.Build();
        Call<Object> postSugerenciaAlFiscal = service.PostSugerencia(sugerenciaAlFiscal);
        postSugerenciaAlFiscal.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if( response.isSuccessful() || response.code() == 204 ){
                    presenter.onPostSugerenciaAlFiscalSuccess();
                }
                else{
                    presenter.onPostSugerenciaAlFiscalError();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                presenter.onConecctionError();
            }
        });
    }
}
