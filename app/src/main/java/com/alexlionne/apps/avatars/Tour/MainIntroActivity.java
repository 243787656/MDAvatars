package com.alexlionne.apps.avatars.Tour;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.alexlionne.apps.avatars.MainActivity;
import com.alexlionne.apps.avatars.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;
import com.heinrichreimersoftware.materialintro.slide.Slide;

public class MainIntroActivity extends IntroActivity {

    public static final String EXTRA_FULLSCREEN = "com.heinrichreimersoftware.materialintro.demo.EXTRA_FULLSCREEN";

    public static final String EXTRA_SCROLLABLE = "com.heinrichreimersoftware.materialintro.demo.EXTRA_SCROLLABLE";

    public static final String EXTRA_CUSTOM_FRAGMENTS = "com.heinrichreimersoftware.materialintro.demo.EXTRA_CUSTOM_FRAGMENTS";

    public static final String EXTRA_PERMISSIONS = "com.heinrichreimersoftware.materialintro.demo.EXTRA_PERMISSIONS";

    public static final String EXTRA_SKIP_ENABLED = "com.heinrichreimersoftware.materialintro.demo.EXTRA_SKIP_ENABLED";

    public static final String EXTRA_FINISH_ENABLED = "com.heinrichreimersoftware.materialintro.demo.EXTRA_FINISH_ENABLED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        boolean fullscreen = intent.getBooleanExtra(EXTRA_FULLSCREEN, false);
        boolean scrollable = intent.getBooleanExtra(EXTRA_SCROLLABLE, false);
        boolean customFragments = intent.getBooleanExtra(EXTRA_CUSTOM_FRAGMENTS, true);
        boolean permissions = intent.getBooleanExtra(EXTRA_PERMISSIONS, true);
        boolean skipEnabled = intent.getBooleanExtra(EXTRA_SKIP_ENABLED, false);
        boolean finishEnabled = intent.getBooleanExtra(EXTRA_FINISH_ENABLED, false);

        setFullscreen(fullscreen);

        super.onCreate(savedInstanceState);

        setSkipEnabled(skipEnabled);
        setFinishEnabled(finishEnabled);
        startNextMatchingActivity(new Intent(MainIntroActivity.this, MainActivity.class));

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title)
                .description(R.string.desc)
                .image(R.drawable.moto360)
                .background(R.color.primary)
                .backgroundDark(R.color.primary_dark)
                .scrollable(scrollable)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title)
                .description(R.string.desc)
                .image(R.drawable.moto360)
                .background(R.color.primary)
                .backgroundDark(R.color.primary_dark)
                .scrollable(scrollable)
                .build());

        final Slide permissionsSlide;
        if (permissions) {
            permissionsSlide = new SimpleSlide.Builder()
                    .title(R.string.title)
                    .description(R.string.intro_latest_step)
                    .background(R.color.primary)
                    .backgroundDark(R.color.primary_dark)
                    .scrollable(scrollable)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .build();
            addSlide(permissionsSlide);
        } else {
            permissionsSlide = null;
        }
    }

}