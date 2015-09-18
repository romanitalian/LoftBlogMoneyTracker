package com.loftschool.loftmoneytracker.rest;

import com.loftschool.loftmoneytracker.rest.api.RegisterUserAPI;

import retrofit.RestAdapter;

/**
 * Created by Constantine on 16.09.2015.
 */
public class RestClient {

    private static final String BASE_URL = "http://62.109.17.114";

    private RegisterUserAPI registerUserAPI;

    public RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .build();

        registerUserAPI = restAdapter.create(RegisterUserAPI.class);
    }

    public RegisterUserAPI getRegisterUserAPI() {
        return registerUserAPI;
    }
}
