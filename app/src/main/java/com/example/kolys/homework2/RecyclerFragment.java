package com.example.kolys.homework2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kolys.homework2.RecyclerView.RecyclerAdapter;

import java.util.ArrayList;

import io.reactivex.Observable;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment {

    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    Toolbar toolbar;

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
        recyclerAdapter = new RecyclerAdapter(new RecycleDiffCallBack());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(v.getContext());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.submitList(getList());
        recyclerView.setLayoutManager(manager);
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
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setView(LayoutInflater.from(getContext()).inflate(R.layout.progress_bar, null))
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.cancel()).create();
        dialog.show();
        ProgressBar pb = dialog.findViewById(R.id.pb_dialog);
        switch (item.getItemId()) {
            case R.id.sort_by_name:
                Observable.fromIterable(getList())
                        .take(12)
                        .doOnNext(planet -> {
                            planet.setName(planet.getName() + planet.getName().length());
                            pb.setProgress(pb.getProgress() + 1);
                        })
                        .toSortedList((p1, p2) -> p1.getName().compareTo(p2.getName()))
                        .subscribe(list -> recyclerAdapter.submitList(list));
                Toast.makeText(getContext(), "Name", Toast.LENGTH_LONG).show();
                break;
            case R.id.sort_by_temp:
                Observable.fromIterable(getList())
                        .take(12)
                        .doOnNext(planet -> {
                            planet.setName(planet.getName() + planet.getName().length());
                            pb.setProgress(pb.getProgress() + 1);
                        })
                        .toSortedList((p1, p2) -> p1.getTemp() - p2.getTemp())
                        .subscribe(list -> recyclerAdapter.submitList(list));
                Toast.makeText(getContext(), "Temp", Toast.LENGTH_LONG).show();
                break;
        }
        dialog.dismiss();
        return true;
    }

    public static ArrayList<Planet> getList() {
        ArrayList<Planet> list = new ArrayList<Planet>();
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetA",
                "It's a planet", "A lot", 10000, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetD",
                "It's a planet", "A lot", 10090, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetG",
                "It's a planet", "A lot", 10400, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetB",
                "It's a planet", "A lot", 17600, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetO",
                "It's a planet", "A lot", 20000, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetE",
                "It's a planet", "A lot", 40000, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetL",
                "It's a planet", "A lot", 44000, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetM",
                "It's a planet", "A lot", 16000, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetQ",
                "It's a planet", "A lot", 10008, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetS",
                "It's a planet", "A lot", 23000, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetL",
                "It's a planet", "A lot", 32000, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetC",
                "It's a planet", "A lot", 65000, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetX",
                "It's a planet", "A lot", 12200, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetY",
                "It's a planet", "A lot", 32100, "far away"));
        list.add(new Planet(R.drawable.ic_planet_template, "PlanetZ",
                "It's a planet", "A lot", 65800, "far away"));
        return list;
    }

}
