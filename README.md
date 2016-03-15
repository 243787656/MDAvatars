# MDAvatars

    Welcome to MDavatars you can follow the development here, i'll update this README later 
    to show you how to use and add SVG kit to this app.
    
###CREATE YOUR OWN KIT

to create your own kit you will need a valid svg file in 500x500px

First add your kit to the package by creating a new class that extends Kit class
    
    package com.alexlionne.apps.avatars.objects.kits;


    public class MyCustomKit extends Kit {
       private Context context;


use now the setter of the super class

    public MyCustomKite(Context context) {
    super(context);
    this.context = context.getApplicationContext();
    super.setName("My Custom Kit I");
    super.setSmallDesc("My Custom Description");
    super.setShowcase(R.drawable.gmd_kit_1);
    super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
    super.setSvg("file:///android_asset/gmd_mykit_1.html");
    super.setCategories(getGoogleKitOneCategories());
    super.setListener(getGoogleKitOneListeners());


        }

You will need to set up 2 Array List for fragment generation, 

The first one is about Categories
do not forget to add the title at the fisrt position of your list like :

    background.add("Background");
    
Your first Array looks like :

    private ArrayList<ArrayList<String>> getMyCustomKitCategories(){
        ArrayList<String> background = new ArrayList<>();
                background.add("Background");
                background.add("Color");
                background.add("Image");


        ArrayList<String> head = new ArrayList<>();
                head.add("Head");
                head.add("Style");
                head.add("Skin color");
                head.add("Eyes color");


        ArrayList<String> ....

Setup your resultat Array (the one that contain categories)

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result.add(background);
        result.add(head);
        result.add(...)
        ...
    return result;
    }
    
Your second ArrayList is about Listener of each item of previous category array !

    public ArrayList<AdapterView.OnItemClickListener> getMyCustomKitListeners(){
Your result list

        ArrayList<AdapterView.OnItemClickListener> list = new ArrayList<>();
        
Your Item can Implement ColorChooserDialog while clicked : 

        AdapterView.OnItemClickListener background = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                //case 0 of first category array 
                    case 0:
                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {
                                        GoogleKitOne.super.getWebView().setBackgroundColor(color);
                                    }
                                }))
                                .positiveButton("Okay")
                                .negativeButton("Cancel")
                                .title("Select Color")
                                .positiveButtonColor(Color.BLUE)
                                .build()
                                .show(AvatarActivity.fragmentManager, null);
                        break;
                     case 1:
                        Toast.makeText(context,"[insert image here !]",Toast.LENGTH_LONG).show();
                        break;
                     case ...
                }


            }
        };


        AdapterView.OnItemClickListener head_list = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:
                        ...
                        break;
                    case 1:
                        ...
                        break;
                }


            }
        };
            ...

add them to the result list that you will return

        list.add(background);
        list.add(...);
        ...
    return list;}

}
    
