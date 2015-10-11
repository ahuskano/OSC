package co.ahuskano.something.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import co.ahuskano.something.R;
import co.ahuskano.something.util.Constants;

/**
 * Created by ahuskano on 11.10.2015..
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = new SupportMapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, mapFragment).commit();
        mapFragment.getMapAsync(this);
        initToolbar();
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle(getIntent().getExtras().getString(Constants.KEY_SPACE_NAME));
        toolbar.getMenu().clear();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(getIntent().getStringExtra(Constants.KEY_LOCATION_LAT)), Double.valueOf(getIntent().getStringExtra(Constants.KEY_LOCATION_LNG))), 10));
        googleMap.addMarker(
                new MarkerOptions().position(
                        new LatLng(
                                Double.valueOf(getIntent().getStringExtra(Constants.KEY_LOCATION_LAT)),
                                Double.valueOf(getIntent().getStringExtra(Constants.KEY_LOCATION_LNG))))
                        .title(getIntent().getStringExtra(Constants.KEY_SPACE_NAME)).snippet(getIntent().getStringExtra(Constants.KEY_SPACE_ID)).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_map_smaller)));

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                Intent intent = new Intent(MapActivity.this, SpaceDetailActivity.class);
                intent.putExtra(Constants.KEY_SPACE_ID, getIntent().getStringExtra(Constants.KEY_SPACE_ID));
                intent.putExtra(Constants.KEY_SPACE_NAME, getIntent().getStringExtra(Constants.KEY_SPACE_NAME));
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
