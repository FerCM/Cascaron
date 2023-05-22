package com.fiscaliageneralags.fiscalia.Models;

import android.util.Log;

import com.fiscaliageneralags.fiscalia.Utils.StringFormatterUtil;


/**
 * Created by ERodriguezF on 26/02/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class Municipios {
    public int Cve_mun;
    public String Latitud;
    public String Longitud;
    public String Municipio;

    public Municipios() {
    }

    public Municipios(int cve_mun, String latitud, String longitud, String municipio) {
        Cve_mun = cve_mun;

        Latitud = latitud;
        Longitud = longitud;
        Municipio = municipio;
    }

    @Override
    public String toString() {
        return StringFormatterUtil.convertStringToFUL(Municipio);
    }

    public double GetLatitud(){
        try {
            return Double.parseDouble(Latitud);
        }catch (Exception e){
            Log.e("ERROR_PARSE",e.toString());
        }
        return 0.0;
    }

    public double GetLongitud(){
        try {
            return Double.parseDouble(Longitud);
        }catch (Exception e){
            Log.e("ERROR_PARSE",e.toString());
        }
        return 0.0;
    }
}
