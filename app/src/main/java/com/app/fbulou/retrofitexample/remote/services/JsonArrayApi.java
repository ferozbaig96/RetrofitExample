package com.app.fbulou.retrofitexample.remote.services;

import com.app.fbulou.retrofitexample.models.ja.SampleArrayResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface JsonArrayApi {

    @GET
    Call<List<SampleArrayResponse>> getArrayResponse(@Url String url);
}