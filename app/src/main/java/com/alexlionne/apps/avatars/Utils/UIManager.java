package com.alexlionne.apps.avatars.Utils;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;

import org.xdty.preference.colorpicker.ColorPickerDialog;

/**
 * Created by root on 28/03/16.
 */
public class UIManager {
    private WebView webView;
    public UIManager(){

    }
    public UIManager(WebView webView){
        this.webView = webView;
    }
    public void attachWebView(WebView webView){
        this.webView = webView;
    }
    public WebView getWebView(){return this.webView;}

    /***Load a c olor for a specific svg item
     *
     * @param color
     *              color to use
     * @param item
     *              item to theme, note :  that this item isn't under a group
     */
    public void loadColor(int color, String item){
        String javascript = " javascript:document.getElementById('"+item+"').setAttribute('fill','"+ Utils.convertHexColorString(color)  +"');";
        getWebView().loadUrl(javascript);
    }
    public void loadColor(String color, String item){
        String javascript = " javascript:document.getElementById('"+item+"').setAttribute('fill','"+ color  +"');";
        getWebView().loadUrl(javascript);
    }
    public void loadStrokeColor(int color, String item){
        String javascript = " javascript:document.getElementById('"+item+"').setAttribute('stroke','"+  Utils.convertHexColorString(color)  +"');";
        getWebView().loadUrl(javascript);
    }
    public void loadStrokeColor(String color, String item){
        String javascript = " javascript:document.getElementById('"+item+"').setAttribute('stroke','"+  color  +"');";
        getWebView().loadUrl(javascript);
    }
    public void loadColorforGroup(int color,String group){
        String javascript= "javascript:var svgElement=document.getElementById('"+group+"');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(color) + "');};";
        getWebView().loadUrl(javascript);
    }
    public void loadColorforGroup(String color,String group){
        String javascript= "javascript:var svgElement=document.getElementById('"+group+"');var circles=svgElement.getElementsByTagName('path');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + color + "');};";
        getWebView().loadUrl(javascript);
    }

    public void loadColorforGroup(int color,String tag,String group){
        String javascript= "javascript:var svgElement=document.getElementById('"+group+"');var circles=svgElement.getElementsByTagName('"+tag+"');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + Utils.convertHexColorString(color) + "');};";
        getWebView().loadUrl(javascript);
    }
    public void loadColorforGroup(String color,String tag,String item){
        String javascript= "javascript:var svgElement=document.getElementById('"+item+"');var circles=svgElement.getElementsByTagName('"+tag+"');for(var i=0;i<circles.length;i++){circles[i].setAttribute('fill', '" + color + "');};";
        getWebView().loadUrl(javascript);
    }

    public void loadColorforItemInGroup(int color,String group,String item){
        String javascript= "javascript:var svgElement=document.getElementById('"+group+"');var item = svgElement.querySelector('#'+'"+item+"');item.setAttribute('fill', '" + Utils.convertHexColorString(color) + "');";
        getWebView().loadUrl(javascript);
    }
    public void loadColorStrokeforItemInGroup(int color,String group,String item){
        String javascript= "javascript:var svgElement=document.getElementById('"+group+"');var item = svgElement.querySelector('#'+'"+item+"');item.setAttribute('stroke', '" + Utils.convertHexColorString(color) + "');";
        getWebView().loadUrl(javascript);
    }

    public void setWebViewBgColor(int color){
        webView.setBackgroundColor(color);
        AvatarActivity.view2.setBackgroundColor(color);
    }
    public void setNavigationBarColor(int color){

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = AvatarActivity.getWindowView();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Utils.getAccentDarkColor(color));
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setNavigationBarColor(color);
        }
    }

    public static void updateView(int p1, int p2, int color, String item) {
        TextView text = AvatarActivity.getKit().getAllcategories().get(p1).getItem(p2).getTextView();
        CardView card = AvatarActivity.getKit().getAllcategories().get(p1).getItem(p2).getCard();

        if (color != AvatarActivity.getActivity().getResources().getColor(R.color.md_white_1000)) {
            text.setTextColor(Utils.getAccentLightColor(color));
            AvatarActivity.getKit().getAllcategories().get(p1).getItem(p2).getIcon().color(Utils.getAccentLightColor(color));
            card.setVisibility(View.INVISIBLE);
            card.setCardBackgroundColor(color);
            Utils.reveal(card);
            AvatarActivity.changeColor(item, color);
        }else{
            text.setTextColor(Color.BLACK);
            AvatarActivity.getKit().getAllcategories().get(p1).getItem(p2).getIcon().color(Color.BLACK);
            card.setVisibility(View.INVISIBLE);
            card.setCardBackgroundColor(color);
            Utils.reveal(card);
            AvatarActivity.changeColor(item, color);
        }
    }
    public void update(String item, String pref){
        AvatarActivity.changePref(item, pref);
    }
    public ColorPickerDialog colorChooser(int title,int colors[], int selected) {
        ColorPickerDialog dialog = ColorPickerDialog.newInstance(title,
                colors,
                selected,
                5,
                ColorPickerDialog.SIZE_SMALL);
        dialog.show(AvatarActivity.fragmentManager, "color_dialog_test");
        return dialog;
    }


}
