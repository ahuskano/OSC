package co.ahuskano.something.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

import co.ahuskano.something.R;
import co.ahuskano.something.api.BaseResponse;
import co.ahuskano.something.api.LogInResponse;
import co.ahuskano.something.controllers.BaseController;
import co.ahuskano.something.controllers.LogInController;
import co.ahuskano.something.util.PreferenceManager;
import retrofit.RetrofitError;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener, BaseController.OnDataReadListener, BaseController.OnDataErrorListener {

    private Button logIn;
    private AppCompatEditText username, password;
    private CheckBox rememberMe;
    private LogInController controller;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        controller=new LogInController(this);
        controller.setOnDataErrorListener(this);
        controller.setOnDataReadListener(this);
        initToolbar();

        setupViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#704F87"));
        }
    }

    private void setupViews() {
        username = (AppCompatEditText) findViewById(R.id.email);
        password = (AppCompatEditText) findViewById(R.id.password);
        rememberMe = (CheckBox) findViewById(R.id.chckRememberMe);
        logIn = (Button) findViewById(R.id.btnLogin);
        logIn.setOnClickListener(this);
        checkBox=(CheckBox) findViewById(R.id.chckRememberMe);
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setLogo(R.drawable.ourspace_white_small);
            actionBar.setTitle("");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                PreferenceManager.setUsername(this,username.getText().toString());
                controller.logIn(username.getText().toString(),password.getText().toString(), PreferenceManager.getGCMkey(this));
                /*
                if(username.getText().toString().equals("user")){
                    startActivity(new Intent(this,MainActivity.class));
                }else{
                    Snackbar.make(password, "Odjebi", Snackbar.LENGTH_LONG).show();
                }*/
                break;
        }
    }

    @Override
    public void onDataErrorReceive(RetrofitError error) {
        Snackbar.make(password, error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDataReceive(BaseResponse response) {
        LogInResponse responseLogIn=(LogInResponse) response;
        if(responseLogIn.isSuccess()){
            if(checkBox.isChecked())
                PreferenceManager.setRememberMe(this,true);
            else
                PreferenceManager.setRememberMe(this, false);

            PreferenceManager.setToken(this,responseLogIn.getData().getApi_key());
            startActivity(new Intent(this, MainActivity.class));
            finish();
            }
    }
}

