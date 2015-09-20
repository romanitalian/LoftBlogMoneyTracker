package com.loftschool.loftmoneytracker.rest;

import com.loftschool.loftmoneytracker.rest.models.AddCategoryModel;
import com.loftschool.loftmoneytracker.rest.models.UserLoginModel;
import com.loftschool.loftmoneytracker.rest.models.UserRegisterModel;


/**
 * Created by Constantine on 16.09.2015.
 */
public class RestService {

    private static final String REGISTER_FLAG = "1";

    private RestClient restClient;

    public RestService() {
        restClient = new RestClient();
    }

    public UserRegisterModel register(String login, String password) {
        return restClient.getRegisterUserAPI().registerUser(login, password, REGISTER_FLAG);
    }

    public UserLoginModel login(String login, String password) {
        return restClient.getLoginUserAPI().loginUser(login, password);
    }

    public AddCategoryModel addCategory(String title, String token) {
        return restClient.getAddCategoryAPI().addCategory(title, token);
    }
}
