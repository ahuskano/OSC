package co.ahuskano.something.api;


import com.google.gson.annotations.Expose;

import co.ahuskano.something.models.Space;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class SpaceResponse extends BaseResponse {

    @Expose
    private Space data;

    public Space getData() {
        return data;
    }

    public void setData(Space data) {
        this.data = data;
    }
}
