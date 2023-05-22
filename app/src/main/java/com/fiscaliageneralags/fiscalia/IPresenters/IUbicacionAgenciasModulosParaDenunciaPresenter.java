package com.fiscaliageneralags.fiscalia.IPresenters;

import com.fiscaliageneralags.fiscalia.Models.EdificiosParaDenuncia;
import com.fiscaliageneralags.fiscalia.Models.Municipios;

/**
 * Created by ERodriguezF on 05/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public interface IUbicacionAgenciasModulosParaDenunciaPresenter {

    //Services
    void getEdificiosParaDenunciar();
    void getMunicipios();
    void getTodasLasAgencias();

    //Response From Services;
    void onGetEdificiosParaDenunciar(EdificiosParaDenuncia[] edificiosParaDenuncias);
    void onGetMunicipiosSuccess(Municipios[] municipios);
    void onGetTodasLasAgenciasSuccess(EdificiosParaDenuncia[] edificiosParaDenuncias);
    //General Errors
    void onConecctionError();

}
