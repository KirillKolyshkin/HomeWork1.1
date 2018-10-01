package com.example.kolys.homework2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MyListener {

    TextView login;
    TextView email;
    Fragment message_fr;
    Fragment news_fr;
    ProfileFragment profile_fr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        login = view.findViewById(R.id.tv_header_login);
        email = view.findViewById(R.id.tv_header_email);
        message_fr = new MessageFragment();
        news_fr = new NewsFragment();
        profile_fr = new ProfileFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Создадим новый фрагмент
        Fragment fragment = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (id) {
            case R.id.nav_messages:
                fragmentTransaction.replace(R.id.container, message_fr);
                fragmentTransaction.addToBackStack("message");
                break;

            case R.id.nav_news:
                fragmentTransaction.replace(R.id.container, news_fr);
                fragmentTransaction.addToBackStack("news");
                break;

            case R.id.nav_profile:
                fragmentTransaction.replace(R.id.container, profile_fr);
                fragmentTransaction.addToBackStack("profile");
        }
        fragmentTransaction.commit();
        item.setChecked(true);

        setTitle(item.getTitle());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void callBack(String login, String email) {
        profile_fr.saveData(login, email);
        this.login.setText(login);
        this.email.setText(email);
    }
}
