package com.example.administrator.retorfit_20170106.retrofitGet;

import com.example.administrator.retorfit_20170106.okHttpPost.MultUser;
import com.example.administrator.retorfit_20170106.okHttpPost.User;
import com.example.administrator.retorfit_20170106.okHttpPost.UserResult;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public interface RetrofitApi {
    //GET请求
    @GET("https://api.github.com/{search}/repositories")
    Call<ResponseBody> getData(@Path("search")String search, @Query("q")String q,@Query("page")int page);

    //post请求
    //泛型中的类型<>解析出的java实体类决定,当已创建实体类时,添加相应的实体类,未知类型时使用ResponseBody;
    @POST("http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=register")
    Call<UserResult> register(@Body User user);

    //from请求
    @POST("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=register")
    Call<ResponseBody> getFrom(@Field("username")String name,@Field("password")String password);
    //from_map
    @POST("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=register")
    Call<ResponseBody> getFromMap(@FieldMap Map<String ,String>map);

    //多部分请求
    @Multipart
    @POST("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=update")
    Call<ResponseBody> getMult(@Part("user")MultUser multUser);
}
