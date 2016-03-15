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

        super.setName("Google-Kit I");
        super.setSmallDesc("Material palette, grain shadows,rounded shapes ");
        super.setShowcase(R.drawable.gmd_kit_1);
        super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));


I assume you've store your `.svg` file into Asset folder, link your kit to its svg 
```java
    super.setSvg("file:///android_asset/android_kit.html");
```

As I said before a kit need 2 Arrays :
```java
        super.setCategories(getAndroidKitCategories());
        super.setListener(getAndroidKitListeners());
```

A categorie one and another for listeners
Let's create them

####Arrays
