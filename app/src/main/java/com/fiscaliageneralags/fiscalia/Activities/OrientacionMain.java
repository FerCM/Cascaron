package com.fiscaliageneralags.fiscalia.Activities;

//import androidx.appcompat.app.AppCompatActivity;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ScrollView;

import com.fiscaliageneralags.fiscalia.R;
import com.github.florent37.expansionpanel.ExpansionHeader;
import com.github.florent37.expansionpanel.ExpansionLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrientacionMain extends AppCompatActivity {
    @BindView(R.id.expansionLayou)
    ExpansionLayout personaDesaparecidaLayout;
    @BindView(R.id.expansionHeade)
    ExpansionHeader personaDesaparecidaHeader;
    @BindView(R.id.expansionLayout) ExpansionLayout conDetenidoLayout;
    @BindView(R.id.expansionHeader) ExpansionHeader conDetenidoHeader;
    @BindView(R.id.expansionLayout1) ExpansionLayout sinDetenidoLayout;
    @BindView(R.id.expansionHeader1) ExpansionHeader sinDetenidoHeader;
    @BindView(R.id.expansionLayout2) ExpansionLayout vehicularLayout;
    @BindView(R.id.expansionHeader2) ExpansionHeader vehicularHeader;

    @BindView(R.id.scrollView)
    ScrollView scrollView;


    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientacion_main);
        unbinder = ButterKnife.bind(this);

        final int desfase =  +50;

        personaDesaparecidaLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    personaDesaparecidaHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_1);
                }

            }
        });
        personaDesaparecidaLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    personaDesaparecidaHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_1);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });


        conDetenidoLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    conDetenidoHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_2);
                }

            }
        });
        conDetenidoLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    conDetenidoHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_2);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });


        sinDetenidoLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    sinDetenidoHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_3);
                }
            }
        });
        sinDetenidoLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    sinDetenidoHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_3);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });


        vehicularLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    vehicularHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_4);
                }
            }
        });
        vehicularLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    vehicularHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_4);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.orientacion_llamada_extorsion)
    public void clickLlamarDenunciaSecuestro(){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:088"));
        startActivity(intent);
    }
    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
        System.out.println("Prrakola");
        super.onBackPressed();
    }
}
