package co.ahuskano.something.models;

import com.google.gson.annotations.Expose;

/**
 * Created by ahuskano on 11.10.2015..
 */
public class Review  extends BaseModel{

    @Expose
    private int id;
    @Expose
    private int space_id;
    @Expose
    private int wifi_score;
    @Expose
    private int price_score;
    @Expose
    private int noise_price;
    @Expose
    private String description;

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

    public int getWifi_score() {
        return wifi_score;
    }

    public void setWifi_score(int wifi_score) {
        this.wifi_score = wifi_score;
    }

    public int getPrice_score() {
        return price_score;
    }

    public void setPrice_score(int price_score) {
        this.price_score = price_score;
    }

    public int getNoise_price() {
        return noise_price;
    }

    public void setNoise_price(int noise_price) {
        this.noise_price = noise_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
