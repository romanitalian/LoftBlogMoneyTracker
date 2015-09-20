package com.loftschool.loftmoneytracker.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.loftschool.loftmoneytracker.R;
import com.loftschool.loftmoneytracker.TrackerApplication;
import com.loftschool.loftmoneytracker.rest.RestService;
import com.loftschool.loftmoneytracker.rest.models.AddCategoryModel;
import com.loftschool.loftmoneytracker.rest.models.UserLoginModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Constantine on 18.09.2015.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();

    @ViewById
    EditText etLogin, etPassword;

    @ViewById
    Button btnLogin;

    @AfterViews
    void ready() {
        //login();
    }

    @Click(R.id.btnLogin)
    void btnLogin() {
        login();
    }

    @Background
    void login() {
        RestService restService = new RestService();
        UserLoginModel login = restService.login(etLogin.getText().toString(),
                etPassword.getText().toString());

        TrackerApplication.setToken(this, login.getAuthToken());

        String authToken = TrackerApplication.getToken(this);

        Log.e(LOG_TAG, " " + authToken);
        AddCategoryModel category = restService.addCategory("Rest", authToken);
        Log.e(LOG_TAG, "Category name: " + category.getData().getTitle()
                       + ", category id: " + category.getData().getId());
    }
}
