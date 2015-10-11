package co.ahuskano.something.api;

import com.google.gson.annotations.Expose;

import co.ahuskano.something.models.LogIn;

/**
 * Created by ahuskano on 11.10.2015..
 */
public class LogInResponse extends BaseResponse {

    @Expose
    private LogIn data;

    public LogIn getData() {
        return data;
    }

    public void setData(LogIn data) {
        this.data = data;
    }
}
