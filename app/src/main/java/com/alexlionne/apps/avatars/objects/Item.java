package com.alexlionne.apps.avatars.objects;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;



import com.mikepenz.iconics.IconicsDrawable;

/**
 * Created by root on 19/03/16.
 */
public class Item extends ListItem{
    private int drawable;
    private boolean checkbox;
    private boolean state;
    private boolean colorchooser;
    private String name;
    private int color;
    private Bubble bubble;
    private IconicsDrawable iconicsDrawable;
    private ImageView imageView;
    private CheckBox check;
    private TextView textView;
    private ViewGroup view;
    private Options options;
    private String label;
    private CardView card;
    private ImageView imageView2;
    private String id;


    public Item(String name,int color,Options options){
        super();
        this.color = color;
        this.name = name;
        this.colorchooser = options.getColorChooser();
        this.state = options.getState();
        this.checkbox = options.getCheckBox();
        this.options = options;
    }
    public Item(String name,String id,IconicsDrawable iconicsDrawable,int color,Options options){
        super();
        this.id=id;
        this.color = color;
        this.options = options;
        this.colorchooser = options.getColorChooser();
        this.state = options.getState();
        this.checkbox = options.getCheckBox();
        this.name = name;
        this.iconicsDrawable = iconicsDrawable;
    }
    public Item(String name,int drawable,IconicsDrawable iconicsDrawable,int color,Options options){
        super();
        this.drawable=drawable;
        this.color = color;
        this.options = options;
        this.colorchooser = options.getColorChooser();
        this.state = options.getState();
        this.checkbox = options.getCheckBox();
        this.name = name;
        this.iconicsDrawable = iconicsDrawable;
    }


    public boolean getColorchooser() {
        return this.colorchooser;
    }
    public void attachLayout(ViewGroup view){
        this.view = view;
    }
    public ViewGroup getLayout(){
        return this.view;
    }
    public void attachImageView(ImageView imageView){
        this.imageView = imageView;
    }
    public void attachCheckBox(CheckBox check){
        this.check = check;
    }
    public CheckBox getCheckBox(){
        return this.check;
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
    public void attachTextView(TextView textView){
        this.textView = textView;
    }
    public TextView getTextView(){
        return this.textView;
    }
    public Options getOptions(){
        return this.options;
    }
    public IconicsDrawable getIcon(){
        return this.iconicsDrawable;
    }
    public void attachCard(CardView card){
        this.card = card;
    }
    public CardView getCard(){
        return this.card;
    }
    public int getDefaultCardColor(){
        return this.color;
    }
    public void setIconicsDrawable(IconicsDrawable icon){
        this.iconicsDrawable = icon;
    }
    public void attachBackgroundImage(ImageView imageView){
     this.imageView2 = imageView;
    }
    public ImageView getBackgroundImage(){
        return this.imageView2;
    }
    public String getId(){
        return this.id;
    }
    public int getDrawable(){
        return this.drawable;
    }

}
