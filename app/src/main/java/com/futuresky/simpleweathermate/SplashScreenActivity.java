package com.futuresky.simpleweathermate;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    public static final String TAG = "splash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ///for .glf splash screen///
        runSplash();
    }

    private void runSplash() {
        Thread timer  = new Thread(){
            public void run(){
                try {
                    sleep(3500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                finally{
                    finish();
                   Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                   startActivity(intent);
                }
            }

        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}