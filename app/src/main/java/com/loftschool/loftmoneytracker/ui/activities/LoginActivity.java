package com.loftschool.loftmoneytracker.ui.activities;

import android.accounts.AccountManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;
import com.loftschool.loftmoneytracker.R;
import com.loftschool.loftmoneytracker.TrackerApplication;
import com.loftschool.loftmoneytracker.rest.RestService;
import com.loftschool.loftmoneytracker.rest.models.UserLoginModel;

import org.androidannotations.annotations.AfterViews;
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

    @AfterViews
    void ready() {
        //login();
    }

    @Click(R.id.btnLogin)
    void btnLogin() {
        login();
    }

    @Click(R.id.sign_in_button)
    void btnPlusLogin() {
        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                new String[]{"com.google"}, false, null, null, null, null);
        startActivityForResult(intent, 10);
    }

    @Background
    void login() {
        RestService restService = new RestService();
        UserLoginModel login = restService.login(etLogin.getText().toString(),
                etPassword.getText().toString());

        TrackerApplication.setToken(this, login.getAuthToken());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10 && resultCode == RESULT_OK) {
            getToken(data);
        }
    }

    @Background
    void getToken(Intent data) {
        final String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String googleToken = null;
        try {
            googleToken = GoogleAuthUtil.getToken(LoginActivity.this, accountName, SplashActivity.SCOPES);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GoogleAuthException e) {
            e.printStackTrace();
        }

        TrackerApplication.setGoogleToken(this, googleToken);
        String googleSharedToken = TrackerApplication.getGoogleToken(this);
        Log.d(LOG_TAG, "Google Token " + " " + googleSharedToken);

        if (!googleSharedToken.equalsIgnoreCase("2")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity_.class);
            startActivity(intent);
            finish();
        }
    }
}
