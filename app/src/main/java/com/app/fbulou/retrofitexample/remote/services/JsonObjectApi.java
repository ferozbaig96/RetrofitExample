package com.app.fbulou.retrofitexample.remote.services;

import com.app.fbulou.retrofitexample.models.jo.get.SamplePojo;
import com.app.fbulou.retrofitexample.models.jo.post.SamplePostResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonObjectApi {

    @GET("users")
    Call<SamplePojo> getResponse(@Query("page") int page);

    // Use JsonObject from GSON
    @POST("users")
    Call<SamplePostResponse> getPostResponse(@Body JsonObject body);
}