package com.fiscaliageneralags.fiscalia.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ERodriguezF on 22/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class UCUtil {

    private Context context;

    /**
     * Creates a new User Credentials Util for getting the credentials.
     * @param context Context for the shared preferences
     */
    public UCUtil(Context context){
        this.context = context;
    }

    public int getUCId(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.AppPersistantKey,0);
        return sharedPreferences.getInt(AppConstants.UCUserId,-1);
    }

    public String getUCEmail(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.AppPersistantKey,0);
        return sharedPreferences.getString(AppConstants.UCUserEmail,"ERROR_READING_CREDENTIALS");
    }

    public String getUCPassword(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.AppPersistantKey,0);
        return sharedPreferences.getString(AppConstants.UCUserPassword,"ERROR_READING_CREDENTIALS");
    }

}
