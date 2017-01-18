package com.example.administrator.retorfit_20170106.retrofitGet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.retorfit_20170106.R;
import com.example.administrator.retorfit_20170106.okHttpPost.MultUser;
import com.example.administrator.retorfit_20170106.okHttpPost.User;
import com.example.administrator.retorfit_20170106.okHttpPost.UserResult;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class RetorfitActivity extends AppCompatActivity {
    private Button mBtn_retorfit_get;
    private Button mBtn_retorfit_post;
    private Button mBtn_retorfit_from;
    private Button mBtn_retorfit_from_map;
    private Button mBtn_retorfit_mult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retorfit);
        mBtn_retorfit_get= (Button) findViewById(R.id.retorfit_get);
        mBtn_retorfit_post= (Button) findViewById(R.id.retorfit_post);
        mBtn_retorfit_from= (Button) findViewById(R.id.retorfit_from);
        mBtn_retorfit_from_map= (Button) findViewById(R.id.retorfit_from_map);
        mBtn_retorfit_mult= (Button) findViewById(R.id.retorfit_from_mult);
        retorfitGet();
        retorfitPost();
        retorfitFrom();
        retorfitFromMap();
        retorfitMult();
    }

    private void retorfitMult() {
        mBtn_retorfit_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultUser user=new MultUser("yt59856b15cf394e7b84a7d48447d16098",
                        "xc62",
                        "555",
                        "123456",
                        "0F8EC12223174657B2E842076D54C361");
                RetrofitNetClient.getInstances().mRetrofitApi.getMult(user).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(RetorfitActivity.this, "请求成功" + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(RetorfitActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void retorfitFromMap() {
        mBtn_retorfit_from_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> map=new HashMap<String, String>();
                map.put("username","zxcv");
                map.put("password","123456");
                RetrofitNetClient.getInstances().mRetrofitApi.getFromMap(map).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(RetorfitActivity.this, "请求成功" + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(RetorfitActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void retorfitFrom() {
        mBtn_retorfit_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitNetClient.getInstances().mRetrofitApi.getFrom("zxc","123").enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(RetorfitActivity.this, "请求成功" + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(RetorfitActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void retorfitPost() {
        mBtn_retorfit_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user=new User("username","123456");
                RetrofitNetClient.getInstances().mRetrofitApi.register(user).enqueue(new Callback<UserResult>() {
                    @Override
                    public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                        if(response.isSuccessful()){
                            UserResult userResult=response.body();
                            Toast.makeText(RetorfitActivity.this, "请求成功" + response, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResult> call, Throwable t) {
                        Toast.makeText(RetorfitActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void retorfitGet() {
        mBtn_retorfit_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call=RetrofitNetClient.getInstances().mRetrofitApi.getData("search","language:java",1);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(RetorfitActivity.this, "请求成功" + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(RetorfitActivity.this, "请求失败", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}
