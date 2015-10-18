package com.loftschool.loftmoneytracker.rest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Constantine on 18.09.2015.
 */
public class UserLoginModel {

    @Expose
    private String status;
    @Expose
    private String id;
    @SerializedName("auth_token")
    @Expose
    private String authToken;

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     *
     * @param authToken
     * The auth_token
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
