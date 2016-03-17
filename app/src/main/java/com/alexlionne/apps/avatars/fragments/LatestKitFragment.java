package com.alexlionne.apps.avatars.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.adapters.KitAdapter;
import com.alexlionne.apps.avatars.objects.Kit;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarFragment;

import java.util.ArrayList;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by Alex on 08/03/2016.
 */
public class LatestKitFragment extends Fragment implements KitAdapter.OnItemClickListener {
    private ViewGroup root;
    private int mColumnCount;
    private int numColumns = 1;
    private static int DEFAULT_COLUMNS_PORTRAIT;
    private static int DEFAULT_COLUMNS_LANDSCAPE;
    private RecyclerView recyclerView;
    private KitAdapter kitAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Kit> kits;
    private ArrayList<Kit> kits_result;
    private boolean hidden = true;

    public LatestKitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.latest_kit_layout, container, false);


        recyclerView = (RecyclerView) root.findViewById(R.id.recycler);

        kitAdapter = null;
        DEFAULT_COLUMNS_PORTRAIT = 2;
        DEFAULT_COLUMNS_LANDSCAPE = 2;
        final boolean isLandscape = isLandscape();
        int mColumnCountPortrait = DEFAULT_COLUMNS_PORTRAIT;
        int mColumnCountLandscape = DEFAULT_COLUMNS_LANDSCAPE;
        int newColumnCount = isLandscape ? mColumnCountLandscape : mColumnCountPortrait;
        if (mColumnCount != newColumnCount) {
            mColumnCount = newColumnCount;
            numColumns = mColumnCount;
        }
        root.post(new Runnable() {
            @Override
            public void run() {

                UI();
            }
        });

        return root;

    }

    public void UI() {


        class UpdateUI extends AsyncTask<Void, Void, Void> {

            @Override
            public void onPreExecute() {
            }

            @Override
            protected Void doInBackground(Void... params) {

                kits = new Kit(getActivity().getApplicationContext()).getAllKits();
                kits_result = new ArrayList<>();
                for (int i = kits.size() - 1; i >= 0; i--) {
                    if (i < 5) {
                        kits_result.add(kits.get(i));
                    }
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void args) {

                layoutManager = new GridLayoutManager(getContext(), numColumns);
                kitAdapter = new KitAdapter(getContext(), kits_result, LatestKitFragment.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(kitAdapter);


            }
        }
        new UpdateUI().execute();
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onItemClick(int position) {
        Kit kit = ((KitAdapter) recyclerView.getAdapter()).getItemAtPosition(position);
        Intent i = new Intent(getActivity(), AvatarActivity.class)
                .putExtra("kit", kit.getName());
        getActivity().startActivity(i);

    }
}
