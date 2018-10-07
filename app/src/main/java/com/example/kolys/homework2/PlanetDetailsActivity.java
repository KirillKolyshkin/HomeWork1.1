package com.example.kolys.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlanetDetailsActivity extends AppCompatActivity {

    ImageView iv_photo;
    TextView tv_name;
    TextView tv_discription;
    TextView tv_distance;
    TextView tv_weigth;
    TextView tv_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_details);
        iv_photo = findViewById(R.id.iv_photo);
        tv_name = findViewById(R.id.tv_name);
        tv_discription = findViewById(R.id.tv_desc);
        tv_distance = findViewById(R.id.tv_distance);
        tv_weigth = findViewById(R.id.tv_weigth);
        tv_temp = findViewById(R.id.tv_temp);

        Intent intent = getIntent();
        if (intent != null) {
            iv_photo.setImageResource(intent.getIntExtra("PlanetPhoto", 0));
            tv_name.setText(intent.getStringExtra("PlanetName"));
            tv_discription.setText(intent.getStringExtra("PlanetDesc"));
            tv_weigth.setText(intent.getStringExtra("PlanetWeight"));
            tv_temp.setText(intent.getStringExtra("PlanetTemp"));
            tv_distance.setText(intent.getStringExtra("PlanetDist"));
        }
    }
}
