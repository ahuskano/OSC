package co.ahuskano.something.api;

import com.google.gson.annotations.Expose;

/**
 * Created by ahuskano on 11.10.2015..
 */
public class LogInRequest {

    @Expose
    private String password;

    @Expose
    private String username;

    @Expose
    private String gcm_token;

    public String getPassword() {
        return password;
    }

    public LogInRequest(String username, String password, String gcm) {
        this.password = password;
        this.username = username;
        this.gcm_token=gcm;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
