package com.fiscaliageneralags.fiscalia.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.fiscaliageneralags.fiscalia.R;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ERodriguezF on 05/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class EvidenciaAnonimaSeleccionarUbicacionActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener{

    public static final int RESULT_OK = 1;
    public static final int RESULT_BAD = -1;
    public static final int REQUEST_CODE = 2165;
    public static final String REQUEST_DATA_NAME = "ADDRESS_SELECTED";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_select_button) Button selectButton;
    @BindView(R.id.evidencia_anonima_direccion_selecionada) TextView direccionTextView;

    private GoogleMap mMap;
    private Geocoder geocoder;
    private Address direccionSelecionada;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        geocoder = new Geocoder(this, Locale.getDefault());
        setContentView(R.layout.activity_ubicacion_seleccionar_anonima_evidencia);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        selectButton.setEnabled(false);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.evidencia_anonima_main_map_view);
        mapFragment.getMapAsync(this);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            Fade fade = new Fade();
            fade.setDuration(1000);
            getWindow().setEnterTransition(fade);
        }
    }

    @OnClick(R.id.toolbar_select_button)
    public void SelectedAdress(){
        if(direccionSelecionada != null ){
            Intent result  = new Intent();
            result.putExtra(REQUEST_DATA_NAME,direccionSelecionada);
            setResult(RESULT_OK,result);
            finish();
        }else{
            setResult(RESULT_BAD);
            finish();
        }
    }

    @OnClick(R.id.toolbar_back_btn)
    public void backToolbarButton(){
        setResult(RESULT_BAD);
        finish();
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        selectLocation(latLng);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //TODO: Implement Listener for Change location //mMap.OnMyLocationClickListener
        // For dropping a marker at a point on the Map
        LatLng fiscalia = new LatLng(21.880870, -102.282461);
        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(fiscalia).zoom(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        if (    ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                ) {
            mMap.setMyLocationEnabled(true);
            LocationServices.getFusedLocationProviderClient(getApplicationContext()).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null){
                        LatLng myPosition = new LatLng(location.getLatitude(),location.getLongitude());
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(myPosition).zoom(17).build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(latLng));
                        selectLocation(latLng);


                    }
                }
            });
        }
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
    }

    public void selectLocation(LatLng location){
        try {
            direccionSelecionada = retriveAdressFromLocation(location);
        } catch (IOException e) {
            Log.e("LOCATION_GEOCODER","Unable to find address from location.");
            return;
        }
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(location));
        setTextDirecctionSelected(direccionSelecionada);
        selectButton.setEnabled(true);
    }

    public void setTextDirecctionSelected(Address address){
        StringBuilder strBuilder = new StringBuilder();
        if(address.getMaxAddressLineIndex() > 0){
            strBuilder.append(address.getAddressLine(0)).append(' ');
        }
        strBuilder.append(address.getLocality()).append(' ');
        strBuilder.append(address.getAdminArea()).append(' ');
        strBuilder.append(address.getCountryName()).append('.');
        direccionTextView.setText(strBuilder.toString());
    }

    public Address retriveAdressFromLocation(LatLng location) throws IOException {
        List<Address>  lista = geocoder.getFromLocation(location.latitude,location.longitude,1);
        if(lista.size()>0){
            return lista.get(0);
        }
        else{
            throw new IOException();
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        selectLocation(latLng);
    }
}