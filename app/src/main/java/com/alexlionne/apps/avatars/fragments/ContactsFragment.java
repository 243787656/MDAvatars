package com.alexlionne.apps.avatars.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alexlionne.apps.avatars.MainActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.Utils.Utils;
import com.alexlionne.apps.avatars.adapters.ContactsAdapters;
import com.alexlionne.apps.avatars.adapters.FileAdapter;
import com.alexlionne.apps.avatars.objects.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Alex on 08/03/2016.
 */
public class ContactsFragment extends Fragment implements ContactsAdapters.OnItemClickListener {
    private ViewGroup root;
    private int mColumnCount;
    private int numColumns = 1;
    private static int DEFAULT_COLUMNS_PORTRAIT;
    private static int DEFAULT_COLUMNS_LANDSCAPE;
    private RecyclerView recyclerView;
    private ContactsAdapters kitAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private  ArrayList<Contact> myAvatars;
    private Contact contact;
    private Utils utils;
    private SwipeRefreshLayout swipeRefreshLayout;



    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.my_avatars_layout, container, false);
        FloatingActionButton fab = (FloatingActionButton)root.findViewById(R.id.fab);

        kitAdapter = null;
        DEFAULT_COLUMNS_PORTRAIT = 1;
        DEFAULT_COLUMNS_LANDSCAPE = 1;
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler);
        final boolean isLandscape = isLandscape();
        int mColumnCountPortrait = DEFAULT_COLUMNS_PORTRAIT;
        int mColumnCountLandscape = DEFAULT_COLUMNS_LANDSCAPE;
        int newColumnCount = isLandscape ? mColumnCountLandscape : mColumnCountPortrait;
        if (mColumnCount != newColumnCount) {
            mColumnCount = newColumnCount;
            numColumns = mColumnCount;
        }
        utils = new Utils(getActivity());
        new UpdateUI().execute();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).result.setSelection(0);
                ((MainActivity) getActivity()).switchFragment(0, "Kits", "Kit");
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.feed_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new UpdateUI().execute();
            }
        });
     return root;

    }

    class UpdateUI extends AsyncTask<Void, Void, Void> {

        @Override
        public void onPreExecute() {
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected Void doInBackground(Void... params) {

            myAvatars = new ArrayList<>();
            contact = new Contact(getActivity());
            if(contact.getAllContacts().size()!=0) {

                for (int i = 0; i<contact.getAllContacts().size(); i++) {
                    myAvatars.add(contact.getAllContacts().get(i));
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
            kitAdapter = new ContactsAdapters(getContext(), myAvatars, ContactsFragment.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(kitAdapter);
            if(swipeRefreshLayout != null) swipeRefreshLayout.setRefreshing(false);


        }
    }




    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
    @Override
    public void onItemClick(int position) {
        final Contact contact = ((ContactsAdapters) recyclerView.getAdapter()).getItemAtPosition(position);

    }
}