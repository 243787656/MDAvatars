package com.alexlionne.apps.avatars.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexlionne.apps.avatars.AvatarActivity;
import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.adapters.KitAdapter;
import com.alexlionne.apps.avatars.objects.Kit;
import com.alexlionne.apps.avatars.objects.kits.GoogleKitOne;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarFragment;
import com.roughike.bottombar.BottomBarTab;

import java.util.ArrayList;

/**
 * Created by Alex on 08/03/2016.
 */
public class KitFragment extends Fragment {
    private ViewGroup root;
    private BottomBar mBottomBar;

    public KitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kit_layout,container, false);

        BottomBar bottomBar = BottomBar.attach(view, savedInstanceState);
        bottomBar.setFragmentItems(getActivity().getSupportFragmentManager(), R.id.container,
                        new BottomBarFragment(new LatestKitFragment(), new IconicsDrawable(getActivity(), CommunityMaterial.Icon.cmd_trending_up).sizeDp(18), "Trending"),
                        new BottomBarFragment(new LatestKitFragment(), new IconicsDrawable(getActivity(), CommunityMaterial.Icon.cmd_newspaper).sizeDp(18), "Latest"),
                        new BottomBarFragment(new LatestKitFragment(), new IconicsDrawable(getActivity(), CommunityMaterial.Icon.cmd_tag_faces).sizeDp(18), "All")
        );

        return bottomBar;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }
}