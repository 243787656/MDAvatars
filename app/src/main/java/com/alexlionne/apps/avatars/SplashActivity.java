package com.alexlionne.apps.avatars;

/**
 * Created by root on 08/04/16.
 */

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alexlionne.apps.avatars.Utils.Utils;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();



}}

