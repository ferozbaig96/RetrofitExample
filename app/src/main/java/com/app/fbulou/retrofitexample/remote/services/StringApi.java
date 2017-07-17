package com.app.fbulou.retrofitexample.remote.services;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface StringApi {

    // Use 'ResponseBody' for String requests

    /*
        @Headers("Cache-Control: no-cache") on the method should work.
         If you want to do it dynamically you can add a
         @Header("Cache-Control") String cacheControl
         parameter and pass null or "no-cache"
    */

    @Headers("Cache-Control: no-cache")
    @GET
    Call<ResponseBody> getBookExtract(@Url String url);

    @GET("users")
    Call<ResponseBody> getResponse(@QueryMap Map<String, String> options);
}