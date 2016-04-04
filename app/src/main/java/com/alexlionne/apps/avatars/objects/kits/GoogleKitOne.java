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
            super.setSmallDesc("In progress ");
            super.setShowcase(R.drawable.gmd_kit_1);
            super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
            super.setSvg("file:///android_asset/gmd_kit_1.html");
            //super.setCategories(getGoogleKitOneCategories());
            //super.setListener(getGoogleKitOneListeners());
            super.setDefaultBgColor(context.getResources().getColor(R.color.md_green_200));


        }


}