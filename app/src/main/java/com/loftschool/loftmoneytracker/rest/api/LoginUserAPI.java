package com.loftschool.loftmoneytracker.rest.api;

import com.loftschool.loftmoneytracker.rest.models.UserLoginModel;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Constantine on 18.09.2015.
 */
public interface LoginUserAPI {

    @GET("/auth")
    UserLoginModel loginUser(@Query("login") String login,
                             @Query("password") String password);

}
