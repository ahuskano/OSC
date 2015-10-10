package co.ahuskano.something.controllers;

import android.app.Activity;
import android.util.Log;

import co.ahuskano.something.api.OSCApi;
import co.ahuskano.something.api.SpaceResponse;
import co.ahuskano.something.api.SpacesResponse;
import co.ahuskano.something.util.APIUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class SpaceController extends OSCController{

    private Callback<SpaceResponse> callbackSpace = new Callback<SpaceResponse>() {
        @Override
        public void success(SpaceResponse responseSpace, Response response) {
            Log.d("test", "success");
            dismissDialog();
            if (getOnDataReadListener() != null)
                getOnDataReadListener().onDataReceive(responseSpace);

        }

        @Override
        public void failure(RetrofitError error) {
            dismissDialog();
            Log.d("test", "failure "+ error.getMessage());
            if (getOnDataErrorListener() != null)
                getOnDataErrorListener().onDataErrorReceive(error);
        }
    };

    public SpaceController(Activity activity) {
        super(activity);
    }

    public void getSpaces(String id){
        APIUtils.getRestAdapter(OSCApi.API_LOCATION).create(OSCApi.class).getSpace(id, callbackSpace);
    }
}
