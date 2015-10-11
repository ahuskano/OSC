package co.ahuskano.something.controllers;

import android.app.Activity;
import android.util.Log;

import co.ahuskano.something.api.OSCApi;
import co.ahuskano.something.api.SpacesResponse;
import co.ahuskano.something.util.APIUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ahuskano on 11.10.2015..
 */
public class TopSpacesController extends OSCController {

    private Callback<SpacesResponse> callbackSpaces = new Callback<SpacesResponse>() {
        @Override
        public void success(SpacesResponse responseSpaces, Response response) {
            dismissDialog();
            Log.d("test", "success");
            if (getOnDataReadListener() != null) {
                Log.d("test", "success if");

                getOnDataReadListener().onDataReceive(responseSpaces);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            dismissDialog();
            if (getOnDataErrorListener() != null)
                getOnDataErrorListener().onDataErrorReceive(error);
        }
    };

    public TopSpacesController(Activity activity) {
        super(activity);
    }

    public void getSpaces(String lng, String lat){
        showDialog();
        APIUtils.getRestAdapter(OSCApi.API_LOCATION).create(OSCApi.class).getTopSpaces(lat,lng, callbackSpaces);
    }

}
