package com.alexlionne.apps.avatars;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.transition.Explode;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alexlionne.apps.avatars.fragments.EditionFragment;
import com.alexlionne.apps.avatars.objects.Kit;
import com.alexlionne.apps.avatars.objects.kits.AndroidKit;
import com.alexlionne.apps.avatars.objects.kits.GoogleKitOne;
import com.alexlionne.apps.avatars.objects.kits.GoogleKitTwo;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;


public class AvatarActivity extends AppCompatActivity {
    private static int accentPreselect;
    private int currentFragment;
    private WebView webview;
    public static Kit kit;
    private FragmentManager fm;
    private FragmentTransaction fragmentTransaction;
    private EditionFragment fragment[];
    private ArrayList<ArrayList<String>> list;
    private EditionFragment pre;
    private EditionFragment post;
    private EditionFragment current;
    public static FragmentManager fragmentManager;
    private static Window window;
    private boolean hidden = true;
    private final String MDSdirectory = "/sdcard/MDAvatar/";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private  ProgressBar progressBar;


    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_layout);
        final RelativeLayout view = (RelativeLayout)findViewById(R.id.layout);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.md_grey_900), PorterDuff.Mode.SRC_IN);

        webview = (WebView) findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);




        String current = getIntent().getStringExtra("kit");
         if (current.equals("Google I")) {
            kit = new GoogleKitOne(this);
        } else if (current.equals("Android")) {
            kit = new AndroidKit(this);
        } else if (current.equals("Google II")) {
            kit = new GoogleKitTwo(this);
        }
        attachKit(kit);
        setWindow(getWindow());
        webview.setVisibility(View.INVISIBLE);
        webview.post(new Runnable() {

            @Override
            public void run() {
                reveal(webview);

            }
        });

        webview.loadUrl(kit.getSvg());
        webview.setBackgroundColor(kit.getDefaultBgColor());
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.getSettings().setUseWideViewPort(true);
        webview.setWebChromeClient(new WebChromeClient());
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Utils.getAccentDarkColor(kit.getDefaultBgColor()));
        }
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }


        });




        fragmentManager = getFragmentManager();
        kit.attachWebView(webview);
        list = kit.getAllcategories();
        fragment = new EditionFragment[list.size()];

        for (int i = 0; i < list.size(); i++) {
            addFragment(i);

        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment[0]);
        fragmentTransaction.commit();
        currentFragment = 0;
        setCurrentFragment(fragment[currentFragment]);
        setNextFragment(fragment[getCurrentFragment().getPosition() + 1]);
        progressBar.setProgress(getCurrentFragment().getProgress());

        final Button button = (Button) findViewById(R.id.change);
        final Button back = (Button) findViewById(R.id.change_back);
        assert button != null;
        assert back != null;
        button.setText(getNextFragment().getTitle());
        back.setVisibility(View.INVISIBLE);
        button.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        back.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isClicked = true;
                if (getCurrentFragment().getPosition() == fragment.length - 1 && isClicked) {
                    save();
                } else {

                    back.setVisibility(View.VISIBLE);
                    setNextFragment(fragment[getCurrentFragment().getPosition() + 1]);
                    switchFragment(getNextFragment());

                    for (int i = 0; i <= fragment.length; i++) {

                        if (getCurrentFragment().getPosition() == fragment.length - 1) {
                            button.setText("Save");
                            back.setText(fragment[getCurrentFragment().getPosition() - 1].getTitle());
                        } else {
                            back.setText(fragment[getCurrentFragment().getPosition() - 1].getTitle());
                            button.setText(fragment[getCurrentFragment().getPosition() + 1].getTitle());
                        }


                    }




                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNextFragment(fragment[getCurrentFragment().getPosition() - 1]);
                switchBackFragment(getNextFragment());

                for (int i = 0; i < fragment.length; i++) {
                    if (getCurrentFragment().getPosition() == 0) {
                        button.setText(fragment[getCurrentFragment().getPosition() + 1].getTitle());
                        back.setVisibility(View.INVISIBLE);
                    } else {
                        back.setText(fragment[getCurrentFragment().getPosition() - 1].getTitle());
                        button.setText(fragment[getCurrentFragment().getPosition() + 1].getTitle());
                    }


                }

                if (getCurrentFragment().getPosition() != 0) {
                    setPreviousFragment(fragment[getCurrentFragment().getPosition() + 1]);
                }
                progressBar.setProgress(getNextFragment().getProgress());


            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        //getWindow().setEnterTransition(new Explode());
        //getWindow().setExitTransition(new Explode());
        getWindow().getEnterTransition().addListener( new Transition.TransitionListener(){
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    private void reveal(View v) {
        v.setVisibility(View.INVISIBLE);
// get the center for the clipping circle
        int cx = v.getWidth() / 2;
        int cy = v.getHeight() / 2;

// get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
        Animator anim =
                android.view.ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);

// make the view visible and start the animation
        v.setVisibility(View.VISIBLE);
        anim.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addFragment(int i) {
        int UNIT_SIZE = (i + 1) * (100 / fragment.length);
        String title = list.get(i).get(0);
        fragment[i] = new EditionFragment();
        fragment[i].setPosition(i);
        fragment[i].setListener(kit.getListeners());
        fragment[i].setTitle(title);
        fragment[i].setList(list.get(i));
        fragment[i].setProgress(UNIT_SIZE);
        list.get(i).remove(0);

    }



    public static void setWindow(Window window){
       AvatarActivity.window = window;
    }
    public static Window getWindowView(){
        return AvatarActivity.window;
    }

    public void switchFragment(EditionFragment to) {
        if (getCurrentFragment().getPosition() != 0) {
            setPreviousFragment(fragment[getCurrentFragment().getPosition() - 1]);
        }
        if(android.os.Build.VERSION.SDK_INT >= 11){
            // will update the "progress" propriety of seekbar until it reaches progress
            ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", to.getProgress());
            animation.setDuration(500); // 0.5 second
            animation.setInterpolator(new DecelerateInterpolator());
            animation.start();
        }
        else

            progressBar.setProgress(getNextFragment().getProgress());

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left,
                R.anim.slide_out_right, 0, 0);
        transaction.replace(R.id.container, to);
        transaction.commit();
        setCurrentFragment(to);


    }

    public void switchBackFragment(EditionFragment to) {
        if (getCurrentFragment().getPosition() != 0) {
            setPreviousFragment(fragment[getCurrentFragment().getPosition() - 1]);
        }

        if(android.os.Build.VERSION.SDK_INT >= 11){
            // will update the "progress" propriety of seekbar until it reaches progress
            ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", to.getProgress());
            animation.setDuration(500); // 0.5 second
            animation.setInterpolator(new DecelerateInterpolator());
            animation.start();
        } else {
            progressBar.setProgress(getPreviousFragment().getProgress());
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right,
                0, 0);
        transaction.replace(R.id.container, to);
        transaction.commit();
        setCurrentFragment(to);


    }

    public void attachKit(Kit kit) {
        AvatarActivity.kit = kit;
    }

    public static Kit getKit() {
        return AvatarActivity.kit;
    }

    public void setNextFragment(EditionFragment post) {
        this.post = post;
    }

    public EditionFragment getNextFragment() {
        return this.post;
    }

    public void setCurrentFragment(EditionFragment current) {
        this.current = current;
    }

    public EditionFragment getCurrentFragment() {
        return this.current;
    }

    public void setPreviousFragment(EditionFragment pre) {
        if (pre.getPosition() != 0) {
            this.pre = pre;
        }

    }

    public EditionFragment getPreviousFragment() {
        return this.pre;

    }

    public void save() {
        int count = Utils.getAllSavedAvatars().size()+1;
        new MaterialDialog.Builder(AvatarActivity.this)
                .title("Name")
                .content("Set a name for your Avatar")
                .inputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputMaxLength(20)
                .positiveText("go")
                .input("name", "my_avatar_"+count, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        Picture picture = webview.capturePicture();
                        Bitmap b = Bitmap.createBitmap(
                                picture.getWidth(), picture.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas c = new Canvas(b);
                        picture.draw(c);

                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(MDSdirectory + input.toString() + ".png");
                            if (fos != null) {
                                b.compress(Bitmap.CompressFormat.PNG, 100, fos);
                                fos.close();
                            }
                        } catch (Exception e) {
                            System.out.println("-----error--" + e);
                        }

                    }
                })
                .show();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Avatar Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.alexlionne.apps.avatars/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Avatar Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.alexlionne.apps.avatars/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
   /* @Override
    public void onBackPressed(){


    }*/
}
