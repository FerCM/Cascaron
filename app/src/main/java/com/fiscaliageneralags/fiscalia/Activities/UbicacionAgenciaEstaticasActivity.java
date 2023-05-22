package com.fiscaliageneralags.fiscalia.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.fiscaliageneralags.fiscalia.Adapters.AgencyInfoWindowAdapter;
import com.fiscaliageneralags.fiscalia.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ERodriguezF on 16/02/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class UbicacionAgenciaEstaticasActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ubicacion_agencias_map_auto_text_view)
    MaterialBetterSpinner mSearchItem;

    private GoogleMap mMap;
    private int indexColor = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimations();
        setContentView(R.layout.activity_module_map_agencias_ubicaciones);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.ubicacion_agencias_main_map_view);
        mapFragment.getMapAsync(this);
        mSearchItem.setVisibility(View.GONE);
    }

    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
            super.onBackPressed();
        }

    private void setupWindowAnimations() {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            Fade fade = new Fade();
            fade.setDuration(1000);
            getWindow().setEnterTransition(fade);
        }
    }

    public void addMarkerstToMap(){
        addMarker(21.879226, -102.281118,"Vice Fiscalía de Investigación del Delito","Edificio Sede de la Vice Fiscalía de Investigación del Delito");
        addMarker(21.878875, -102.280817,"Investigación Especializada","Edificio Sede de Investigación Especializada");
        addMarker(21.848902, -102.314742,"Unidad Especializada en el Combate al Robo de Vehículo","Edificio Sede de la Unidad Especializada en el Combate al Robo de Vehículo");
        addMarker(22.145435, -102.285566,"Sub sede de la Fiscalía en el Municipio de Pabellón de Arteaga","Edificio Sede de la Fiscalía en el Municipio de Pabellón de Arteaga");
        addMarker(21.867681, -102.309366,"DIF ESTATAL","Edificio de DIF ESTATAL");
        addMarker(22.150366, -102.415876,"Secretaria de Seguridad Pública del Municipio de San José de Gracia","Edificio de la Secretaria de Seguridad Pública del Municipio de San José de Gracia");
        addMarker(22.225328, -102.323359,"Juzgados Mixtos en el Municipio de Rincón de Romos","Edificio de Juzgados Mixtos en el Municipio de Rincón de Romos");
        addMarker(21.957233, -102.345547,"Agencia del Ministerio Público en el Municipio de Jesús María","Edificio de la Agencia del Ministerio Público en el Municipio de Jesús María");
        addMarker(22.361496, -102.297010,"Seguridad Pública y Vialidad del Municipio de Cosío","Edificio de Seguridad Pública y Vialidad del Municipio de Cosío");
        addMarker(22.224268, -102.166940,"Presidencia Municipal de Tepezalá","Agencia en el interior de las oficinas de la Presidencia Municipal de Tepezalá");
        addMarker(21.844934, -102.723325,"Agencia del Ministerio Público del municipio de Calvillo","Edificio de la Agencia del Ministerio Público del Municipio de Calvillo");
        addMarker(22.225475, -102.322972,"Juzgados Mixtos en el Municipio de Rincón de Romos","Edificio de Juzgados Mixtos en el Municipio de Rincón de Romos");
        addMarker(22.071432, -102.270616,"Agencia del Ministerio Publico en el Municipio de San Francisco de los Romo","Edificio de la Agencia del Ministerio Publico en el Municipio de San Francisco de los Romo");
        addMarker(21.859370, -102.254512,"Seguridad Pública Municipal","Complejo de Seguridad Pública Municipal C4");
        addMarker(21.866833, -102.252607,"Centro de Justicia para las Mujeres","Edificio Sede del Centro de Justicia para las Mujeres");
        addMarker(21.879583, -102.281229,"Policía Ministerial","Edificio Sede de la Policía Ministerial");
    }

    public void addMarker(double lat, double lng, String title,String snippet){
        LatLng fiscalia = new LatLng(lat, lng);
        float color;
        switch (indexColor){
            case 1:
                color = 86;
                indexColor = 2;
                break;
            case 2:
                color = 25;
                indexColor = 3;
                break;
            case 3:
                color = 39;
                indexColor = 4;
                break;
            case 4:
                color = 198;
                indexColor = 1;
                break;
            default:
                color = 86;
                indexColor = 2;
                break;
        }

        mMap.addMarker(new MarkerOptions().position(fiscalia).title(title).snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(color)));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new AgencyInfoWindowAdapter(getLayoutInflater()));
        // For dropping a marker at a point on the Map
        LatLng fiscalia = new LatLng(21.880870, -102.282461);
        mMap.addMarker(new MarkerOptions().position(fiscalia).title("Fiscalía General del Estado").snippet("Edificio Sede de la Fiscalía General del Estado de Aguascalientes"));
        addMarkerstToMap();
        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(fiscalia).zoom(9).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        if (    ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                ) {
            mMap.setMyLocationEnabled(true);
        }
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(14).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
