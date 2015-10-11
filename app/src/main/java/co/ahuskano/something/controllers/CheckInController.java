package co.ahuskano.something.controllers;

import android.app.Activity;
import android.util.Log;

import co.ahuskano.something.api.CheckInReponse;
import co.ahuskano.something.api.LogInRequest;
import co.ahuskano.something.api.LogInResponse;
import co.ahuskano.something.api.OSCApi;
import co.ahuskano.something.util.APIUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ahuskano on 11.10.2015..
 */
public class CheckInController extends OSCController {

    public CheckInController(Activity activity) {
        super(activity);
    }


    private Callback<CheckInReponse> callbackCheckin = new Callback<CheckInReponse>() {
        @Override
        public void success(CheckInReponse responseLogIn, Response response) {
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


    public void chechIn(String status_id, String token){
        APIUtils.getRestAdapter(OSCApi.API_LOCATION).create(OSCApi.class).checkIn(status_id,token, callbackCheckin);
    }
}
