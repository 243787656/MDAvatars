package com.alexlionne.apps.avatars.Tour;

import android.Manifest;
import android.os.Bundle;

import com.alexlionne.apps.avatars.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

/**
 * Created by ahmed bakir on 4/8/2016.
 */
public class MainIntroActivity extends IntroActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Standard slide (like Google's intros)
         */
        addSlide(new SimpleSlide.Builder()
                .title(R.string.title)
                .description(R.string.desc)
                .image(R.drawable.moto360)
                .background(R.color.primary)
                .backgroundDark(R.color.primary_dark)
                .permission(Manifest.permission.CAMERA)
                .build());
    }
}