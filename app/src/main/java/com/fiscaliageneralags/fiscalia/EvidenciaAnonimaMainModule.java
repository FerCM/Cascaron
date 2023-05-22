package com.fiscaliageneralags.fiscalia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fiscaliageneralags.fiscalia.Activities.EvidenciaAnonimaSeleccionarUbicacionActivity;
import com.fiscaliageneralags.fiscalia.Adapters.SearchLocationArrayAdapter;
import com.fiscaliageneralags.fiscalia.IPresenters.IEvidenciaAnonimaPresenter;
import com.fiscaliageneralags.fiscalia.IViews.IEvidenciaAnonimaView;
import com.fiscaliageneralags.fiscalia.Models.Municipios;
import com.fiscaliageneralags.fiscalia.Presenters.EvidenciaAnonimaPresenter;
import com.fiscaliageneralags.fiscalia.Utils.Interfaces.IConnectionErrorHandler;
import com.fiscaliageneralags.fiscalia.Utils.PostMETValidator;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;
import com.squareup.picasso.Picasso;
import com.tomergoldst.tooltips.ToolTip;
import com.tomergoldst.tooltips.ToolTipsManager;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.Unbinder;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;
import pl.aprilapps.easyphotopicker.EasyImage;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static java.security.AccessController.getContext;

public class EvidenciaAnonimaMainModule extends AppCompatActivity implements IEvidenciaAnonimaView, AdapterView.OnItemClickListener, EasyPermissions.PermissionCallbacks {
    private static final int EXTERNAL_STORAGE_REQUEST_CODE = 4655;
    private static final int LOCATION_REQUEST_CODE = 1651;

    @BindView(R.id.ev_image_selected)
    ImageView imageView3;
    @BindView(R.id.check_box_evidencia_anonima_anonima)
    CheckBox checkBoxEvidenciaAnonimaAnonima;
    @BindView(R.id.edit_text_evidencia_anonima_nombre)
    MaterialEditText editTextEvidenciaAnonimaNombre;
    @BindView(R.id.frame_evidencia_anonima_nombre)
    FrameLayout frameEvidenciaAnonimaNombre;
    @BindView(R.id.edit_text_evidencia_anonima_correo)
    MaterialEditText editTextEvidenciaAnonimaCorreo;
    @BindView(R.id.frame_evidencia_anonima_correo)
    FrameLayout frameEvidenciaAnonimaCorreo;
    @BindView(R.id.evidencia_anonima_direccion_selecionada)
    TextView evidenciaAnonimaDireccionSelecionada;
    @BindView(R.id.edit_text_evidencia_anonima_colonia)
    MaterialEditText editTextEvidenciaAnonimaColonia;
    @BindView(R.id.edit_text_evidencia_anonima_calle)
    MaterialEditText editTextEvidenciaAnonimaCalle;
    @BindView(R.id.edit_text_evidencia_anonima_num_ext)
    MaterialEditText editTextEvidenciaAnonimaNumExt;
    @BindView(R.id.edit_text_evidencia_anonima_num_int)
    MaterialEditText editTextEvidenciaAnonimaNumInt;
    @BindView(R.id.evidencia_anonima_ubiacacion_layout)
    ConstraintLayout evidenciaAnonimaUbiacacionLayout;
    @BindView(R.id.edit_text_evidencia_anonima_narracion)
    MaterialEditText editTextEvidenciaAnonimaNarracion;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.edit_text_evidencia_anonima_municipio)
    MaterialEditText editTextEvidenciaAnonimaMunicipio;
    @BindView(R.id.better_spinner_evidencia_anonima_municipio)
    MaterialBetterSpinner betterSpinnerEvidenciaAnonimaMunicipio;
    @BindView(R.id.frame_evidencia_anonima_municipio)
    FrameLayout frameEvidenciaAnonimaMunicipio;
    @BindView(R.id.edit_text_evidencia_anonima_estado)
    MaterialEditText editTextEvidenciaAnonimaEstado;
    @BindView(R.id.frame_evidencia_anonima_estado)
    FrameLayout frameEvidenciaAnonimaEstado;
    @BindView(R.id.frame_evidencia_anonima_catalogo_municipio)
    FrameLayout frameEvidenciaAnonimaCatalogoMunicipio;
    @BindView(R.id.rootView)
    ConstraintLayout rootView;
    @BindView(R.id.frame_evidencia_anonima_colonia)
    FrameLayout frameEvidenciaAnonimaColonia;
    @BindView(R.id.frame_evidencia_anonima_calle)
    FrameLayout frameEvidenciaAnonimaCalle;
    @BindView(R.id.frame_evidencia_anonima_num_ext)
    FrameLayout frameEvidenciaAnonimaNumExt;
    @BindView(R.id.frame_evidencia_anonima_num_int)
    FrameLayout frameEvidenciaAnonimaNumInt;

    private Unbinder unbinder;

    private IEvidenciaAnonimaPresenter presenter;
    private Address direccionSeleccionada;
    private File selectedImage;
    private boolean validationForSubmit = false;
    private MaterialDialog processDialog;
    private Municipios selectedMun;
    private ToolTipsManager toolTipsManager = new ToolTipsManager();
    private LatLng LocationPhone = null;
    private String phoneNumber = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evidencia_anonima_main_module);
     //   View rootView inflate(R.layout.activity_evidencia_anonima_main_module, container, false);
        String[] perms1 = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        if (!EasyPermissions.hasPermissions(this, perms1)) {
            EasyPermissions.requestPermissions(this, "Se necesita el permiso para que las llamadas puedan ser bloqueadas correctamente.", LOCATION_REQUEST_CODE, perms1);
        }
        unbinder = ButterKnife.bind(this);
        presenter = new EvidenciaAnonimaPresenter(this, this);
        addPostMETValidatorForLength(editTextEvidenciaAnonimaNarracion, 20, 3000);
        processDialog = new MaterialDialog.Builder(this)
                .content("Enviando Evidencia ...")
                .progress(true, 0)
                .build();
        processDialog.setCanceledOnTouchOutside(false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new String[]{""});
        betterSpinnerEvidenciaAnonimaMunicipio.setAdapter(adapter);
        presenter.getMunicipios();
        this.loadInitialAnimation();
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationServices.getFusedLocationProviderClient(this.getApplicationContext()).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            System.out.println("Aki??");
                            LocationPhone = new LatLng(location.getLatitude(), location.getLongitude());
                        }
                    }
                });
            }
        }
        perms = new String[]{Manifest.permission.READ_PHONE_STATE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            TelephonyManager phoneMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
            phoneNumber = phoneMgr.getLine1Number();
        }

    }

    /**
     * Handle click for adding the location
     */
    @OnClick({R.id.evidencia_anonima_ubiacacion_layout, R.id.evidencia_anonima_direccion_selecionada})
    public void onUbicacionClick() {
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "Se necesita el permiso para que las llamadas puedan ser bloqueadas correctamente.", LOCATION_REQUEST_CODE, perms);
        } else {
            Intent navigationIntent = new Intent(this, EvidenciaAnonimaSeleccionarUbicacionActivity.class);//this,NavigationFloatDrawerMainActivity.class);
            startActivityForResult(navigationIntent, EvidenciaAnonimaSeleccionarUbicacionActivity.REQUEST_CODE);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                Intent navigationIntent = new Intent(this, EvidenciaAnonimaSeleccionarUbicacionActivity.class);//this,NavigationFloatDrawerMainActivity.class);
                startActivityForResult(navigationIntent, EvidenciaAnonimaSeleccionarUbicacionActivity.REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                Intent navigationIntent = new Intent(this, EvidenciaAnonimaSeleccionarUbicacionActivity.class);//this,NavigationFloatDrawerMainActivity.class);
                startActivityForResult(navigationIntent, EvidenciaAnonimaSeleccionarUbicacionActivity.REQUEST_CODE);
                break;
        }
    }


    @OnFocusChange({
            R.id.edit_text_evidencia_anonima_municipio,
            R.id.edit_text_evidencia_anonima_calle,
            R.id.edit_text_evidencia_anonima_colonia,
            R.id.edit_text_evidencia_anonima_correo,
            R.id.edit_text_evidencia_anonima_estado,
            R.id.edit_text_evidencia_anonima_narracion,
            R.id.better_spinner_evidencia_anonima_municipio,
            R.id.edit_text_evidencia_anonima_nombre,
            R.id.edit_text_evidencia_anonima_num_ext,
            R.id.edit_text_evidencia_anonima_num_int,
    })
    public void focusedChanged(View view) {
        if (view.isFocused()) {
            toolTipsManager.dismissAll();
            String mensaje =
                    view.getId() == R.id.edit_text_evidencia_anonima_municipio ||
                            view.getId() == R.id.better_spinner_evidencia_anonima_municipio
                            ? "Municipio donde ocurrió el hecho delictivo."
                            : view.getId() == R.id.edit_text_evidencia_anonima_correo
                            ? "Correo electrónico con el cual se pueda dar un mejor seguimiento a la evidencia"
                            : view.getId() == R.id.edit_text_evidencia_anonima_estado
                            ? "Estado donde ocurrió el hecho delictivo."
                            : view.getId() == R.id.edit_text_evidencia_anonima_narracion
                            ? "Descripción/narración de los echos o actos delictivos."
                            : view.getId() == R.id.check_box_evidencia_anonima_anonima
                            ? "Active esta casilla si desea enviar su evidencia como anónimo."
                            : null;
            if (mensaje != null) {
                ToolTip.Builder builder = new ToolTip.Builder(
                        this,
                        view,
                        rootView,
                        mensaje,
                        R.id.better_spinner_evidencia_anonima_municipio == view.getId()? ToolTip.POSITION_BELOW :ToolTip.POSITION_ABOVE);
                builder.setBackgroundColor(getResources().getColor(R.color.colorTooltip));
                toolTipsManager.show(builder.build());
            }
        }
    }

    /**
     * Handle checkbox change and animate accordingly
     * @param view
     */
    @OnCheckedChanged(R.id.check_box_evidencia_anonima_anonima)
    public void onChekedChanged(CheckBox view) {
        if (view.isChecked()) {
            frameEvidenciaAnonimaCorreo.animate()
                    .alpha(0.5f)
                    .translationY(-frameEvidenciaAnonimaCorreo.getHeight())
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaCorreo.setVisibility(View.GONE);
                        }
                    })
                    .start();
            frameEvidenciaAnonimaCorreo.clearAnimation();
            frameEvidenciaAnonimaNombre.animate()
                    .alpha(0.5f)
                    .translationY(-frameEvidenciaAnonimaNombre.getHeight())
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaNombre.setVisibility(View.GONE);
                        }
                    })
                    .start();
            frameEvidenciaAnonimaNombre.clearAnimation();
        } else {
            frameEvidenciaAnonimaCorreo.animate()
                    .alpha(1.0f)
                    .translationY(0)
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaCorreo.setVisibility(View.VISIBLE);

                        }
                    })
                    .start();
            frameEvidenciaAnonimaCorreo.clearAnimation();
            frameEvidenciaAnonimaNombre.animate()
                    .alpha(1.0f)
                    .translationY(0)
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaNombre.setVisibility(View.VISIBLE);
                        }
                    })
                    .start();
            frameEvidenciaAnonimaNombre.clearAnimation();
        }
    }

    /**
     * Handle checkbox change and animate accordingly
     * @param view
     */
    @OnCheckedChanged(R.id.check_box_direccion_especificar)
    public void onChekedDireccionChanged(CheckBox view) {
        if (!view.isChecked()) {
            frameEvidenciaAnonimaColonia.animate()
                    .alpha(0.5f)
                    .translationY(-frameEvidenciaAnonimaColonia.getHeight())
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaColonia.setVisibility(View.GONE);
                        }
                    })
                    .start();
            frameEvidenciaAnonimaColonia.clearAnimation();
            frameEvidenciaAnonimaCalle.animate()
                    .alpha(0.5f)
                    .translationY(-frameEvidenciaAnonimaCalle.getHeight())
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaCalle.setVisibility(View.GONE);
                        }
                    })
                    .start();
            frameEvidenciaAnonimaCalle.clearAnimation();
            frameEvidenciaAnonimaNumInt.animate()
                    .alpha(0.5f)
                    .translationY(-frameEvidenciaAnonimaNumInt.getHeight())
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaNumInt.setVisibility(View.GONE);
                        }
                    })
                    .start();
            frameEvidenciaAnonimaNumInt.clearAnimation();
            frameEvidenciaAnonimaNumExt.animate()
                    .alpha(0.5f)
                    .translationY(-frameEvidenciaAnonimaNumExt.getHeight())
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaNumExt.setVisibility(View.GONE);
                        }
                    })
                    .start();
            frameEvidenciaAnonimaNumExt.clearAnimation();
        } else {
            frameEvidenciaAnonimaColonia.animate()
                    .alpha(1.0f)
                    .translationY(0)
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaColonia.setVisibility(View.VISIBLE);

                        }
                    })
                    .start();
            frameEvidenciaAnonimaColonia.clearAnimation();
            frameEvidenciaAnonimaCalle.animate()
                    .alpha(1.0f)
                    .translationY(0)
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaCalle.setVisibility(View.VISIBLE);
                        }
                    })
                    .start();
            frameEvidenciaAnonimaCalle.clearAnimation();
            frameEvidenciaAnonimaNumExt.animate()
                    .alpha(1.0f)
                    .translationY(0)
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaNumExt.setVisibility(View.VISIBLE);

                        }
                    })
                    .start();
            frameEvidenciaAnonimaNumExt.clearAnimation();
            frameEvidenciaAnonimaNumInt.animate()
                    .alpha(1.0f)
                    .translationY(0)
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationEnd(animation);
                            frameEvidenciaAnonimaNumInt.setVisibility(View.VISIBLE);
                        }
                    })
                    .start();
            frameEvidenciaAnonimaNumInt.clearAnimation();
        }
    }

    /**
     * Easy image permission to help choose image properly
     * @param view
     */
    @AfterPermissionGranted(EXTERNAL_STORAGE_REQUEST_CODE)
    @OnClick({R.id.ev_image_selected, R.id.floatingActionButton})
    public void onViewClicked(View view) {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(Objects.requireNonNull(this), perms)) {
            EasyImage.openChooserWithGallery(this, "Ingresar nueva imagen.", 0);
        } else {
            EasyPermissions.requestPermissions(this, "Es necesario que nos permita acceder a su cámara y guardar las imágenes.",
                    EXTERNAL_STORAGE_REQUEST_CODE, perms);
        }
    }

    @OnClick(R.id.btn_evidencia_anonima_enviar)
    public void onViewClicked() {
        System.out.println("Locación?" + LocationPhone);
        if (isValidEvidenciaAnonima()) {
            processDialog.show();
            presenter.postEvidenciaAnonima(
                    selectedImage,
                    editTextEvidenciaAnonimaCorreo.getText().toString(),
                    editTextEvidenciaAnonimaNombre.getText().toString(),
                    direccionSeleccionada,
                    editTextEvidenciaAnonimaNarracion.getText().toString(),
                    editTextEvidenciaAnonimaCalle.getText().toString(),
                    editTextEvidenciaAnonimaColonia.getText().toString(),
                    editTextEvidenciaAnonimaNumInt.getText().toString(),
                    editTextEvidenciaAnonimaNumExt.getText().toString(),
                    selectedMun != null ? selectedMun.Cve_mun : -1,
                    selectedMun == null || selectedMun.Cve_mun == -1 ? editTextEvidenciaAnonimaMunicipio.getText().toString() : selectedMun.Municipio,
                    selectedMun == null || selectedMun.Cve_mun == -1 ? editTextEvidenciaAnonimaEstado.getText().toString() : "Aguascalientes",
                    this.phoneNumber,
                    LocationPhone != null ? this.LocationPhone.latitude + "": "",
                    LocationPhone != null ? this.LocationPhone.longitude + "": ""
            );
        }
        else{
            scrollView.smoothScrollTo(0,imageView3.getTop());
        }
    }

    /**
     * Item click to handle animation of components for another state.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Municipios item = (Municipios) parent.getItemAtPosition(position);
        selectedMun = item;
        if (item.Cve_mun != -1) {
            if (frameEvidenciaAnonimaMunicipio.getVisibility() == View.VISIBLE) {
                frameEvidenciaAnonimaMunicipio.animate()
                        .alpha(0.5f)
                        .translationY(-frameEvidenciaAnonimaMunicipio.getHeight())
                        .setDuration(700)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                frameEvidenciaAnonimaMunicipio.setVisibility(View.GONE);
                            }
                        })
                        .start();
                frameEvidenciaAnonimaMunicipio.clearAnimation();
                frameEvidenciaAnonimaEstado.animate()
                        .alpha(0.5f)
                        .translationY(-frameEvidenciaAnonimaEstado.getHeight())
                        .setDuration(700)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                frameEvidenciaAnonimaEstado.setVisibility(View.GONE);
                            }
                        })
                        .start();
                frameEvidenciaAnonimaEstado.clearAnimation();
            }
        } else {
            if (frameEvidenciaAnonimaEstado.getVisibility() == View.GONE) {
                frameEvidenciaAnonimaMunicipio.animate()
                        .alpha(1.0f)
                        .translationY(0)
                        .setDuration(700)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                super.onAnimationEnd(animation);
                                frameEvidenciaAnonimaMunicipio.setVisibility(View.VISIBLE);

                            }
                        })
                        .start();
                frameEvidenciaAnonimaMunicipio.clearAnimation();
                frameEvidenciaAnonimaEstado.animate()
                        .alpha(1.0f)
                        .translationY(0)
                        .setDuration(700)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                super.onAnimationEnd(animation);
                                frameEvidenciaAnonimaEstado.setVisibility(View.VISIBLE);
                            }
                        })
                        .start();
                frameEvidenciaAnonimaEstado.clearAnimation();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * Handle the image picked with Easy Image or handle the selected address to send in the new anonymous evidence
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Context context= this;
        if (requestCode == EvidenciaAnonimaSeleccionarUbicacionActivity.REQUEST_CODE &&
                resultCode == EvidenciaAnonimaSeleccionarUbicacionActivity.RESULT_OK) {
            direccionSeleccionada = data.getParcelableExtra(EvidenciaAnonimaSeleccionarUbicacionActivity.REQUEST_DATA_NAME);
            boolean locationAdded = true;
            setTextDirecctionSelected(direccionSeleccionada);
            String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            if (EasyPermissions.hasPermissions(this, perms)) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    LocationServices.getFusedLocationProviderClient(this.getApplicationContext()).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                LocationPhone = new LatLng(location.getLatitude(), location.getLongitude());
                            }
                        }
                    });
                }
            }
        } else {
            EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
                @Override
                public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                }
                @Override
                public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                    selectedImage = imageFile;
                    Picasso.with(context).load(selectedImage).transform(new CropSquareTransformation()).into(imageView3);
                }
                @Override
                public void onCanceled(EasyImage.ImageSource source, int type) {
                    if (source == EasyImage.ImageSource.CAMERA) {
                        File photoFile = EasyImage.lastlyTakenButCanceledPhoto(context);
                        if (photoFile != null) photoFile.delete();
                    }
                }
            });
        }
        buildInitialTooltips();
    }

    @Override
    public void onPostEvidenciaAnonimaSuccess() {
        processDialog.dismiss();
        Context context = this;
        new MaterialDialog.Builder(this)
                .content("La Evidencia Anónima fue enviada con éxito, agradecemos su cooperación.")
                .autoDismiss(true)
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        System.out.println("Aki?");
                        Salir();
                    }
                })
                .show();
    }
    public void Salir(){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onPostEvidenciaAnonimaError() {
        processDialog.dismiss();
        new MaterialDialog.Builder(this)
                .content("La Evidencia Anónima fue enviada con éxito, agradecemos su cooperación.")
                .autoDismiss(true)
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        System.out.println("Aki?");
                        Salir();
                    }
                })
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onGetMunicipiosSuccess(Municipios[] municipios) {
        List<Municipios> mun = new ArrayList<Municipios>(Arrays.asList(municipios));
        mun.add(new Municipios(-1, "0", "0", "Otro"));
        setupSeachModule(mun.toArray(new Municipios[0]));
    }

    @Override
    public void onConecctionError() {
        processDialog.dismiss();
        Context context = this;
        new MaterialDialog.Builder(this)
                .content("Error al enviar evidencia anónima, intentelo más tarde")
                .autoDismiss(true)
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Intent intent = new Intent (context, MainActivity.class);
                        startActivityForResult(intent, 0);
                    }
                })
                .show();
    }

    /**
     * Add validation min and max length to {@link MaterialEditText} object
     * @param editText
     * @param minCharacters
     * @param maxCharacters
     */
    public void addPostMETValidatorForLength(@NonNull MaterialEditText editText, final int minCharacters, final int maxCharacters) {
        Context context= this;
        editText.addValidator(new PostMETValidator(editText, "Error de general",
                new METValidator("Error General") {
                    @Override
                    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                        return text.length() >= minCharacters && text.length() <= maxCharacters;
                    }
                }) {
            @Override
            public void postValidation(@NonNull MaterialEditText caller, @NonNull CharSequence text) {
                caller.setMinCharacters(minCharacters);
                caller.setMaxCharacters(maxCharacters);
                if (text.length() < maxCharacters) {
                    setErrorMessage(String.format(getString(R.string.sign_up_validation_error_generic_minimum_char), minCharacters));
                    if (validationForSubmit)
                        new MaterialDialog.Builder(context)
                                .content(String.format(getString(R.string.sign_up_validation_error_generic_minimum_char), minCharacters))
                                .show();
                }
                if (text.length() > maxCharacters) {
                    setErrorMessage(String.format(getString(R.string.sign_up_validation_error_generic_max_char), maxCharacters));
                    if (validationForSubmit)
                        new MaterialDialog.Builder(context)
                                .content(String.format(getString(R.string.sign_up_validation_error_generic_max_char), maxCharacters))
                                .show();
                }
            }
        });
    }

    /**
     * Get and Set the direction of the {@link Address} object into the corresponding field.
     * @param address
     */
    public void setTextDirecctionSelected(Address address) {
        StringBuilder strBuilder = new StringBuilder();
        if (address.getMaxAddressLineIndex() > 0 && address.getAddressLine(0) != null) {
            strBuilder.append(address.getAddressLine(0)).append(' ');
            if (editTextEvidenciaAnonimaCalle.getText().toString().isEmpty())
                editTextEvidenciaAnonimaCalle.setText(address.getAddressLine(0));
        }
        if (address.getLocality() != null)
            strBuilder.append(address.getLocality()).append(", ");
        if (address.getAdminArea() != null)
            strBuilder.append(address.getAdminArea()).append(' ');
        if (address.getCountryName() != null)
            strBuilder.append(address.getCountryName()).append('.');
        evidenciaAnonimaUbiacacionLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.dialog_positive));
        evidenciaAnonimaDireccionSelecionada.setText(strBuilder.toString());
    }

    public boolean isValidEvidenciaAnonima() {
        return editTextEvidenciaAnonimaNarracion.validate();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setupSeachModule(Municipios[] municipios) {
        SearchLocationArrayAdapter searchLocationArrayAdapter = new SearchLocationArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, municipios);
        betterSpinnerEvidenciaAnonimaMunicipio.setAdapter(searchLocationArrayAdapter);
        betterSpinnerEvidenciaAnonimaMunicipio.setVisibility(View.VISIBLE);
        betterSpinnerEvidenciaAnonimaMunicipio.setOnItemClickListener(this);
        betterSpinnerEvidenciaAnonimaMunicipio.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                betterSpinnerEvidenciaAnonimaMunicipio.clearFocus();
            }
        });
    }

    private void loadInitialAnimation() {
        frameEvidenciaAnonimaCorreo.animate()
                .alpha(0.5f)
                .translationY(-frameEvidenciaAnonimaCorreo.getHeight())
                .setDuration(50)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        frameEvidenciaAnonimaCorreo.setVisibility(View.GONE);
                    }
                })
                .start();
        frameEvidenciaAnonimaCorreo.clearAnimation();
        frameEvidenciaAnonimaNombre.animate()
                .alpha(0.5f)
                .translationY(-frameEvidenciaAnonimaNombre.getHeight())
                .setDuration(50)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        frameEvidenciaAnonimaNombre.setVisibility(View.GONE);
                    }
                })
                .start();
        frameEvidenciaAnonimaNombre.clearAnimation();
        frameEvidenciaAnonimaEstado.animate()
                .alpha(0.5f)
                .translationY(-frameEvidenciaAnonimaEstado.getHeight())
                .setDuration(50)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        frameEvidenciaAnonimaEstado.setVisibility(View.GONE);
                    }
                })
                .start();
        frameEvidenciaAnonimaEstado.clearAnimation();
        frameEvidenciaAnonimaMunicipio.animate()
                .alpha(0.5f)
                .translationY(-frameEvidenciaAnonimaMunicipio.getHeight())
                .setDuration(50)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        frameEvidenciaAnonimaMunicipio.setVisibility(View.GONE);
                    }
                })
                .start();
        frameEvidenciaAnonimaMunicipio.clearAnimation();
        frameEvidenciaAnonimaColonia.animate()
                .alpha(0.5f)
                .translationY(-frameEvidenciaAnonimaColonia.getHeight())
                .setDuration(50)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        frameEvidenciaAnonimaColonia.setVisibility(View.GONE);
                    }
                })
                .start();
        frameEvidenciaAnonimaColonia.clearAnimation();
        frameEvidenciaAnonimaCalle.animate()
                .alpha(0.5f)
                .translationY(-frameEvidenciaAnonimaCalle.getHeight())
                .setDuration(50)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        frameEvidenciaAnonimaCalle.setVisibility(View.GONE);
                    }
                })
                .start();
        frameEvidenciaAnonimaCalle.clearAnimation();
        frameEvidenciaAnonimaNumInt.animate()
                .alpha(0.5f)
                .translationY(-frameEvidenciaAnonimaNumInt.getHeight())
                .setDuration(50)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        frameEvidenciaAnonimaNumInt.setVisibility(View.GONE);
                    }
                })
                .start();
        frameEvidenciaAnonimaNumInt.clearAnimation();
        frameEvidenciaAnonimaNumExt.animate()
                .alpha(0.5f)
                .translationY(-frameEvidenciaAnonimaNumExt.getHeight())
                .setDuration(50)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        frameEvidenciaAnonimaNumExt.setVisibility(View.GONE);
                    }
                })
                .start();
        frameEvidenciaAnonimaNumExt.clearAnimation();
    }

    private void buildInitialTooltips() {
        ToolTip.Builder builder = new ToolTip.Builder(
               this,
                imageView3,
                rootView,
                "Imagen de la evidencia a enviar. (Opcional)",
                ToolTip.POSITION_BELOW);
        builder.setBackgroundColor(getResources().getColor(R.color.colorTooltip));
        toolTipsManager.show(builder.build());
        builder = new ToolTip.Builder(
                this,
                evidenciaAnonimaUbiacacionLayout,
                rootView,
                "De click aquí para agregar la localización de los hechos. (Opcional)",
                ToolTip.POSITION_ABOVE);
        builder.setBackgroundColor(getResources().getColor(R.color.colorTooltip));
        toolTipsManager.show(builder.build());
    }
    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
        System.out.println("Prrakola");
        super.onBackPressed();
    }
}
