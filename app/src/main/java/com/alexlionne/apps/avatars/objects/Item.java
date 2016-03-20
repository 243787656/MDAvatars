package com.alexlionne.apps.avatars.objects;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by root on 19/03/16.
 */
public class Item extends ListItem{
    private boolean checkbox;
    private boolean state;
    private boolean colorchooser;
    private String name;
    private int color;
    private Bubble bubble;
    private ImageView imageView;
    public Item(String name, boolean checkbox,boolean state, boolean colorchooser,Bubble bubble){
        super();
        this.name = name;
        this.checkbox = checkbox;
        this.colorchooser = colorchooser;
        this.bubble = bubble;
        this.state = state;
    }
    public boolean getColorchooser() {
        return this.colorchooser;
    }
    public void attachImageView(ImageView imageView){
        this.imageView = imageView;
    }
    public ImageView getImageView(){
        return this.imageView;
    }

    public boolean getCheckbox() {
        return this.checkbox;
    }
    public int getColor(){
        return this.color;
    }
    public Bubble getBubble(){
        return this.bubble;
    }

    public String getName(){return this.name;}
    public boolean isEnable(){
        return this.state;
    }
}
