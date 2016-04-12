package com.alexlionne.apps.avatars.objects.kits;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

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


public class AndroidKit extends Kit {

    private Context context;

    private final Options checkboxNDisable = new Options().checkboxNDiasable();
    private final Options checkboxNEnable = new Options().checkboxNEnable();
    private final Options colorChooser = new Options().colorChooser();
    private final Options null_options = new Options().null_options();
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final int WHITE = 0;
    private com.alexlionne.apps.avatars.Utils.UIManager UIManager;



    public AndroidKit(Context context) {
        super(context);
        this.context = context.getApplicationContext();
        //name of the KIT
        super.setName("BugDroid");
        //Small description of the kit
        super.setSmallDesc("Simple BugDroid");
        //setting up a showcase Drawable (shown in kits list)
        //super.setShowcase(R.drawable.gmd_kit_2);
        //Icon fo the kit
        super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_android).sizeDp(18));
        //link to html file (that contain svg), into @assets
        super.setSvg("file:///android_asset/android_kit.html");
        //two listeners, one for categories properties
        //the other one for their listeners
        super.setCategories(getGoogleKitTwoCategories());
        super.setListener(getGoogleKitTwoListeners());
        //default bg color of WebView
        super.setDefaultBgColor(context.getResources().getColor(R.color.md_green_A200));
        //Initialisazion of the kit (default value)
        init();
    }

void init(){
    //getting preferences
    preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
    editor = preferences.edit();
    //setting up default values
    editor.putInt("BackgroundColor", getDefaultBgColor());
    editor.putInt("AK_HeadColor", context.getResources().getColor(R.color.md_green_A700));
    editor.putInt("AK_BodyColor", context.getResources().getColor(R.color.md_green_A700));
    editor.putInt("AK_LeftArmColor", context.getResources().getColor(R.color.md_green_A700));
    editor.putInt("AK_RightArmColor", context.getResources().getColor(R.color.md_green_A700));
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
        head.addItem("HEAD", new Item("Head color", "AK_HeadColor", new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_face).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("AK_HeadColor", 0),
                colorChooser));

        ListItem body = new ListItem();
        body.setTitle("Body");
        body.addItem("BODY", new Item("Body Color", "AK_BodyColor", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("AK_BodyColor", 0),
                colorChooser));
        body.addItem(null, new Item("Image", R.drawable.image, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_public).sizeDp(18), WHITE, colorChooser));

        ListItem hand = new ListItem();
        hand.setTitle("Arms");
        hand.addItem("ARMS", new Item("Right arm Color", "AK_RightArmColor", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("AK_RightArmColor", 0),
                colorChooser));
        hand.addItem(null, new Item("Left arm Color", "AK_LeftArmColor", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("AK_LeftArmColor", 0),
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
    final Utils utils = new Utils(context);
        ArrayList<CustomAdapter.OnItemClickListener> list = new ArrayList<>();
        CustomAdapter.OnItemClickListener background = new  CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {


                switch (p) {
                    case 0:
                        UIManager = new UIManager(AndroidKit.super.getWebView());
                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {

                                AvatarActivity.view.setBackgroundColor(color);
                                UIManager.setWebViewBgColor(color);
                                UIManager.updateView(fp, p, color, utils.getItem(fp, p).getId());
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
                UIManager = new UIManager(AndroidKit.super.getWebView());
                switch (p) {
                    case 0:

                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color,
                                context.getResources().getIntArray(R.array.md_colors)
                                ,preferences.getInt(utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, utils.getItem(fp, p).getId());
                            }

                        });

                        break;

                }
            }
        };
        CustomAdapter.OnItemClickListener body = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {
                UIManager = new UIManager(AndroidKit.super.getWebView());
                switch (p) {
                    case 0:

                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, utils.getItem(fp, p).getId());
                            }

                        });
                        break;
                    case 1 :
                        AvatarActivity.selectImageBodyBackground(utils.getItem(fp, 0).getId());
                        break;


                }
            }
        };
        CustomAdapter.OnItemClickListener arms = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {
                UIManager = new UIManager(AndroidKit.super.getWebView());
                switch (p) {
                    case 0:

                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, utils.getItem(fp, p).getId());
                            }

                        });
                        break;
                    case 1 :
                        dialog =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, utils.getItem(fp, p).getId());
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
