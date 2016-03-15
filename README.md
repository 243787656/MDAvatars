# MDAvatars

    Welcome to MDavatars you can follow the development here, i'll update this README later 
    to show you how to use and add SVG kit to this app.
    
###CREATE YOUR OWN KIT

to create your own kit you will need a valid svg file in 500x500px

-first add your kit to the package by creating a new class that extends Kit class
    package com.alexlionne.apps.avatars.objects.kits;


    public class GoogleKitOne extends Kit {
        private Context context;



    public GoogleKitOne(Context context) {
    super(context);
    this.context = context.getApplicationContext();
    super.setName("Google-Kit I");
    super.setSmallDesc("Material palette, grain shadows,rounded shapes ");
    super.setShowcase(R.drawable.gmd_kit_1);
    super.setIcon(new IconicsDrawable(context, CommunityMaterial.Icon.cmd_google).sizeDp(18));
    super.setSvg("file:///android_asset/gmd_kit_1.html");
    super.setCategories(getGoogleKitOneCategories());
    super.setListener(getGoogleKitOneListeners());


        }

    private ArrayList<ArrayList<String>> getGoogleKitOneCategories(){
        ArrayList<String> background = new ArrayList<>();
                background.add("Background");
                background.add("Color");
                background.add("Image");

        ArrayList<String> head = new ArrayList<>();
                head.add("Head");
                head.add("Style");
                head.add("Skin color");
                head.add("Eyes color");

        ArrayList<String> hairs = new ArrayList<>();
                hairs.add("Hairs");
                hairs.add("Style");
                hairs.add("Color");

        ArrayList<String> body = new ArrayList<>();
                body.add("Body");
                body.add("Style");
                body.add("Color");

        ArrayList<String> skin = new ArrayList<>();
                skin.add("Skin");
                skin.add("color");

        ArrayList<String> arms = new ArrayList<>();
                arms.add("Arms");
                arms.add("left");
                arms.add("right");

        ArrayList<String> accessories = new ArrayList<>();
                accessories.add("Accessories");
                accessories.add("phone");
                accessories.add("glasses");
                accessories.add("fork");

        ArrayList<String> saves = new ArrayList<>();
        saves.add("Save and options");
        saves.add("shadow");
        saves.add("hide left arm");



        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result.add(background);
        result.add(head);
        result.add(hairs);
        result.add(body);
        result.add(skin);
        result.add(arms);
        result.add(accessories);
        result.add(saves);

   return result;
    }
    public ArrayList<AdapterView.OnItemClickListener> getGoogleKitOneListeners(){
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
                }


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
                                        GoogleKitOne.super.getWebView().loadUrl(javascript);
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
                        Toast.makeText(context,"case 1 clicked !",Toast.LENGTH_LONG).show();
                        break;
                }


            }
        };

        AdapterView.OnItemClickListener body = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int p, long arg3) {


                switch (p) {
                    case 0:
                        Toast.makeText(context,"case style clicked !",Toast.LENGTH_LONG).show();
                        break;
                    case 1:

                        new ColorChooserDialog.Builder(context.getApplicationContext())
                                .colors(R.array.md_colors)
                                .listener((new ColorChooserDialog.ColorListener() {
                                    @Override
                                    public void onColorSelect(int color) {

                                        String javascript = " javascript:document.getElementById('body').setAttribute('fill','"+Utils.convertHexColorString(color)  +"');";
                                        GoogleKitOne.super.getWebView().loadUrl(javascript);
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
        list.add(background);
        list.add(head_list);
        list.add(head_list);
        list.add(body);
        list.add(head_list);
        list.add(head_list);
        list.add(head_list);
        list.add(head_list);
    return list;}

}
    
