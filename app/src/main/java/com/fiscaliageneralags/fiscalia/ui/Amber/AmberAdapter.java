package com.fiscaliageneralags.fiscalia.ui.Amber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.fiscaliageneralags.fiscalia.R;

import java.util.List;

public class AmberAdapter extends PagerAdapter {
    private Context context;
    private List<Model> amberList;
    private LayoutInflater layoutInflater;

    AmberAdapter(Context context, List <Model> amberList){
        this.context= context;
        this.amberList= amberList;
    }
    @Override
    public int getCount() {
        return amberList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = layoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.amber, container, false);

        ImageView imageView;
        TextView nombre, fenac, fecha, nacio, gen, hair, eyes, cm, kg, senas, lugar;

        imageView = view.findViewById(R.id.image);
        nombre = view.findViewById(R.id.name);
        fenac = view.findViewById(R.id.fenacInfo);
        fecha = view.findViewById(R.id.fechosInfo);
        nacio = view.findViewById(R.id.nacioInfo);
        gen = view.findViewById(R.id.genderInfo);
        hair = view.findViewById(R.id.hairInfo);
        eyes = view.findViewById(R.id.eyesInfo);
        cm = view.findViewById(R.id.estaInfo);
        kg = view.findViewById(R.id.pesoInfo);
        senas = view.findViewById(R.id.senasInfo);
        lugar = view.findViewById(R.id.lugarInfo);
        // **************************************************************
        ///Imagen
        String EDteamImage = amberList.get(position).getImg();
        System.out.println("imagen? " + EDteamImage);
        Glide.with(context).load("https://www.fiscalia-aguascalientes.gob.mx"+EDteamImage).into(imageView);
        //**************************************************************
        nombre.setText(amberList.get(position).getNombre());
        fenac.setText(amberList.get(position).getFenac());
        fecha.setText(amberList.get(position).getFechaecho());
        nacio.setText(amberList.get(position).getNaci());
        gen.setText(amberList.get(position).getGen());
        hair.setText(amberList.get(position).getCab());
        eyes.setText(amberList.get(position).getOjos());
        cm.setText(new Double(amberList.get(position).getEsta()).toString() + " Metros");
        kg.setText(new Double(amberList.get(position).getPeso()).toString() + " Kg");
        senas.setText(amberList.get(position).getSeñas());
        lugar.setText(amberList.get(position).getLugar());

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
