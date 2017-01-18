package com.example.administrator.retorfit_20170106.okHttpGet;

import com.example.administrator.retorfit_20170106.okHttpPost.MultUser;
import com.example.administrator.retorfit_20170106.okHttpPost.User;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/1/6 0006.
 */

public interface OkHttpApi {
    Call getData(String url);
    Call register(String url, User user);
    Call postFrom(String url,String name,String password);
    Call postMult(String url, MultUser multUser);
}
