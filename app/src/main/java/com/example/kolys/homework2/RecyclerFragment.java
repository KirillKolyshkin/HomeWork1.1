package com.example.kolys.homework2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kolys.homework2.RecyclerView.RecyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment {

    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    private ArrayList<Planet> planets = new ArrayList<Planet>();

    public RecyclerFragment() {
    }

    public static RecyclerFragment newInstance() {
        Bundle args = new Bundle();
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);
        fillTestData();
        recyclerAdapter = new RecyclerAdapter(new RecycleDiffCallBack(), planets);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(v.getContext());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(manager);
        recyclerAdapter.submitList(planets);
        toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((BottomNavigationActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tb, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ArrayList<Planet> planets = this.planets;
        switch (item.getItemId()) {
            case R.id.sort_by_name:
                Collections.sort(planets, (planet1, planet2) ->
                        planet1.getName().compareTo(planet2.getName())//.charAt(6) - planet2.getName().charAt(6)
                );
                recyclerAdapter.submitList(planets);
                recyclerAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Name", Toast.LENGTH_LONG).show();
                break;
            case R.id.sort_by_temp:
                Collections.sort(planets, (planet1, planet2) ->
                        planet1.getTemp() - planet2.getTemp()
                );
                recyclerAdapter.submitList(planets);
                recyclerAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Temp", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    private void fillTestData() {
        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            Planet planet = new Planet(R.drawable.ic_planet_template, "Planet" +  r.nextInt(99),
                    "It's a planet", "A lot", r.nextInt(10000000), "far away");
            planets.add(planet);
        }
    }
}
