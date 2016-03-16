package com.alexlionne.apps.avatars.objects.kits;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils;
import com.alexlionne.apps.avatars.objects.Kit;
import com.kennyc.colorchooser.ColorChooserDialog;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;

public class GoogleKitTwo extends Kit {
    private Context context;
    private static int selectedColor ;


    public GoogleKitTwo(Context context) {
        super(context);
        this.context = context.getApplicationContext();
        super.setName("Google-Kit II");
        super.setSmallDesc("Material palette, grain shadows,rounded shapes ");
        super.setShowcase(R.drawable.gmd_kit_2);
        super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
        super.setSvg("file:///android_asset/gmd_kit_2.html");
        super.setCategories(getGoogleKitTwoCategories());
        super.setListener(getGoogleKitTwoListeners());
        super.setDefaultBgColor(context.getResources().getColor(R.color.md_orange_700));
    }

    private ArrayList<ArrayList<String>> getGoogleKitTwoCategories() {
        ArrayList<String> background = new ArrayList<>();
        background.add("Background");
        background.add("Color");

        ArrayList<String> head = new ArrayList<>();
        head.add("Head");
        head.add("Color");

        ArrayList<String> hand = new ArrayList<>();
        hand.add("Hand");
        hand.add("hand");

        ArrayList<String> hairs = new ArrayList<>();
        hairs.add("Hairs");
        hairs.add("Color");


        ArrayList<String> body = new ArrayList<>();
        body.add("Clothes");
        body.add("Clothes Color");

        ArrayList<String> saves = new ArrayList<>();
        saves.add("Save and options");
        saves.add("Save");
        saves.add("Share");

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result.add(background);
        result.add(head);
        result.add(hand);
        result.add(hairs);
        result.add(body);
        result.add(saves);


        return result;
    }

    public ArrayList<AdapterView.OnItemClickListener> getGoogleKitTwoListeners(){
        ArrayList<AdapterView.OnItemClickListener> list = new ArrayList<>();
        AdapterView.OnItemClickListener background = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {

                                        selectedColor = color;
                                        GoogleKitTwo.super.getWebView().setBackgroundColor(color);
                                        if (Build.VERSION.SDK_INT >= 21) {
                                            Window window = AvatarActivity.getWindowView();
                                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                                            window.setStatusBarColor(Utils.getAccentDarkColor(color));
                                        }
                                    }
                                }))
                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .selectedColor(selectedColor)
                                .positiveButtonColor(context.getResources().getColor(R.color.accent))
                                .build()
                                .show(AvatarActivity.fragmentManager, null);


                        break;

                }

            }
        };



        AdapterView.OnItemClickListener head = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors_skin)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = " javascript:document.getElementById('head').setAttribute('fill','" + Utils.convertHexColorString(color) + "');";
                                        GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                    }
                                }))
                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);


                        break;

                }
            }
        };
        AdapterView.OnItemClickListener hand = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:
                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors_skin_plus)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = " javascript:document.getElementById('hand').setAttribute('fill','"+ Utils.convertHexColorString(color)  +"');";
                                        GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                    }
                                }))
                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);


                        break;


                }
            }
        };
        AdapterView.OnItemClickListener clothes = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = "javascript:document.getElementById('body').setAttribute('fill','" + Utils.convertHexColorString(color) + "');";
                                        GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                        String javascript_more = "javascript:document.getElementById('uparm').setAttribute('fill','" + Utils.convertHexColorString(color) + "');";
                                        GoogleKitTwo.super.getWebView().loadUrl(javascript_more);
                                        String javascript_shadow_one = "javascript:var svgElement=document.getElementById('shadow1');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color)) + "');};";
                                        GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_one);
                                        String javascript_shadow_two = "javascript:var svgElement=document.getElementById('shadow2');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color)) + "');};";
                                        GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_two);
                                        String javascript_shadow_three = "javascript:var svgElement=document.getElementById('shadow3');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color)) + "');};";
                                        GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_three);



                                    }}))

                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);


                        break;

                }
            }
        };
        AdapterView.OnItemClickListener hairs = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = " javascript:document.getElementById('hairskrillex').setAttribute('fill','"+ Utils.convertHexColorString(color)  +"');";
                                        GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                    }
                                }))
                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);


                        break;
                }
            }
        };
        AdapterView.OnItemClickListener saves = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:

                        break;

                }
            }
        };



        list.add(background);
        list.add(head);
        list.add(hand);
        list.add(hairs);
        list.add(clothes);
        list.add(saves);

        return list;
    }

}
