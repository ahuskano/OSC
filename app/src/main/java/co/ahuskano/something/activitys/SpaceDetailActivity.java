package co.ahuskano.something.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import co.ahuskano.something.R;
import co.ahuskano.something.api.BaseResponse;
import co.ahuskano.something.api.SpaceResponse;
import co.ahuskano.something.controllers.BaseController;
import co.ahuskano.something.controllers.CheckInController;
import co.ahuskano.something.controllers.SpaceController;
import co.ahuskano.something.models.Space;
import co.ahuskano.something.util.Constants;
import co.ahuskano.something.util.PreferenceManager;
import retrofit.RetrofitError;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class SpaceDetailActivity extends AppCompatActivity implements BaseController.OnDataReadListener, BaseController.OnDataErrorListener {

    private Toolbar toolbar;
    private ImageView image;
    private TextView tvAddress,tvDescription,tvContact;
    private Space space;
    private FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.space_detail_activity);
        initToolbar();
        final SpaceController space=new SpaceController(this);
        space.setOnDataReadListener(this);
        space.setOnDataErrorListener(this);
        space.getSpaces(getIntent().getStringExtra(Constants.KEY_SPACE_ID));
        image=(ImageView) findViewById(R.id.ivPhoto);
        tvAddress=(TextView) findViewById(R.id.tvAddress);
        tvDescription=(TextView) findViewById(R.id.tvDescription);
        tvContact=(TextView) findViewById(R.id.tvContact);
        fab=(FloatingActionButton) findViewById(R.id.fabCheckIn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIn();
            }
        });
        if(getIntent().getBooleanExtra(Constants.KEY_GCM,false))
            fab.setVisibility(View.GONE);
        findViewById(R.id.tvReview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SpaceDetailActivity.this,ReviewsActivity.class));
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#704F87"));
        }
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null && !getIntent().getBooleanExtra(Constants.KEY_GCM,false)) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle(getIntent().getExtras().getString(Constants.KEY_SPACE_NAME));
        toolbar.getMenu().clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.menu_map);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_map:
                Intent intent=new Intent(this, MapActivity.class);
                intent.putExtra(Constants.KEY_SPACE_NAME,space.getName());
                intent.putExtra(Constants.KEY_LOCATION_LAT,space.getLat());
                intent.putExtra(Constants.KEY_LOCATION_LNG,space.getLongitude());
                intent.putExtra(Constants.KEY_SPACE_ID,String.valueOf(space.getId()));
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataErrorReceive(RetrofitError error) {


    }

    @Override
    public void onDataReceive(BaseResponse response) {
        if(response instanceof SpaceResponse) {

            SpaceResponse spaceR = (SpaceResponse) response;
            space = spaceR.getData();
            Constants.reviews=space.getReviews();
            Picasso.with(this).load(space.getImage().getUrl()).placeholder(R.drawable.default_image).into(image);
            tvAddress.setText(space.getAddress());
            tvDescription.setText(space.getDescription());
            tvContact.setText(space.getContact());
            if(space.getAverage_noise_rating()<30){
                ((ImageView)findViewById(R.id.icVolume)).setImageResource(R.drawable.ic_volume_up_white_24dp_red);
            }else if(space.getAverage_noise_rating()<70){
                ((ImageView)findViewById(R.id.icVolume)).setImageResource(R.drawable.ic_volume_up_white_24dp_orange);

            }else{
                ((ImageView)findViewById(R.id.icVolume)).setImageResource(R.drawable.ic_volume_up_white_24dp_green);
            }
            if(space.getAverage_wifi_rating()<30){
                ((ImageView)findViewById(R.id.icWifi)).setImageResource(R.drawable.ic_settings_input_antenna_white_24dp_red);
            }else if(space.getAverage_wifi_rating()<70){
                ((ImageView)findViewById(R.id.icWifi)).setImageResource(R.drawable.ic_settings_input_antenna_white_24dp_orange);

            }else{
                ((ImageView)findViewById(R.id.icWifi)).setImageResource(R.drawable.ic_settings_input_antenna_white_24dp_green);
            }
            if(space.getAverage_price_rating()<30){
                ((ImageView)findViewById(R.id.icPrice)).setImageResource(R.drawable.ic_loyalty_white_24dp_red);
            }else if(space.getAverage_price_rating() <70){
                ((ImageView)findViewById(R.id.icPrice)).setImageResource(R.drawable.ic_loyalty_white_24dp_orange);

            }else{
                ((ImageView)findViewById(R.id.icPrice)).setImageResource(R.drawable.ic_loyalty_white_24dp_green);
            }
            getSupportActionBar().setTitle(space.getName());
        }else{
            Snackbar.make(fab, response.getMessages()[0], Snackbar.LENGTH_LONG).show();
        }
    }

    private void checkIn(){
        CheckInController con=new CheckInController(this);
        con.setOnDataReadListener(this);
        con.setOnDataErrorListener(this);
        con.chechIn(String.valueOf(space.getId()), PreferenceManager.getToken(this));

    }
}
