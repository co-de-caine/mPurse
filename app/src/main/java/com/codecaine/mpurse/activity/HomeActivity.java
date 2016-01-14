package com.codecaine.mpurse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.codecaine.mpurse.R;
import com.codecaine.mpurse.adapter.MainPagerAdapter;
import com.codecaine.mpurse.entity.DBConstants;
import com.codecaine.mpurse.helper.SQLiteHandler;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private SQLiteHandler db;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton bFab;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TextView name;
    private TextView wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new SQLiteHandler(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        View nav_header = mNavigationView.getHeaderView(0);

        name = (TextView) nav_header.findViewById(R.id.tvName);
        wallet = (TextView) nav_header.findViewById(R.id.tvWalletAmt);

        setupActionBarDrawerToogle();
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new MainPagerAdapter(
                getSupportFragmentManager()));
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);
        bFab = (FloatingActionButton) findViewById(R.id.fab);
        bFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MyWiFiActivity.class));
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Snackbar.make(main_content, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void setupActionBarDrawerToogle() {

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                Snackbar.make(view, R.string.drawer_close, Snackbar.LENGTH_SHORT).show();
                updateNavDrawerDetails();
            }


            public void onDrawerOpened(View drawerView) {
                Snackbar.make(drawerView, R.string.drawer_open, Snackbar.LENGTH_SHORT).show();
                updateNavDrawerDetails();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    private void updateNavDrawerDetails() {
        HashMap<String, String> user = db.getUserDetails();
        db.close();
        name.setText(user.get(DBConstants.KEY_NAME));
        wallet.setText(user.get(DBConstants.KEY_WALLET));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                updateNavDrawerDetails();
                return true;
            case R.id.recv_request:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}
