package co.ahuskano.something.activitys;


import android.content.Intent;
import android.os.Bundle;

import com.dmacan.lightandroidgcm.listener.OnGcmRegisteredListener;

import co.ahuskano.something.R;
import co.ahuskano.something.util.PreferenceManager;
import co.ahuskano.something.util.gcm.SimpleGcmRegistar;

public class SplashActivity extends SplashAbstract implements OnGcmRegisteredListener {

    private SimpleGcmRegistar gcmRegistar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutRes());
        if(PreferenceManager.getGCMkey(getApplicationContext()).equals("default"))
            setup();
    }

    private void setup() {
        this.gcmRegistar = new SimpleGcmRegistar(this);
        this.gcmRegistar.setOnGcmRegisteredListener(this);
        this.gcmRegistar.registerInBackground(this.getString(R.string.sender_id));
    }

    @Override
    public int provideLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public int getSplashTime() {
        return 1500;
    }

    @Override
    public Class getNextClassActivity() {
        if(PreferenceManager.getRememberMe(this))
            return MainActivity.class;
        return LogInActivity.class;
    }

    @Override
    public void onGcmRegistered(boolean b, String key) {
        PreferenceManager.setGCMkey(getApplicationContext(),key);
        Intent intent = new Intent(getBaseContext(), getNextClassActivity());
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
