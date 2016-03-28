package com.alexlionne.apps.avatars;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.graphics.Palette;
import android.util.Base64;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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

    public static int getAccentLightColor(int color) {
        int red = (int) ((Color.red(color) * (1 - 0.8f) / 255 + 0.8f) * 255);
        int green = (int) ((Color.green(color) * (1 - 0.8f) / 255 + 0.8f) * 255);
        int blue = (int) ((Color.blue(color) * (1 - 0.8f) / 255 + 0.8f) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

    public static int getAccentDarkColor(int color){
            int a = Color.alpha(color);
            int r = Color.red( color );
            int g = Color.green( color );
            int b = Color.blue( color );

            return Color.argb(a,
                    Math.max((int) (r * 0.8f), 0),
                    Math.max((int) (g * 0.8f), 0),
                    Math.max((int) (b * 0.8f), 0));

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
    public static void reveal(View v) {
        int cx = v.getWidth() / 2;
        int cy = v.getHeight() / 2;
        //float finalRadius = (float) Math.hypot(cx, cy);
        int finalRadius = Math.max(v.getWidth(), v.getHeight());
        Animator anim =
                android.view.ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);
        v.setVisibility(View.VISIBLE);
        anim.setDuration(400);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
    }

    public static int getLightPalettefromBitmap(Bitmap bitmap){
        Palette p = Palette.from(bitmap).generate();
        Palette.Swatch vibrantSwatch = p.getLightVibrantSwatch();
        if (vibrantSwatch != null) {
            return vibrantSwatch.getRgb();
        }
        return 0;
    }
    public static int getTitleTextColor(Bitmap bitmap){
        Palette p = Palette.from(bitmap).generate();
        Palette.Swatch vibrantSwatch = p.getLightVibrantSwatch();
        if (vibrantSwatch != null) {
            return vibrantSwatch.getTitleTextColor();
        }
        return 0;
    }

    // To animate view slide out from left to right
    public static void slideToRight(View view){
        TranslateAnimation animate = new TranslateAnimation(0,view.getWidth(),0,0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }
    // To animate view slide out from right to left
    public static void slideToLeft(View view){
        TranslateAnimation animate = new TranslateAnimation(0,-view.getWidth(),0,0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    // To animate view slide out from top to bottom
    public static void slideToBottom(View view){
        TranslateAnimation animate = new TranslateAnimation(0,0,0,view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    // To animate view slide out from bottom to top
    public static void slideToTop(View view){
        TranslateAnimation animate = new TranslateAnimation(0,0,0,-view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }
}
