package com.loftschool.loftmoneytracker.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.loftschool.loftmoneytracker.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by Andrew on 29.09.2015.
 */
@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1500;

    @AfterViews
    void main() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent regIntent = new Intent(SplashActivity.this, LoginActivity_.class);
                startActivity(regIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
