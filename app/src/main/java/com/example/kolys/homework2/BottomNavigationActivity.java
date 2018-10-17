package com.example.kolys.homework2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BottomNavigationActivity extends AppCompatActivity {

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_recycler:
                                fragment = RecyclerFragment.newInstance();
                                break;
                            case R.id.action_pager:
                                fragment = ViewPagerFragment.newInstance();
                                break;
                            case R.id.action_solar_system:
                                fragment = GalaxyTitleFragment.newInstance();
                                break;
                        }
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.container, fragment)
                                .commit();
                        return true;
                    }
                });
    }

}
