package com.fiscaliageneralags.fiscalia.IViews;

import com.fiscaliageneralags.fiscalia.Models.EdificiosParaDenuncia;
import com.fiscaliageneralags.fiscalia.Models.Municipios;


/**
 * Created by ERodriguezF on 05/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IUbicacionAgenciasModulosParaDenunciaView {

    //Response From Services;
    void onGetEdificiosParaDenunciar(EdificiosParaDenuncia[] edificiosParaDenuncias);
    void onGetMunicipiosSuccess(Municipios[] municipios);
    void onGetTodasLasAgenciasSuccess(EdificiosParaDenuncia[] edificiosParaDenuncias);

    //General Errors
    void onConecctionError();

}
