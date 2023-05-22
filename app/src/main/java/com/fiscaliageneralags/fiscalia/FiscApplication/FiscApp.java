package com.fiscaliageneralags.fiscalia.FiscApplication;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.fiscaliageneralags.fiscalia.BuildConfig;
import com.fiscaliageneralags.fiscalia.Models.MyObjectBox;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

/**
 * Created by ERodriguezF on 27/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class FiscApp extends MultiDexApplication {
    public static final boolean EXTERNAL_DIR = false;

    /**
     * Store data for database manipulation.
     */
    private BoxStore boxStore;

    /**
     * Configure box store instance.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(FiscApp.this).build();
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(this);
        }
        Log.d("FiscApp", "Using ObjectBox " + BoxStore.getVersion() + " (" + BoxStore.getVersionNative() + ")");
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
