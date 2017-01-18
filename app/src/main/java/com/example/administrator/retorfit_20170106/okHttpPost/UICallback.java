package com.example.administrator.retorfit_20170106.okHttpPost;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public abstract class UICallback implements Callback {
    //调用主线程的Handler,用主线程的Handler的post方法将数据传递到主线程,更新UI;
    private Handler mHandler=new Handler(Looper.getMainLooper());
    //后台线程的方法
    @Override
    public void onFailure(final Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onUIFailure(call,e);
            }
        });
    }

    //后台线程的方法
    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onUIResponse(call,response);
            }
        });
    }
    //主线程的方法
    public abstract void onUIResponse(Call call, Response response);

    //主线程的方法
    public abstract void onUIFailure(Call call, IOException e);

}
