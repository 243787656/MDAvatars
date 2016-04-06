package com.alexlionne.apps.avatars.objects.kits;

import android.content.Context;

import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.objects.Kit;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

/**
 * Created by Alex on 08/03/2016.
 */

public class GoogleKitOne extends Kit {
    private Context context;

        public GoogleKitOne(Context context) {
            super(context);
            this.context = context.getApplicationContext();
            super.setName("Google I");
            super.setSmallDesc("In progress ");
            super.setShowcase(R.drawable.gmd_kit_1);
            super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
            super.setSvg("file:///android_asset/gmd_kit_1.html");
            //super.setCategories(getGoogleKitOneCategories());
            //super.setListener(getGoogleKitOneListeners());
            super.setDefaultBgColor(context.getResources().getColor(R.color.md_green_200));


        }


}