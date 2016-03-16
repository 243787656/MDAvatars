package com.alexlionne.apps.avatars;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex Lionne on 20/01/2016.
 */
public class Utils {

    private static String directory ="/sdcard/MDAvatar/";
    private static ArrayList<File> mFile;

    public static String convertHexColorString(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }
    public static int getAccentDarkColor(int color){
            int a = Color.alpha(color);
            int r = Color.red( color );
            int g = Color.green( color );
            int b = Color.blue( color );

            return Color.argb( a,
                    Math.max( (int)(r * 0.8f), 0 ),
                    Math.max( (int)(g * 0.8f), 0 ),
                    Math.max( (int)(b * 0.8f), 0 ) );

         }

    public static ArrayList<File> getAllSavedAvatars(){
        File file = new File(directory);
        mFile = new ArrayList<>();
        if (file.isDirectory())
        {
            File[] listFile = file.listFiles();

            for (File aListFile : listFile) {
                File fil = new File(aListFile.getAbsolutePath());
                mFile.add(fil);
            }
        }
    return mFile;}
}
