package com.alexlionne.apps.avatars.objects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.alexlionne.apps.avatars.R;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

/**
 * Created by Alex on 06/03/2016.
 */
public class Head extends ImageView {
    private Kit kit;
    private SVG head;
    private int head_src;
    private Context context;
    public Head(Context context) {
        super(context);
        this.context = context;
    }

    public Head(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public Head(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    public void setKit(Kit kit){
        this.kit = kit;
    }
        public void setType(int id){
            head_src =  R.raw.kit_1_head_1;
            head =  SVGParser.getSVGFromResource(this.getResources(), head_src);
            this.setImageDrawable(head.createPictureDrawable());

    }

   public void setColor(int color){
       head =  SVGParser.getSVGFromResource(this.getResources(), head_src,getResources().getColor(R.color.head_default),color);
       this.setImageDrawable(head.createPictureDrawable());
   }

    public SVG getSVG(){
        return this.head;
    }


}
