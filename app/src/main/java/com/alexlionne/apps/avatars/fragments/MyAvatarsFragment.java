package com.alexlionne.apps.avatars.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alexlionne.apps.avatars.MainActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils.Utils;
import com.alexlionne.apps.avatars.adapters.FileAdapter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Alex on 08/03/2016.
 */
public class MyAvatarsFragment extends Fragment implements FileAdapter.OnItemClickListener {
    private ViewGroup root;
    private int mColumnCount;
    private int numColumns = 1;
    private static int DEFAULT_COLUMNS_PORTRAIT;
    private static int DEFAULT_COLUMNS_LANDSCAPE;
    private RecyclerView recyclerView;
    private FileAdapter kitAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private  ArrayList<File> myAvatars;



    public MyAvatarsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.my_avatars_layout, container, false);
        FloatingActionButton fab = (FloatingActionButton)root.findViewById(R.id.fab);

        kitAdapter = null;
        DEFAULT_COLUMNS_PORTRAIT = 2;
        DEFAULT_COLUMNS_LANDSCAPE = 2;
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler);
        final boolean isLandscape = isLandscape();
        int mColumnCountPortrait = DEFAULT_COLUMNS_PORTRAIT;
        int mColumnCountLandscape = DEFAULT_COLUMNS_LANDSCAPE;
        int newColumnCount = isLandscape ? mColumnCountLandscape : mColumnCountPortrait;
        if (mColumnCount != newColumnCount) {
            mColumnCount = newColumnCount;
            numColumns = mColumnCount;
        }


        new UpdateUI().execute();
        Log.d("Skinner : ", "Checking permissions");
        Utils.checkPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).result.setSelection(0);
                ((MainActivity) getActivity()).switchFragment(0, "Kits", "Kit");
            }
        });
     return root;

    }

    class UpdateUI extends AsyncTask<Void, Void, Void> {

        @Override
        public void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... params) {

            myAvatars = new ArrayList<>();
            if(Utils.getAllSavedAvatars().size()!=0) {

                for (int i = 0; i < Utils.getAllSavedAvatars().size(); i++) {
                    myAvatars.add(Utils.getAllSavedAvatars().get(i));
                }

            }else{
                Log.d("Skinner : ", "No Avatars");
            }


            return null;
        }
        protected void onProgressUpdate(Void... progress) {


        }
        @Override
        protected void onPostExecute(Void args) {
            layoutManager = new GridLayoutManager(getContext(), numColumns);
            kitAdapter = new FileAdapter(getContext(), myAvatars, MyAvatarsFragment.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(kitAdapter);

        }
    }


    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
    @Override
    public void onItemClick(int position) {
        final File file = ((FileAdapter) recyclerView.getAdapter()).getItemAtPosition(position);

        new MaterialDialog.Builder(getActivity())
                .title("Avatar")
                .content("What do you want to do ?")
                .positiveText("rename")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Log.d("Skinner : ", "Rename the file to : ");
                    }
                })
                .negativeText("share")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        File tmpFile = new File(file.getPath());
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
                            startActivity(shareIntent);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Log.d("Skinner : ", "Error while sharing : "+e);
                        }
                    }
                })
                .neutralText("delete")
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        file.delete();
                        new UpdateUI().execute();
                        dialog.dismiss();
                        //snackbar
                    }
                })
                .show();

    }
}