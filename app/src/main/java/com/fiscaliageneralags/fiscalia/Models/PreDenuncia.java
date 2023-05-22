package com.fiscaliageneralags.fiscalia.Models;

/**
 * Created by ERodriguezF on 09/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class PreDenuncia {
    //Datos de usuario
    public String nombreCompleto;
    public int edad;
    public int id_estado_civil;
    public String nacionalidad;
    public String domicilio;
    public String telefono;
    public String correo;

    public String Latitud;
    public String Longitud;
    public String Imputado;
    public String DireImputado;
    //DELITO
    public int TipoDelito;
    public String momentoHechos;
    public String lugardeHechos;
    public String percanceHechos;
    public String narracionHechos;
    public String testigoHechos;
    String personas_presentes;
    //Caso de robo

    public String objetoRobado;
    public int testigo_presencial;

    //Lesiones
    public String lesionesHechos;
    public int armasHechos;
    public int atencionMedica;
    public String lugaratencionMedica;

    //Caso de daños
    public String objetosDaniados;

      public PreDenuncia(String nombreCompleto, int edad, String correo, String imputado) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.correo = correo;
    }
    @Override
    public String toString() {
        return "PreDenuncia{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", edad=" + edad +
                ", id_estado_civil=" + id_estado_civil +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", Latitud='" + Latitud + '\'' +
                ", Longitud='" + Longitud + '\'' +
                ", Imputado='" + Imputado + '\'' +
                ", DireImputado='" + DireImputado + '\'' +
                ", TipoDelito=" + TipoDelito +
                ", momentoHechos='" + momentoHechos + '\'' +
                ", lugardeHechos='" + lugardeHechos + '\'' +
                ", percanceHechos='" + percanceHechos + '\'' +
                ", narracionHechos='" + narracionHechos + '\'' +
                ", testigoHechos='" + testigoHechos + '\'' +
                ", personas_presentes='" + personas_presentes + '\'' +
                ", objetoRobado='" + objetoRobado + '\'' +
                ", testigo_presencial=" + testigo_presencial +
                ", lesionesHechos='" + lesionesHechos + '\'' +
                ", armasHechos=" + armasHechos +
                ", atencionMedica=" + atencionMedica +
                ", lugaratencionMedica='" + lugaratencionMedica + '\'' +
                ", objetosDaniados='" + objetosDaniados + '\'' +
                '}';
    }

}
