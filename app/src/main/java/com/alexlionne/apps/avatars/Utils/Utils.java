package com.alexlionne.apps.avatars.Utils;

import android.animation.Animator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.Toast;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.objects.Item;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.Manifest;

/**
 * Created by Alex Lionne on 20/01/2016.
 */
public class Utils {

    private static String directory = "/sdcard/MDAvatar/";
    private static ArrayList<File> mFile;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static Context context;

    public Utils(Context context){
        this.context = context;
    }
    /***Tool to convert an HEXADECIMAL color to its String representation
     *
     * @param color
     * int color used to convert
     * @return
     * the String representation of the Color
     */
    public static String convertHexColorString(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }


    /***Getting light accent of a color
     *
     * @param color
     * color used
     * @return
     * the modified (light) color
     */
    public static int getAccentLightColor(int color) {
        int red = (int) ((Color.red(color) * (1 - 0.8f) / 255 + 0.8f) * 255);
        int green = (int) ((Color.green(color) * (1 - 0.8f) / 255 + 0.8f) * 255);
        int blue = (int) ((Color.blue(color) * (1 - 0.8f) / 255 + 0.8f) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

    /***Getting dark accent of a color
     *
     * @param color
     * color used
     * @return
     * the modified (dark) color
     */
    public static int getAccentDarkColor(int color) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        return Color.argb(a,
                Math.max((int) (r * 0.8f), 0),
                Math.max((int) (g * 0.8f), 0),
                Math.max((int) (b * 0.8f), 0));

    }
    /***Storage Tool to get All saved Avatars
     * @return
     * the modified ArrayList<File> of all avatars
     */
    public ArrayList<File> getAllSavedAvatars() {
        File file = new File(directory);
        mFile = new ArrayList<>();
        for (String str :  Utils.getSavedDirectories(Utils.context))
           mFile.add(new File(str));
        if (file.isDirectory()) {
            File[] listFile = file.listFiles();

            for (File aListFile : listFile) {
                File fil = new File(aListFile.getAbsolutePath());
                mFile.add(fil);
            }
        }
        return mFile;
    }

    /***Tool to reveal a view with material animation( API 21 mini)
     *
     * @param v
     * view to reveal
     */
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

    /***Tool to get the light Color of a bitmap (if known)
     *
     * @param bitmap
     * bitmap to use
     * @return
     * an int of the light color genrated
     */
    public static int getLightPalettefromBitmap(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        Palette.Swatch vibrantSwatch = p.getLightVibrantSwatch();
        if (vibrantSwatch != null) {
            return vibrantSwatch.getRgb();
        }else{
            Log.d("Skinner : ", "No Light Palette found");
            return 0;
        }


    }

    /***Get the TextColor from biptmap by palette
     *
     * @param bitmap
     * bitmap to use
     * @return
     * TextColor genrated
     */
    public static int getTitleTextColor(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        Palette.Swatch vibrantSwatch = p.getLightVibrantSwatch();
        if (vibrantSwatch != null) {
            return vibrantSwatch.getTitleTextColor();
        }else{
            Log.d("Skinner : ", "No Text Palette found");
            return 0;
        }
    }

    // To animate view slide out from left to right
    public static void slideToRight(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, view.getWidth(), 0, 0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    // To animate view slide out from right to left
    public static void slideToLeft(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, -view.getWidth(), 0, 0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    // To animate view slide out from top to bottom
    public static void slideToBottom(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, 0, 0, view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    // To animate view slide out from bottom to top
    public static void slideToTop(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, 0, 0, -view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }


    /***Get the Item depending on Fragment position and Item position
     * (@Categories : List<Item>
     *
     * @param p1
     * Fragment position
     * @param p2
     * List<Item> position
     * @return
     * The coresponding Item
     */
    public static Item getItem(int p1, int p2) {
        return AvatarActivity.getKit().getAllcategories().get(p1).getItem(p2);
    }

    public void checkPermission(final Context context,String permission){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Log.d("Skinner : ", "Permission granted");
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Log.d("Skinner : ", "Permission denied");
            }


        };
        new TedPermission(context)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(permission)
                .check();
    }
    /***Add Directory to scan for saves avatars
     *
     * @param path
     * path to be added
     */
    public static void addDirectorySet(Context context,String path){
        preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
        Set<String> hs = preferences.getStringSet("directories", new HashSet<String>());
        hs.add(path);
        editor = preferences.edit();
        editor.putStringSet("directories", hs);
        editor.apply();
    }

    /***get all the saved directories
     *
     * @return
     * Set<String> of directories
     */
    public static Set<String> getSavedDirectories(Context context){
        preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
        return preferences.getStringSet("directories", new HashSet<String>());
    }


}
