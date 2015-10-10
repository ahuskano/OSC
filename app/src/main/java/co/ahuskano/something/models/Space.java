package co.ahuskano.something.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.ahuskano.something.util.recycleView.BasicModel;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class Space extends BaseModel{

    @Expose
    private long id;

    @Expose
    private String name;

    @Expose
    private String address;

    @Expose
    private String contact;

    @Expose
    private String description;

    @Expose
    private String lat;

    @Expose
    @SerializedName("long")
    private String longitude;

    @Expose
    private int approved;

    @Expose
    private String updatedAt;

    @Expose
    private String createdAt;

    @Expose
    private Image image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
