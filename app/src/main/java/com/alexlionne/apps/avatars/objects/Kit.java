package com.alexlionne.apps.avatars.objects;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.alexlionne.apps.avatars.adapters.CustomAdapter;
import com.alexlionne.apps.avatars.adapters.KitAdapter;
import com.alexlionne.apps.avatars.objects.kits.AndroidKit;
import com.alexlionne.apps.avatars.objects.kits.GoogleKitOne;
import com.alexlionne.apps.avatars.objects.kits.GoogleKitTwo;
import com.alexlionne.apps.avatars.objects.kits.MCKit;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Alex on 06/03/2016.
 */
public class Kit {
    private Bitmap head;
    private static ArrayList<Kit> kitArrayList;
    private String name;
    private String smalldesc;
    private int bgcolor;
    private int showcase;
    private ArrayList<CustomAdapter.OnItemClickListener> listeners;
    private IconicsDrawable icon;
    private String svg_path;
    private ArrayList<ListItem> category;
    private static Context context;
    private WebView webView;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public Kit() {

    }

    /**public Kit(Context context)
     * you need set a context to a kit to use it
     *
     * @param context
     * context passed to the kit
     */
    public Kit(Context context) {
        Kit.context = context.getApplicationContext();
    }


    /***Attach a kit to the object
     *
     * Object type excepted Kit
     * @param kit
     *
     * return the Kit
     * @return the Kit
     */
    public Kit with(Kit kit) {
        return kit;
    }

    /***Setting a small description for the kit
     *
     * @param smalldesc
     *
     * This smalldesc is used into KitFragment
     */
    public void setSmallDesc(String smalldesc) {
        this.smalldesc = smalldesc;
    }

    /***Getter of SmallDescription
     *
     * @return the SmallDescription
     */
    public String getSmallDesc() {
        return this.smalldesc;
    }

    /***Link the svg file (html in fact) from assets
     *
     * @param svg_path
     * path of the svg (@link:Assets)
     */
    public void setSvg(String svg_path) {
        this.svg_path = svg_path;
    }

    /***Getting the svg path
     *
     * @return
     * return the svg path
     */
    public String getSvg() {
        return this.svg_path;
    }

    /***Setting the Default background color for the webview
     * (used at init)
     *
     * @param bgcolor
     * value of the background color
     */
    public void setDefaultBgColor(int bgcolor){
        this.bgcolor = bgcolor;
    }

    /***Get the default Background color
     *
     * @return
     * the defaulft background color of webview
     */
    public int getDefaultBgColor(){
        return this.bgcolor;
    }

    /***Get the attached context of a kit
     *
     * @return
     * Context context, used into subKit Classes
     */
    public Context getContext(){
        return context;
    }

    /***Setting the name of the kit
     *
     * @param name
     * Name of the kit
     */
    public void setName(String name) {
        this.name = name;
    }

    /***Getting the name of the Kit
     *
     * @return
     * the name of the Kit
     */
    public String getName() {
        return this.name;
    }

    /***Set the Showcase Icon of a Kit
     *
     * @param showcase
     * the int value of the Ressource eg : (R.drawable.my_showcase)
     */
    public void setShowcase(int showcase) {
        this.showcase = showcase;
    }

    /***Get the showcase int
     *
     * @return
     * Showcase int of the Ressource eg : (R.drawable.my_showcase)
     */
    public int getShowcase() {
        return this.showcase;
    }

    /***Setting the Icon of a kit
     *
     * @param icon
     * icon for the kit, its an IconicsDrawable
     */
    public void setIcon(IconicsDrawable icon) {
        this.icon = icon;
    }

    /***Get the IconicsDrawable
     *
     * @return
     * icon of the kit
     */
    public IconicsDrawable getIcon() {
        return this.icon;
    }

    /***Setting the Category list of a Kit
     *
     * @param category
     * ArrayList<ListItem>
     */
    public void setCategories(ArrayList<ListItem> category) {
        this.category = category;
    }

    /***Set the Listener depending on Category List
     *
     * @param listener
     * ArrayList<CustomAdapter.OnItemClickListener>
     */
    public void setListener(ArrayList<CustomAdapter.OnItemClickListener> listener) {
        this.listeners = listener;

    }
    /*public void setBubbleColor(int color){
        this.backgrouncolor = color;
    }public int getBubbleColor(){
        return this.backgrouncolor;
    }*/

    /***Get the listener
     *
     * @return
     * ArrayList<CustomAdapter.OnItemClickListener>
     */
    public ArrayList<CustomAdapter.OnItemClickListener> getListeners() {
        return this.listeners;
    }

    /***Get the Categories
     *
     * @return
     * ArrayList<ListItem>
     */
    public ArrayList<ListItem> getAllcategories() {
        return this.category;
    }

    /***Attach the WebView the Kit
     *
     * @param webView
     * webview attached for the Kit
     */
    public void attachWebView(WebView webView) {
        this.webView = webView;
    }

    /***Get the WebView
     *
     * @return
     * the attached webview of the Kit
     */
    public WebView getWebView() {
        return this.webView;
    }

    /**get all Kit
     *
     * @return
     * ArrayList<Kit> of all Kit
     */
    public static ArrayList<Kit> getAllKits() {
        kitArrayList = new ArrayList<>();
        kitArrayList.add(new Kit(context).with(new MCKit(context)));
        kitArrayList.add(new Kit(context).with(new AndroidKit(context)));
        //kitArrayList.add(new Kit(context).with(new GoogleKitOne(context)));
        kitArrayList.add(new Kit(context).with(new GoogleKitTwo(context)));


        return kitArrayList;
    }
    public boolean isfavourite(boolean state){
        preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
         preferences.getBoolean(getName(),false);
        editor = preferences.edit();
        editor.putBoolean(getName(), state);
        return preferences.getBoolean(getName(),false);
    }
    public boolean isfavourite(){
        preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);;
        return preferences.getBoolean(getName(),false);
    }


}


