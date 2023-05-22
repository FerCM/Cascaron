package com.fiscaliageneralags.fiscalia.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fiscaliageneralags.fiscalia.EvidenciaAnonimaMainModule;
import com.fiscaliageneralags.fiscalia.IPresenters.IPreDenunciaPresenter;
import com.fiscaliageneralags.fiscalia.IViews.IPreDenunciaView;
import com.fiscaliageneralags.fiscalia.MainActivity;
import com.fiscaliageneralags.fiscalia.Models.PreDenuncia;
import com.fiscaliageneralags.fiscalia.Presenters.PreDenunciaPresenter;
import com.fiscaliageneralags.fiscalia.R;
import com.fiscaliageneralags.fiscalia.Utils.PostMETValidator;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class PredenunciaMain extends AppCompatActivity implements IPreDenunciaView, AdapterView.OnItemSelectedListener {
    private final static String[] delitos = { "Seleccione tipo de delito", "Robo", "Daños","Generales"};
    private final static String[] estadocivil = {"Seleccione estado civil", "Soltero", "Casado", "Divorciado"};
    private static final int LOCATION_REQUEST_CODE = 1651;
    @BindView(R.id.edit_text_pre_denuncia_nombre)
    MaterialEditText editTextPreDenunciaNombre;
    @BindView(R.id.edit_text_pre_denuncia_edad)
    MaterialEditText editTextPreDenunciaEdad;
    @BindView(R.id.pre_denuncia_estado)
    Spinner spinnerEstadoCivil;

    @BindView(R.id.edit_text_pre_denuncia_nacionalidad)
    MaterialEditText editTextPreDenunciaNacionalidad;
    @BindView(R.id.pre_denuncia_ubiacacion_layout)
    ConstraintLayout preDenunciaUbiacacionLayout;
    @BindView(R.id.edit_text_pre_denuncia_direccion)
    MaterialEditText editTextPreDenunciaDireccion;
    @BindView(R.id.pre_denuncia_direccion_selecionada)
    TextView preDenunciaDireccionSelecionada;
    @BindView(R.id.edit_text_pre_denuncia_telefono)
    MaterialEditText editTextPreDenunciaTel;
    @BindView(R.id.edit_text_pre_denuncia_correo)
    MaterialEditText editTextPreDenunciaCorreo;
    @BindView(R.id.edit_text_pre_denuncia_inculpado_nombre)
    MaterialEditText editTextPreDenunciaImputadoNombre;
    @BindView(R.id.edit_text_pre_denuncia_inculpado_direccion)
    MaterialEditText editTextPreDenunciaImputadoDireccion;

    @BindView(R.id.pre_denuncia_exp)
    TextView preDenunciaExp;

    @BindView(R.id.pre_denuncia_exp_delitos)
    TextView preDenunciaDelitosExp;

    @BindView(R.id.btn_pre_denuncia_continuar)
    Button continuar;

    FrameLayout nombre;
    FrameLayout edad;
    FrameLayout estado;
    FrameLayout nacionalidad;
    ConstraintLayout ubicacion;
    FrameLayout telefono;
    FrameLayout correo;
    FrameLayout imputado_nombre;
    FrameLayout imputado_dire;

    /*Todo sobre las denuncias */
    //General

    @BindView(R.id.pre_denuncia_tipospiner)
    Spinner spinnerDenuncias;
    @BindView(R.id.edit_text_pre_denuncia_momento)
    MaterialEditText momentoHechos;
    @BindView(R.id.edit_text_pre_denuncia_lugar)
    MaterialEditText lugardeHechos;
    @BindView(R.id.edit_text_pre_denuncia_percance)
    MaterialEditText percanceHechos;
    @BindView(R.id.edit_text_pre_denuncia_narracion)
    MaterialEditText narracionHechos;
    @BindView(R.id.edit_text_pre_denuncia_testigo)
    MaterialEditText testigoHechos;

    //Caso de robo
    @BindView(R.id.edit_text_pre_denuncia_objetos)
    MaterialEditText objetoRobado;
    @BindView(R.id.pre_denuncia_nota)
    TextView nota;
    @BindView(R.id.pre_denuncia_testigos)
    TextView testigoRobo;

    @BindView(R.id.pre_denuncia_imputado)
    TextView imputado;

    //Caso de daños
    @BindView(R.id.edit_text_pre_denuncia_objeto_dan)
    MaterialEditText objetosDañados;

    @BindView(R.id.btn_pre_denuncia_enviar)
    Button enviar;

    private Unbinder unbinder;
    private Address direccionSeleccionada;
    private boolean validationForSubmit = false;
    private MaterialDialog processDialog;
    private Activity activity;
    private IPreDenunciaPresenter presenter;

    private CheckBox opcionSi, opcionNo, testigos_si, testigos_no;

    FrameLayout tipodelito;
    FrameLayout hechos ;
    FrameLayout lugarhechos ;
    FrameLayout percance;
    FrameLayout narracion ;
    FrameLayout testigos;
    FrameLayout direccion;
    FrameLayout objetos;
    FrameLayout objeto_dan;
    PreDenuncia newPreDenuncia; //Generamos la instancia
    int delito, estadoc, testigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predenuncia_main);
        unbinder = ButterKnife.bind(this);
        presenter = new PreDenunciaPresenter(this);
        opcionSi = findViewById(R.id.opcion_si);
        opcionNo = findViewById(R.id.opcion_no);

        testigos_no= findViewById(R.id.pre_denuncia_testigos_no);
        testigos_si= findViewById(R.id.pre_denuncia_testigos_si);
        nombre= findViewById(R.id.frame_pre_denuncia_nombre);
        edad = findViewById(R.id.frame_pre_denuncia_edad);
        estado = findViewById(R.id.frame_pre_denuncia_estado);
        nacionalidad = findViewById(R.id.frame_pre_denuncia_nacionalidad);
        ubicacion = findViewById(R.id.pre_denuncia_ubiacacion_layout);
        telefono = findViewById(R.id.frame_pre_denuncia_telefono);
        correo = findViewById(R.id.frame_pre_denuncia_correo);
        imputado_nombre = findViewById(R.id.frame_pre_denuncia_inculpado_nombre);
        imputado_dire = findViewById(R.id.frame_pre_denuncia_inculpado_direccion);
        tipodelito = findViewById(R.id.frame_pre_tipodelito);
        hechos = findViewById(R.id.frame_momento_hechos);
        lugarhechos = findViewById(R.id.frame_lugar_hechos);
        percance = findViewById(R.id.frame_percance_hechos);
        narracion = findViewById(R.id.pre_denuncia_narracion_layout);
        testigos = findViewById(R.id.frame_pre_denuncia_testigos);
        direccion = findViewById(R.id.frame_pre_denuncia_direccion);
        objetos = findViewById(R.id.frame_pre_denuncia_objetos);
        objeto_dan = findViewById(R.id.frame_objeto_dan);

        mostrar(false); //Iniciamos solo con datos del usuario
        opcionSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(opcionSi.isChecked()){
                    opcionNo.setChecked(false);
                    imputado_nombre.setVisibility(View.VISIBLE);
                    imputado_dire.setVisibility(View.VISIBLE);
                }else{
                    imputado_nombre.setVisibility(View.GONE);
                    imputado_dire.setVisibility(View.GONE);
                }
            }
        });
        opcionNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(opcionNo.isChecked()){
                    opcionSi.setChecked(false);
                    imputado_nombre.setVisibility(View.GONE);
                    imputado_dire.setVisibility(View.GONE);
                }else{
                    imputado_nombre.setVisibility(View.VISIBLE);
                    imputado_dire.setVisibility(View.VISIBLE);

                }
            }
        });

        testigos_si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(testigos_si.isChecked()){
                    testigos_no.setChecked(false);
                    testigo=1;
                }
            }
        });
        testigos_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( testigos_no.isChecked()){
                    testigos_si.setChecked(false);
                    testigo=0;
                }
            }
        });



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, delitos);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerDenuncias.setAdapter(adapter);
        spinnerDenuncias.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapteredo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estadocivil);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerEstadoCivil.setAdapter(adapteredo);
        spinnerEstadoCivil.setOnItemSelectedListener(this);

        spinnerEstadoCivil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                System.out.println("Aki stoy "+ pos);
                String delito = adapterView.getItemAtPosition(pos).toString();
                System.out.println("Escogio? " + delito);
                estadoc = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });
        // addPostMETValidatorForLength(editTextPreDenunciaNarracion, 50, 30000);
        METValidator requiredValidation = new METValidator("Este compo es requerido.") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return text.length() >= 1 && !isEmpty;
            }
        };
        editTextPreDenunciaNombre.addValidator(requiredValidation);
        editTextPreDenunciaNombre.setValidateOnFocusLost(true);

        editTextPreDenunciaNacionalidad.addValidator(requiredValidation);
        editTextPreDenunciaNacionalidad.setValidateOnFocusLost(true);

        editTextPreDenunciaDireccion.addValidator(requiredValidation);
        editTextPreDenunciaDireccion.setValidateOnFocusLost(true);

        editTextPreDenunciaTel.addValidator(requiredValidation);
        editTextPreDenunciaTel.setValidateOnFocusLost(true);

        editTextPreDenunciaEdad.addValidator(requiredValidation);
        editTextPreDenunciaEdad.setValidateOnFocusLost(true);

        editTextPreDenunciaCorreo.addValidator(requiredValidation);
        editTextPreDenunciaCorreo.setValidateOnFocusLost(true);

        if(opcionSi.isChecked()){
            editTextPreDenunciaImputadoNombre.addValidator(requiredValidation);
            editTextPreDenunciaImputadoNombre.setValidateOnFocusLost(true);
        }
        editTextPreDenunciaCorreo.addValidator(new METValidator(getResources().getString(R.string.login_validator_error_email)) {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return Patterns.EMAIL_ADDRESS.matcher(text).matches();
            }
        });

        //-----------------------------------------------------------------------------------------------------
        momentoHechos.addValidator(requiredValidation);
        momentoHechos.setValidateOnFocusLost(true);

        lugardeHechos.addValidator(requiredValidation);
        lugardeHechos.setValidateOnFocusLost(true);

        percanceHechos.addValidator(requiredValidation);
        percanceHechos.setValidateOnFocusLost(true);

        if(delito!=1){
            narracionHechos.addValidator(requiredValidation);
            narracionHechos.setValidateOnFocusLost(true);
        }


        testigoHechos.addValidator(requiredValidation);
        testigoHechos.setValidateOnFocusLost(true);
        switch (delito){
            case 1: //robo
                objetoRobado.addValidator(requiredValidation);
                objetoRobado.setValidateOnFocusLost(true);
                break;
            case 2: //Caso de daños
                objetosDañados.addValidator(requiredValidation);
                objetosDañados.setValidateOnFocusLost(true);
                break;

        }

        processDialog = new MaterialDialog.Builder(this)
                .content("Generando Pre Denuncia ...")
                .progress(true, 0)
                .build();
        processDialog.setCanceledOnTouchOutside(false);
    }
    public void mostrar(boolean mostrar){
        if(mostrar){
            tipodelito.setVisibility(View.VISIBLE);
            preDenunciaDelitosExp.setVisibility(View.VISIBLE);
            preDenunciaExp.setVisibility(View.INVISIBLE);
            continuar.setVisibility(View.GONE);
            enviar.setVisibility(View.GONE);
            nombre.setVisibility(View.GONE);
            edad.setVisibility(View.GONE);
            estado.setVisibility(View.GONE);
            nacionalidad.setVisibility(View.GONE);
            direccion.setVisibility(View.GONE);
            ubicacion.setVisibility(View.GONE);
            telefono.setVisibility(View.GONE);
            correo.setVisibility(View.GONE);

            // tipodelito.setVisibility(View.GONE);
            hechos.setVisibility(View.GONE);
            lugarhechos.setVisibility(View.GONE);
            percance.setVisibility(View.GONE);
            narracion.setVisibility(View.GONE);
            testigos.setVisibility(View.GONE);
            objetos.setVisibility(View.GONE);
            nota.setVisibility(View.GONE) ;
            objeto_dan.setVisibility(View.GONE);
            imputado_nombre.setVisibility(View.GONE);
            imputado_dire.setVisibility(View.GONE);
            opcionSi.setVisibility(View.GONE);
            opcionNo.setVisibility(View.GONE);
            imputado.setVisibility(View.GONE);



        }else{
            preDenunciaDelitosExp.setVisibility(View.INVISIBLE);
            preDenunciaExp.setVisibility(View.VISIBLE);
            imputado_nombre.setVisibility(View.GONE);
            imputado_dire.setVisibility(View.GONE);
            tipodelito.setVisibility(View.GONE);
            hechos.setVisibility(View.GONE);
            lugarhechos.setVisibility(View.GONE);
            percance.setVisibility(View.GONE);
            narracion.setVisibility(View.GONE);
            testigos.setVisibility(View.GONE);
            objetos.setVisibility(View.GONE);
            nota.setVisibility(View.GONE) ;
            objeto_dan.setVisibility(View.GONE);
            enviar.setVisibility(View.GONE);
            testigoRobo.setVisibility(View.GONE);
            testigos_no.setVisibility(View.GONE);
            testigos_si.setVisibility(View.GONE);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id)
    {

        String delito = parent.getItemAtPosition(pos).toString();
        System.out.println("Delitoooooooooooooooooooo: "+ pos+ ".- "+ delito);
        switch (pos){
            case 1: newPreDenuncia.TipoDelito= 2; break; // Robo
            case 2: newPreDenuncia.TipoDelito= 4; break; // Daños
            case 3: newPreDenuncia.TipoDelito= 1; break; // General
        }
        this.delito= pos;
        tipoDelito(pos);
        //spinnerDenuncias.setText(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        hechos.setVisibility(View.GONE);
        lugarhechos.setVisibility(View.GONE);
        percance.setVisibility(View.GONE);
        narracion.setVisibility(View.GONE);
        testigos.setVisibility(View.GONE);
        objetos.setVisibility(View.GONE);
        nota.setVisibility(View.GONE) ;
        objeto_dan.setVisibility(View.GONE);
    }

    public void tipoDelito(int select){
        imputado_dire.setVisibility(View.GONE);
        if(select != 0){
            hechos.setVisibility(View.VISIBLE);
            lugarhechos.setVisibility(View.VISIBLE);
            percance.setVisibility(View.VISIBLE);
            narracion.setVisibility(View.VISIBLE);
            testigos.setVisibility(View.VISIBLE);
            if(select == 3){ //General
                objetos.setVisibility(View.GONE);
                nota.setVisibility(View.GONE) ;
                objeto_dan.setVisibility(View.GONE);
                testigoRobo.setVisibility(View.GONE);
                testigos_no.setVisibility(View.GONE);
                testigos_si.setVisibility(View.GONE);

                // enviar.layout
            }else if( select == 1 ){ //Robo
                testigos.setVisibility(View.GONE);
                testigoRobo.setVisibility(View.VISIBLE);
                testigos_no.setVisibility(View.VISIBLE);
                testigos_si.setVisibility(View.VISIBLE);
                objetos.setVisibility(View.VISIBLE);
                nota.setVisibility(View.VISIBLE) ;
                narracion.setVisibility(View.GONE);
                objeto_dan.setVisibility(View.GONE);
            }else { //Daños
                objetos.setVisibility(View.GONE);
                nota.setVisibility(View.GONE) ;
                objeto_dan.setVisibility(View.VISIBLE);
                testigoRobo.setVisibility(View.GONE);
                testigos_no.setVisibility(View.GONE);
                testigos_si.setVisibility(View.GONE);
            }
            enviar.setVisibility(View.VISIBLE);
            //12.+ enviar.setVisibility(View.VISIBLE);
        }

    }
    @OnClick({R.id.pre_denuncia_ubiacacion_layout, R.id.pre_denuncia_direccion_selecionada})
    public void onUbicacionClick() {
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "Se necesita el permiso para que las llamadas puedan ser bloqueadas correctamente.", LOCATION_REQUEST_CODE, perms);
        } else {
            Intent navigationIntent = new Intent(this, EvidenciaAnonimaSeleccionarUbicacionActivity.class);//this,NavigationFloatDrawerMainActivity.class);
            startActivityForResult(navigationIntent, EvidenciaAnonimaSeleccionarUbicacionActivity.REQUEST_CODE);
        }
    }

    @AfterPermissionGranted(LOCATION_REQUEST_CODE)
    public void LocationAccessGranted() {
        Intent navigationIntent = new Intent(this, EvidenciaAnonimaSeleccionarUbicacionActivity.class);
        startActivityForResult(navigationIntent, EvidenciaAnonimaSeleccionarUbicacionActivity.REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EvidenciaAnonimaSeleccionarUbicacionActivity.REQUEST_CODE &&
                resultCode == EvidenciaAnonimaSeleccionarUbicacionActivity.RESULT_OK) {
            direccionSeleccionada = data.getParcelableExtra(EvidenciaAnonimaSeleccionarUbicacionActivity.REQUEST_DATA_NAME);
            setTextDirecctionSelected(direccionSeleccionada);
            String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        }
    }

    public boolean validatePreDenuncia() {
        boolean valid = true;
        valid = editTextPreDenunciaNombre.validate() && valid;
        valid = editTextPreDenunciaEdad.validate()  && valid;
        valid = editTextPreDenunciaNacionalidad.validate() && valid;
        valid = editTextPreDenunciaDireccion.validate() && valid;
        valid = editTextPreDenunciaTel.validate() && valid;
        valid = editTextPreDenunciaCorreo.validate() && valid;
        valid = editTextPreDenunciaImputadoNombre.validate() && valid;

        return valid;
    }

    public void setTextDirecctionSelected(Address address) {
        StringBuilder strBuilder = new StringBuilder();
        if (address.getLocality() != null) {
            strBuilder.append(address.getLocality()).append(", ");
        }
        if (address.getAdminArea() != null) {
            strBuilder.append(address.getAdminArea()).append(' ');
        }
        if (address.getCountryName() != null) {
            strBuilder.append(address.getCountryName()).append('.');
        }
        preDenunciaUbiacacionLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.dialog_positive));
        preDenunciaDireccionSelecionada.setText(strBuilder.toString());
    }

    @OnClick(R.id.btn_pre_denuncia_continuar)
    public void onViewClicked() {
        System.out.println("Aki entre fer");
        if(this.validatePreDenuncia()){
            if(Integer.parseInt(this.editTextPreDenunciaEdad.getText().toString()) < 18 ||
                    Integer.parseInt(this.editTextPreDenunciaEdad.getText().toString()) > 140
            )
            {
                new MaterialDialog.Builder(this).content("Por favor ingrese una edad valida").show();
                return;
            }
            String impu ="";
            if(opcionSi.isChecked()){
                impu = this.editTextPreDenunciaImputadoNombre.getText().toString();
            }
            System.out.println("Estado? " + estadoc);
            newPreDenuncia =  new PreDenuncia(
                    this.editTextPreDenunciaNombre.getText().toString(), //Nombre del usuario
                    Integer.parseInt(this.editTextPreDenunciaEdad.getText().toString()), //Edad
                    this.editTextPreDenunciaCorreo.getText().toString(), // //Correo
                    impu //Persona que demanda
            );
            if(estadoc == 0){
                new MaterialDialog.Builder(this).content("Por favor ingrese un estado civil valido").show();
                return;
            }else{
                newPreDenuncia.id_estado_civil = estadoc;
            }

            //newPreDenuncia.EstadoCivil = this.editTextPreDenunciaEstadoCivil.getText().toString();
            newPreDenuncia.nacionalidad =this.editTextPreDenunciaNacionalidad.getText().toString();
            newPreDenuncia.domicilio = this.editTextPreDenunciaDireccion.getText().toString();
            if(direccionSeleccionada != null){
                newPreDenuncia.Latitud = direccionSeleccionada.getLatitude()+"";
                newPreDenuncia.Longitud = direccionSeleccionada.getLongitude()+"";
            }
            newPreDenuncia.telefono = this.editTextPreDenunciaTel.getText().toString();
            newPreDenuncia.correo = this.editTextPreDenunciaCorreo.getText().toString();
            newPreDenuncia.Imputado = this.editTextPreDenunciaImputadoNombre.getText().toString();
            newPreDenuncia.DireImputado = this.editTextPreDenunciaImputadoDireccion.getText().toString();
            mostrar(true); //Iniciamos con la pre denuncia

        } else {
            new MaterialDialog.Builder(this)
                    .content("Por favor introduzca todos los datos requeridos.")
                    .show();
        }
    }
    public boolean validateDenuncia() {
        boolean valid = true;
        valid = lugardeHechos.validate()  && valid;
        System.out.println("Delito?" + delito);
        //Caso de robo
        switch (delito){
            case 1: //robo
                valid = percanceHechos.validate() && valid;
                valid =objetoRobado.validate() && valid;
                break;
            case 2: //Caso de daños
                valid = narracionHechos.validate() && valid;
                valid = percanceHechos.validate() && valid;
                valid = objetosDañados.validate() && valid;
                valid = testigoHechos.validate() && valid;
                break;
            case 3:
                valid = testigoHechos.validate() && valid;
                valid = narracionHechos.validate() && valid;
        }
        return valid;

    }
    @OnClick(R.id.btn_pre_denuncia_enviar)
    public void onClick () {
        System.out.println("Aki entre en enviar Fer");
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(momentoHechos.getText().toString());
            if(this.validateDenuncia()){
                newPreDenuncia.momentoHechos = this.momentoHechos.getText().toString();
                newPreDenuncia.lugardeHechos =this.lugardeHechos.getText().toString();
                newPreDenuncia.percanceHechos = this.percanceHechos.getText().toString();
                newPreDenuncia.narracionHechos= this.narracionHechos.getText().toString();
                newPreDenuncia.testigoHechos = this.testigoHechos.getText().toString();

                newPreDenuncia.objetoRobado= this.objetoRobado.getText().toString();
                newPreDenuncia.testigo_presencial= this.testigo;

                newPreDenuncia.lesionesHechos = "";
                newPreDenuncia.armasHechos = 0;
                newPreDenuncia.atencionMedica = 0;
                newPreDenuncia.lugaratencionMedica= "";

                newPreDenuncia.objetosDaniados= this.objetosDañados.getText().toString();

                System.out.println("PREDENUNCIA GENERADA: " + newPreDenuncia);
                //pr
                processDialog.show();
                presenter.postPreDenuncia(newPreDenuncia);

            } else {
                new MaterialDialog.Builder(this)
                        .content("Por favor introduzca todos los datos requeridos.")
                        .show();
            }
        } catch (ParseException ex) {
            new MaterialDialog.Builder(this)
                    .content("Por favor introduzca una fecha valida.")
                    .show();
        }

    }

    @Override
    public void onPreDenunciaSucesss(String folio) {
        processDialog.dismiss();
        Context context = this;
        new MaterialDialog.Builder(this)
                .content("Tu predenuncia fue exitosamente registrada, tu numero de folio es: "+folio)
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                       Salir();
                    }
                })
                .show();
    }
    public void Salir(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onPreDenunciaError() {
        processDialog.dismiss();
        System.out.println("Error al insertar");
        new MaterialDialog.Builder(this)
                .content("Lo sentimos ocurrió un error, por favor inténtelo nuevamente.")
                .show();
    }

    @Override
    public void onConecctionError() {
        System.out.println("Error de concexión");
        processDialog.dismiss();
        new MaterialDialog.Builder(this)
                .content("Lo sentimos ocurrió un error, por favor inténtelo nuevamente.")
                .show();
    }
    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
        System.out.println("Prrakola");
        super.onBackPressed();
    }
}
