package com.alexlionne.apps.avatars.objects.kits;

import android.content.Context;

import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.objects.Kit;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class AndroidKit extends Kit {
    private Context context;


    public AndroidKit(Context context) {
        super(context);
        this.context = context.getApplicationContext();
        super.setName("Android");
        super.setSmallDesc("Simple Bugdroid ! ");
        super.setShowcase(R.drawable.android_kit);
        super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_android).sizeDp(18));
        super.setSvg("file:///android_asset/android_kit.html");
        //super.setCategories(getAndroidKitCategories());
        //super.setListener(getAndroidKitListeners());
        super.setDefaultBgColor(context.getResources().getColor(R.color.md_green_300));
    }




}
