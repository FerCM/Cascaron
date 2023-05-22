package com.fiscaliageneralags.fiscalia.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.fiscaliageneralags.fiscalia.Adapters.NumerosEmergenciaRecyclerViewAdapter;
import com.fiscaliageneralags.fiscalia.Models.NumeroEmergenciaItem;
import com.fiscaliageneralags.fiscalia.R;
import com.fiscaliageneralags.fiscalia.Utils.Interfaces.INumeroEmergenciaItemOnClickListener;
import com.fiscaliageneralags.fiscalia.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.fiscaliageneralags.fiscalia.R.color.colorPrimary;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    @BindView(R.id.numeros_emergencia_recycler_view)
    RecyclerView numerosEmergencia;
    private Unbinder unbinder;
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static SlideshowFragment newInstance() {
        SlideshowFragment fragment = new SlideshowFragment();
        Bundle args = new Bundle();
        //Put aguments to pass to the fragment
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_slideshow, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        toolbar =  getActivity().findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(colorPrimary));

        List<NumeroEmergenciaItem> numerosEmeregencia = getNumerosEmegencia();
        NumerosEmergenciaRecyclerViewAdapter adapter = new NumerosEmergenciaRecyclerViewAdapter(numerosEmeregencia, new INumeroEmergenciaItemOnClickListener() {
            @Override
            public void onClick(Context context, NumeroEmergenciaItem item) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + item.getNumeroParaLlamada()));
                startActivity(intent);
            }
        });
        numerosEmergencia.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        numerosEmergencia.setItemAnimator(new DefaultItemAnimator());
        numerosEmergencia.setAdapter(adapter);
        return rootView;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private List<NumeroEmergenciaItem> getNumerosEmegencia(){
        List<NumeroEmergenciaItem> items = new ArrayList<>();

        Resources resources = this.getContext().getResources();
        Resources.Theme theme = this.getContext().getTheme();
        Drawable drawable;

        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_tres_juntos, theme);
        items.add( new NumeroEmergenciaItem(drawable,"Emergencias - Atención Estatal","911","911") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_policia, theme);
        items.add( new NumeroEmergenciaItem(drawable,"Fiscalía General del Estado","(449)478-28-00","+524494782800") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_policia, theme);
        items.add( new NumeroEmergenciaItem(drawable,"Denuncia Anónima","089","089") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_policia, theme);
        items.add( new NumeroEmergenciaItem(drawable,"Denuncia Extorsión/Secuestro","088","088") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_fuego, theme);
        items.add( new NumeroEmergenciaItem(drawable,"Bomberos","(449)970-00-65","+524499700065") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_cruz_roja, theme);
        items.add( new NumeroEmergenciaItem(drawable,"Cruz Roja","065","065") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_cruz_roja, theme);
        items.add( new NumeroEmergenciaItem(drawable,"I.M.S.S. Urgencias","(449)970-35-53","+524499703553") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_cruz_roja, theme);
        items.add( new NumeroEmergenciaItem(drawable,"I.S.S.S.T.E. Urgencias ext-117","(449)914-21-03 ext. 117","+524499142103,117") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_escudo, theme);
        items.add( new NumeroEmergenciaItem(drawable,"Locatel","(449)910-20-20","+524499102020") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_policia, theme);
        items.add( new NumeroEmergenciaItem(drawable,"Policía Federal","(449)910-50-20","+524499105020") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_policia, theme);
        items.add( new NumeroEmergenciaItem(drawable,"Policía Federal","(449)910-50-21","+524499105021") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_escudo, theme);
        items.add( new NumeroEmergenciaItem(drawable,"Protección Civil","(449)910-20-29","+524499102029") );
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_eme_policia, theme);
        items.add( new NumeroEmergenciaItem(drawable,"Seguridad Pública","(449)970-00-29","+524499700029") );

        return items;
    }
}
