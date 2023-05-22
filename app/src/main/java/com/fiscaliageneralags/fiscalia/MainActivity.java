package com.fiscaliageneralags.fiscalia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.fiscaliageneralags.fiscalia.ui.Amber.AmberFragment;
import com.fiscaliageneralags.fiscalia.ui.gallery.GalleryFragment;
import com.fiscaliageneralags.fiscalia.ui.home.HomeFragment;
import com.fiscaliageneralags.fiscalia.ui.slideshow.SlideshowFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

import static com.fiscaliageneralags.fiscalia.R.color.colorPrimary;

public class MainActivity extends AppCompatActivity /*implements NavigationView.OnNavigationItemSelectedListener*/{
    private AppBarConfiguration mAppBarConfiguration;
    private final static int REQ_CODE_PHONE_STATE = 1025;
    private MenuItem menuItemSelected;
    private Fragment actualFragment;
    @BindView(R.id.nav_view) NavigationView navigationView;
    ActionBarDrawerToggle toogle;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);;
        drawer = findViewById(R.id.drawer_layout);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
     //   navigationView.setNavigationItemSelectedListener(this);
        ///////////////////AQUI INICIA LA OTRA APP
        toogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu_ham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[] perms = {Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.INTERNET,Manifest.permission.RECEIVE_BOOT_COMPLETED,Manifest.permission.MODIFY_PHONE_STATE,Manifest.permission.ACCESS_WIFI_STATE};//READ_CONTACTS};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "Se necesita el permiso", REQ_CODE_PHONE_STATE, perms);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        System.out.println("Click? ");
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void onBackPressed() {

       // super.onBackPressed();
    }
   @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("PERRAMADRE");
        AppBarLayout layout = (AppBarLayout) findViewById(R.id.fondoToolbar);
        layout.setBackgroundColor(getResources().getColor(colorPrimary));
        if(toogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


   /* @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
   /* @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        System.out.println("Item? " + item);
        int id = item.getItemId();
       /* if(id == R.id.drawer_nav_close_session){
            System.out.println("aki?");
            drawer.closeDrawer(GravityCompat.START);
            this.finish();
        }else {
          switch (id){
              case R.id.nav_home:
                  replaceFragment(HomeFragment.newInstance());
                  break;
              case R.id.nav_gallery:
                  replaceFragment(GalleryFragment.newInstance());
                  break;
              case R.id.nav_slideshow:
                  replaceFragment(SlideshowFragment.newInstance());
                  break;
              case R.id.nav_amber:
                  replaceFragment(AmberFragment.newInstance());
                  break;

          }
        }

        return false;
    }*/
    public void replaceFragment(Fragment fg){
            actualFragment = fg;
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_anim_slide_left_enter,R.animator.fragment_anim_slide_left_exit).replace(R.id.module_display_fragment, fg).commit();

    }

}
