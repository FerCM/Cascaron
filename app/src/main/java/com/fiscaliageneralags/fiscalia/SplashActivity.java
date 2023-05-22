package com.fiscaliageneralags.fiscalia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;


public class SplashActivity extends AppCompatActivity implements OnCompleteListener<InstanceIdResult>, OnFailureListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Task<InstanceIdResult> task = FirebaseInstanceId.getInstance().getInstanceId();
        task.addOnCompleteListener(this);
        task.addOnFailureListener(this);
    }

    /**
     * On Completed the token retrieval.
     * @param task
     */
    @Override
    public void onComplete(@NonNull Task<InstanceIdResult> task) {
        if (!task.isSuccessful()) {
            Log.w("SPLASH", "getInstanceId failed", task.getException());
            return;
        }
        // Get new Instance ID token
        String token = task.getResult().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        String msg = "Token:"+token;
        Log.d("SPLASH", msg);
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
        this.finish();
    }

    /**
     * On failure token retrieval.
     * @param e
     */
    @Override
    public void onFailure(@NonNull Exception e) {
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
        this.finish();
    }
}
