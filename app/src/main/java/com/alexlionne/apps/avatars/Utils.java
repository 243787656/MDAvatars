package com.alexlionne.apps.avatars;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Alex Lionne on 20/01/2016.
 */
public class Utils {

    public static String convertHexColorString(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }
}
