package com.fiscaliageneralags.fiscalia.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.fiscaliageneralags.fiscalia.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ComunicacionSocial extends AppCompatActivity {
    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicacion_social);
        unbinder = ButterKnife.bind(this);
    }
    @OnClick(R.id.facebook)
    public void clickFacebook(){
        System.out.println("Fesbu?");
        Intent fbIntent = newFacebookIntent(this.getPackageManager(),"https://www.facebook.com/Fiscal%C3%ADa-General-del-Estado-Ags-1383427291678537/");
        startActivity(fbIntent);
    }
    @OnClick(R.id.twiiter)
    public void clickTwitter(){
        Intent twitter = new Intent(this, Twitter.class);
        startActivity(twitter);
    }
    @NonNull
    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                  uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }
    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
        System.out.println("Prrakola");
        super.onBackPressed();
    }
}
