package co.ahuskano.something.activitys;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import co.ahuskano.something.R;
import co.ahuskano.something.api.BaseResponse;
import co.ahuskano.something.api.SpaceResponse;
import co.ahuskano.something.controllers.BaseController;
import co.ahuskano.something.controllers.SpaceController;
import co.ahuskano.something.util.Constants;
import retrofit.RetrofitError;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class SpaceDetailActivity extends AppCompatActivity implements BaseController.OnDataReadListener, BaseController.OnDataErrorListener {

    private Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.space_detail_activity);
        initToolbar();
        SpaceController space=new SpaceController(this);
        space.setOnDataReadListener(this);
        space.setOnDataErrorListener(this);
        space.getSpaces(getIntent().getStringExtra(Constants.KEY_SPACE_ID));

    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Log.d("test", "TEST: " + getIntent().getStringExtra(Constants.KEY_SPACE_NAME));
        getSupportActionBar().setTitle(getIntent().getExtras().getString(Constants.KEY_SPACE_NAME));
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

    @Override
    public void onDataErrorReceive(RetrofitError error) {

    }

    @Override
    public void onDataReceive(BaseResponse response) {
        SpaceResponse space=(SpaceResponse) response;
        Log.d("test","SPACE: "+ space.getData().getName());
    }
}
