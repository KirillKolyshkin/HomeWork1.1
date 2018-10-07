package com.example.kolys.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kolys.homework2.RecyclerView.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private List<Planet> planets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillTestData();
        MyAdapter adapter = new MyAdapter(planets, this);
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    private void fillTestData() {
        planets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Planet planet = new Planet(R.drawable.ic_planet_template, "Planet" + i,
                    "It's a planet", "A lot", "Too hot", "Far away");
            planets.add(planet);
        }
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(this, PlanetDetailsActivity.class);
        intent.putExtra("PlanetName", planets.get(position).getName());
        intent.putExtra("PlanetDesc", planets.get(position).getDiscription());
        intent.putExtra("PlanetDist", planets.get(position).getDistance());
        intent.putExtra("PlanetTemp", planets.get(position).getTemp());
        intent.putExtra("PlanetWeight", planets.get(position).getWeight());
        intent.putExtra("PlanetPhoto", planets.get(position).getPhotoResId());
        startActivity(intent);
    }
}
