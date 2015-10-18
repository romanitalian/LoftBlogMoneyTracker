package com.loftschool.loftmoneytracker.rest.api;

import com.loftschool.loftmoneytracker.rest.models.GoogleTokenStatusModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Andrew on 30.09.2015.
 */
public interface CheckGoogleTokenAPI {
    @GET("/gcheck")
    void tokenStatus(@Query("google_token") String gToken,
                     Callback<GoogleTokenStatusModel> modelCallback);

    @GET("/gjson")
    GoogleTokenStatusModel googleJson(@Query("google_token") String gToken);
}
