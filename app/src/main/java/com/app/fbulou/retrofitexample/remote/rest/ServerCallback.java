package com.app.fbulou.retrofitexample.remote.rest;

import retrofit2.Call;
import retrofit2.Response;

public interface ServerCallback {

    <T> void onAPISuccess(String apiTag, Call<T> call, Response<T> response);

    <T> void onAPIResponse(String apiTag, Call<T> call, Response<T> response, int statusCode);

    <T> void onAPIFailure(String apiTag, Call<T> call, Throwable t);
}
