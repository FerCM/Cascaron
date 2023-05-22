package com.fiscaliageneralags.fiscalia.ui.Amber;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.fiscaliageneralags.fiscalia.IPresenters.Presenter;
import com.fiscaliageneralags.fiscalia.IViews.ViewCarpeta;
import com.fiscaliageneralags.fiscalia.Presenters.CarpetaPresenter;
import com.fiscaliageneralags.fiscalia.R;
import com.fiscaliageneralags.fiscalia.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

import static com.fiscaliageneralags.fiscalia.R.color.colorPrimary;

public class AmberFragment extends Fragment implements ViewCarpeta {
    ViewPager viewPager;
    AmberAdapter adapter;
    Integer colors [] = null;
    private List<Model> amberList = new ArrayList<>();
    private AmberViewModel slideshowViewModel;
    private Presenter carpetaServicio;
    TextView disable;
    ViewPager viewPage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Unbinder unbinder;

    public static AmberFragment newInstance() {
        AmberFragment fragment = new AmberFragment();
        Bundle args = new Bundle();
        //Put aguments to pass to the fragment
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(AmberViewModel.class);
        View root = inflater.inflate(R.layout.fragment_amber, container, false);
        toolbar =  getActivity().findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(colorPrimary));
        viewPager = root.findViewById(R.id.viewPager);
        disable =  root.findViewById(R.id.title);
        disable.setVisibility(View.INVISIBLE);
       // viewPager.setVisibility(View.INVISIBLE);

        ///SERVICIOOOOOOOOOOOOO
        carpetaServicio = new CarpetaPresenter(this);
        carpetaServicio.getAlertaAmber(1);
       // insertAmber();
        return root;
    }

    @Override
    public void onCarpetaSucess(String amber) {
        System.out.println("Entre en carpeta?");
        boolean pintar = false;
        System.out.println("aki perros");
        if(amber.length()== 0 || amber.isEmpty()){
            System.out.println("aki? ");
        }else {
            try {
                JSONArray jsonarray = new JSONArray(amber);
                System.out.println("Longitud? " + jsonarray.length());
                if(jsonarray.length() > 0){
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject objetoJson = jsonarray.getJSONObject(i);
                        String nombre = objetoJson.getString("nombre") + " " + objetoJson.getString("a_paterno") + " " +  objetoJson.getString("a_materno");
                        String genero = (objetoJson.getInt("genero")== 1)?"Mujer":"Hombre";

                        Model modelo = new Model(
                                nombre,
                                objetoJson.getString("fecha_nacimiento"),
                                objetoJson.getString("fecha_hechos"),
                                objetoJson.getString("nacionalidad"),
                                genero,
                                objetoJson.getString("cabello"),
                                objetoJson.getString("ojos"),
                                objetoJson.getString("senas_particulares"),
                                objetoJson.getString("lugar_hechos"),
                                objetoJson.getInt("edad"),
                                objetoJson.getDouble("peso"),
                                objetoJson.getDouble("estatura"),
                                objetoJson.getString("url_imagen"));
                        System.out.println("Amber: " + modelo);
                        System.out.println("Img? " + objetoJson.getString("url_imagen"));
                        amberList.add(modelo);

                    }
                    pintar = true;
                }else{
                    pintar = false;
                }

            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("aki?");
                pintar = false;
            }
        }
        pintar(pintar);
    }

    @Override
    public void onCarpetaError() {

    }

    @Override
    public void onAmberSucess(String amber) {
        boolean pintar = false;
        System.out.println("aki perros");
        if(amber.length()== 0 || amber.isEmpty()){
            System.out.println("aki? ");
        }else {
            try {
                JSONArray jsonarray = new JSONArray(amber);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject objetoJson = jsonarray.getJSONObject(i);
                    String nombre = objetoJson.getString("nombre") + " " + objetoJson.getString("a_paterno") + " " +  objetoJson.getString("a_materno");
                    String genero = (objetoJson.getInt("genero")== 1)?"Mujer":"Hombre";

                    Model modelo = new Model(
                            nombre,
                            objetoJson.getString("fecha_nacimiento"),
                            objetoJson.getString("fecha_hechos"),
                            objetoJson.getString("nacionalidad"),
                            genero,
                            objetoJson.getString("cabello"),
                            objetoJson.getString("ojos"),
                            objetoJson.getString("senas_particulares"),
                            objetoJson.getString("lugar_hechos"),
                            objetoJson.getInt("edad"),
                            objetoJson.getDouble("peso"),
                            objetoJson.getDouble("estatura"),
                            objetoJson.getString("url_imagen"));
                    System.out.println("Amber: " + modelo);
                    amberList.add(modelo);

                }
                pintar = true;
            } catch (JSONException e) {
                e.printStackTrace();
                pintar = false;
            }
        }
       pintar(pintar);
    }
    public void pintar(boolean pintar){
        if(pintar){
            //viewPager.setVisibility(View.VISIBLE);
            AmberAdapter amberAdapter = new AmberAdapter(getContext(),amberList);
            viewPager.setAdapter(amberAdapter);
        }else {
            disable.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onAmberError() {

    }

    @Override
    public void onConecctionError() {

    }


}
