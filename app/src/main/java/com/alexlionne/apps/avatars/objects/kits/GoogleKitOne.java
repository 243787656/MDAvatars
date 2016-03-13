package com.alexlionne.apps.avatars.objects.kits;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.objects.Kit;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Alex on 08/03/2016.
 */

public class GoogleKitOne extends Kit {
    private Context context;

        public GoogleKitOne(Context context) {
            super(context);
            this.context = context.getApplicationContext();
            super.setName("Google-Kit I");
            super.setSmallDesc("Material palette, grain shadows,rounded shapes ");
            super.setShowcase(R.drawable.gmd_kit_1);
            super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
            super.setSvg("file:///android_asset/gmd_kit_1.html");
            super.setCategories(getGoogleKitOneCategories());
            super.setListener(getGoogleKitOneListeners());


        }

    private ArrayList<ArrayList<String>> getGoogleKitOneCategories(){
        ArrayList<String> head = new ArrayList<>();
                head.add("Head");
                head.add("Style");
                head.add("Skin color");
                head.add("Eyes color");

        ArrayList<String> hairs = new ArrayList<>();
                hairs.add("Hairs");
                hairs.add("Style");
                hairs.add("Color");



        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result.add(head);
        result.add(hairs);

   return result;
    }

    public ArrayList<ArrayList<View.OnClickListener>> getGoogleKitOneListeners() {
        ArrayList<View.OnClickListener> head = new ArrayList<>();
        //adding a first null element to override Title in over Arrays
        head.add(null);
        View.OnClickListener head_style = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"test",Toast.LENGTH_LONG).show();
                String javascript = "javascript:document.getElementById('body').setAttribute('fill','"+"none"/*+Utils.convertHexColorString(getResources().getColor(android.R.color.transparent))*/  +"');";
                       GoogleKitOne.super.getWebView().loadUrl(javascript);

            }
        };


        ArrayList<ArrayList<View.OnClickListener>> result = new ArrayList<>();
        result.add(head);
        return result;
    }
}