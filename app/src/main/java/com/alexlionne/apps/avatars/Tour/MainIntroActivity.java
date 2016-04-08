package com.alexlionne.apps.avatars.Tour;

import android.Manifest;
import android.os.Bundle;

import com.alexlionne.apps.avatars.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.app.NavigationPolicy;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

/**
 * Created by ahmed bakir on 4/8/2016.
 */
public class MainIntroActivity extends IntroActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setSkipEnabled(false);
        setNavigationPolicy(new NavigationPolicy() {
            @Override
            public boolean canGoForward(int position) {
                return true;
            }

            @Override
            public boolean canGoBackward(int position) {
                return true;
            }
        });

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title)
                .description(R.string.desc)
                .image(R.drawable.moto360)
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .permission(Manifest.permission.CAMERA)
                .scrollable(true)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title)
                .description(R.string.desc)
                .image(R.drawable.accessories_cap)
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .permission(Manifest.permission.CAMERA)
                .scrollable(true)
                .build());
    }
}