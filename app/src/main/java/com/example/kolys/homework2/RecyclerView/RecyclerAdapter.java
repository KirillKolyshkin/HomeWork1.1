package com.example.kolys.homework2.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.kolys.homework2.Planet;
import com.example.kolys.homework2.R;
import com.example.kolys.homework2.RecyclerFragment;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends android.support.v7.recyclerview.extensions.ListAdapter<Planet, RecyclerAdapter.PlanetViewHolder> {

    ArrayList<Planet> planets;

    public RecyclerAdapter(@NonNull DiffUtil.ItemCallback<Planet> diffCallback, ArrayList<Planet> planets) {
        super(diffCallback);
        this.planets = planets;
    }

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_item, viewGroup, false);
        return new PlanetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetViewHolder planetViewHolder, int i) {
        Planet planet = planets.get(i);
        planetViewHolder.nameTV.setText(planet.getName());
        planetViewHolder.distanceTV.setText("temp = " + String.valueOf(planet.getTemp()));
        planetViewHolder.photoIV.setImageResource(planet.getPhotoResId());
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    class PlanetViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTV;
        public TextView distanceTV;
        public ImageView photoIV;

        public PlanetViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.planet_name);
            distanceTV = itemView.findViewById(R.id.planet_distance);
            photoIV = itemView.findViewById(R.id.planet_photo);
        }
    }
}
