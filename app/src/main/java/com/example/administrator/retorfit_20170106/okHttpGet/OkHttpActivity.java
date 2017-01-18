package com.example.administrator.retorfit_20170106.okHttpGet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.retorfit_20170106.R;
import com.example.administrator.retorfit_20170106.okHttpPost.MultUser;
import com.example.administrator.retorfit_20170106.okHttpPost.UICallback;
import com.example.administrator.retorfit_20170106.okHttpPost.User;
import com.example.administrator.retorfit_20170106.okHttpPost.UserResult;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener {

    private Call newCall;
    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        Button btnGet= (Button) findViewById(R.id.btn_okhttp_get);
        Button okBtnFet=(Button) findViewById(R.id.btn_get_ok);
        Button btnPot=(Button) findViewById(R.id.btn_get_post);
        Button btn_from=(Button) findViewById(R.id.btn_from);
        Button btn_mult=(Button) findViewById(R.id.btn_mult);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_okhttp_get:
                //创建拦截器并设置拦截级别
                HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                mOkHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)//将拦截器配置给okhttp
                        .build();
                Request request=new Request.Builder()
                        .get()//Get请求
                        .url("https://www.baidu.com")
                        .build();
                newCall = mOkHttpClient.newCall(request);
                newCall.enqueue(new Callback() {
                    //onFailure请求失败,后台的线程
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("TAG", "请求失败");
                    }
                    //onResponse请求成功,后台线程,不能更新UI
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i("TAG", "请求成功"+response.body().string());
                    }
                });

                if (newCall!=null) {
                    newCall.cancel();
                }
                break;

            case R.id.btn_get_ok:
                Call call = OkHttpNetClient.getInstances().getData(OkHttpNetClient.OKHTTP_GET);
                call.enqueue(new UICallback() {
                    @Override
                    public void onUIResponse(Call call, Response response) {
                        response.code();
                        response.body();
                        try {
                            Toast.makeText(OkHttpActivity.this, "请求成功" + response.code() + ",请求信息:" + response.body().string(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onUIFailure(Call call, IOException e) {
                        Toast.makeText(OkHttpActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            case R.id.btn_get_post:
                User user=new User("123456","asdas");
                /*OkHttpNetClient.getInstances().register(OkHttpNetClient.OKHTTP_POST,user).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            String json = response.body().string();
                            UserResult userResult=new Gson().fromJson(json,UserResult.class);
                            if(userResult!=null){
                                Log.i("TAG","请求的结果"+userResult.getErrmsg());
                            }
                        }


                    }
                });*/
                OkHttpNetClient.getInstances().register(OkHttpNetClient.OKHTTP_POST,user).enqueue(new UICallback() {
                    @Override
                    public void onUIResponse(Call call, Response response) {
                        if(response.isSuccessful()){
                            String json=null;
                            try {
                                json=response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            UserResult userResult=new Gson().fromJson(json,UserResult.class);
                            if(userResult!=null){
                                Toast.makeText(OkHttpActivity.this,  "请求结果" + userResult.getErrmsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onUIFailure(Call call, IOException e) {
                        Toast.makeText(OkHttpActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_from:
                OkHttpNetClient.getInstances().postFrom(OkHttpNetClient.OKHTTP_FORM,"name","123456").enqueue(new UICallback() {
                    @Override
                    public void onUIResponse(Call call, Response response) {
                        Toast.makeText(OkHttpActivity.this, "请求成功" + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUIFailure(Call call, IOException e) {
                        Toast.makeText(OkHttpActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.btn_mult:
                MultUser multUser=new MultUser("yt59856b15cf394e7b84a7d48447d16098",
                        "xc62",
                        "555",
                        "123456",
                        "0F8EC12223174657B2E842076D54C361");
                OkHttpNetClient.getInstances().postMult(OkHttpNetClient.OKHTTP_MULT,multUser).enqueue(new UICallback() {
                    @Override
                    public void onUIResponse(Call call, Response response) {
                        Toast.makeText(OkHttpActivity.this, "成功" + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUIFailure(Call call, IOException e) {
                        Toast.makeText(OkHttpActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

        }
    }
}
