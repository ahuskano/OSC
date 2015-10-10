package co.ahuskano.something.activitys;


import co.ahuskano.something.R;

public class SplashActivity extends SplashAbstract {

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

        return MainActivity.class;
    }
}
