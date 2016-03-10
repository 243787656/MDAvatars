package com.alexlionne.apps.avatars.objects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.alexlionne.apps.avatars.R;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

/**
 * Created by Alex on 06/03/2016.
 */
public class Body extends ImageView {
    private Kit kit;
    private SVG body;
    private int body_src;
    private Context context;
    public Body(Context context) {
        super(context);
        this.context = context;
    }

    public Body(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public Body(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    public void setKit(Kit kit){
        this.kit = kit;
    }
    public void setType(int id){
        body_src =  R.raw.kit_1_body_1;
        body =  SVGParser.getSVGFromResource(this.getResources(), body_src);
        this.setImageDrawable(body.createPictureDrawable());

    }

    public void setColor(int color){
        body =  SVGParser.getSVGFromResource(this.getResources(), body_src,getResources().getColor(R.color.body_default),color);
        this.setImageDrawable(body.createPictureDrawable());
    }

    public SVG getSVG(){
        return this.body;
    }


}