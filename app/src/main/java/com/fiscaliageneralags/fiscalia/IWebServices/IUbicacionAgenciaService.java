package com.fiscaliageneralags.fiscalia.IWebServices;

import com.fiscaliageneralags.fiscalia.Models.AgenciaDeExpediente;
import com.fiscaliageneralags.fiscalia.Models.CaseFileCredentials;
import com.fiscaliageneralags.fiscalia.Models.EdificiosParaDenuncia;
import com.fiscaliageneralags.fiscalia.Models.Municipios;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by ERodriguezF on 26/02/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IUbicacionAgenciaService {

    // GET: AgencyLocations/Municipios
    @GET("AgencyLocations/Municipios")
    Call<Municipios[]> getMunicipios();

    // GET: AgencyLocations/Agencies
    @GET("AgencyLocations/Agencies")
    Call<EdificiosParaDenuncia[]> getAgenciasEnElEstado();

    // GET: AgencyLocations/EdificiosDenuncia
    @GET("AgencyLocations/EdificiosDenuncia")
    Call<EdificiosParaDenuncia[]> getAgenciasParaDenunciar();

    // GET: AgencyLocations/Expediente

    @POST("AgencyLocations/Expediente")
    Call<AgenciaDeExpediente> getAgenciaEnExpediente(@Body CaseFileCredentials caseFileCredentials);//@Part("caseFileCode") String numExpediente,@Part("passGuid") String password);

}
