package com.example.administrator.retorfit_20170106.okHttpGet;

import com.example.administrator.retorfit_20170106.okHttpPost.MultUser;
import com.example.administrator.retorfit_20170106.okHttpPost.User;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2017/1/6 0006.
 */

public class OkHttpNetClient implements OkHttpApi{
    public static final String OKHTTP_GET = "https://api.github.com/search/repositories?q=language:java&page=1";
    public static final String OKHTTP_POST="http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=register";
    public static final String OKHTTP_FORM="http://wx.feicuiedu.com:9094/yitao/UserWeb?method=register";
    public static final String OKHTTP_MULT = "http://wx.feicuiedu.com:9094/yitao/UserWeb?method=update";

    private static OkHttpNetClient mOkHttpNetClient;
    private final OkHttpClient mOkHttpClient;

    private OkHttpNetClient() {
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

    }
    public static synchronized OkHttpNetClient getInstances(){
        if (mOkHttpNetClient==null) {
            mOkHttpNetClient=new OkHttpNetClient();
        }
        return mOkHttpNetClient;
    }

    //构建GET的请求方法
    @Override
    public Call getData(String url){
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        return mOkHttpClient.newCall(request);
    }
    //构建Post请求
    @Override
    public Call register(String url,User user ){
        Gson gson=new Gson();
        String json = gson.toJson(user);
        RequestBody requestBody = RequestBody.create(null, json);
        Request request=new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();
        return mOkHttpClient.newCall(request);
    }
    //构建表单请求
    @Override
    public Call postFrom(String url, String name, String password) {
        RequestBody requestBody=new FormBody.Builder()
                .add("username",name)
                .add("password",password)
                .build();
        Request request=new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();
        return mOkHttpClient.newCall(request);
    }
    //构建多部分请求
    @Override
    public Call postMult(String url, MultUser multUser) {
        RequestBody requestBody=new MultipartBody.Builder()
                .addPart(RequestBody.create(null,new Gson().toJson(multUser)))
                .build();

        Request request=new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();
        return mOkHttpClient.newCall(request);
    }
}
