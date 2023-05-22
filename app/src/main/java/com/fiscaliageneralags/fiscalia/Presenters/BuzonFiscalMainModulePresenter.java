package com.fiscaliageneralags.fiscalia.Presenters;

import android.content.Context;

import com.fiscaliageneralags.fiscalia.IInteractors.IBuzonFiscalMainModuleInteractor;
import com.fiscaliageneralags.fiscalia.IPresenters.IBuzonFiscalMainModulePresenter;
import com.fiscaliageneralags.fiscalia.IViews.IBuzonFiscalMainModuleView;
import com.fiscaliageneralags.fiscalia.Interactors.BuzonFiscalMainModuleInteractor;
import com.fiscaliageneralags.fiscalia.Models.SugerenciaAlFiscal;


/**
 * Created by ERodriguezF on 03/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class BuzonFiscalMainModulePresenter implements IBuzonFiscalMainModulePresenter {

    private IBuzonFiscalMainModuleInteractor interactor;
    private IBuzonFiscalMainModuleView view;

    public BuzonFiscalMainModulePresenter(Context context, IBuzonFiscalMainModuleView view){
        interactor =  new BuzonFiscalMainModuleInteractor(context,this);
        this.view =  view;
    }

    @Override
    public void postSugerenciaAlFiscal(SugerenciaAlFiscal sugerenciaAlFiscal) {
        interactor.postSugerenciaAlFiscal(sugerenciaAlFiscal);
    }

    @Override
    public void onPostSugerenciaAlFiscalSuccess() {
        view.onPostSugerenciaAlFiscalSuccess();
    }

    @Override
    public void onPostSugerenciaAlFiscalError() {
        view.onPostSugerenciaAlFiscalError();
    }

    @Override
    public void onConecctionError() {
        view.onConecctionError();
    }
}
