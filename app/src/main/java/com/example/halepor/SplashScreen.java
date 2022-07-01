package com.example.halepor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startActivity(new Intent(SplashScreen.this, login.class));

        int Splash_Time = 900; // 1 detik
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, login.class);
                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();
            }
        }, Splash_Time);
    }
}