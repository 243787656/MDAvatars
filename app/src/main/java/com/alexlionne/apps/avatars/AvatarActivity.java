package com.alexlionne.apps.avatars;


import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alexlionne.apps.avatars.fragments.EditionFragment;
import com.alexlionne.apps.avatars.objects.Kit;
import com.alexlionne.apps.avatars.objects.kits.GoogleKitOne;


import java.util.ArrayList;


public class AvatarActivity extends AppCompatActivity {
    private int currentFragment ;
    private WebView webview;
    public static Kit kit;
    private FragmentManager fm;
    private FragmentTransaction fragmentTransaction;
    private EditionFragment fragment[];
    private ArrayList<ArrayList<String>> list;
    private EditionFragment pre;
    private EditionFragment post;
    private EditionFragment current;


    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.md_grey_900), PorterDuff.Mode.SRC_IN);

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
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

            }


        });

        kit.attachWebView(webview);
        list = kit.getAllcategories();
        fragment  = new EditionFragment[list.size()];

        for(int i = 0;i<list.size();i++) {
           addFragment(i);

        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment[0]);
        fragmentTransaction.commit();
        currentFragment=0;
        setCurrentFragment(fragment[currentFragment]);
        setNextFragment(fragment[currentFragment + 1]);
        if(getCurrentFragment().getPosition()!=0){
            setPreviousFragment(fragment[currentFragment - 1]);
        }

        final int position = getCurrentFragment().getPosition();


        progressBar.setProgress(getCurrentFragment().getProgress());

        Button button = (Button)findViewById(R.id.change);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchFragment(getNextFragment());
                progressBar.setProgress(getNextFragment().getProgress());
                setNextFragment(fragment[position+1]);
                if(getCurrentFragment().getPosition() != 0) {
                    setPreviousFragment(fragment[position - 1]);
                }

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




    public void addFragment(int i){
        int UNIT_SIZE = (i+1)*(100 / fragment.length) ;
        String title = list.get(i).get(0);
        fragment[i] = new EditionFragment();
        fragment[i].setPosition(i);
        fragment[i].setListener(kit.getListeners());
        fragment[i].setTitle(title);
        fragment[i].setList(list.get(i));
        fragment[i].setProgress(UNIT_SIZE);
        list.get(i).remove(0);

    }
    public void switchFragment(EditionFragment to){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, to);
        transaction.commit();


    }
    public void attachKit(Kit kit){
        AvatarActivity.kit = kit;
    }
    public static Kit getKit(){
        return AvatarActivity.kit;
    }

    public void setNextFragment(EditionFragment post){
        this.post = post;
    }
    public EditionFragment getNextFragment(){
        return this.post;
    }
    public void setCurrentFragment(EditionFragment current){
        this.current = current;
    }
    public EditionFragment getCurrentFragment(){
        return  this.current;
    }
    public void setPreviousFragment(EditionFragment pre)throws IllegalArgumentException{
        if(pre.getPosition()==0) {
            throw new IllegalArgumentException("current is 0!");
        }
        this.pre = pre;
    }
    public EditionFragment getPreviousFragment(){
        return this.pre;
    }




}