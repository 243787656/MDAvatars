package com.alexlionne.apps.avatars.objects.kits;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils.UIManager;
import com.alexlionne.apps.avatars.Utils.Utils;
import com.alexlionne.apps.avatars.adapters.CustomAdapter;
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

/**
 * Created by Alex on 08/03/2016.
 */

public class GoogleKitOne extends Kit {
    private Context context;

    private final Options checkboxNDisable = new Options().checkboxNDiasable();
    private final Options checkboxNEnable = new Options().checkboxNEnable();
    private final Options colorChooser = new Options().colorChooser();
    private final Options null_options = new Options().null_options();
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final int WHITE = 0;
    private com.alexlionne.apps.avatars.Utils.UIManager UIManager;

        public GoogleKitOne(Context context) {
            super(context);
            this.context = context.getApplicationContext();
            super.setName("Google I");
            super.setSmallDesc("In progress");
            //super.setShowcase(R.drawable.gmd_kit_1);
            super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
            super.setSvg("file:///android_asset/gmd_kit_1.html");
            super.setCategories(getGoogleKitOneCategories());
            super.setListener(getGoogleKitOneListeners());
            super.setDefaultBgColor(context.getResources().getColor(R.color.md_green_200));
            init();
        }


    void init(){
        //getting preferences
        preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
        editor = preferences.edit();
        //setting up default values
        editor.putInt("BackgroundColor", getDefaultBgColor());
        editor.putInt("GMD1_SkinColor", context.getResources().getColor(R.color.md_brown_500));
        editor.putInt("GMD1_BodyColor", context.getResources().getColor(R.color.md_green_A700));
        editor.putInt("GMD1_ArmColor", context.getResources().getColor(R.color.md_green_A700));
        editor.putInt("GMD1_HairColor", context.getResources().getColor(R.color.md_black_1000));
        editor.apply();

    }


    private ArrayList<ListItem> getGoogleKitOneCategories() {
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
        head.addItem("HEAD", new Item("Skin color", "GMD1_SkinColor", new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_face).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("GMD1_HeadColor", 0),
                colorChooser));
        head.addItem(null, new Item("Hair color", "GMD1_HairColor", new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_face).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("GMD1_HairColor", 0),
                colorChooser));

        ListItem body = new ListItem();
        body.setTitle("Body");
        body.addItem("BODY", new Item("Body Color", "GMD1_BodyColor", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("GMD1_BodyColor", 0),
                colorChooser));

        ListItem hand = new ListItem();
        hand.setTitle("Arms");
        hand.addItem("ARMS", new Item("Arms Color", "GMD1_ArmsColor", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("GMD1_ArmsColor", 0),
                colorChooser));


        ListItem save = new ListItem();
        save.setTitle("Save and options");
        save.addItem("SAVE", new Item("Save to sdcard", WHITE, null_options));
        save.addItem(null, new Item("Share", WHITE, null_options));


        ArrayList<ListItem> result = new ArrayList<>();
        result.add(backgroundItem);
        result.add(head);
        result.add(body);
        result.add(hand);
        result.add(save);

        return result;

    }

    public ArrayList<CustomAdapter.OnItemClickListener> getGoogleKitOneListeners(){

        ArrayList<CustomAdapter.OnItemClickListener> list = new ArrayList<>();
        CustomAdapter.OnItemClickListener background = new  CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p, final int fp) {


                switch (p) {
                    case 0:
                        UIManager = new UIManager(GoogleKitOne.super.getWebView());
                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {

                                AvatarActivity.view.setBackgroundColor(color);
                                UIManager.setWebViewBgColor(color);
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
                UIManager = new UIManager(GoogleKitOne.super.getWebView());
                switch (p) {
                    case 0:

                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color,
                                context.getResources().getIntArray(R.array.md_colors)
                                ,preferences.getInt(Utils.getItem(fp, p).getId(),0));



                        
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
        CustomAdapter.OnItemClickListener body = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {
                UIManager = new UIManager(GoogleKitOne.super.getWebView());
                switch (p) {
                    case 0:

                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }

                        });
                        break;
                    case 1 :
                        AvatarActivity.selectImageBodyBackground(Utils.getItem(fp, 0).getId());
                        break;


                }
            }
        };
        CustomAdapter.OnItemClickListener arms = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {
                UIManager = new UIManager(GoogleKitOne.super.getWebView());
                switch (p) {
                    case 0:

                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }

                        });
                        break;
                    case 1 :
                        dialog =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
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
        list.add(body);
        list.add(arms);
        list.add(saves);

        return list;
    }


}
