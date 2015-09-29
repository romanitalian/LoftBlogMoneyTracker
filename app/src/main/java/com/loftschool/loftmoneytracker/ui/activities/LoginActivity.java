package com.loftschool.loftmoneytracker.ui.activities;

import android.accounts.AccountManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import com.loftschool.loftmoneytracker.R;
import com.loftschool.loftmoneytracker.TrackerApplication;
import com.loftschool.loftmoneytracker.rest.RestService;
import com.loftschool.loftmoneytracker.rest.models.AddCategoryModel;
import com.loftschool.loftmoneytracker.rest.models.UserLoginModel;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

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

    @Click(R.id.btnLogin)
    void btnLogin() {
        login();
    }

    @Click(R.id.sign_in_button)
    void btnGplusLogin() {
        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"},
                false, null, null, null, null);
        startActivityForResult(intent, 123);
    }

    @Background
    void login() {
        RestService restService = new RestService();
        UserLoginModel login = restService.login(etLogin.getText().toString(),
                etPassword.getText().toString());

        TrackerApplication.setToken(this, login.getAuthToken());

        String authToken = TrackerApplication.getToken(this);
        String googleToken = TrackerApplication.getGoogleToken(this);

        Log.e(LOG_TAG, " " + authToken + " " + " GToken:" + " " + googleToken);

        AddCategoryModel category = restService.addCategory("Rest", googleToken, authToken);
        Log.e(LOG_TAG, "Category name: " + category.getData().getTitle()
                + ", category id: " + category.getData().getId());

        if (login.getStatus().equalsIgnoreCase("success")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoginActivity.this, MainActivity_.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Background
    void getToken(Intent data) {
        final String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String token = null;
        try {
            token = GoogleAuthUtil.getToken(LoginActivity.this, accountName,
                    SplashActivity.SCOPES);
        } catch (UserRecoverableAuthException userAuthEx) {
            startActivityForResult(userAuthEx.getIntent(), 123);
        } catch (IOException ioEx) {
            Log.d(LOG_TAG, "IOException");
        } catch (GoogleAuthException fatalAuthEx) {
            Log.d(LOG_TAG, "Fatal Authorization Exception" + fatalAuthEx.getLocalizedMessage());
        }
        TrackerApplication.setGoogleToken(this, token);
        Log.d(LOG_TAG, "TOKEN" + " " + TrackerApplication.getGoogleToken(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123 && resultCode == RESULT_OK) {
            getToken(data);
        }
    }
}
