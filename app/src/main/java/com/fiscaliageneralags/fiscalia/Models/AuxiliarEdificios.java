package com.fiscaliageneralags.fiscalia.Models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Index;
@Entity
public class AuxiliarEdificios {
    @io.objectbox.annotation.Id( assignable = true)
    public long Id;
    @Index
    public String Latitud;
    @Index
    public String Longitud;
    @Index
    public String Edificio;
    @Index
    public String Horario_Denuncia;
    @Index
    public String Domicilio;
    @Index
    public int Cve_Mun;
    @Index
    public String Telefono;

    public Double getLatitud(){
        return Double.parseDouble(Latitud);
    }

    public Double getLongitud(){
        return Double.parseDouble(Longitud);
    }

    public AuxiliarEdificios(String telefono, String edificio, String Horario_denuncia, int Cve_mun, String domi) {
        Telefono = telefono;
        Edificio= edificio;
        Horario_Denuncia= Horario_denuncia;
        Cve_Mun= Cve_mun;
        Domicilio=domi;
    }
}
