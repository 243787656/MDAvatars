package com.alexlionne.apps.avatars.Tour;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import com.alexlionne.apps.avatars.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainIntroActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullscreen(false);

        super.onCreate(savedInstanceState);

        setSkipEnabled(false);
        setFinishEnabled(true);

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title1)
                .description(R.string.desc1)
                .image(R.drawable.showcase1)
                .background(R.color.primary)
                .backgroundDark(R.color.primary_dark)
                .build());
        addSlide(new SimpleSlide.Builder()
                .description(R.string.desc2)
                //.image(R.drawable.moto360)
                .background(R.color.primary)
                .backgroundDark(R.color.primary_dark)
                .build());
        if (Build.VERSION.SDK_INT >= 23) {
            addSlide(new SimpleSlide.Builder()
                    .title(R.string.title1)
                    .description(R.string.intro_latest_step)
                    .background(R.color.primary)
                    .backgroundDark(R.color.primary_dark)
                    .permissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE})
                    .build());
        }
        addSlide(new FragmentSlide.Builder()
                .background(R.color.primary)
                .backgroundDark(R.color.primary_dark)
                .fragment(userName_Fragment.newInstance())
                .build());
    }

}
