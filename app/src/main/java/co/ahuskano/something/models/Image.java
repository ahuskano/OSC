package co.ahuskano.something.models;

import com.google.gson.annotations.Expose;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class Image{

    @Expose
    private int id;

    @Expose
    private int space_id;

    @Expose
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpace_id() {
        return space_id;
    }

    public void setSpace_id(int space_id) {
        this.space_id = space_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
