package com.alexlionne.apps.avatars;


import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alexlionne.apps.avatars.fragments.EditionFragment;
import com.alexlionne.apps.avatars.objects.Kit;
import com.alexlionne.apps.avatars.objects.kits.GoogleKitOne;


import java.util.ArrayList;


public class AvatarActivity extends AppCompatActivity {
    private WebView webview;
    public static Kit kit;
    private EditionFragment fragment;
    private android.app.FragmentTransaction ft;
    private FragmentManager fm;
    private  ArrayList<EditionFragment> fragments;


    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        progressBar.setProgress(35);
        webview = (WebView) findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);


        if (getIntent().getStringExtra("kit").equals("Google-Kit I")){
            kit = new GoogleKitOne(this);
            attachKit(kit);
        }

        webview.loadUrl(kit.getSvg());
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.getSettings().setUseWideViewPort(true);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view,String url){

            }



        });

        kit.attachWebView(webview);
        ArrayList<ArrayList<String>> list = kit.getAllcategories();
        fragments = new ArrayList<>();


        for(int i = 0;i<list.size();i++){

            EditionFragment fragment = new  EditionFragment().newInstance(list.get(i).get(0),list.get(i));
            list.get(i).remove(0);
            fragments.add(fragment);
            fm = getFragmentManager();
            ft = fm.beginTransaction().add(R.id.container,fragments.get(0));
            ft.commit();
        }
        //fragment = fragments.get(0);


        Button button = (Button)findViewById(R.id.change);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(fragments.get(1));
            }
        });



                //kit.getListeners().get(0).get(1));
       /* button.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String javascript = "javascript:document.getElementById('body').setAttribute('fill','"+"none"/*+Utils.convertHexColorString(getResources().getColor(android.R.color.transparent))*/  //  +"');";
               /* webview.loadUrl(javascript);


            }
        });*/

    }
    public void switchFragment(EditionFragment to){
        to = new EditionFragment();
        ft = fm.beginTransaction();
        ft.replace(R.id.container, to);
        ft.commit();
    }
public void attachKit(Kit kit){
    AvatarActivity.kit = kit;
}
    public static Kit getKit(){
        return AvatarActivity.kit;
    }



}
