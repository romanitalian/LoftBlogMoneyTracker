package com.loftschool.loftmoneytracker.rest.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Constantine on 18.09.2015.
 */
public class Data {

    @Expose
    private String title;
    @Expose
    private Integer id;

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
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
