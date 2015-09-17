package com.loftschool.loftmoneytracker.ui.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.loftschool.loftmoneytracker.R;
import com.loftschool.loftmoneytracker.rest.RestService;
import com.loftschool.loftmoneytracker.rest.models.UserRegisterModel;
import com.loftschool.loftmoneytracker.rest.statuses.RegisterUserResponseStatus;
import com.loftschool.loftmoneytracker.utils.NetworkStatusChecker;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

/**
 * Created by Constantine on 16.09.2015.
 */
@EActivity(R.layout.activity_registration)
public class RegistrationActivity extends AppCompatActivity {

    @ViewById
    LinearLayout rootLayout;

    @ViewById
    EditText etLogin, etPassword;

    @ViewById
    Button btnRegister;

    @StringRes(R.string.error_empty_login_field)
    String emptyLoginField;

    @StringRes(R.string.error_empty_password_field)
    String emptyPasswordField;

    @StringRes(R.string.error_login_not_available)
    String loginNotAvailable;

    @StringRes(R.string.error_network_access_disabled)
    String networkAccessDisabled;

    @StringRes(R.string.error_server_is_down)
    String serverIsDown;

    @Click(R.id.btnRegister)
    void btnRegister() {
        if (checkIsEmpty()) {
            register(etLogin.getText().toString(), etPassword.getText().toString());
        }
    }

    /** *
     *  Running a background thread for making registration request
     * **/
    @Background
    void register(String login, String password) {
        changeButtonColor(true);
        UserRegisterModel registerResponse;
        if (NetworkStatusChecker.isNetworkAvailable(this)) {
            registerResponse = RestService.register(login, password);
            checkResponseStatus(registerResponse.getStatus());
        } else {
            showErrorMessage(networkAccessDisabled);
        }
        changeButtonColor(false);
    }

    /** *
     *  Checking if EditTexts for login and password aren't empty,
     * if so - displaying an error.
     * **/
    private boolean checkIsEmpty() {
        if (etLogin.getText().length() == 0) {
            this.etLogin.requestFocus();
            etLogin.setError(emptyLoginField);
            return false;
        }
        if (etPassword.getText().length() == 0) {
            this.etPassword.requestFocus();
            etPassword.setError(emptyPasswordField);
            return false;
        }

        return true;
    }

    /** *
     * Checking if response status is OK
     * otherwise display an error.
     * **/
    private void checkResponseStatus(String responseStatus) {
        if (RegisterUserResponseStatus.STATUS_LOGIN_BUSY.equals(responseStatus)) {
            loginNotAvailable();
        } else if (RegisterUserResponseStatus.STATUS_SUCCESS.equals(responseStatus)){
            Intent intent = new Intent(this, MainActivity_.class);
            startActivity(intent);
        } else {
            showErrorMessage(serverIsDown);
        }
    }

    /** *
     * If another person uses this login
     * **/
    private void loginNotAvailable() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                etLogin.setError(loginNotAvailable);
            }
        });
    }

    /** *
     * Showing a Snackbar with an error when internet disabled or
     * server sends unknown status
     * **/
    private void showErrorMessage(String errorMessage) {
        Snackbar.make(rootLayout, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    /** *
     * Simple method for changing button color
     * **/
    private void changeButtonColor(final boolean flag) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (flag) {
                    btnRegister.setBackgroundColor(getResources().getColor(R.color.button_material_dark));
                } else {
                    btnRegister.setBackgroundColor(getResources().getColor(R.color.primary));
                }
            }
        });
    }
}
