package co.ahuskano.something.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dmacan.lightandroidgcm.GcmObserver;
import com.dmacan.lightandroidgcm.listener.OnGcmMessageReceivedListener;
import com.dmacan.lightandroidgcm.listener.OnGcmRegisteredListener;

import co.ahuskano.something.R;
import co.ahuskano.something.fragments.FragmentFactory;
import co.ahuskano.something.util.PreferenceManager;
import co.ahuskano.something.util.gcm.SimpleGcmRegistar;


public class MainActivity extends AppCompatActivity implements OnGcmRegisteredListener, OnGcmMessageReceivedListener {

    private DrawerLayout drawerLayout;
    private View content;
    private TextView title;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private SimpleGcmRegistar gcmRegistar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = findViewById(R.id.content);
        initToolbar();
        setupDrawerLayout();
        if(PreferenceManager.getGCMkey(getApplicationContext()).equals("default"))
            setup();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }

    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.getOrder() != FragmentFactory.FRAGMENT_SETTINGS) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, FragmentFactory.provideFragment(menuItem.getOrder())).commit();
                    menuItem.setChecked(true);
                    getSupportActionBar().setTitle(menuItem.getTitle());
                    setMenu(menuItem.getOrder());
                    drawerLayout.closeDrawers();
                } else {
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    setMenu(menuItem.getOrder());
                    drawerLayout.closeDrawers();
                }                return true;
            }
        });
    }

    private void setMenu(int id) {
        switch (id) {
            case FragmentFactory.FRAGMENT_OVERVIEW:
                toolbar.getMenu().clear();
                break;
            case FragmentFactory.FRAGMENT_LIST:
                toolbar.getMenu().clear();
                break;
            case FragmentFactory.FRAGMENT_MAP:
                toolbar.getMenu().clear();
            default:
                toolbar.getMenu().clear();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onGcmMessageReceived(boolean b, Intent intent) {
        Log.i("test", "Gcm message received");
        Log.i("test", intent.getStringExtra("message"));
    }

    @Override
    public void onGcmRegistered(boolean b, String key) {
        Log.i("test", "Gcm registered: " + b);
        Log.i("test", "RegId: " + key);
        PreferenceManager.setGCMkey(getApplicationContext(),key);
    }

    private void setup() {
            this.gcmRegistar = new SimpleGcmRegistar(this);
            this.gcmRegistar.setOnGcmRegisteredListener(this);
            this.gcmRegistar.registerInBackground(this.getString(R.string.sender_id));
    }

}
