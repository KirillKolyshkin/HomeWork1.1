package com.example.kolys.homework2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalaxyTitleFragment extends Fragment {


    public GalaxyTitleFragment() {
        // Required empty public constructor
    }

    public static GalaxyTitleFragment newInstance() {

        Bundle args = new Bundle();

        GalaxyTitleFragment fragment = new GalaxyTitleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_galaxy_title, container, false);
    }

}
