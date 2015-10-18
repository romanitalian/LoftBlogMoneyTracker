package com.loftschool.loftmoneytracker.rest.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Constantine on 16.09.2015.
 */
public class UserRegisterModel {

    @Expose
    private String status;
    @Expose
    private Integer id;

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
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
