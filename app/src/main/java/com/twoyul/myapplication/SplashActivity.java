package com.twoyul.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try{
            Thread.sleep(3000);
            Intent mainIntent = new Intent(this, MainMap.class);
            startActivity(mainIntent);
            finish();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
