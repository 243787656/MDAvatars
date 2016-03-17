package com.alexlionne.apps.avatars.objects.kits;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils;
import com.alexlionne.apps.avatars.objects.Kit;
import com.google.common.primitives.Ints;
import com.kennyc.colorchooser.ColorChooserDialog;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Alex on 08/03/2016.
 */

public class GoogleKitOne extends Kit {
    private Context context;

        public GoogleKitOne(Context context) {
            super(context);
            this.context = context.getApplicationContext();
            super.setName("Google I");
            super.setSmallDesc("Material Design ! ");
            super.setShowcase(R.drawable.gmd_kit_1);
            super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
            super.setSvg("file:///android_asset/gmd_kit_1.html");
            super.setCategories(getGoogleKitOneCategories());
            super.setListener(getGoogleKitOneListeners());
            super.setDefaultBgColor(context.getResources().getColor(R.color.md_green_200));


        }

    private ArrayList<ArrayList<String>> getGoogleKitOneCategories(){
        ArrayList<String> background = new ArrayList<>();
                background.add("Background");
                background.add("Color");
                background.add("Image");

        ArrayList<String> head = new ArrayList<>();
                head.add("Head");
                head.add("Style");
                head.add("Skin color");
                head.add("Eyes color");

        ArrayList<String> hairs = new ArrayList<>();
                hairs.add("Hairs");
                hairs.add("Style");
                hairs.add("Color");

        ArrayList<String> body = new ArrayList<>();
                body.add("Body");
                body.add("Style");
                body.add("Color");

        ArrayList<String> skin = new ArrayList<>();
                skin.add("Skin");
                skin.add("color");

        ArrayList<String> arms = new ArrayList<>();
                arms.add("Arms");
                arms.add("left");
                arms.add("right");

        ArrayList<String> accessories = new ArrayList<>();
                accessories.add("Accessories");
                accessories.add("phone");
                accessories.add("glasses");
                accessories.add("fork");

        ArrayList<String> saves = new ArrayList<>();
        saves.add("Save and options");
        saves.add("shadow");
        saves.add("hide left arm");



        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result.add(background);
        result.add(head);
        result.add(hairs);
        result.add(body);
        result.add(skin);
        result.add(arms);
        result.add(accessories);
        result.add(saves);

   return result;
    }
    public ArrayList<AdapterView.OnItemClickListener> getGoogleKitOneListeners(){
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

                                        GoogleKitOne.super.getWebView().setBackgroundColor(color);
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
                        Toast.makeText(context,"[insert image here !]",Toast.LENGTH_LONG).show();
                        break;
                }


            }
        };


        AdapterView.OnItemClickListener head_list = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = "javascript:document.body.style.background = " + color + ";";
                                        GoogleKitOne.super.getWebView().loadUrl(javascript);
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
                        Toast.makeText(context,"case 1 clicked !",Toast.LENGTH_LONG).show();
                        break;
                }


            }
        };

        AdapterView.OnItemClickListener body = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:
                        Toast.makeText(context,"case style clicked !",Toast.LENGTH_LONG).show();
                        break;
                    case 1:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {

                                        String javascript = " javascript:document.getElementById('body').setAttribute('fill','"+Utils.convertHexColorString(color)  +"');";
                                        GoogleKitOne.super.getWebView().loadUrl(javascript);
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
        list.add(background);
        list.add(head_list);
        list.add(head_list);
        list.add(body);
        list.add(head_list);
        list.add(head_list);
        list.add(head_list);
        list.add(head_list);
    return list;}

}