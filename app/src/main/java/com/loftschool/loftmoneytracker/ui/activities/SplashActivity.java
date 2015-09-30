package com.loftschool.loftmoneytracker.ui.activities;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import com.loftschool.loftmoneytracker.R;
import com.loftschool.loftmoneytracker.TrackerApplication;
import com.loftschool.loftmoneytracker.rest.RestClient;
import com.loftschool.loftmoneytracker.rest.models.GoogleTokenStatusModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;

import java.io.IOException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Andrew on 29.09.2015.
 */
@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1500;
    private String gToken;
    private RestClient restClient;

    private final static String G_PLUS_SCOPE =
            "oauth2:https://www.googleapis.com/auth/plus.me";
    private final static String USERINFO_SCOPE =
            "https://www.googleapis.com/auth/userinfo.profile";
    private final static String EMAIL_SCOPE =
            "https://www.googleapis.com/auth/userinfo.email";

    public final static String SCOPES = G_PLUS_SCOPE + " " + USERINFO_SCOPE + " " + EMAIL_SCOPE;

    @AfterViews
    void main() {
        restClient = new RestClient();
        gToken = TrackerApplication.getGoogleToken(this);
        if (gToken.equalsIgnoreCase("2")) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent regIntent = new Intent(SplashActivity.this, LoginActivity_.class);
                    startActivity(regIntent);
                    finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        } else {
            checkTokenValid();
        }
    }

    @Background
    void checkTokenValid() {

        restClient.getCheckGoogleTokenAPI().tokenStatus(gToken, new Callback<GoogleTokenStatusModel>() {
            @Override
            public void success(GoogleTokenStatusModel googleTokenStatusModel, Response response) {
                Log.e("TAG", "STATUS" + " " + googleTokenStatusModel.getStatus());
                if (googleTokenStatusModel.getStatus().equalsIgnoreCase("error")) {
                    doubleTokenExc();
                } else {
                    Intent regIntent = new Intent(SplashActivity.this, MainActivity_.class);
                    startActivity(regIntent);
                    finish();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                doubleTokenExc();
            }
        });
    }

    private void doubleTokenExc() {
        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"},
                false, null, null, null, null);
        startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123 && resultCode == RESULT_OK) {
            getToken(data);
        }
    }

    @Background
    void getToken(Intent data) {
        final String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String token = null;
        try {
            token = GoogleAuthUtil.getToken(SplashActivity.this, accountName,
                    SplashActivity.SCOPES);
        } catch (UserRecoverableAuthException userAuthEx) {
            startActivityForResult(userAuthEx.getIntent(), 123);
        } catch (IOException ioEx) {
            Log.d("TAG", "IOException");
        } catch (GoogleAuthException fatalAuthEx) {
            Log.d("TAG", "Fatal Authorization Exception" + fatalAuthEx.getLocalizedMessage());
        }
        TrackerApplication.setGoogleToken(this, token);
        Log.d("TAG", "TOKEN" + " " + TrackerApplication.getGoogleToken(this));
    }
}

