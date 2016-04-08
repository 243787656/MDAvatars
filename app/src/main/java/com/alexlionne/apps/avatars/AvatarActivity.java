package com.alexlionne.apps.avatars;


import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.folderselector.FolderChooserDialog;
import com.alexlionne.apps.avatars.Utils.DirectoryChooserFragment;
import com.alexlionne.apps.avatars.Utils.UIManager;
import com.alexlionne.apps.avatars.Utils.Utils;
import com.alexlionne.apps.avatars.adapters.OnSwipeTouchListener;
import com.alexlionne.apps.avatars.fragments.EditionFragment;
import com.alexlionne.apps.avatars.objects.Kit;
import com.alexlionne.apps.avatars.objects.ListItem;
import com.alexlionne.apps.avatars.objects.kits.AndroidKit;
import com.alexlionne.apps.avatars.objects.kits.GoogleKitOne;
import com.alexlionne.apps.avatars.objects.kits.GoogleKitTwo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class AvatarActivity extends AppCompatActivity {
    private int currentFragment;
    private static WebView webview;
    public static Kit kit;
    private EditionFragment fragment[];
    private ArrayList<ListItem> list;
    private EditionFragment pre;
    private EditionFragment post;
    private EditionFragment current;
    public static FragmentManager fragmentManager;
    private static Window window;
    private static final String MDSdirectory = "/sdcard/MDAvatar/";
    private static Activity activity;
    private static Bitmap bitmap;
    public static RelativeLayout view;
    public static RelativeLayout view2;
    public static Button button;
    public static Button back;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static android.support.v4.app.FragmentManager sfm;
    private ProgressBar progressBar;
    private FloatingActionButton fab;
    private static Utils utils;


    public void setActivity(Activity activity) {
        AvatarActivity.activity = activity;
    }

    public static Activity getActivity() {
        return AvatarActivity.activity;
    }

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_layout);
        view = (RelativeLayout) findViewById(R.id.bottom_tasks);
        view2 = (RelativeLayout) findViewById(R.id.layout);
        setActivity(this);
        preferences = getSharedPreferences("com.alexlionne.apps.avatars", MODE_PRIVATE);
        editor = preferences.edit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.accent), PorterDuff.Mode.SRC_IN);
        webview = (WebView) findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);

        utils = new Utils(this);
        utils.checkPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        utils.checkPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        utils.checkPermission(getActivity(), Manifest.permission.INTERNET);

        String current = getIntent().getStringExtra("kit");
        if (current.equals("Google I")) {
            kit = new GoogleKitOne(this);
        } else if (current.equals("BugDroid")) {
            kit = new AndroidKit(this);
        } else if (current.equals("Google II")) {
            kit = new GoogleKitTwo(this);
        }
        attachKit(kit);
        setWindow(getWindow());
        editor.putInt("BackgroundColor", getKit().getDefaultBgColor());
        editor.apply();
        webview.loadUrl(kit.getSvg());
        webview.setBackgroundColor(kit.getDefaultBgColor());
        view2.setBackgroundColor(kit.getDefaultBgColor());
        view.setBackgroundColor(kit.getDefaultBgColor());
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.getSettings().setUseWideViewPort(true);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setVerticalScrollBarEnabled(false);
        webview.setHorizontalScrollBarEnabled(false);
        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Utils.getAccentDarkColor(kit.getDefaultBgColor()));
        }
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }


        });


        fragmentManager = getFragmentManager();
        sfm = getSupportFragmentManager();
        kit.attachWebView(webview);
        list = kit.getAllcategories();
        fragment = new EditionFragment[list.size()];

        for (int i = 0; i < list.size(); i++) {
            addFragment(i);

        }

        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment[0]);
        fragmentTransaction.commit();
        currentFragment = 0;
        setCurrentFragment(fragment[currentFragment]);
        setNextFragment(fragment[getCurrentFragment().getPosition() + 1]);
        progressBar.setProgress(getCurrentFragment().getProgress());

        button = (Button) findViewById(R.id.change);
        back = (Button) findViewById(R.id.change_back);
        assert button != null;
        assert back != null;
        button.setText(getNextFragment().getTitle());
        Drawable left_arrow = new IconicsDrawable(this, GoogleMaterial.Icon.gmd_keyboard_arrow_left).sizeDp(16).paddingDp(4).color(Color.WHITE);
        Drawable right_arrow = new IconicsDrawable(this, GoogleMaterial.Icon.gmd_keyboard_arrow_right).sizeDp(16).paddingDp(4).color(Color.WHITE);
        button.setCompoundDrawablesWithIntrinsicBounds(null, null, right_arrow, null);
        back.setCompoundDrawablesWithIntrinsicBounds(left_arrow, null, null, null);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setNavigationBarColor(kit.getDefaultBgColor());

        back.setVisibility(View.INVISIBLE);
        button.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        back.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        view2.setOnTouchListener(new OnSwipeTouchListener(AvatarActivity.this) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                boolean isClicked = true;
                if (getCurrentFragment().getPosition() == fragment.length - 1 && isClicked) {
                    save();
                } else {

                    back.setVisibility(View.VISIBLE);
                    setNextFragment(fragment[getCurrentFragment().getPosition() + 1]);
                    switchFragment(getNextFragment());

                    for (int i = 0; i <= fragment.length; i++) {

                        if (getCurrentFragment().getPosition() == fragment.length - 1) {
                            button.setText("Save");
                            back.setText(fragment[getCurrentFragment().getPosition() - 1].getTitle());

                        } else {
                            back.setText(fragment[getCurrentFragment().getPosition() - 1].getTitle());
                            button.setText(fragment[getCurrentFragment().getPosition() + 1].getTitle());
                        }


                    }


                }
            }

            public void onSwipeLeft() {
                setNextFragment(fragment[getCurrentFragment().getPosition() - 1]);
                switchBackFragment(getNextFragment());

                for (int i = 0; i < fragment.length; i++) {
                    if (getCurrentFragment().getPosition() == 0) {
                        button.setText(fragment[getCurrentFragment().getPosition() + 1].getTitle());
                        back.setVisibility(View.INVISIBLE);

                    } else {
                        back.setText(fragment[getCurrentFragment().getPosition() - 1].getTitle());
                        button.setText(fragment[getCurrentFragment().getPosition() + 1].getTitle());
                    }


                }

                if (getCurrentFragment().getPosition() != 0) {
                    setPreviousFragment(fragment[getCurrentFragment().getPosition() + 1]);
                }
                progressBar.setProgress(getNextFragment().getProgress());

            }

            public void onSwipeBottom() {

            }

        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isClicked = true;
                if (getCurrentFragment().getPosition() == fragment.length - 1 && isClicked) {

                    save();
                } else {

                    back.setVisibility(View.VISIBLE);
                    setNextFragment(fragment[getCurrentFragment().getPosition() + 1]);
                    switchFragment(getNextFragment());

                    for (int i = 0; i <= fragment.length; i++) {

                        if (getCurrentFragment().getPosition() == fragment.length - 1) {
                            button.setText("Save");

                            back.setText(fragment[getCurrentFragment().getPosition() - 1].getTitle());

                        } else {
                            back.setText(fragment[getCurrentFragment().getPosition() - 1].getTitle());
                            button.setText(fragment[getCurrentFragment().getPosition() + 1].getTitle());
                        }


                    }


                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNextFragment(fragment[getCurrentFragment().getPosition() - 1]);
                switchBackFragment(getNextFragment());

                for (int i = 0; i < fragment.length; i++) {
                    if (getCurrentFragment().getPosition() == 0) {
                        button.setText(fragment[getCurrentFragment().getPosition() + 1].getTitle());
                        back.setVisibility(View.INVISIBLE);

                    } else {
                        back.setText(fragment[getCurrentFragment().getPosition() - 1].getTitle());
                        button.setText(fragment[getCurrentFragment().getPosition() + 1].getTitle());
                    }


                }

                if (getCurrentFragment().getPosition() != 0) {
                    setPreviousFragment(fragment[getCurrentFragment().getPosition() + 1]);
                }
                progressBar.setProgress(getNextFragment().getProgress());


            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addFragment(int i) {
        int UNIT_SIZE = (i + 1) * (100 / fragment.length);
        String title = list.get(i).getTitle();
        fragment[i] = new EditionFragment();
        fragment[i].setPosition(i);
        fragment[i].setListener(kit.getListeners());
        fragment[i].setTitle(title);
        fragment[i].setList(list);
        fragment[i].setProgress(UNIT_SIZE);
    }


    public static void setWindow(Window window) {
        AvatarActivity.window = window;
    }

    public static Window getWindowView() {
        return AvatarActivity.window;
    }

    public void switchFragment(EditionFragment to) {
        if (getCurrentFragment().getPosition() != 0) {
            setPreviousFragment(fragment[getCurrentFragment().getPosition() - 1]);
        }
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            // will update the "progress" propriety of seekbar until it reaches progress
            ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", to.getProgress());
            animation.setDuration(500); // 0.5 second
            animation.setInterpolator(new DecelerateInterpolator());
            animation.start();
        } else

            progressBar.setProgress(getNextFragment().getProgress());

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left,
                R.anim.slide_out_right, 0, 0);
        transaction.replace(R.id.container, to);
        transaction.commit();
        setCurrentFragment(to);


    }


    public void switchBackFragment(EditionFragment to) {
        if (getCurrentFragment().getPosition() != 0) {
            setPreviousFragment(fragment[getCurrentFragment().getPosition() - 1]);
        }

        if (android.os.Build.VERSION.SDK_INT >= 11) {
            // will update the "progress" propriety of seekbar until it reaches progress
            ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", to.getProgress());
            animation.setDuration(500); // 0.5 second
            animation.setInterpolator(new DecelerateInterpolator());
            animation.start();
        } else {
            progressBar.setProgress(getPreviousFragment().getProgress());
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right,
                0, 0);
        transaction.replace(R.id.container, to);
        transaction.commit();
        setCurrentFragment(to);


    }

    public void attachKit(Kit kit) {
        AvatarActivity.kit = kit;
    }

    public static Kit getKit() {
        return AvatarActivity.kit;
    }

    public void setNextFragment(EditionFragment post) {
        this.post = post;
    }

    public EditionFragment getNextFragment() {
        return this.post;
    }

    public void setCurrentFragment(EditionFragment current) {
        this.current = current;
    }

    public EditionFragment getCurrentFragment() {
        return this.current;
    }

    public void setPreviousFragment(EditionFragment pre) {
        if (pre.getPosition() != 0) {
            this.pre = pre;
        }

    }

    public EditionFragment getPreviousFragment() {
        return this.pre;

    }

    public static void share() {
        webview.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                bitmap = Bitmap.createBitmap(webview.getWidth(), webview.getHeight(), Bitmap.Config.ARGB_8888);
                final Canvas c = new Canvas(bitmap);
                webview.draw(c);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(MDSdirectory+"tmp.png");
                    if (fos != null) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        fos.close();
                    }
                } catch (Exception e) {
                    System.out.println("Error : " + e);
                }
            }
        }, 100);
        File tmpFile = new File(MDSdirectory+"tmp.png");
        final String photoUri;
        try {
            photoUri = MediaStore.Images.Media.insertImage(
                    getActivity().getContentResolver(), tmpFile.getAbsolutePath(), null, null);

            Intent shareIntent = ShareCompat.IntentBuilder.from(getActivity())
                    .setText("Woaw its my new Avatar ! free and open sourced ! : https://github.com/AlexLionne/MDAvatars ")
                    .setType("image/jpeg")
                    .setStream(Uri.parse(photoUri))
                    .getIntent()
                    .setPackage("com.google.android.apps.plus");
            AvatarActivity.getActivity().startActivity(shareIntent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tmpFile.delete();
    }

    public static void  save() {
        int count = utils.getAllSavedAvatars().size() + 1;
        new MaterialDialog.Builder(AvatarActivity.getActivity())
                .title("Name")
                .content("Set a name for your Avatar")
                .inputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputMaxLength(20)
                .positiveText("go")
                .input("name", "my_avatar_" + count, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, final CharSequence input) {

                        webview.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                bitmap = Bitmap.createBitmap(webview.getWidth(), webview.getHeight(), Bitmap.Config.ARGB_8888);
                                final Canvas c = new Canvas(bitmap);
                                webview.draw(c);
                                FileOutputStream fos = null;
                                try {
                                    fos = new FileOutputStream(MDSdirectory + input.toString() + ".png");
                                    if (fos != null) {
                                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                                        fos.close();
                                    }
                                } catch (Exception e) {
                                    System.out.println("-----error--" + e);
                                }
                            }
                        }, 100);


                    }
                })
                .show();
    }



    public static Bitmap selectImageBackground() {
        utils.checkPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE);
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        activity.startActivityForResult(photoPickerIntent, 1);
return AvatarActivity.bitmap;
    }
    public static void changeColor(String name, int color){
        preferences = AvatarActivity.getActivity().getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putInt(name, color);
        editor.apply();
    }
    public static void changePref(String name, String pref){
        preferences = AvatarActivity.getActivity().getSharedPreferences("com.alexlionne.apps.avatars", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString(name, pref);
        editor.apply();
    }

    public static void selectImageBodyBackground(final String bodyType) {

        new MaterialDialog.Builder(AvatarActivity.getActivity())
                .title("Link")
                .content("Provide a link to your image")
                .inputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .positiveText("go")
                .input("link", null, false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, final CharSequence input) {

                                try {
                                    Bitmap bitmap = getBitmapFromURL(input.toString());

                                    int color = Utils.getLightPalettefromBitmap(bitmap);

                                    String javascript = "javascript:addSvgStuff('" + bodyType + "','body','" + input.toString() + "')";
                                    UIManager UI = new UIManager(webview);
                                    UI.loadColor("transparent","uparm");
                                    UI.loadColorforGroup(Utils.convertHexColorString(color), "shadow1");
                                    UI.loadColorforGroup(Utils.convertHexColorString(color), "shadow2");
                                    UI.loadColorforGroup(Utils.convertHexColorString(color), "shadow3");

                                    webview.loadUrl(javascript);


                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                ).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Uri selectedImage = data.getData();

                    String filePath = getPath(selectedImage);
                    String file_extn = filePath.substring(filePath.lastIndexOf(".") + 1);
                    File file_bitmap = new File(filePath);

                    if (file_extn.equals("img") || file_extn.equals("jpg") || file_extn.equals("jpeg") || file_extn.equals("gif") || file_extn.equals("png")) {

                        int targetW = webview.getWidth();
                        int targetH = webview.getHeight();
                        // Get the dimensions of the bitmap
                        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                        bmOptions.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(file_bitmap.getAbsolutePath(), bmOptions);
                        int photoW = bmOptions.outWidth;
                        int photoH = bmOptions.outHeight;

                        // Determine how much to scale down the image
                        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

                        // Decode the image file into a Bitmap sized to fill the View
                        bmOptions.inJustDecodeBounds = false;
                        bmOptions.inSampleSize = scaleFactor;
                        bmOptions.inPurgeable = true;

                        AvatarActivity.bitmap = BitmapFactory.decodeFile(file_bitmap.getAbsolutePath(), bmOptions);


                        if (Build.VERSION.SDK_INT >= 21) {
                            Window window = AvatarActivity.getWindowView();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                            window.setStatusBarColor(Utils.getLightPalettefromBitmap(bitmap));
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setNavigationBarColor(Utils.getLightPalettefromBitmap(bitmap));
                        }

                        updateViews(bitmap);
                        Drawable drawable = new BitmapDrawable(bitmap);
                        webview.setBackgroundColor(Color.TRANSPARENT);
                        webview.setBackground(drawable);
                        getKit().getAllcategories().get(0).getItem(1).getBackgroundImage().setVisibility(View.VISIBLE);
                        getKit().getAllcategories().get(0).getItem(1).getBackgroundImage().setImageBitmap(bitmap);
                        getKit().getAllcategories().get(0).getItem(0).getCard().setCardBackgroundColor(Utils.getLightPalettefromBitmap(bitmap));
                        getKit().getAllcategories().get(0).getItem(0).getTextView().setTextColor(getResources().getColor(R.color.md_white_1000));
                        getKit().getAllcategories().get(0).getItem(0).getIcon().color(getResources().getColor(R.color.md_white_1000));
                        changeColor("BackgroundColor",Utils.getLightPalettefromBitmap(bitmap));
                    } else {
                        //NOT IN REQUIRED FORMAT
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        }else if(requestCode==3){
            if (resultCode == Activity.RESULT_OK) {

            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

    public static Bitmap getBitmapFromURL(final String src) throws ExecutionException, InterruptedException {
        class BitmapLoader extends AsyncTask<Bitmap, Void, Bitmap> {
            private Bitmap myBitmap;

            @Override
            protected Bitmap doInBackground(Bitmap... params) {
                try {
                    java.net.URL url = new java.net.URL(src);
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    myBitmap = BitmapFactory.decodeStream(input);

                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }

                return myBitmap;
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                setBitmap(result);
            }

            private void setBitmap(Bitmap bitmap) {
                this.myBitmap = bitmap;
            }

            private Bitmap getMyBitmap() {
                return this.myBitmap;
            }

        }

        return new BitmapLoader().execute().get();
    }

    public void updateViews(Bitmap bitmap) {
        Drawable left_arrow = new IconicsDrawable(this, GoogleMaterial.Icon.gmd_keyboard_arrow_left).sizeDp(16).paddingDp(4).color(Utils.getTitleTextColor(bitmap));
        Drawable right_arrow = new IconicsDrawable(this, GoogleMaterial.Icon.gmd_keyboard_arrow_right).sizeDp(16).paddingDp(4).color(Utils.getTitleTextColor(bitmap));
        button.setCompoundDrawablesWithIntrinsicBounds(null, null, right_arrow, null);
        back.setCompoundDrawablesWithIntrinsicBounds(left_arrow, null, null, null);
        view.setBackgroundColor(Utils.getLightPalettefromBitmap(bitmap));
        button.setTextColor(Utils.getTitleTextColor(bitmap));
        back.setTextColor(Utils.getTitleTextColor(bitmap));
    }

    @Override
    public void onBackPressed(){
        new MaterialDialog.Builder(AvatarActivity.getActivity())
                .title("Exit editor")
                .content("Are you sure you want to leave ?")
                .positiveText("Yes")
                .negativeText("Back")
                .negativeColor(getResources().getColor(R.color.accent))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Intent i = new Intent(AvatarActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }


    public static void showDirectoryChooser() {
        final DirectoryChooserFragment df = DirectoryChooserFragment.newInstance("wall-splash", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());
        df.setDirectoryChooserListener(new DirectoryChooserFragment.OnFragmentInteractionListener() {
            @Override
            public void onSelectDirectory(@NonNull final String path) {

                int count = utils.getAllSavedAvatars().size() + 1;
                new MaterialDialog.Builder(AvatarActivity.getActivity())
                        .title("Name")
                        .content("Set a name for your Avatar")
                        .inputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                                InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                        .inputMaxLength(20)
                        .positiveText("go")
                        .input("name", "my_avatar_" + count, false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, final CharSequence input) {

                                webview.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        // TODO Auto-generated method stub
                                        bitmap = Bitmap.createBitmap(webview.getWidth(), webview.getHeight(), Bitmap.Config.ARGB_8888);
                                        final Canvas c = new Canvas(bitmap);
                                        webview.draw(c);
                                        FileOutputStream fos = null;
                                        try {
                                            fos = new FileOutputStream(path +"/"+ input.toString() + ".png");
                                            utils.addDirectorySet(getActivity(),path +"/"+ input.toString() + ".png");
                                            if (fos != null) {
                                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                                                fos.close();
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Error : " + e);
                                        }
                                    }
                                }, 100);


                            }
                        })
                        .show();
            df.dismiss();
            }

            @Override
            public void onCancelChooser() {
                df.dismiss();
            }
        });
        df.show(sfm, "MyDF");
    }
}

