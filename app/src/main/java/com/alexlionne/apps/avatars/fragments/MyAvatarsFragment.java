package com.alexlionne.apps.avatars.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils;
import com.alexlionne.apps.avatars.adapters.FileAdapter;
import com.alexlionne.apps.avatars.adapters.KitAdapter;
import com.alexlionne.apps.avatars.objects.Kit;

import java.io.File;
import java.io.FileOutputStream;
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.kit_layout, container, false);
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
     return root;

    }

    class UpdateUI extends AsyncTask<Void, Void, Void> {

        @Override
        public void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... params) {

            myAvatars = Utils.getAllSavedAvatars();



            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            layoutManager = new GridLayoutManager(getContext(), numColumns);
            kitAdapter = new FileAdapter(getContext(), myAvatars, MyAvatarsFragment.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter( kitAdapter);


        }
    }


    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
    @Override
    public void onItemClick(int position) {
        File file = ((FileAdapter) recyclerView.getAdapter()).getItemAtPosition(position);

        new MaterialDialog.Builder(getActivity())
                .title("Avatar")
                .content("What do you want to do ?")
                .positiveText("set")
                .neutralText("share")
                .negativeText("delete")
                .show();

    }
}