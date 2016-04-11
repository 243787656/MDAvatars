package com.alexlionne.apps.avatars.Tour;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexlionne.apps.avatars.R;

public class userName_Fragment extends Fragment {

    public userName_Fragment() {
        // Required empty public constructor
    }

    public static userName_Fragment newInstance() {
        return new userName_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_name, container, false);

        return rootView;
    }


    public interface OnFragmentInteractionListener {
    }
}
