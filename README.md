# MDAvatars

    Welcome to MDavatars you can follow the development here, i'll update this README later 
    to show you how to use and add SVG kit to this app.
    
###CREATE YOUR OWN KIT
####Part I : Illustration
Lets start ! to create your own kit you need to provide to the app your SVG file. I'm using 
Adobe Illustrator cc here
We will start from scratch with the android one :


![Android SVG](/../master/illustrator_1.png?raw=true "Android SVG")




You have many tutos on the web to show you how to make correct svg for android apps. for now 
you have to be sure to name all component of your svg path/group as below : 


![Android SVG](/../master/illustrator_2.png?raw=true "Android SVG")




After this you can save your file with #crtl + alt + S. Be sure to select SVG Basic 1.1 or Tiny 2.1
You can compare the svg code


![Android SVG](/../master/illustrator_3.png?raw=true "Android SVG")



####Part II : Java code

To start with Java code you need to know bases of Kit implementation : 
- a custom kit extends Kit class and all methods of this class
- a custom kit is used to generate fragments (categories)
- a custom kit has 2 main Arrays wich are used for the fragment : `Categories` & `Listener`
- all item into `Categories` (except the title) MUST HAVE a `Listener` associated

great you will see what I mean

First let's create a new AndroidKit class (or whatever you want) that extends Kit:

#####Create the class
```java
    package com.alexlionne.apps.avatars.objects.kits;
     
    import android.content.Context;
    import com.alexlionne.apps.avatars.objects.Kit;
     
    public class AndroiKit extends Kit {
    private Context context;
     
    public AndroiKit(Context context) {
        super(context);
    }
    }
```  
After this you need to set all informations about your kit from superclass methods : 

```java
     public AndroiKit(Context context) {
       super(context);
        this.context = context.getApplicationContext();
        super.setName("Android Kit");
        super.setSmallDesc("Simple Bugdroid ! ");
        super.setShowcase(R.drawable.gmd_kit_1);
        super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_android).sizeDp(18));
        super.setSvg("file:///android_asset/android_kit.html");
        super.setCategories(getAndroidKitCategories());
        super.setListener(getAndroidKitListeners());
    }
```

####Explanations

You will create your kit object via an Activity so we need to get its context to use `IconicsDrawable`

```java
        super(context);
        this.context = context.getApplicationContext();
```

Set a name to your kit, a small description and showcase image and an icon that appear into Kits's dashboard
```java
        super.setName("Android Kit);
        super.setSmallDesc("Simple BugDroid !");
        super.setShowcase(R.drawable.android_kit_1);
        super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_android).sizeDp(18));
```

I assume you've store your `.svg` file into Asset folder, link your kit to its svg 

```java
    super.setSvg("file:///android_asset/android_kit.html");
```

As I said before a kit need 2 Arrays :

```java
        super.setCategories(getAndroidKitCategories());
        super.setListener(getAndroidKitListeners());
```
A categorie one and another for listeners.


####Arrays
First ArrayList is about Categories to be displayed in fragments
```java
private ArrayList<ArrayList<String>> getAndroidKitCategories() {
        ArrayList<String> background = new ArrayList<>();
        background.add("Background");
        background.add("Color");

        ArrayList<String> head = new ArrayList<>();
        head.add("Head");
        head.add("Color");

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result.add(background);
        result.add(head);

        return result;
    }
```
####Explanations

Here we have an Array background 

```java
ArrayList<String> background = new ArrayList<>();
        background.add("Background");
        background.add("Color");
        background.add("Image");

```
that contain a Title (all the time the first element do not forget it !)

```java
        background.add("Background");
```

and item to be displayed in a ListView

```java
        background.add("Color");
        background.add("Image");
```
This will be displayed in 1 Fragment, you can make more if you want more Categories : 
```java
ArrayList<String> head = new ArrayList<>();
        head.add("Head");
        head.add("Color");
```

Store all those Array into a result Array that you will return : 
```java
ArrayList<ArrayList<String>> result = new ArrayList<>();
        result.add(background);
        result.add(head);

        return result;
```

You're done !, you've just create all categories, now you need to associate all items to a custom
Listener, its why we need a second Array ! 

###Listeners

You need now to set up Listener for your Items. Have in mind that all your items (displayed in the listview) 
can have a custom action that we're defined now : 

```java
public ArrayList<AdapterView.OnItemClickListener> getAndroidKitListeners(){
        ArrayList<AdapterView.OnItemClickListener> list = new ArrayList<>();
        AdapterView.OnItemClickListener background = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {

                                        AndroiKitsuper.getWebView().setBackgroundColor(color);
                                    }
                                }))
                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);


                        break;

            }
        };


        AdapterView.OnItemClickListener head_list = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        String javascript = "javascript:document.body.style.background = " + color + ";";
                                        AndroiKit.super.getWebView().loadUrl(javascript);
                                    }
                                }))
                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);


                        break;

                }


            }
        };
```

####Explanations

Now we have set 2 Categories : Background and Head. They both have one item. So we need to setup exactly
1 Array of 2 Custom `AdapterView.OnItemClickListener` (we are setting actions via cases) 
```java
switch (p) {
                    case 0:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {

                                        AndroiKit.super.getWebView().setBackgroundColor(color);
                                    }
                                }))
                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);


                        break;
```

case 0 correspond to `Color` Item into Background Array :p

As we need to change color i've imported `com.kennyc.colorchooser.ColorChooserDialog` to build the DialogChooser

Set an array of color
```java
.colors(R.array.md_colors)
```
set your custom action ! 
```java
.listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {

                                        AndroiKit.super.getWebView().setBackgroundColor(color);
                                    }
                                }))
```
here we need to change the background color, so you can get the webview by `super.getWebView()` method

Set other attributes 
```java
.positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);
```

#####Do the same for all items and you class will be ok !

####Latest steps, you will need to edit AvatarActivity class like below and add your kit to the condition, and register it to Kit class as well, You need to add a new fragment too, `Save and Options` 




```java
AndroidKit.class
  ArrayList<String> saves = new ArrayList<>();
        saves.add("Save and options");
        saves.add("add shadow");
        
...
AvatarActivity.class
String current = getIntent().getStringExtra("kit")
        if (current.equals("Google-Kit I")){
            kit = new GoogleKitOne(this);
        }else if(current.equals("Android Kit")){
            kit = new AndroidKit(this);
        }
        attachKit(kit);
...
Kit.class
...
 public static ArrayList<Kit> getAllKits() {
        kitArrayList = new ArrayList<>();
        kitArrayList.add(new Kit(context).with(new GoogleKitOne(context)));
        kitArrayList.add(new Kit(context).with(new AndroidKit(context)));

        return kitArrayList;
    }
```

###All is done do not forget to contact me if needed ! 
