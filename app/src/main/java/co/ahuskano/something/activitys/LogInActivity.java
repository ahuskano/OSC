package co.ahuskano.something.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import co.ahuskano.something.R;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logIn;
    private AppCompatEditText username, password;
    private CheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initToolbar();
        setupViews();
    }

    private void setupViews() {
        username = (AppCompatEditText) findViewById(R.id.email);
        password = (AppCompatEditText) findViewById(R.id.password);
        rememberMe = (CheckBox) findViewById(R.id.chckRememberMe);
        logIn = (Button) findViewById(R.id.btnLogin);
        logIn.setOnClickListener(this);
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if(username.getText().toString().equals("user")){
                    startActivity(new Intent(this,MainActivity.class));
                }else{
                    Snackbar.make(password, "Odjebi", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

}

