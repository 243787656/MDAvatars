package com.alexlionne.apps.avatars.objects;

import android.view.View;

/**
 * Created by root on 19/03/16.
 */
public class Item extends ListItem{
    private boolean checkbox;
    private boolean colorchooser;
    private String name;
    private int color;
    private Bubble bubble;
    public Item(String name, boolean checkbox, boolean colorchooser,Bubble bubble){
        super();
        this.name = name;
        this.checkbox = checkbox;
        this.colorchooser = colorchooser;
        this.bubble = bubble;
    }
    public boolean getColorchooser() {
        return this.colorchooser;
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

}
