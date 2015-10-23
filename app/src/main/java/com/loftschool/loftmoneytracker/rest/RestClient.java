package com.loftschool.loftmoneytracker.rest;

import com.loftschool.loftmoneytracker.rest.api.AddCategoryAPI;
import com.loftschool.loftmoneytracker.rest.api.CheckGoogleTokenAPI;
import com.loftschool.loftmoneytracker.rest.api.LoginUserAPI;
import com.loftschool.loftmoneytracker.rest.api.RegisterUserAPI;

import retrofit.RestAdapter;

/**
 * Created by Constantine on 16.09.2015.
 */
public class RestClient {

    private static final String BASE_URL = "http://lmt.loftblog.tmweb.ru/";

    private RegisterUserAPI registerUserAPI;
    private LoginUserAPI loginUserAPI;
    private AddCategoryAPI addCategoryAPI;
    private CheckGoogleTokenAPI checkGoogleTokenAPI;

    public RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .build();

        registerUserAPI = restAdapter.create(RegisterUserAPI.class);
        loginUserAPI = restAdapter.create(LoginUserAPI.class);
        addCategoryAPI = restAdapter.create(AddCategoryAPI.class);
        checkGoogleTokenAPI = restAdapter.create(CheckGoogleTokenAPI.class);

    }

    public RegisterUserAPI getRegisterUserAPI() {
        return registerUserAPI;
    }

    public LoginUserAPI getLoginUserAPI() {
        return loginUserAPI;
    }

    public AddCategoryAPI getAddCategoryAPI() {
        return addCategoryAPI;
    }

    public CheckGoogleTokenAPI getCheckGoogleTokenAPI() {
        return checkGoogleTokenAPI;
    }
}
