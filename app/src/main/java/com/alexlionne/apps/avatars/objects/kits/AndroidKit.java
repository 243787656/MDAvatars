package com.alexlionne.apps.avatars.objects.kits;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils;
import com.alexlionne.apps.avatars.objects.Kit;
import com.kennyc.colorchooser.ColorChooserDialog;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import java.util.ArrayList;

public class AndroidKit extends Kit {
    private Context context;


    public AndroidKit(Context context) {
        super(context);
        this.context = context.getApplicationContext();
        super.setName("Android Kit");
        super.setSmallDesc("Simple Bugdroid ! ");
        super.setShowcase(R.drawable.android_kit);
        super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_android).sizeDp(18));
        super.setSvg("file:///android_asset/android_kit.html");
        super.setCategories(getAndroidKitCategories());
        super.setListener(getAndroidKitListeners());
        super.setDefaultBgColor(context.getResources().getColor(R.color.md_green_300));
    }

    private ArrayList<ArrayList<String>> getAndroidKitCategories() {
        ArrayList<String> background = new ArrayList<>();
        background.add("Background");
        background.add("Color");

        ArrayList<String> head = new ArrayList<>();
        head.add("Head");
        head.add("Color");

        ArrayList<String> arms = new ArrayList<>();
        arms.add("Arms");
        arms.add("Left arm Color");
        arms.add("Right arm Color");

        ArrayList<String> body = new ArrayList<>();
        body.add("Body");
        body.add("Body Color");

        ArrayList<String> saves = new ArrayList<>();
        saves.add("Save and options");
        saves.add("Save");
        saves.add("Share");

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result.add(background);
        result.add(head);
        result.add(arms);
        result.add(body);
        result.add(saves);


        return result;
    }

    public ArrayList<AdapterView.OnItemClickListener> getAndroidKitListeners(){
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

                                        AndroidKit.super.getWebView().setBackgroundColor(color);
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



        AdapterView.OnItemClickListener head = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = " javascript:document.getElementById('head').setAttribute('fill','"+ Utils.convertHexColorString(color)  +"');";
                                        AndroidKit.super.getWebView().loadUrl(javascript);
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
        AdapterView.OnItemClickListener arms = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:
                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = " javascript:document.getElementById('lef_x5F_arm').setAttribute('fill','"+ Utils.convertHexColorString(color)  +"');";
                                        AndroidKit.super.getWebView().loadUrl(javascript);
                                    }
                                }))
                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);


                        break;
                    case 1:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = " javascript:document.getElementById('right_x5F_arm').setAttribute('fill','"+ Utils.convertHexColorString(color)  +"');";
                                        AndroidKit.super.getWebView().loadUrl(javascript);
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
        AdapterView.OnItemClickListener body = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = " javascript:document.getElementById('body').setAttribute('fill','"+ Utils.convertHexColorString(color)  +"');";
                                        AndroidKit.super.getWebView().loadUrl(javascript);
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
        AdapterView.OnItemClickListener legs = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = " javascript:document.getElementById('lef_x5F_arm').setAttribute('fill','"+ Utils.convertHexColorString(color)  +"');";
                                        AndroidKit.super.getWebView().loadUrl(javascript);
                                    }
                                }))
                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);


                        break;
                    case 1:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = " javascript:document.getElementById('right_x5F_arm').setAttribute('fill','"+ Utils.convertHexColorString(color)  +"');";
                                        AndroidKit.super.getWebView().loadUrl(javascript);
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
        list.add(arms);
        list.add(body);
        list.add(saves);

        return list;
    }

}
