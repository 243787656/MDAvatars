package com.alexlionne.apps.avatars.objects;

import android.content.Context;

/**
 * Created by Alex on 06/03/2016.
 */
public class Avatar extends Kit{
    private Background background;
    private Accessories accessories;
    private Context context;
    private Head head;
    private Body body;
    private Kit kit;

    public Avatar(Context context,Kit kit){
        super(context);
        this.kit = kit;
        this.context = context;
    }

    public void setHead(Head head){
        this.head = head;
    }

    public Head getHead(){
        return this.head;
    }
    public void setBody(Body body){
        this.body = body;
    }

    public Body getBody(){
        return this.body;
    }



}
