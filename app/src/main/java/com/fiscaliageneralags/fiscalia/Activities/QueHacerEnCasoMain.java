package com.fiscaliageneralags.fiscalia.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ScrollView;

import com.fiscaliageneralags.fiscalia.R;
import com.github.florent37.expansionpanel.ExpansionHeader;
import com.github.florent37.expansionpanel.ExpansionLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class QueHacerEnCasoMain extends AppCompatActivity {
    @BindView(R.id.expansionLa) ExpansionLayout extravioRegistroLayout;
    @BindView(R.id.expansionHe)
    ExpansionHeader extravioRegistroHeader;
    @BindView(R.id.expansionLay) ExpansionLayout extravioPlacasLayout;
    @BindView(R.id.expansionHea) ExpansionHeader extravioPlacasHeader;
    @BindView(R.id.expansionLayo) ExpansionLayout extravioDocumentosLayout;
    @BindView(R.id.expansionHead) ExpansionHeader extravioDocumentosHeader;
    @BindView(R.id.expansionLayou) ExpansionLayout personaDesaparecidaLayout;
    @BindView(R.id.expansionHeade) ExpansionHeader personaDesaparecidaHeader;
    @BindView(R.id.expansionLayout) ExpansionLayout conDetenidoLayout;
    @BindView(R.id.expansionHeader) ExpansionHeader conDetenidoHeader;
    @BindView(R.id.expansionLayout1) ExpansionLayout sinDetenidoLayout;
    @BindView(R.id.expansionHeader1) ExpansionHeader sinDetenidoHeader;
    @BindView(R.id.expansionLayout2) ExpansionLayout vehicularLayout;
    @BindView(R.id.expansionHeader2) ExpansionHeader vehicularHeader;
    @BindView(R.id.expansionLayout3) ExpansionLayout secuestroFamiliarLayout;
    @BindView(R.id.expansionHeader3) ExpansionHeader secuestroFamiliarHeader;
    @BindView(R.id.expansionLayout4) ExpansionLayout secuestroPersonalLayout;
    @BindView(R.id.expansionHeader4) ExpansionHeader secuestroPersonalHeader;
    @BindView(R.id.expansionLayout5) ExpansionLayout secuestroRecomendacionesLayout;
    @BindView(R.id.expansionHeader5) ExpansionHeader secuestroRecomendacionesHeader;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_hacer_en_caso_main);
        unbinder = ButterKnife.bind(this);
        final int desfase =  +50;
        extravioRegistroLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    extravioRegistroHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_1);
                }
            }
        });
        extravioRegistroLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    extravioRegistroHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_1);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });
        extravioPlacasLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    extravioPlacasHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_2);
                }
            }
        });
        extravioPlacasLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    extravioPlacasHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_2);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });
        extravioDocumentosLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    extravioDocumentosHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_3);
                }
            }
        });
        extravioDocumentosLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    extravioDocumentosHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_3);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });
        personaDesaparecidaLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    personaDesaparecidaHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_4);
                }
            }
        });
        personaDesaparecidaLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    personaDesaparecidaHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_4);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });
        conDetenidoLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    conDetenidoHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_1);
                }
            }
        });
        conDetenidoLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    conDetenidoHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_1);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });
        sinDetenidoLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    sinDetenidoHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_2);
                }
            }
        });
        sinDetenidoLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    sinDetenidoHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_2);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });
        vehicularLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    vehicularHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_3);
                }
            }
        });
        vehicularLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    vehicularHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_3);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });
        secuestroFamiliarLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    secuestroFamiliarHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_4);
                }
            }
        });
        secuestroFamiliarLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    secuestroFamiliarHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_4);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });
        secuestroPersonalLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    secuestroPersonalHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_1);
                }
            }
        });
        secuestroPersonalLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    secuestroPersonalHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_1);
                    ObjectAnimator.ofInt(scrollView,"scrollY",expansionLayout.getBottom()+desfase).setDuration(800).start();
                }
            }
        });
        secuestroRecomendacionesLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                if(!expanded){
                    secuestroRecomendacionesHeader.setBackgroundResource(R.drawable.shape_expand_layout_collapsed_2);
                }
            }
        });
        secuestroRecomendacionesLayout.addIndicatorListener(new ExpansionLayout.IndicatorListener() {
            @Override
            public void onStartedExpand(ExpansionLayout expansionLayout, boolean willExpand) {
                if(willExpand){
                    secuestroRecomendacionesHeader.setBackgroundResource(R.drawable.shape_expand_layout_expanded_2);
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
    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
        System.out.println("Prrakola");
        super.onBackPressed();
    }
}
