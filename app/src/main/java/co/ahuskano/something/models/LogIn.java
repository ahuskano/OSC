package co.ahuskano.something.models;

import com.google.gson.annotations.Expose;

/**
 * Created by ahuskano on 11.10.2015..
 */
public class LogIn extends BaseModel {

    @Expose
    private String api_key;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
}
