package com.loftschool.loftmoneytracker.rest.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Andrew on 30.09.2015.
 */
public class GoogleTokenStatusModel {

    @Expose
    private String status;

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
}

