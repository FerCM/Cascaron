package com.fiscaliageneralags.fiscalia.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fiscaliageneralags.fiscalia.Adapters.AgencyInfoWindowAdapter;
import com.fiscaliageneralags.fiscalia.Adapters.SearchLocationArrayAdapter;
import com.fiscaliageneralags.fiscalia.IPresenters.IUbicacionAgenciasModulosParaDenunciaPresenter;
import com.fiscaliageneralags.fiscalia.IViews.IUbicacionAgenciasModulosParaDenunciaView;
import com.fiscaliageneralags.fiscalia.Models.EdificiosParaDenuncia;
import com.fiscaliageneralags.fiscalia.Models.Municipios;
import com.fiscaliageneralags.fiscalia.Presenters.UbicacionAgenciasModulosParaDenunciaPresenter;
import com.fiscaliageneralags.fiscalia.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ERodriguezF on 21/12/2017.
 * @author ERodriguezF
 * @version 1.18
 */

public class UbicacionAgenciasModulosParaDenunciaActivity extends AppCompatActivity implements IUbicacionAgenciasModulosParaDenunciaView, OnMapReadyCallback,AdapterView.OnItemClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ubicacion_agencias_map_auto_text_view)
    MaterialBetterSpinner mSearchItem;

    private GoogleMap mMap;
    private MaterialDialog processDialog;
    private IUbicacionAgenciasModulosParaDenunciaPresenter presenter;
    private int indexColor = 0;
    private UbicacionAgenciasModulosParaDenunciaActivity activity;
    int map;
    /**
     * Load initial configuration.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setupWindowAnimations();
        map = getIntent().getExtras().getInt("map");
        System.out.println("Akitoy: " + map);
        setContentView(R.layout.activity_module_map_agencias_ubicaciones);
        ButterKnife.bind(this);
        processDialog = new MaterialDialog.Builder(this)
                .content("Cargando, por favor espere.")
                .progress(true, 0)
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        activity.finish();
                    }
                })
                .build();
        processDialog.setCanceledOnTouchOutside(false);
        processDialog.show();
        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.ubicacion_agencias_main_map_view);
        mapFragment.getMapAsync(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new String[]{""});
        mSearchItem.setAdapter(adapter);
        mSearchItem.setHint(R.string.ubi_age_municipio_search_hint);
        mSearchItem.setFloatingLabelText(getResources().getText(R.string.ubi_age_municipio_search_hint));
        mSearchItem.setVisibility(View.GONE);
        presenter =  new UbicacionAgenciasModulosParaDenunciaPresenter(this,this);
        presenter.getMunicipios();
        // Create a new thread inside your Actvity.
        Thread thread = new Thread() {

            @Override
            public void run() {
                // Block this thread for 2 seconds.
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Log.d("THREAD_ERROR:","Thread Failed to Sleep");
                }
                // After sleep finished blocking, create a Runnable to run on the UI Thread.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(mMap == null){
                            Toast.makeText(activity,"Los servicios de Google play necesitan actualizarse para utilizar esta funcionalidad.",Toast.LENGTH_LONG).show();
                            activity.finish();
                        }
                    }
                });
            }

        };
        // Don't forget to start the thread.
        thread.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setupSeachModule(Municipios[] municipios){
        SearchLocationArrayAdapter searchLocationArrayAdapter =  new SearchLocationArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,municipios);
        mSearchItem.setAdapter(searchLocationArrayAdapter);
        mSearchItem.setVisibility(View.VISIBLE);
        mSearchItem.setOnItemClickListener(this);
        mSearchItem.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                mSearchItem.clearFocus();
            }
        });
    }

    private void setupWindowAnimations() {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            Fade fade = new Fade();
            fade.setDuration(1000);
            getWindow().setEnterTransition(fade);
        }
    }

    private float getColorForPin(int cve){
        float color;
        switch (cve){
            case 1: //Aguascalientes
                color = 0;
                break;
            case 2: //Calvillo
                color = 25;
                break;
            case 4: //Rincon de Romos
                color = 39;
                break;
            case 5: //Jesus María
                color = 198;
                break;
            case 7: //Cosio
                color = 86;
                break;
            case 8: //San Fco. Romo
                color = 330;
                break;
            case 18: //Asientos
                color = 98;
                break;
            case 66: //Tepezala
                color = 240;
                break;
            case 85: //El llano
                color = 60;
                break;
            default: //San José
                color = 270;
                break;
        }
        return color;
    }

    public void  addPinsToMap(EdificiosParaDenuncia[] edificiosParaDenuncia){
        if (mMap!=null) {
            mMap.clear();
            for (EdificiosParaDenuncia edificioParaDenuncia : edificiosParaDenuncia) {
                String snip ="";
                if(edificioParaDenuncia.Horario_Denuncia!= "null" && edificioParaDenuncia.Horario_Denuncia != null){
                    snip= edificioParaDenuncia.Horario_Denuncia;
                    if(edificioParaDenuncia.Telefono!= "null" && edificioParaDenuncia.Telefono != null){
                        snip=snip+ " \n\t Telefono: " + edificioParaDenuncia.Telefono;

                    }
                }else{
                    if(edificioParaDenuncia.Telefono!= "null" && edificioParaDenuncia.Telefono != null){
                        snip= edificioParaDenuncia.Telefono;

                    }
                }
                Marker marker = mMap.addMarker(
                        new MarkerOptions()
                                .title(edificioParaDenuncia.Edificio)
                                .snippet(snip)
                                .icon(BitmapDescriptorFactory.defaultMarker(getColorForPin(edificioParaDenuncia.Cve_Mun)))
                                .position(new LatLng(edificioParaDenuncia.getLatitud(), edificioParaDenuncia.getLongitud()))
                );
                marker.setTag(edificioParaDenuncia);
            }
        }
        else{
            Toast.makeText(this,"Los servicios de Google play necesitan actualizarse para utilizar esta funcionalidad.",Toast.LENGTH_LONG).show();
            this.finish();
        }
    }

    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
        super.onBackPressed();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new AgencyInfoWindowAdapter(getLayoutInflater()));
        // For dropping a marker at a point on the Map
        LatLng fiscalia = new LatLng(21.880870, -102.282461);
        mMap.addMarker(new MarkerOptions().position(fiscalia).title("Fiscalía General del Estado").snippet("Fiscalía General del Estado de Aguascalientes"));
        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(fiscalia).zoom(9).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        if(map==1){
            presenter.getTodasLasAgencias();
        }else{
            presenter.getEdificiosParaDenunciar();
        }

        if (    ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Municipios item =  (Municipios) adapterView.getItemAtPosition(position);
        LatLng myPosition = new LatLng(item.GetLatitud(),item.GetLongitud());
        CameraPosition cameraPosition = new CameraPosition.Builder().target(myPosition).zoom(position==0?11:14).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onGetEdificiosParaDenunciar(EdificiosParaDenuncia[] edificiosParaDenuncias) {
        addPinsToMap(edificiosParaDenuncias);
        processDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        processDialog.dismiss();
    }

    public void onGetTodasLasAgenciasSuccess(EdificiosParaDenuncia[] edificiosParaDenuncias) {
        addPinsToMap(edificiosParaDenuncias);
        processDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        processDialog.dismiss();
    }
    @Override
    public void onGetMunicipiosSuccess(Municipios[] municipios) {
        setupSeachModule(municipios);
    }

    @Override
    public void onConecctionError() {
        processDialog.dismiss();
        Toast.makeText(this,"Lo sentimos ha ocurrido un error, por favor verifique su conexión a Internet.",Toast.LENGTH_LONG).show();
        this.finish();
    }
}
