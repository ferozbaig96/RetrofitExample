package com.app.fbulou.retrofitexample.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.app.fbulou.retrofitexample.R;
import com.app.fbulou.retrofitexample.remote.rest.RetrofitClient;
import com.app.fbulou.retrofitexample.remote.rest.RetrofitManager;
import com.app.fbulou.retrofitexample.remote.rest.ServerCallback;
import com.app.fbulou.retrofitexample.remote.services.JsonArrayApi;
import com.app.fbulou.retrofitexample.remote.services.JsonObjectApi;
import com.app.fbulou.retrofitexample.remote.services.StringApi;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServerCallback {

    /*
        https://guides.codepath.com/android/Consuming-APIs-with-Retrofit
    */

    private String TAG = getClass().getSimpleName();
    @SuppressWarnings("deprecation")
    private ProgressDialog progressDialog;

    private StringApi stringApi;
    private JsonObjectApi jsonObjectApi;
    private JsonArrayApi jsonArrayApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initProgressDialog();
        init();
    }

    @SuppressWarnings("deprecation")
    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
    }

    private void init() {
        Button btnString = (Button) findViewById(R.id.btnString);
        Button btnJO = (Button) findViewById(R.id.btnJO);
        Button btnJA = (Button) findViewById(R.id.btnJA);
        btnString.setOnClickListener(this);
        btnJO.setOnClickListener(this);
        btnJA.setOnClickListener(this);
        stringApi = RetrofitClient.getClient().create(StringApi.class);
        jsonObjectApi = RetrofitClient.getClient().create(JsonObjectApi.class);
        jsonArrayApi = RetrofitClient.getClient().create(JsonArrayApi.class);
    }

    @Override
    public void onClick(View view) {
        progressDialog.show();

        switch (view.getId()) {
            case R.id.btnString:
                stringRequests();
                break;
            case R.id.btnJO:
                joRequests();
                break;
            case R.id.btnJA:
                jaRequests();
                break;
        }
    }

    @SuppressWarnings("unused")
    void normalWay() {
   /* void stringRequests() {
        // String response (GET)
        stringApi.getBookExtract("http://www.newthinktank.com/wordpress/lotr.txt").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                hideProgressDialog();
                Log.e(TAG, "@url: " + call.request().url().url().toString());
                if (response.isSuccessful()) {
                    try {
                        Log.e(TAG, "@body: " + response.body().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "@statusCode: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                hideProgressDialog();
                t.printStackTrace();
                Log.e(TAG, "error loading from API");
            }
        });

        stringApi.getResponse("foo", "bar").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                hideProgressDialog();
                Log.e(TAG, "@url: " + call.request().url().url().toString());
                if (response.isSuccessful()) {
                    try {
                        Log.e(TAG, "@body: " + response.body().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "@statusCode: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                hideProgressDialog();
                t.printStackTrace();
                Log.e(TAG, "error loading from API");
            }
        });
    }*/
    }

    void stringRequests() {
        // String response (GET)
        RetrofitManager.requestCall("s GET", stringApi.getBookExtract("http://www.newthinktank.com/wordpress/lotr.txt"), this);

        // jsonObject response. GET with params
        Map<String, String> map = new HashMap<>();
        map.put("name", "foo");
        map.put("job", "bar");
        RetrofitManager.requestCall("jo GET params", stringApi.getResponse(map), this);
    }

    void joRequests() {
        //jsonObject response (GET)
        RetrofitManager.requestCall("JOR - jo GET", jsonObjectApi.getResponse(2), this);

        //jsonObject response. POST with params
        JsonObject jsonObjectParams = new JsonObject();
        jsonObjectParams.addProperty("name", "foo");
        jsonObjectParams.addProperty("job", "bar");
        RetrofitManager.requestCall("JOR - jo POST params", jsonObjectApi.getPostResponse(jsonObjectParams), this);
    }

    void jaRequests() {
        //jsonArray response (GET)
        RetrofitManager.requestCall("JAR - ja GET", jsonArrayApi.getArrayResponse("http://jsonplaceholder.typicode.com/posts"), this);
    }

    private void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public <T> void onAPISuccess(String apiTag, Call<T> call, Response<T> response) {
        hideProgressDialog();
        Log.e(TAG, "@url: " + call.request().method() + "\t" + call.request().url().url().toString());

        //noinspection StatementWithEmptyBody
        switch (apiTag) {
            case "s GET":
            case "jo GET params":
                try {
                    //noinspection ConstantConditions
                    Log.e(TAG, "@body: " + ((ResponseBody) response.body()).string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

        // TODO explicitly cast response to model Object
        //noinspection ConstantConditions
        Log.e(TAG, "@body: " + response.body().toString());
    }

    @Override
    public <T> void onAPIResponse(String apiTag, Call<T> call, Response<T> response, int statusCode) {
        hideProgressDialog();

        //noinspection StatementWithEmptyBody
        switch (apiTag) {
            // TODO cases as per apiTag
        }

        Log.e(TAG, "@statusCode: " + statusCode);
    }

    @Override
    public <T> void onAPIFailure(String apiTag, Call<T> call, Throwable t) {
        hideProgressDialog();
        //noinspection StatementWithEmptyBody
        switch (apiTag) {
            // TODO cases as per apiTag
        }

        Log.e(TAG, "error loading from API");
    }
}
