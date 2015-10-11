package co.ahuskano.something.controllers;

import android.app.Activity;
import android.util.Log;

import co.ahuskano.something.api.LogInRequest;
import co.ahuskano.something.api.LogInResponse;
import co.ahuskano.something.api.OSCApi;
import co.ahuskano.something.api.SpaceResponse;
import co.ahuskano.something.util.APIUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ahuskano on 11.10.2015..
 */
public class LogInController extends OSCController {

    public LogInController(Activity activity) {
        super(activity);
    }


    private Callback<LogInResponse> callbackLogin = new Callback<LogInResponse>() {
        @Override
        public void success(LogInResponse responseLogIn, Response response) {
            Log.d("test", "success");
            dismissDialog();
            if (getOnDataReadListener() != null)
                getOnDataReadListener().onDataReceive(responseLogIn);

        }

        @Override
        public void failure(RetrofitError error) {
            dismissDialog();
            Log.d("test", "failure "+ error.getMessage());
            if (getOnDataErrorListener() != null)
                getOnDataErrorListener().onDataErrorReceive(error);
        }
    };


    public void logIn(String username, String password, String gcm){
        showDialog();
        LogInRequest request=new LogInRequest(username,password,gcm);
        APIUtils.getRestAdapter(OSCApi.API_LOCATION).create(OSCApi.class).logIn(request, callbackLogin);
    }
}
