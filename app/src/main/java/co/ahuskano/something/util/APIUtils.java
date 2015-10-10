package co.ahuskano.something.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class APIUtils {

    public static final String RETROFIT_DEBUG = "ALEN";

    public static Gson createGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public static RestAdapter getRestAdapter(String endpoint) {
        return new RestAdapter.Builder().setConverter(new GsonConverter(createGson())).setEndpoint(endpoint).setLogLevel(RestAdapter.LogLevel.FULL).setLog(new RestAdapter.Log() {
            @Override
            public void log(String message) {
                Log.d(RETROFIT_DEBUG, message);
            }
        }).build();
    }
}
