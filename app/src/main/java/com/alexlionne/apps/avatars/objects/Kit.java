package com.alexlionne.apps.avatars.objects;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.alexlionne.apps.avatars.adapters.KitAdapter;
import com.alexlionne.apps.avatars.objects.kits.GoogleKitOne;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Alex on 06/03/2016.
 */
public class Kit {
    private Bitmap head;
    private static ArrayList<Kit> kitArrayList;
    private String name;
    private String smalldesc;
    private int showcase;
    private ArrayList<AdapterView.OnItemClickListener> listeners;
    private IconicsDrawable icon;
    private String svg_path;
    private ArrayList<ArrayList<String>> category;
    private static Context context;
    private WebView webView;

    public Kit(Context context) {
        Kit.context = context.getApplicationContext();
    }


    public Kit with(Kit kit) {
        return kit;
    }

    public void setSmallDesc(String smalldesc) {
        this.smalldesc = smalldesc;
    }

    public String getSmallDesc() {
        return this.smalldesc;
    }

    public void setSvg(String svg_path) {
        this.svg_path = svg_path;
    }

    public String getSvg() {
        return this.svg_path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setShowcase(int showcase) {
        this.showcase = showcase;
    }

    public int getShowcase() {
        return this.showcase;
    }

    public void setIcon(IconicsDrawable icon) {
        this.icon = icon;
    }

    public IconicsDrawable getIcon() {
        return this.icon;
    }

    public void setCategories(ArrayList<ArrayList<String>> category) {
        this.category = category;
    }

    public void setListener(ArrayList<AdapterView.OnItemClickListener> listener) {
        this.listeners = listener;

    }

    public ArrayList<AdapterView.OnItemClickListener> getListeners() {
        return this.listeners;
    }

    public ArrayList<ArrayList<String>> getAllcategories() {
        return this.category;
    }

    public void attachWebView(WebView webView) {
        this.webView = webView;
    }

    public WebView getWebView() {
        return this.webView;
    }

    public static ArrayList<Kit> getAllKits() {
        kitArrayList = new ArrayList<>();
        kitArrayList.add(new Kit(context).with(new GoogleKitOne(context)));

        return kitArrayList;
    }


}


