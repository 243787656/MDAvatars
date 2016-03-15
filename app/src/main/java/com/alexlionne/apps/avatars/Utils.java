package com.alexlionne.apps.avatars;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex Lionne on 20/01/2016.
 */
public class Utils {

    public static String convertHexColorString(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }
    public static String getAccentDarkColor(Context context, String color){
        List<String> colorList = Arrays.asList(context.getResources().getStringArray(R.array.md_colors));
        for(int i = 0;i<colorList.size();i++){
            if (colorList.get(i).equals(color)){
                return  colorList.get(i + 2);
            }
        }
        return null; }
}
