package com.loftschool.loftmoneytracker.rest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Constantine on 18.09.2015.
 */
public class GoogleTokenStatusModel {

    @Expose
    private String status;
    @Expose
    private String name;
    @Expose
    private String picture;

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
}
