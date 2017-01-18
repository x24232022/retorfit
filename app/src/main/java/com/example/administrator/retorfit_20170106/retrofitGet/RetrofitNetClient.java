package com.example.administrator.retorfit_20170106.retrofitGet;

import com.example.administrator.retorfit_20170106.okHttpPost.User;
import com.example.administrator.retorfit_20170106.okHttpPost.UserResult;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public class RetrofitNetClient  {
    private static RetrofitNetClient mRetrofitNetClient;

    public final RetrofitApi mRetrofitApi;

    private RetrofitNetClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //创建爹的拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)//将爹的拦截器给儿子
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //在接口中传入构建方法需要的参数,create自动构建方法
        mRetrofitApi = retrofit.create(RetrofitApi.class);

    }

    public static synchronized RetrofitNetClient getInstances() {
        if (mRetrofitNetClient == null) {
            mRetrofitNetClient = new RetrofitNetClient();
        }
        return mRetrofitNetClient;
    }

}
