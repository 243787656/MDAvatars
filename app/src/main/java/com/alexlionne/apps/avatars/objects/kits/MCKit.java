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

;
;


public class MCKit extends Kit {

    private Context context;

    private final Options checkboxNDisable = new Options().checkboxNDiasable();
    private final Options checkboxNEnable = new Options().checkboxNEnable();
    private final Options colorChooser = new Options().colorChooser();
    private final Options null_options = new Options().null_options();
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final int WHITE = 0;
    private com.alexlionne.apps.avatars.Utils.UIManager UIManager;
    private int color_old;



    public MCKit(Context context) {
        super(context);
        this.context = context.getApplicationContext();
        //name of the KIT
        super.setName("MC Kit");
        //Small description of the kit
        super.setSmallDesc("Michael Cook Kit");
        //setting up a showcase Drawable (shown in kits list)
        //super.setShowcase(R.drawable.gmd_kit_2);
        //Icon fo the kit
        super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
        //link to html file (that contain svg), into @assets
        super.setSvg("file:///android_asset/mc_kit.html");
        //two listeners, one for categories properties
        //the other one for their listeners
        super.setCategories(getGoogleKitTwoCategories());
        super.setListener(getGoogleKitTwoListeners());
        //default bg color of WebView
        super.setDefaultBgColor(context.getResources().getColor(R.color.md_light_blue_700));
        //Initialisazion of the kit (default value)
        init();
    }

void init(){
    //getting preferences
    preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
    editor = preferences.edit();
    //setting up default values
    editor.putInt("BackgroundColor", getDefaultBgColor());
    editor.putInt("MC_head", context.getResources().getColor(R.color.md_orange_300));
    editor.putInt("MC_mouth", context.getResources().getColor(R.color.md_orange_400));
    editor.putInt("MC_noise", context.getResources().getColor(R.color.md_orange_400));
    editor.putInt("MC_right_arm", context.getResources().getColor(R.color.md_light_blue_300));
    editor.putInt("MC_left_arm", context.getResources().getColor(R.color.md_light_blue_300));
    editor.putInt("MC_body", context.getResources().getColor(R.color.md_light_blue_500));
    editor.putInt("MC_hair", context.getResources().getColor(R.color.md_brown_700));
    editor.putInt("MC_beard", context.getResources().getColor(R.color.md_brown_700));
    editor.putInt("MC_eyes", context.getResources().getColor(R.color.md_brown_700));
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
        head.addItem("HEAD", new Item("Head color", "MC_head", new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_face).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("MC_head", 0),
                colorChooser));
        head.addItem(null, new Item("Mouth color", "MC_mouth", new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_face).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("MC_mouth", 0),
                colorChooser));
        head.addItem("MORE", new Item("Noise color", "MC_noise", new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_face).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("MC_noise", 0),
                colorChooser));
        head.addItem(null, new Item("Eyes color", "MC_eyes", new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_face).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("MC_eyes", 0),
                colorChooser));
        head.addItem("HAIR &", new Item("Hair color", "MC_hair", new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_face).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("MC_hair", 0),
                colorChooser));
        head.addItem(null, new Item("Beard color", "MC_beard", new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_face).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("MC_hair", 0),
                colorChooser));

        ListItem body = new ListItem();
        body.setTitle("Body");
        body.addItem("BODY", new Item("Body Color", "MC_body", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("MC_body", 0),
                colorChooser));
        body.addItem(null, new Item("Image", R.drawable.image, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_public).sizeDp(18), WHITE, colorChooser));


        ListItem hand = new ListItem();
        hand.setTitle("Arms");
        hand.addItem("ARMS", new Item("Right arm Color", "MC_right_arm", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("MC_right_arm", 0),
                colorChooser));
        hand.addItem(null, new Item("Left arm Color", "MC_left_arm", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("MC_left_arm", 0),
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

    public ArrayList<CustomAdapter.OnItemClickListener> getGoogleKitTwoListeners(){

        ArrayList<CustomAdapter.OnItemClickListener> list = new ArrayList<>();
        CustomAdapter.OnItemClickListener background = new  CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {


                switch (p) {
                    case 0:
                        UIManager = new UIManager(MCKit.super.getWebView());
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
                UIManager = new UIManager(MCKit.super.getWebView());
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
                    case 1:

                        ColorPickerDialog dialog1 =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog1.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }

                        });
                        break;
                    case 2:

                        ColorPickerDialog dialog2 =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog2.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }

                        });
                        break;
                    case 3:

                        ColorPickerDialog dialog3 =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog3.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }

                        });

                        break;
                    case 5:
                        ColorPickerDialog dialog5 =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog5.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                if (color_old == color) {
                                    UIManager.loadColor("transparent", Utils.getItem(fp, p).getId());
                                    UIManager.updateView(fp, p, R.color.md_white_1000, Utils.getItem(fp, p).getId());
                                } else {
                                    color_old = color;
                                    UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                    UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }
                            }

                        });

                        break;
                    case 4:

                        ColorPickerDialog dialog4 =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog4.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
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
                UIManager = new UIManager(MCKit.super.getWebView());
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
                        AvatarActivity.selectImageBodyBackground("MC_body");
                        break;


                }
            }
        };
        CustomAdapter.OnItemClickListener arms = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {
                UIManager = new UIManager(MCKit.super.getWebView());
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
