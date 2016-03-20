package com.alexlionne.apps.avatars.objects.kits;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils;
import com.alexlionne.apps.avatars.adapters.CustomAdapter;
import com.alexlionne.apps.avatars.fragments.EditionFragment;
import com.alexlionne.apps.avatars.fragments.LatestKitFragment;
import com.alexlionne.apps.avatars.objects.Bubble;
import com.alexlionne.apps.avatars.objects.Item;
import com.alexlionne.apps.avatars.objects.Kit;
import com.alexlionne.apps.avatars.objects.ListItem;
import com.kennyc.colorchooser.ColorChooserDialog;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;

public class GoogleKitTwo extends Kit {
    private Context context;
    private static int selectedColor ;
    private String hairs;
    private String clothes;
    private String logo;
    private static Bubble skin;
    private static Bubble background;
    private static Bubble hairs_c;
    private static Bubble clothes_c;
    private static Bubble hand_c;





    public GoogleKitTwo(Context context) {
        super(context);
        this.context = context.getApplicationContext();
        super.setName("Google II");
        super.setSmallDesc("Material Design ! V2");
        super.setShowcase(R.drawable.gmd_kit_2);
        super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
        super.setSvg("file:///android_asset/gmd_kit_2.html");
        super.setCategories(getGoogleKitTwoCategories());
        super.setListener(getGoogleKitTwoListeners());
        super.setDefaultBgColor(context.getResources().getColor(R.color.md_orange_700));
        setHairs("hairskrillex");
        setClothes("mainbody");
        setLogo("logogoogle");

        skin = new Bubble();
        skin.setColor(context.getResources().getColor(R.color.md_amber_300));
        background = new Bubble();
        background.setColor(context.getResources().getColor(R.color.md_orange_700));
        hairs_c = new Bubble();
        hairs_c.setColor(context.getResources().getColor(R.color.md_brown_500));
        clothes_c = new Bubble();
        clothes_c.setColor(context.getResources().getColor(R.color.md_amber_500));
        hand_c = new Bubble();
        hand_c.setColor(context.getResources().getColor(R.color.md_amber_300));
    }


    public void setHairs(String hairs){
        this.hairs = hairs;
    }
    public String getHairs(){
       return this.hairs;
    }
    public void setClothes(String clothes){
        this.clothes = clothes;
    }
    public String getClothes(){
        return this.clothes;
    }
    public void setLogo(String logo){
        this.logo = logo;
    }
    public String getLogo(){
        return this.logo;
    }


    private ArrayList<ListItem> getGoogleKitTwoCategories() {

        ListItem background = new ListItem();
        background.setTitle("Background");
        background.addItem(new Item("Color", false, true, GoogleKitTwo.background));
        background.addItem(new Item("Image", false, false, null));

        ListItem head= new ListItem();
        head.setTitle("Head");
        head.addItem(new Item("Skin color", false, true, GoogleKitTwo.skin));

        ListItem hand = new ListItem();
        hand.setTitle("Hand");
        hand.addItem(new Item("Match with skin color", true, false, null));
        hand.addItem(new Item("Color", false, true, GoogleKitTwo.hand_c));

        ListItem hairs = new ListItem();
        hairs.setTitle("Hairs");
        hairs.addItem(new Item("Style", false, false,null));
        hairs.addItem(new Item("Color", false, true, GoogleKitTwo.hairs_c));

        ListItem clothes = new ListItem();
        clothes.setTitle("Clothes");
        clothes.addItem(new Item("Style", false, false, null));
        clothes.addItem(new Item("Color", false, true,GoogleKitTwo.clothes_c));
        clothes.addItem(new Item("Image", false, false,null));
        clothes.addItem(new Item("Logo", false, false,null));

        ListItem accessories = new ListItem();
        accessories.setTitle("Accessories");
        accessories.addItem(new Item("SmartPhone", true, false,null));
        accessories.addItem(new Item("Watch", true,false,null));
        accessories.addItem(new Item("Headphones", true,false,null));

        ListItem save = new ListItem();
        save.setTitle("Save and options");
        save.addItem(new Item("Save to sd card", true, false,null));
        save.addItem(new Item("Share", false, false,null));


        ArrayList<ListItem> result = new ArrayList<>();
        result.add(background);
        result.add(head);
        result.add(hand);
        result.add(hairs);
        result.add(clothes);
        result.add(accessories);
        result.add(save);


        return result;
    }

    public ArrayList<CustomAdapter.OnItemClickListener> getGoogleKitTwoListeners(){
        ArrayList<CustomAdapter.OnItemClickListener> list = new ArrayList<>();
        CustomAdapter.OnItemClickListener background = new  CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p) {


                switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {

                                        selectedColor = color;
                                        GoogleKitTwo.super.getWebView().setBackgroundColor(color);
                                        GoogleKitTwo.background.setColor(color);
                                        AvatarActivity.view.setBackgroundColor(color);
                                       //AvatarActivity.button.setTextColor(Utils.getAccentDarkColor(color));
                                        //AvatarActivity.back.setTextColor(Utils.getAccentDarkColor(color));


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
                    case 1 :
                        AvatarActivity.selectImageBackground();
                        break;

                }

            }
        };



        CustomAdapter.OnItemClickListener head = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, int p) {


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
        CustomAdapter.OnItemClickListener hand = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, int p) {

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
        CustomAdapter.OnItemClickListener clothes = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, int p) {
                final String default_color = "#F89921";
                final int color =  Color.parseColor(default_color);


                switch (p) {
                    case 0:

                        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                        View view =  inflater.inflate(R.layout.clothes_dialog, null);
                        final Dialog mBottomSheetDialog = new Dialog (AvatarActivity.getActivity(),
                                R.style.MaterialDialogSheet);
                        mBottomSheetDialog.setContentView(view);
                        mBottomSheetDialog.setCancelable(true);
                        mBottomSheetDialog.getWindow ().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                                Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, context.getResources().getDisplayMetrics())));
                        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
                        mBottomSheetDialog.show();


                        ImageView basic = (ImageView)mBottomSheetDialog.findViewById(R.id.basic);
                        ImageView straight = (ImageView)mBottomSheetDialog.findViewById(R.id.straignt);
                        ImageView round = (ImageView)mBottomSheetDialog.findViewById(R.id.round);
                        ImageView chemise = (ImageView)mBottomSheetDialog.findViewById(R.id.chemise);



                        basic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String javascript_more = "javascript:document.getElementById('uparm').setAttribute('fill','" + default_color+ "');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_more);
                                String javascript_shadow_one = "javascript:var svgElement=document.getElementById('shadow1');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color)) + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_one);
                                String javascript_shadow_two = "javascript:var svgElement=document.getElementById('shadow2');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color))+ "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_two);
                                String javascript_shadow_three = "javascript:var svgElement=document.getElementById('shadow3');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" +Utils.convertHexColorString(Utils.getAccentDarkColor(color))   + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_three);


                                String javascript_e = " javascript:document.getElementById('"+getClothes()+"').setAttribute('fill','transparent');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_e);
                                setClothes("mainbody");
                                String javascript = " javascript:document.getElementById('"+getClothes()+"').setAttribute('fill','"+default_color+"');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                mBottomSheetDialog.dismiss();

                            }
                        });

                        straight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String javascript_more = "javascript:document.getElementById('uparm').setAttribute('fill','" + default_color+ "');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_more);
                                String javascript_shadow_one = "javascript:var svgElement=document.getElementById('shadow1');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color))  + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_one);
                                String javascript_shadow_two = "javascript:var svgElement=document.getElementById('shadow2');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color)) + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_two);
                                String javascript_shadow_three = "javascript:var svgElement=document.getElementById('shadow3');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color)) + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_three);


                                String javascript_e = " javascript:document.getElementById('"+getClothes()+"').setAttribute('fill','transparent');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_e);
                                setClothes("vbody");
                                String javascript = " javascript:document.getElementById('"+getClothes()+"').setAttribute('fill','"+default_color+"');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                mBottomSheetDialog.dismiss();
                            }
                        });

                        round.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String javascript_more = "javascript:document.getElementById('uparm').setAttribute('fill','" + default_color + "');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_more);
                                String javascript_shadow_one = "javascript:var svgElement=document.getElementById('shadow1');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" +Utils.convertHexColorString(Utils.getAccentDarkColor(color)) + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_one);
                                String javascript_shadow_two = "javascript:var svgElement=document.getElementById('shadow2');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color)) + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_two);
                                String javascript_shadow_three = "javascript:var svgElement=document.getElementById('shadow3');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color)) + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_three);


                                String javascript_e = " javascript:document.getElementById('" + getClothes() + "').setAttribute('fill','transparent');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_e);
                                setClothes("ubody");
                                String javascript = " javascript:document.getElementById('" + getClothes() + "').setAttribute('fill','" + default_color + "');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                mBottomSheetDialog.dismiss();

                            }
                        });
                        chemise.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String javascript_more = "javascript:document.getElementById('uparm').setAttribute('fill','" + default_color+ "');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_more);
                                String javascript_shadow_one = "javascript:var svgElement=document.getElementById('shadow1');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color))  + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_one);
                                String javascript_shadow_two = "javascript:var svgElement=document.getElementById('shadow2');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color))  + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_two);
                                String javascript_shadow_three = "javascript:var svgElement=document.getElementById('shadow3');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color))  + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_shadow_three);

                                String javascript_e = " javascript:document.getElementById('"+getClothes()+"').setAttribute('fill','transparent');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_e);
                                setClothes("ubodychemise");
                                String javascript = " javascript:document.getElementById('"+getClothes()+"').setAttribute('fill','"+default_color+"');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                mBottomSheetDialog.dismiss();

                            }
                        });
                        break;
                    case 1:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = "javascript:document.getElementById('"+getClothes()+"').setAttribute('fill','" + Utils.convertHexColorString(color) + "');";
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
                    case 2:

                        LayoutInflater inflater2 = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                        View view2 =  inflater2.inflate(R.layout.logo_dialog, null);
                        final Dialog mBottomSheetDialog2 = new Dialog (AvatarActivity.getActivity(),
                                R.style.MaterialDialogSheet);
                        mBottomSheetDialog2.setContentView(view2);
                        mBottomSheetDialog2.setCancelable(true);
                        mBottomSheetDialog2.getWindow ().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                                Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, context.getResources().getDisplayMetrics())));
                        mBottomSheetDialog2.getWindow().setGravity(Gravity.BOTTOM);
                        mBottomSheetDialog2.show();


                        ImageView google = (ImageView)mBottomSheetDialog2.findViewById(R.id.google);
                        ImageView android = (ImageView)mBottomSheetDialog2.findViewById(R.id.android);


                        google.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new ColorChooserDialog.Builder(context.getApplicationContext())
                                        .colors(R.array.md_colors)
                                        .listener((new ColorChooserDialog.ColorListener() {
                                            @Override
                                            public void onColorSelect(int color) {

                                                String javascript_e = " javascript:document.getElementById('"+getLogo()+"').setAttribute('fill','transparent');";
                                                GoogleKitTwo.super.getWebView().loadUrl(javascript_e);
                                                setClothes("logogoogle");
                                                String javascript = " javascript:document.getElementById('"+getLogo()+"').setAttribute('fill','"+Utils.convertHexColorString(color)+"');";
                                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                                mBottomSheetDialog2.dismiss();



                                            }}))

                                        .positiveButton("Okay")
                                        .negativeButton("Cancel")
                                        .title("Select Color")
                                        .positiveButtonColor(Color.BLUE)
                                        .build()
                                        .show(AvatarActivity.fragmentManager, null);


                            }
                        });

                        /*android.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String javascript_e = " javascript:document.getElementById('"+getLogo()+"').setAttribute('fill','transparent');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_e);
                                setLogo("logoandroid");
                                String javascript= "javascript:var svgElement=document.getElementById('"+getLogo()+"');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(Utils.getAccentDarkColor(color)) + "');};";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                mBottomSheetDialog2.dismiss();

                            }
                        });*/



                        break;
                    case 3 :
                        AvatarActivity.selectImageBodyBackground();

                        break;

                }
            }
        };
        CustomAdapter.OnItemClickListener hairs = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, int p) {

                switch (p) {
                    case 0:

                        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                        View view =  inflater.inflate(R.layout.hairs_dialog, null);
                        final Dialog mBottomSheetDialog = new Dialog (AvatarActivity.getActivity(),
                                R.style.MaterialDialogSheet);
                        mBottomSheetDialog.setContentView(view);
                        mBottomSheetDialog.setCancelable(true);
                        mBottomSheetDialog.getWindow ().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                                Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, context.getResources().getDisplayMetrics())));
                        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
                        mBottomSheetDialog.show();


                        ImageView no_hairs = (ImageView)mBottomSheetDialog.findViewById(R.id.no_hairs);
                        ImageView hairs_short = (ImageView)mBottomSheetDialog.findViewById(R.id.hairs_short);
                        ImageView semi_long = (ImageView)mBottomSheetDialog.findViewById(R.id.semi_long);
                        ImageView spiky = (ImageView)mBottomSheetDialog.findViewById(R.id.spike);
                        ImageView long_h = (ImageView)mBottomSheetDialog.findViewById(R.id.long_hairs);


                        final String hairs = getHairs();
                        no_hairs.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String javascript = " javascript:document.getElementById('"+hairs+"').setAttribute('fill','transparent');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                mBottomSheetDialog.dismiss();

                            }
                        });

                        semi_long.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String javascript_e = " javascript:document.getElementById('"+getHairs()+"').setAttribute('fill','transparent');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_e);
                                setHairs("hairssemilong");
                                String javascript = " javascript:document.getElementById('"+getHairs()+"').setAttribute('fill','#795548');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                mBottomSheetDialog.dismiss();
                            }
                        });

                        hairs_short.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String javascript_e = " javascript:document.getElementById('"+getHairs()+"').setAttribute('fill','transparent');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_e);
                                setHairs("hairsshort");
                                String javascript = " javascript:document.getElementById('"+getHairs()+"').setAttribute('fill','#795548');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                mBottomSheetDialog.dismiss();

                            }
                        });

                        spiky.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String javascript_e = " javascript:document.getElementById('"+getHairs()+"').setAttribute('fill','transparent');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_e);
                                setHairs("hairsspiky");
                                String javascript = " javascript:document.getElementById('"+getHairs()+"').setAttribute('fill','#795548');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                mBottomSheetDialog.dismiss();
                            }
                        });

                        long_h.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String javascript_e = " javascript:document.getElementById('"+getHairs()+"').setAttribute('fill','transparent');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript_e);
                                setHairs("hairskrillex");
                                String javascript = " javascript:document.getElementById('"+getHairs()+"').setAttribute('fill','#795548');";
                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                mBottomSheetDialog.dismiss();
                            }
                        });



                        break;
                    case 1:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {

                                            String javascript = " javascript:document.getElementById('" + getHairs() + "').setAttribute('fill','" + Utils.convertHexColorString(color) + "');";
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
        CustomAdapter.OnItemClickListener accessories = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, int p) {

                switch (p) {
                    case 0:

                        break;

                }
            }
        };
        CustomAdapter.OnItemClickListener saves = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, int p) {

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
        list.add(accessories);
        list.add(saves);

        return list;
    }


}
