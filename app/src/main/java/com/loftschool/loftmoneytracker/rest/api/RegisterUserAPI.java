package com.loftschool.loftmoneytracker.rest.api;

import com.loftschool.loftmoneytracker.rest.models.UserRegisterModel;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Constantine on 16.09.2015.
 */
public interface RegisterUserAPI {

    @GET("/auth")
    UserRegisterModel registerUser(@Query("login") String login,
                                   @Query("password") String password,
                                   @Query("register") String registrationFlag);

}
