package com.alexlionne.apps.avatars.objects.kits;

import android.Manifest;
import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alexlionne.apps.avatars.AvatarActivity;;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils.DirectoryChooserFragment;
import com.alexlionne.apps.avatars.Utils.UIManager;
import com.alexlionne.apps.avatars.Utils.Utils;
import com.alexlionne.apps.avatars.adapters.CustomAdapter;;
import com.alexlionne.apps.avatars.objects.Item;
import com.alexlionne.apps.avatars.objects.Kit;
import com.alexlionne.apps.avatars.objects.ListItem;
import com.alexlionne.apps.avatars.objects.Options;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import org.xdty.preference.colorpicker.ColorPickerDialog;
import org.xdty.preference.colorpicker.ColorPickerSwatch;

import java.util.ArrayList;


public class GoogleKitTwo extends Kit {

    private Context context;
    private String hairs;
    private String clothes;
    private String logo;
    private final Options checkboxNDisable = new Options().checkboxNDiasable();
    private final Options checkboxNEnable = new Options().checkboxNEnable();
    private final Options colorChooser = new Options().colorChooser();
    private final Options null_options = new Options().null_options();
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final int WHITE = 0;
    private com.alexlionne.apps.avatars.Utils.UIManager UIManager;



    public GoogleKitTwo(Context context) {
        super(context);
        this.context = context.getApplicationContext();
        //name of the KIT
        super.setName("Google II");
        //Small description of the kit
        super.setSmallDesc("Material Design ! V2");
        //setting up a showcase Drawable (shown in kits list)
        super.setShowcase(R.drawable.gmd_kit_2);
        //Icon fo the kit
        super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
        //link to html file (that contain svg), into @assets
        super.setSvg("file:///android_asset/gmd_kit_2.html");
        //two listeners, one for categories properties
        //the other one for their listeners
        super.setCategories(getGoogleKitTwoCategories());
        super.setListener(getGoogleKitTwoListeners());
        //default bg color of WebView
        super.setDefaultBgColor(context.getResources().getColor(R.color.md_orange_700));
        //Initialisazion of the kit (default value)
        init();
    }

void init(){
    //getting preferences
    preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
    editor = preferences.edit();
    //setting up default values
    editor.putInt("BackgroundColor", getDefaultBgColor());
    editor.putInt("SkinColor", context.getResources().getColor(R.color.md_orange_200));
    editor.putInt("HandColor", context.getResources().getColor(R.color.md_orange_200));
    editor.putInt("ClothesColor", context.getResources().getColor(R.color.md_orange_500));
    editor.putInt("HairColor", context.getResources().getColor(R.color.md_brown_500));
    editor.putInt("ArcColor", context.getResources().getColor(R.color.md_orange_400));
    editor.putString("Hair", "hairskrillex");
    editor.putString("Clothes", "mainbody");
    editor.putString("Watch", "moto360");
    editor.apply();

}


    private ArrayList<ListItem> getGoogleKitTwoCategories() {
        preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
        ListItem backgroundItem = new ListItem();
        backgroundItem.setTitle("Background");
        backgroundItem.addItem("BACKGROUND", new Item("Color", "BackgroundColor",
                new IconicsDrawable(context,
                        CommunityMaterial.Icon.cmd_format_color_fill)
                        .sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("BackgroundColor", 0),
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
        hand.addItem("HAND", new Item("Color", "HandColor", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("HandColor", 0),
                colorChooser));



        ListItem clothes = new ListItem();
        clothes.setTitle("Clothes");
        clothes.addItem("CLOTHES", new Item("Style",R.drawable.clothes_style,
                new IconicsDrawable(context, CommunityMaterial.Icon.cmd_tshirt_crew).sizeDp(18), WHITE, colorChooser));
        clothes.addItem(null, new Item("Color", "ClothesColor", new IconicsDrawable(context, CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("ClothesColor", 0),
                colorChooser));
        clothes.addItem("MORE", new Item("Image", R.drawable.image, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_public).sizeDp(18), WHITE, colorChooser));
        clothes.addItem(null, new Item("Logo", R.drawable.logo, new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_google).sizeDp(18), WHITE, colorChooser));


        ListItem accessories = new ListItem();

        accessories.setTitle("Accessories");
        accessories.addItem("PHONE", new Item("Nexus 5X", R.drawable.phone_nexus_5x, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_smartphone).sizeDp(18), WHITE,
                colorChooser));
        accessories.addItem(null, null);

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
        save.addItem("SAVE", new Item("Save to sdcard", WHITE, null_options));
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
            public void onItemClick(View v, final int p,final int fp) {


                switch (p) {
                    case 0:
                        UIManager = new UIManager(GoogleKitTwo.super.getWebView());
                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {

                                AvatarActivity.view.setBackgroundColor(color);
                                UIManager.setWebViewBgColor(color);
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                                UIManager.setNavigationBarColor(color);

                            }

                        });



                        break;
                    case 1 :
                        AvatarActivity.selectImageBackground();
                        break;

                }

            }
        };



        CustomAdapter.OnItemClickListener head = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {
                UIManager = new UIManager(GoogleKitTwo.super.getWebView());
                switch (p) {
                    case 0:

                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color,
                                context.getResources().getIntArray(R.array.md_colors_skin)
                                ,preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp,p,color,Utils.getItem(fp, p).getId());
                            }

                        });

                        break;
                    case 2:
                        final int DEFAULT_BROWN = context.getResources().getColor(R.color.md_brown_500);
                        final Dialog mBottomSheetDialog = inflateDialog(R.layout.hairs_dialog);
                        ImageView no_hairs = (ImageView)mBottomSheetDialog.findViewById(R.id.no_hairs);
                        ImageView hairs_short = (ImageView)mBottomSheetDialog.findViewById(R.id.hairs_short);
                        ImageView semi_long = (ImageView)mBottomSheetDialog.findViewById(R.id.semi_long);
                        ImageView spiky = (ImageView)mBottomSheetDialog.findViewById(R.id.spike);
                        ImageView long_h = (ImageView)mBottomSheetDialog.findViewById(R.id.long_hairs);

                        no_hairs.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UIManager.loadColor("transparent", preferences.getString(getHairs(), null));
                                mBottomSheetDialog.dismiss();

                            }
                        });

                        semi_long.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UIManager.loadColor("transparent", preferences.getString(getHairs(), null));
                                UIManager.update(getHairs(), "hairssemilong");
                                UIManager.loadColor(DEFAULT_BROWN, preferences.getString(getHairs(),null));
                                mBottomSheetDialog.dismiss();
                            }
                        });

                        hairs_short.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UIManager.loadColor("transparent", preferences.getString(getHairs(), null));
                                UIManager.update(getHairs(), "hairsshort");
                                UIManager.loadColor(DEFAULT_BROWN, preferences.getString(getHairs(),null));
                                mBottomSheetDialog.dismiss();

                            }
                        });

                        spiky.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UIManager.loadColor("transparent", preferences.getString(getHairs(), null));
                                UIManager.update(getHairs(), "hairspiky");
                                UIManager.loadColor(DEFAULT_BROWN, preferences.getString(getHairs(),null));
                                mBottomSheetDialog.dismiss();
                            }
                        });

                        long_h.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UIManager.loadColor("transparent", preferences.getString(getHairs(), null));
                                UIManager.update(getHairs(), "hairskrillex");
                                UIManager.loadColor(DEFAULT_BROWN, preferences.getString(getHairs(),null));
                                mBottomSheetDialog.dismiss();
                            }
                        });
break;
                    case  3 :


                        dialog =  UIManager.colorChooser(R.string.choose_color,context.getResources().getIntArray(R.array.md_colors_skin),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, preferences.getString(getHairs(),null));
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }

                        });
                        break;

                }
            }
        };
        CustomAdapter.OnItemClickListener hand = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {
                UIManager = new UIManager(GoogleKitTwo.super.getWebView());
                switch (p) {
                    case 0:

                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors_skin),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }

                        });
                        break;


                }
            }
        };
        final CustomAdapter.OnItemClickListener clothes = new CustomAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View v, final int p, final int fp) {
                final String default_color = "#F89921";
                final String default_color_white = "#FFFFFF";
                final int color =  Color.parseColor(default_color);
                final int color_white =  Color.parseColor(default_color_white);
                UIManager = new UIManager(GoogleKitTwo.super.getWebView());

                switch (p) {
                    case 0:

                        final Dialog mBottomSheetDialog = inflateDialog(R.layout.clothes_dialog);


                        ImageView basic = (ImageView)mBottomSheetDialog.findViewById(R.id.basic);
                        ImageView straight = (ImageView)mBottomSheetDialog.findViewById(R.id.straignt);
                        ImageView round = (ImageView)mBottomSheetDialog.findViewById(R.id.round);
                        ImageView chemise = (ImageView)mBottomSheetDialog.findViewById(R.id.chemise);



                        basic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UIManager.loadColorforGroup("transparent", "ellipse", "buttons");
                                UIManager.loadColorforGroup("transparent", "polygon", "col");
                                //making previous clothes transparent
                                UIManager.loadColor("transparent", preferences.getString(getClothes(), null));
                                //setting new clothes
                                UIManager.update(getClothes(), "mainbody");
                                UIManager.loadColor(default_color, preferences.getString(getClothes(),null));
                                UIManager.loadColor(default_color, "uparm");
                                //dealing with shadows
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow1");
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow2");
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow3");
                                //dismiss the dialog after click
                                UIManager.updateView(fp, p+1, color, Utils.getItem(fp, p+1).getId());
                                mBottomSheetDialog.dismiss();

                            }
                        });

                        straight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UIManager.loadColorforGroup("transparent", "ellipse", "buttons");
                                UIManager.loadColorforGroup("transparent", "polygon", "col");
                                //making previous clothes transparent
                                UIManager.loadColor("transparent", preferences.getString(getClothes(),null));
                                //setting new clothes
                                UIManager.update(getClothes(), "vbody");
                                UIManager.loadColor(default_color, preferences.getString(getClothes(),null));
                                UIManager.loadColor(default_color, "uparm");
                                //dealing with shadows
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow1");
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow2");
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow3");
                                //dismiss the dialog after click
                                UIManager.updateView(fp, p+1, color, Utils.getItem(fp, p+1).getId());
                                mBottomSheetDialog.dismiss();
                            }
                        });

                        round.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UIManager.loadColorforGroup("transparent", "ellipse", "buttons");
                                UIManager.loadColorforGroup("transparent", "polygon", "col");
                                //making previous clothes transparent
                                UIManager.loadColor("transparent", preferences.getString(getClothes(),null));
                                //setting new clothes
                                UIManager.update(getClothes(), "ubody");
                                UIManager.loadColor(default_color, preferences.getString(getClothes(),null));
                                UIManager.loadColor(default_color, "uparm");
                                //dealing with shadows
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow1");
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow2");
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow3");
                                //dismiss the dialog after click
                                UIManager.updateView(fp, p+1, color, Utils.getItem(fp, p+1).getId());
                                mBottomSheetDialog.dismiss();

                            }
                        });
                        chemise.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                UIManager.loadColor("transparent", preferences.getString(getClothes(),null));
                                UIManager.update(getClothes(), "ubodychemise");
                                UIManager.loadColorforGroup("#212121", "ellipse", "buttons");
                                UIManager.loadColorforGroup("#212121", "polygon", "col");
                                UIManager.loadColor("#ffffff", preferences.getString(getClothes(),null));
                                UIManager.loadColor("#ffffff", "uparm");
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color_white), "shadow1");
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color_white), "shadow2");
                                UIManager.loadColorforGroup(Utils.getAccentDarkColor(color_white), "shadow3");
                                UIManager.updateView(fp, p + 1, color_white, Utils.getItem(fp, p + 1).getId());
                                mBottomSheetDialog.dismiss();

                            }
                        });
                        break;
                    case 1:

                        if(!preferences.getString(getClothes(),null).equals("ubodychemise")) {
                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(), 0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                    UIManager.loadColor(color, preferences.getString(getClothes(),null));
                                    UIManager.loadColor(color, "uparm");
                                    UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow1");
                                    UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow2");
                                    UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow3");
                                    UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }


                        });}else {
                            ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(), 0));
                            dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                                @Override
                                public void onColorSelected(int color) {
                                    UIManager.loadColor("transparent", preferences.getString(getClothes(), null));
                                    UIManager.update(getClothes(), "ubodychemise");
                                    UIManager.loadColor(color, preferences.getString(getClothes(), null));
                                    UIManager.loadColor(color, "uparm");
                                    UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow1");
                                    UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow2");
                                    UIManager.loadColorforGroup(Utils.getAccentDarkColor(color), "shadow3");
                                    UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());

                                    ColorPickerDialog dialog = UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors), preferences.getInt(Utils.getItem(fp, p).getId(), 0));
                                    dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                                        @Override
                                        public void onColorSelected(int color) {
                                            UIManager.loadColorforGroup(color, "polygon", "col");
                                            ColorPickerDialog dialog = UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors), preferences.getInt(Utils.getItem(fp, p).getId(), 0));
                                            dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                                                @Override
                                                public void onColorSelected(int color) {
                                                    UIManager.loadColorforGroup(color, "ellipse", "buttons");
                                                }});

                                        }});
                                }});



                        }

                        break;
                    case 3:

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


                        /*google.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ColorPickerDialog dialog =  UIManager.colorChooser(context.getResources().getIntArray(R.array.md_colors_skin),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                                dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                                    @Override
                                    public void onColorSelected(int color) {
                                        //UIManager = new UIManager(GoogleKitTwo.super.getWebView());
                                        // UIManager.loadColor(color, getHairs());

                                        String javascript_e = " javascript:document.getElementById('"+getLogo()+"').setAttribute('fill','transparent');";
                                                GoogleKitTwo.super.getWebView().loadUrl(javascript_e);
                                                setClothes("logogoogle");
                                                String javascript = " javascript:document.getElementById('"+getLogo()+"').setAttribute('fill','"+Utils.convertHexColorString(color)+"');";
                                                GoogleKitTwo.super.getWebView().loadUrl(javascript);
                                                mBottomSheetDialog2.dismiss();

                                    }

                                });



                            }
                        });*/

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
                    case 2 :
                        AvatarActivity.selectImageBodyBackground(getClothes());


                        break;

                }
            }
        };
        CustomAdapter.OnItemClickListener accessories = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {

                UIManager = new UIManager(GoogleKitTwo.super.getWebView());
                switch (p) {
                    case 0:
                        ColorPickerDialog dialog0 =  UIManager.colorChooser(R.string.arc_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog0.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColorforItemInGroup(color,"Nexus5x","BodyColor");
                                ColorPickerDialog dialog1 =  UIManager.colorChooser(R.string.coussins_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(), 0));
                                dialog1.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                                    @Override
                                    public void onColorSelected(int color) {
                                        UIManager.loadColorforItemInGroup(color, "Nexus5x", "CameraColor");
                                        ColorPickerDialog dialog2 = UIManager.colorChooser(R.string.coussins_color, context.getResources().getIntArray(R.array.md_colors), preferences.getInt(Utils.getItem(fp, p).getId(), 0));
                                        dialog2.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                                            @Override
                                            public void onColorSelected(int color) {
                                                UIManager.loadColorStrokeforItemInGroup(color, "Nexus5x", "FingerPrintStrokeColor");
                                            }
                                        });
                                    }
                                });
                            }});


                        break;
                    case 3 :
                        UIManager.loadColor("transparent", "Bracelet");
                        UIManager.loadColor("transparent", "Circular");
                        UIManager.loadStrokeColor("transparent", "LongColor");
                        ColorPickerDialog dialog3 =  UIManager.colorChooser(R.string.bracelet_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog3.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, "Bracelet");
                                ColorPickerDialog dialog4 =  UIManager.colorChooser(R.string.long_needle_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(), 0));
                                dialog4.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                                    @Override
                                    public void onColorSelected(int color) {
                                        UIManager.loadColor(color, "Rectangular");
                                    }
                                });
                            }});

                        break;
                    case 2:
                        UIManager.loadColor("transparent", "Rectangular");
                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.bracelet_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, "Bracelet");
                                ColorPickerDialog dialog4 =  UIManager.colorChooser(R.string.long_needle_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(), 0));
                                dialog4.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                                    @Override
                                    public void onColorSelected(int color) {
                                        UIManager.loadColor(color, "Circular");
                                        ColorPickerDialog dialog6 =  UIManager.colorChooser(R.string.screen_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(), 0));
                                        dialog6.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                                            @Override
                                            public void onColorSelected(int color) {
                                                UIManager.loadStrokeColor(color, "LongColor");
                                                ColorPickerDialog dialog7 =  UIManager.colorChooser(R.string.short_needle_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(), 0));
                                                dialog7.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                                                    @Override
                                                    public void onColorSelected(int color) {
                                                        UIManager.loadStrokeColor(color, "ShortColor");

                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }});

                        break;


                    case 4:
                        ColorPickerDialog dialog4 =  UIManager.colorChooser(R.string.arc_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog4.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, "ArcColor");
                                ColorPickerDialog dialog5 =  UIManager.colorChooser(R.string.coussins_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(), 0));
                                dialog5.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                                    @Override
                                    public void onColorSelected(int color) {
                                        UIManager.loadColorforGroup(color, "HeadSpeakersColor");
                                    }
                                });
                            }});

                        break;

                }
            }
        };
        CustomAdapter.OnItemClickListener saves = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, int p,int fp) {

                switch (p) {
                    case 0:
                            AvatarActivity.showDirectoryChooser();
                        break;
                    case 1:
                            AvatarActivity.share();
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



    public String getHairs(){
        return "Hair";
    }

    public String getClothes(){
        return "Clothes";
    }
    public String getWatch(){
        return "Watch";
    }
    public String getLogo(){return preferences.getString("Logo", null);}

    public Dialog inflateDialog(int layout) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, null);
        final Dialog mBottomSheetDialog = new Dialog(AvatarActivity.getActivity(),
                R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, context.getResources().getDisplayMetrics())));
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        return mBottomSheetDialog;
    }


}
