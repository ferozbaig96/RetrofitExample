package com.app.fbulou.retrofitexample.remote.rest;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitManager {

    public static <T> void requestCall(@NonNull final String apiTag, Call<T> call, final ServerCallback serverCallback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful())
                    serverCallback.onAPISuccess(apiTag, call, response);
                else
                    serverCallback.onAPIResponse(apiTag, call, response, response.code());
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e("@failure-url", call.request().method() + "\t" + call.request().url().url().toString());
                t.printStackTrace();
                serverCallback.onAPIFailure(apiTag, call, t);
            }
        });
    }
}
