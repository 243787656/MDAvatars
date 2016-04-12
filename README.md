# MDAvatars
![1](/../master/1.png?raw=true "Android SVG")
![2](/../master/2.png?raw=true "Android SVG")

    Welcome to MDavatars. You can follow the development of this app here. I'll update this README later 
    to show you how to use and add SVG kits to the app.
    
![Latest app](/../master/app-debug-1.0.8.apk?raw=true)
    
###CREATE YOUR OWN KIT
####Part I : Illustration
Let's start! To create your own kit, you will need to provide to the app some SVG files. I'm using 
Adobe Illustrator CC here to make the SVG files.
We will start from scratch and I'll show you how I made the kit with the android character :


![Android SVG](/../master/illustrator_1.png?raw=true "Android SVG")




There are many tutorials on the web to show you how to make correct svg files for android apps. Make sure to name all components of your SVG path/group as shown in the image below:


![Android SVG](/../master/illustrator_2.png?raw=true "Android SVG")




After this you can save your file with #crtl + alt + S. Be sure to select SVG Basic 1.1 or Tiny 2.1 while exporting.
You can compare the svg code


![Android SVG](/../master/illustrator_3.png?raw=true "Android SVG")



####Part II : Java code

    
##Using Standalone SVG Manager
I've made a UIManager class to work with svgs that you can use for your projects.

I assume you have a working html file with svg in. Check the Assets folder of this project for more info.
A working path : 
```xml
<path id="AK_HeadColor" fill="#00C853" d="M323.2,278.9l28.5-41.4c1.6-2.4,1.3-5.7-1-7.2c-2.3-1.6-5.4-0.7-7,1.6l-29.6,43
	c-19.5-7.7-41.2-12.1-64-12.1s-44.5,4.2-64,12.1l-29.8-43c-1.6-2.4-4.9-3.3-7-1.6c-2.3,1.6-2.6,4.9-1,7.2l28.5,41.4
	c-45.4,21-77,61.2-81.1,107.8h308.6C400.4,340.1,368.5,300,323.2,278.9z M185.6,344.2c-9.4,0-17.1-7.7-17.1-17.1s7.7-17.1,17.1-17.1
	c9.4,0,17.1,7.7,17.1,17.1S194.9,344.2,185.6,344.2z M314.6,344.2c-9.4,0-17.1-7.7-17.1-17.1s7.7-17.1,17.1-17.1s17.1,7.7,17.1,17.1
	S323.9,344.2,314.6,344.2z"></path>
```

First, you need to create your UIManager Object. For this add the following lines: 
```java
UIManager manager = new UImanager(webview);
```
or
```java
UIManager manager = new UImanager();
manager.attachWebView(mWebview);
```
To load a color for a single item (not under a group) you can use an int or a string variable eg : "#FFFFFF":
```java
public void loadColor(int color, String item);
public void loadColor(String color, String item);
```
All javascript keywords for colors are working (`transparent`,`blue`,`red`...)
```java
manager.loadColor("transparent","head");
```

To load a color for a single item stroke (not under a group) you can use an int or a string variable :
```java
public void loadStrokeColor(int color, String item);
public void loadStrokeColor(String Color, String item);
```

To load a color for a `path` group
```java
public void loadColorforGroup(int color,String group);
public void loadColorforGroup(String color,String group);
```
If your group does not contain only `path` tag :
```xml
<g id="buttons">
	<ellipse  fill="transparent" cx="190.4" cy="-314.6" rx="6.7" ry="6.7"></ellipse>
	<ellipse fill="transparent" cx="190.4" cy="-239.9" rx="6.7" ry="6.7"></ellipse>
	<ellipse  fill="transparent" cx="190.4" cy="-161.1" rx="6.7" ry="6.7"></ellipse>
	<ellipse fill="transparent" cx="190.4" cy="-86.4" rx="6.7" ry="6.7"></ellipse>
        </g>
 ```
You can color items by :
```java
 public void loadColorforGroup(int color,String tag,String group);
 public void loadColorforGroup(String color,String tag,String item);
 //eg
 manager.loadColorforGroup("transparent", "ellipse", "buttons");
```
If your group does not contain all items with the same tag, you can set different colors for each item in the group:
```xml
<g id="Nexus5x">
	<path id="BodyColor" fill="#212121" d="M222.9-219.3h-58c-5.6,0-10.2-4.5-10.2-10.2v-115.3c0-5.6,4.5-10.2,10.2-10.2h58c5.6,0,10.2,4.5,10.2,10.2
		v115.3C233.3-223.9,228.6-219.3,222.9-219.3z"></path>
	<circle id="CameraColor"  fill="#010101" cx="194.2" cy="-331.6" r="8"></circle>
	<circle id="FingerPrintStrokeColor" fill="none" stroke="#444444" stroke-miterlimit="10" cx="194.2" cy="-305.4" r="8"></circle>
</g>
```
Here you have a group @Nexus5X with a #path @BodyColor and two circles @CameraColor & @FingerPrintColor.
They don't have the same tag mame (path,circle), so you can theme a single item by using : 
```java
public void loadColorforItemInGroup(int color,String group,String item);
public void loadColorforItemInGroup(String color,String group,String item);
//eg
manager.loadColorforItemInGroup(color,"Nexus5x","BodyColor");
```

#####Use this class as an SVG Manager. Do not forget to contact me if needed! 


###Making a kit from scratch

There are two way to add a kit to the app 
####I) Provide me your AI or png files, I'll add it to the app and mentionn you as a contributor. 

####II) Help me a bit by making a Custom Kit class :


To start you need to know basics of Kit implementation : 
- a Custom kit extends Kit class and all methods of this class
- a Custom kit is used to generate fragments (categories)
- a Custom kit has 2 main Arrays wich are used for the fragment : `Categories` & `Listener`
- all item into `Categories` (except the title) MUST HAVE a `Listener` associated

You can create a new AndroidKit class (or whatever you want) that extends Kit:

####Final Version, Explanations below:
```java

public class AndroidKit extends Kit {

    private Context context;
    private final Options checkboxNDisable = new Options().checkboxNDiasable();
    private final Options checkboxNEnable = new Options().checkboxNEnable();
    private final Options colorChooser = new Options().colorChooser();
    private final Options null_options = new Options().null_options();
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final int WHITE = 0;
    private com.alexlionne.apps.avatars.Utils.UIManager UIManager;



    public AndroidKit(Context context) {
        super(context);
        this.context = context.getApplicationContext();
        super.setName("BugDroid");
        super.setSmallDesc("Simple BugDroid");
        super.setShowcase(R.drawable.gmd_kit_2);
        super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
        super.setSvg("file:///android_asset/android_kit.html");
        super.setCategories(getGoogleKitTwoCategories());
        super.setListener(getGoogleKitTwoListeners());
        super.setDefaultBgColor(context.getResources().getColor(R.color.md_green_A200));
        init();
    }

void init(){
    preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
    editor = preferences.edit();
    editor.putInt("BackgroundColor", getDefaultBgColor());
    editor.putInt("AK_HeadColor", context.getResources().getColor(R.color.md_green_A700));
    editor.putInt("AK_BodyColor", context.getResources().getColor(R.color.md_green_A700));
    editor.putInt("AK_LeftArmColor", context.getResources().getColor(R.color.md_green_A700));
    editor.putInt("AK_RightArmColor", context.getResources().getColor(R.color.md_green_A700));
    editor.apply();

}


    private ArrayList<ListItem> getGoogleKitTwoCategories() {
        preferences = context.getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
        ListItem backgroundItem = new ListItem();
        backgroundItem.setTitle("Background");
        backgroundItem.addItem("BACKGROUND", new Item("Color", "BackgroundColor",
                new IconicsDrawable(context,
                        CommunityMaterial.Icon.cmd_format_color_fill)
                        .sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("BackgroundColor", 0),
                colorChooser));
        backgroundItem.addItem(null, new Item("Image", R.drawable.image, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_camera).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), WHITE,
                colorChooser));

        ListItem head = new ListItem();
        head.setTitle("Head");
        head.addItem("HEAD", new Item("Head color", "AK_HeadColor", new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_face).sizeDp(18).color(context.getResources().getColor(R.color.md_grey_700)), preferences.getInt("AK_HeadColor", 0),
                colorChooser));

        ListItem body = new ListItem();
        body.setTitle("Body");
        body.addItem("BODY", new Item("Body Color", "AK_BodyColor", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("AK_BodyColor", 0),
                colorChooser));
        body.addItem(null, new Item("Image", R.drawable.image, new IconicsDrawable(context,
                GoogleMaterial.Icon.gmd_public).sizeDp(18), WHITE, colorChooser));

        ListItem hand = new ListItem();
        hand.setTitle("Arms");
        hand.addItem("ARMS", new Item("Right arm Color", "AK_RightArmColor", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("AK_RightArmColor", 0),
                colorChooser));
        hand.addItem(null, new Item("Left arm Color", "AK_LeftArmColor", new IconicsDrawable(context,
                CommunityMaterial.Icon.cmd_format_color_fill).sizeDp(18), preferences.getInt("AK_LeftArmColor", 0),
                colorChooser));


        ListItem save = new ListItem();
        save.setTitle("Save and options");
        save.addItem("SAVE", new Item("Save to sdcard", WHITE, null_options));
        save.addItem(null, new Item("Share", WHITE, null_options));


        ArrayList<ListItem> result = new ArrayList<>();
        result.add(backgroundItem);
        result.add(head);
        result.add(body);
        result.add(hand);
        result.add(save);

        return result;

    }

    public ArrayList<CustomAdapter.OnItemClickListener> getGoogleKitTwoListeners(){

        ArrayList<CustomAdapter.OnItemClickListener> list = new ArrayList<>();
        CustomAdapter.OnItemClickListener background = new  CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {


                switch (p) {
                    case 0:
                        UIManager = new UIManager(AndroidKit.super.getWebView());
                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color,context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {

                                AvatarActivity.view.setBackgroundColor(color);
                                UIManager.setWebViewBgColor(color);
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                                UIManager.setNavigationBarColor(color);

                            }

                        });



                        break;
                    case 1 :
                        AvatarActivity.selectImageBackground();
                        break;

                }

            }
        };



        CustomAdapter.OnItemClickListener head = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {
                UIManager = new UIManager(AndroidKit.super.getWebView());
                switch (p) {
                    case 0:

                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color,
                                context.getResources().getIntArray(R.array.md_colors)
                                ,preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }

                        });

                        break;

                }
            }
        };
        CustomAdapter.OnItemClickListener body = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {
                UIManager = new UIManager(AndroidKit.super.getWebView());
                switch (p) {
                    case 0:

                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }

                        });
                        break;
                    case 1 :
                        AvatarActivity.selectImageBodyBackground(Utils.getItem(fp, 0).getId());
                        break;


                }
            }
        };
        CustomAdapter.OnItemClickListener arms = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, final int p,final int fp) {
                UIManager = new UIManager(AndroidKit.super.getWebView());
                switch (p) {
                    case 0:

                        ColorPickerDialog dialog =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }

                        });
                        break;
                    case 1 :
                        dialog =  UIManager.colorChooser(R.string.choose_color, context.getResources().getIntArray(R.array.md_colors),preferences.getInt(Utils.getItem(fp, p).getId(),0));
                        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                UIManager.loadColor(color, Utils.getItem(fp, p).getId());
                                UIManager.updateView(fp, p, color, Utils.getItem(fp, p).getId());
                            }

                        });
                        break;


                }
            }
        };

        CustomAdapter.OnItemClickListener saves = new CustomAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View v, int p,int fp) {

                switch (p) {
                    case 0:
                            AvatarActivity.showDirectoryChooser();
                        break;
                    case 1:
                            AvatarActivity.share();
                        break;

                }
            }
        };

        list.add(background);
        list.add(head);
        list.add(body);
        list.add(arms);
        list.add(saves);

        return list;
    }


}
```

###That's all folks! If you are interested in contributing to the app or adding a kit to the app, you are welcome! Make pull requests to this repo and I'll add them after review. You can contact me if needed.
