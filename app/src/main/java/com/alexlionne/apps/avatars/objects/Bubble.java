package com.alexlionne.apps.avatars.objects;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.widget.ImageView;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.objects.kits.GoogleKitTwo;

/**
 * Created by root on 19/03/16.
 */
public class Bubble {
    private int color;
    private ImageView imageView;
    public Bubble(){

    }

    public void setColor(int color){
        this.color=color;

    }
    public int getColor(){
        return this.color;
    }

}
