package com.fiscaliageneralags.fiscalia.Interactors;

import android.content.Context;

import com.fiscaliageneralags.fiscalia.IInteractors.IUbicacionAgenciasModulosParaDenunciaInteractor;
import com.fiscaliageneralags.fiscalia.IPresenters.IUbicacionAgenciasModulosParaDenunciaPresenter;
import com.fiscaliageneralags.fiscalia.IWebServices.IUbicacionAgenciaService;
import com.fiscaliageneralags.fiscalia.Models.EdificiosParaDenuncia;
import com.fiscaliageneralags.fiscalia.Models.Municipios;
import com.fiscaliageneralags.fiscalia.WebServices.UbicacionAgenciaServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ERodriguezF on 05/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class UbicacionAgenciasModulosParaDenunciaInteractor implements IUbicacionAgenciasModulosParaDenunciaInteractor {

    private Context context;

    private IUbicacionAgenciasModulosParaDenunciaPresenter presenter;

    public UbicacionAgenciasModulosParaDenunciaInteractor(Context context,IUbicacionAgenciasModulosParaDenunciaPresenter presenter){
        this.context = context;
        this.presenter =  presenter;
    }

    @Override
    public void getEdificiosParaDenunciar() {
        IUbicacionAgenciaService service = UbicacionAgenciaServiceBuilder.Build();
        Call<EdificiosParaDenuncia[]> call = service.getAgenciasParaDenunciar();
        call.enqueue(new Callback<EdificiosParaDenuncia[]>() {
            @Override
            public void onResponse(Call<EdificiosParaDenuncia[]> call, Response<EdificiosParaDenuncia[]> response) {
                if(response.isSuccessful()){
                    presenter.onGetEdificiosParaDenunciar(response.body());
                }
                else{
                    presenter.onConecctionError();
                }
            }

            @Override
            public void onFailure(Call<EdificiosParaDenuncia[]> call, Throwable t) {
                presenter.onConecctionError();
            }
        });

    }
    @Override
    public void getTodasLasAgencias() {
        IUbicacionAgenciaService ubicacionAgenciaService = UbicacionAgenciaServiceBuilder.Build();
        Call<EdificiosParaDenuncia[]> getTodasLasAgencias = ubicacionAgenciaService.getAgenciasEnElEstado();
        getTodasLasAgencias.enqueue(new Callback<EdificiosParaDenuncia[]>() {
            @Override
            public void onResponse(Call<EdificiosParaDenuncia[]> call, Response<EdificiosParaDenuncia[]> response) {
                if(response.isSuccessful()){
                    presenter.onGetTodasLasAgenciasSuccess(response.body());
                }
                else{
                    presenter.onConecctionError();
                }
            }

            @Override
            public void onFailure(Call<EdificiosParaDenuncia[]> call, Throwable t) {
                presenter.onConecctionError();
            }
        });
    }
    @Override
    public void getMunicipios() {
        IUbicacionAgenciaService service = UbicacionAgenciaServiceBuilder.Build();
        Call<Municipios[]> call = service.getMunicipios();
        call.enqueue(new Callback<Municipios[]>() {
            @Override
            public void onResponse(Call<Municipios[]> call, Response<Municipios[]> response) {
                if(response.isSuccessful()){
                    presenter.onGetMunicipiosSuccess(response.body());
                }
                else{
                    presenter.onConecctionError();
                }
            }

            @Override
            public void onFailure(Call<Municipios[]> call, Throwable t) {
                presenter.onConecctionError();
            }
        });

    }
}
