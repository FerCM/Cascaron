package com.fiscaliageneralags.fiscalia.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.fiscaliageneralags.fiscalia.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CheckAuto extends AppCompatActivity {

    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_auto);
        unbinder = ButterKnife.bind(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.vehiculos_robados_abrir_button, R.id.vehiculos_robados_abrir_image_view})
    public void openChecAutoMX(){
        String appPackageName = "mx.gob.cns.consultaciudadana";
        boolean isAppInstalled = appInstalledOrNot(appPackageName);
        if(isAppInstalled) {
            //This intent will help you to launch if the package is already installed
            Intent LaunchIntent = this.getPackageManager()
                    .getLaunchIntentForPackage(appPackageName);
            startActivity(LaunchIntent);
        } else {
            // Do whatever we want to do if application not installed
            // For example, Redirect to play store
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = this.getPackageManager();//getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }
    @OnClick(R.id.toolbar_back_btn)
    public void onBackNavigation(){
        System.out.println("Prrakola");
        super.onBackPressed();
    }
}
