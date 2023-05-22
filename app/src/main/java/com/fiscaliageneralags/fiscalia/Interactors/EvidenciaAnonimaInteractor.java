package com.fiscaliageneralags.fiscalia.Interactors;

import android.content.Context;
import android.location.Address;
import android.util.Log;

import com.fiscaliageneralags.fiscalia.IInteractors.IEvidenciaAnonimaInteractor;
import com.fiscaliageneralags.fiscalia.IPresenters.IEvidenciaAnonimaPresenter;
import com.fiscaliageneralags.fiscalia.IWebServices.IEvidenciaAnonimaService;
import com.fiscaliageneralags.fiscalia.IWebServices.IUbicacionAgenciaService;
import com.fiscaliageneralags.fiscalia.Models.Municipios;
import com.fiscaliageneralags.fiscalia.Utils.UCUtil;
import com.fiscaliageneralags.fiscalia.WebServices.EvidenciaAnonimaServiceBuilder;
import com.fiscaliageneralags.fiscalia.WebServices.UbicacionAgenciaServiceBuilder;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ERodriguezF on 22/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class EvidenciaAnonimaInteractor implements IEvidenciaAnonimaInteractor {
    private Context context;
    private UCUtil userCredentials;
    private IEvidenciaAnonimaPresenter presenter;
    
    public EvidenciaAnonimaInteractor(Context context,IEvidenciaAnonimaPresenter presenter){
        this.context = context;
        this.userCredentials = new UCUtil(context);
        this.presenter =  presenter; 
    }

    @Override
    public void getMunicipios() {
        IUbicacionAgenciaService ubicacionAgenciaService = UbicacionAgenciaServiceBuilder.Build();
        Call<Municipios[]> getMunicipios = ubicacionAgenciaService.getMunicipios();
        getMunicipios.enqueue(new Callback<Municipios[]>() {
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

    @Override
    public void postSendEvidenciaAnonima(File evidencia,
                                         String correo,
                                         String nombre,
                                         Address localizacion,
                                         String descripcionHechos,
                                         String calle,
                                         String colonia,
                                         String numInt,
                                         String numExt,
                                         int municipio,
                                         String municipioForaneo,
                                         String estado,
                                         String celular,
                                         String latitudCelular,
                                         String longitudCelular
                                         ) {

        Log.e("Latitud:",localizacion != null ? String.valueOf(localizacion.getLatitude()):"");
        Log.e("Longitud:",localizacion != null ? String.valueOf(localizacion.getLongitude()):"");
        Log.e("Latitud:",latitudCelular);
        Log.e("Latitud:",longitudCelular);



        IEvidenciaAnonimaService service = EvidenciaAnonimaServiceBuilder.Build();

        MultipartBody.Part filePart;
        if(evidencia != null ){

            filePart= MultipartBody.Part.createFormData("file", evidencia.getName(), RequestBody.create(MediaType.parse("file/*"), evidencia));
        }
        else{
            filePart= MultipartBody.Part.createFormData("nofile", "NODATA", RequestBody.create(MediaType.parse("data/*"), "NODATA"));
        }

        Call<Object> postSugerenciaAlFiscal = service.postEvidenciaAnonima(filePart,
                localizacion != null ? String.valueOf(localizacion.getLatitude()):"",
                localizacion != null ? String.valueOf(localizacion.getLongitude()):"",
                descripcionHechos,
                municipio+"",
                calle,
                colonia,
                numExt,
                numInt,
                correo,
                nombre,
                municipioForaneo,
                estado,
                celular,
                latitudCelular,
                longitudCelular);

        postSugerenciaAlFiscal.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.isSuccessful() || response.code() == 204){
                    presenter.onPostEvidenciaAnonimaSuccess();
                }
                else{
                    presenter.onPostEvidenciaAnonimaError();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                presenter.onConecctionError();
            }
        });

        //Call<Object> postSugerenciaAlFiscal = service;
    }


}
