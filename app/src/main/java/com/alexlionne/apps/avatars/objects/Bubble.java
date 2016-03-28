package com.alexlionne.apps.avatars.objects;

/**
 * Created by root on 19/03/16.
 */
public class Bubble {
    private int color;
    public Bubble(){

    }

    public Bubble setColor(int color){
        this.color=color;
        return new Bubble();
    }
    public int getColor(){
        return this.color;
    }

}
