package com.alexlionne.apps.avatars;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.alexlionne.apps.avatars.Tour.MainIntroActivity;

/**
 * Created by ahmed bakir on 4/8/2016.
 */
public class Splash extends AppCompatActivity {
    public static boolean FIRST_RUN;
    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();


       SharedPreferences prefs = getSharedPreferences("com.alexlionne.apps.avatars", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).apply();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Splash.this, MainIntroActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            finish();
                        }
                    }, 750);
                }
            }, 1500);
        }else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            finish();
                        }
                    }, 750);
                }
            }, 1500);

        }

    }
}