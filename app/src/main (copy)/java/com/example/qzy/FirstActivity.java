package com.example.qzy;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class FirstActivity extends AppCompatActivity {
    FloatingActionButton BtnAddQuiz;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        // ...From section above...
        // Find our drawer view
    Log.e("First", "Begining APplication");
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);


  Log.e("First Act", "Checking nvDrawer"+nvDrawer.toString());
        // Setup drawer view
        setupDrawerContent(nvDrawer);
    }



    private void setupDrawerContent(NavigationView navigationView) {

Log.e("FirstAct","Setting up Drawer contents");
        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = MainAct_frag.class;
        //fragment = (Fragment) fragmentClass.newInstance();

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = MainAct_frag.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = Pref.class;
                break;
            case R.id.nav_Third_fragment:
                Log.e("FirstAct","Reset od DB requested, Deleting DB");
                getApplicationContext().deleteDatabase("Qzy.db");
            case R.id.nav_Fourth_fragment:
                    Log.e("FirstAct","Synch Service starting");
                    startService(new Intent(this,SynchQuizes.class));
            default:
                fragmentClass = MainAct_frag.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();

    }

}