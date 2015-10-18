package com.loftschool.loftmoneytracker.rest.api;

import com.loftschool.loftmoneytracker.rest.models.AddCategoryModel;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Constantine on 18.09.2015.
 */
public interface AddCategoryAPI {

    @GET("/categories/add")
    AddCategoryModel addCategory(@Query("title") String title, @Query("google_token") String gToken,
                                 @Query("auth_token") String token);

}
