package com.loftschool.loftmoneytracker.rest;

import com.loftschool.loftmoneytracker.rest.models.UserRegisterModel;

/**
 * Created by Constantine on 16.09.2015.
 */
public class RestService {

    private static final String REGISTER_FLAG = "1";

    public static UserRegisterModel register(String login, String password) {
        RestClient restClient = new RestClient();

        return restClient.getRegisterUserAPI().registerUser(login, password, REGISTER_FLAG);
    }
}
