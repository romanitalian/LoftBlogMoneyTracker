package com.loftschool.loftmoneytracker.rest.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Constantine on 18.09.2015.
 */
public class AddCategoryModel {

    @Expose
    private String status;
    @Expose
    private Data data;

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
     * The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(Data data) {
        this.data = data;
    }

}
