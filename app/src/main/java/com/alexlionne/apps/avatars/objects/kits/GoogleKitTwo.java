package com.alexlionne.apps.avatars.objects.kits;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alexlionne.apps.avatars.AvatarActivity;;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils;
import com.alexlionne.apps.avatars.adapters.CustomAdapter;;
import com.alexlionne.apps.avatars.objects.Item;
import com.alexlionne.apps.avatars.objects.Kit;
import com.alexlionne.apps.avatars.objects.ListItem;
import com.alexlionne.apps.avatars.objects.Options;
import com.kennyc.colorchooser.ColorChooserDialog;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;




public class GoogleKitTwo extends Kit {
    private Context context;
    private static int selectedColor ;
    private String hairs;
    private String clothes;
    private String logo;
    private final Options checkboxNDiasable = new Options().checkboxNDiasable();
    private final Options checkboxNEnable = new Options().checkboxNEnable();
    private final Options colorChooser = new Options().colorChooser();
    private final Options null_options = new Options().null_options();
    private ListItem backgroundItem;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final int WHITE = 0;


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
        init();
    }


void init(){
    setHairs("hairskrillex");
    setClothes("mainbody");
    setLogo("logogoogle");
    preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
    editor = preferences.edit();
    editor.putInt("BackgroundColor", getDefaultBgColor());
    editor.putInt("SkinColor", context.getResources().getColor(R.color.md_yellow_300));
    editor.putInt("HandColor", context.getResources().getColor(R.color.md_yellow_300));
    editor.putInt("ClothesColor", context.getResources().getColor(R.color.md_orange_500));
    editor.putInt("HairColor", context.getResources().getColor(R.color.md_brown_500));
    editor.apply();

}




    private ArrayList<ListItem> getGoogleKitTwoCategories() {
        preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
        backgroundItem = new ListItem();
        backgroundItem.setTitle("Background");
        backgroundItem.addItem("BACKGROUND", new Item("Color", "BackgroundColor",
                new IconicsDrawable(context,
                        CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("BackgroundColor", 0),
                colorChooser));
        backgroundItem.addItem(null, new Item("Image", R.drawable.image, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_camera).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), WHITE,
                colorChooser));

        ListItem head = new ListItem();
        head.setTitle("Head");
        head.addItem("HEAD", new Item("Skin color", "SkinColor", new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_face).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("SkinColor", 0),
                colorChooser));
        head.addItem(null, null);
        head.addItem("HAIR", new Item("Hair style", R.drawable.hair_style, new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_content_cut).sizeDp(18), WHITE,
                colorChooser));
        head.addItem(null, new Item("Hair color", "HairColor", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_invert_colors).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("HairColor", 0),
                colorChooser));
        ListItem hand = new ListItem();
        hand.setTitle("Hand");
        hand.addItem("HAND", new Item("Match with skin", preferences.getInt("SkinColor", 0), checkboxNEnable));
        hand.addItem(null, new Item("Color", "HandColor", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("HandColor", 0),
                colorChooser));



        ListItem clothes = new ListItem();
        clothes.setTitle("Clothes");
        clothes.addItem("CLOTHES", new Item("Style",R.drawable.clothes_style,
                new IconicsDrawable(context, CommunityMaterial.Icon.cmd_tshirt_crew).sizeDp(18), WHITE, colorChooser));
        clothes.addItem(null, new Item("Color", "ClothesColor", new IconicsDrawable(context,  CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("ClothesColor", 0),
                colorChooser));
        clothes.addItem("MORE", new Item("Image", R.drawable.image, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_camera).sizeDp(18), WHITE, colorChooser));
        clothes.addItem(null, new Item("Logo", R.drawable.logo, new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_google).sizeDp(18), WHITE, colorChooser));


        ListItem accessories = new ListItem();

        accessories.setTitle("Accessories");
        accessories.addItem("PHONE", new Item("Nexus 5X",  R.drawable.phone_nexus_5x, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_smartphone).sizeDp(18), WHITE,
                colorChooser));
        accessories.addItem(null, new Item("Nexus 6P", R.drawable.phone_nexus_6p, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_smartphone).sizeDp(18), WHITE,
                colorChooser));
        accessories.addItem(null, new Item("OnePlus 2", R.drawable.phone_op_2, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_smartphone).sizeDp(18), WHITE,
                colorChooser));
        accessories.addItem(null, new Item("XperiaZ5", R.drawable.phone_speria_z5, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_smartphone).sizeDp(18), WHITE,
                colorChooser));


        accessories.addItem("WATCHES", new Item("Moto 360", R.drawable.moto360, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_watch).sizeDp(18), WHITE,
                colorChooser));
        accessories.addItem(null, new Item("LG GWatch", R.drawable.lg_watch, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_watch).sizeDp(18), WHITE,
                colorChooser));

        accessories.addItem("HEADPHONES", new Item("Classic", R.drawable.headphones, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_headset).sizeDp(18), WHITE,
                colorChooser));


        ListItem save = new ListItem();
        save.setTitle("Save and options");
        save.addItem("Saving", new Item("Save to /sdcard", WHITE, checkboxNDiasable));
        save.addItem(null, new Item("Share", WHITE, null_options));


        ArrayList<ListItem> result = new ArrayList<>();
        result.add(backgroundItem);
        result.add(head);
        result.add(hand);
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
                                        AvatarActivity.view.setBackgroundColor(color);

                                        TextView text = getAllcategories().get(0).getItem(0).getTextView();
                                        text.setTextColor(Utils.getAccentLightColor(color));
                                        CardView card = getAllcategories().get(0).getItem(0).getCard();
                                        getAllcategories().get(0).getItem(0).getIcon().color(Utils.getAccentLightColor(color));
                                        card.setVisibility(View.INVISIBLE);
                                        card.setCardBackgroundColor(color);
                                        Utils.reveal(card);
                                        AvatarActivity.changeColor("BackgroundColor",color);



                                        if (Build.VERSION.SDK_INT >= 21) {
                                            Window window = AvatarActivity.getWindowView();
                                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                                            window.setStatusBarColor(Utils.getAccentDarkColor(color));
                                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                            window.setNavigationBarColor(color);
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

                                        String javascript = " javascript:document.getElementById('head').setAttribute('fill','"+ Utils.convertHexColorString(color)  +"');";
                                        GoogleKitTwo.super.getWebView().loadUrl(javascript);

                                        TextView text = getAllcategories().get(1).getItem(0).getTextView();
                                        text.setTextColor(Utils.getAccentLightColor(color));
                                        CardView card = getAllcategories().get(1).getItem(0).getCard();
                                        getAllcategories().get(1).getItem(0).getIcon().color(Utils.getAccentLightColor(color));
                                        card.setVisibility(View.INVISIBLE);
                                        card.setCardBackgroundColor(color);
                                        Utils.reveal(card);
                                        AvatarActivity.changeColor("SkinColor", color);

                                    }
                                }))
                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);



                        break;
                    case 2:

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
                    case  3 :

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {

                                        String javascript = " javascript:document.getElementById('" + getHairs() + "').setAttribute('fill','" + Utils.convertHexColorString(color) + "');";
                                        GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                        TextView text = getAllcategories().get(1).getItem(3).getTextView();
                                        text.setTextColor(Utils.getAccentLightColor(color));
                                        CardView card = getAllcategories().get(1).getItem(3).getCard();
                                        getAllcategories().get(1).getItem(3).getIcon().color(Utils.getAccentLightColor(color));
                                        card.setVisibility(View.INVISIBLE);
                                        card.setCardBackgroundColor(color);
                                        Utils.reveal(card);
                                        AvatarActivity.changeColor("HairColor", color);

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
                        if (getAllcategories().get(2).getItem(0).isEnable()){
                            getAllcategories().get(2).getItem(1).getLayout().setClickable(false);
                            getAllcategories().get(2).getItem(1).getTextView().setTextColor(context.getResources().getColor(R.color.md_grey_500));
                        }
                        break;
                    case 1:
                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors_skin)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {

                                        String javascript = " javascript:document.getElementById('hand').setAttribute('fill','"+ Utils.convertHexColorString(color)  +"');";
                                        GoogleKitTwo.super.getWebView().loadUrl(javascript);

                                        TextView text = getAllcategories().get(2).getItem(1).getTextView();
                                        text.setTextColor(Utils.getAccentLightColor(color));
                                        CardView card = getAllcategories().get(2).getItem(1).getCard();
                                        getAllcategories().get(2).getItem(1).getIcon().color(Utils.getAccentLightColor(color));
                                        card.setVisibility(View.INVISIBLE);
                                        card.setCardBackgroundColor(color);
                                        Utils.reveal(card);
                                        AvatarActivity.changeColor("HandColor", color);

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

                                        TextView text = getAllcategories().get(3).getItem(1).getTextView();
                                        text.setTextColor(Utils.getAccentLightColor(color));
                                        CardView card = getAllcategories().get(3).getItem(1).getCard();
                                        getAllcategories().get(3).getItem(1).getIcon().color(Utils.getAccentLightColor(color));
                                        card.setVisibility(View.INVISIBLE);
                                        card.setCardBackgroundColor(color);
                                        Utils.reveal(card);
                                        AvatarActivity.changeColor("ClothesColor", color);

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
                        AvatarActivity.selectImageBodyBackground(getClothes());


                        break;

                }
            }
        };
        CustomAdapter.OnItemClickListener accessories = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, int p) {


                switch (p) {
                    case 4:

                        break;
                    case 1:

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
        list.add(clothes);
        list.add(accessories);
        list.add(saves);

        return list;
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
    public String getLogo(){return this.logo;}


}
