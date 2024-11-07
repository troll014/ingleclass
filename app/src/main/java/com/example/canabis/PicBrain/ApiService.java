package com.example.canabis.PicBrain;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Field;

public interface ApiService {
    @GET("get_points.php")
    Call<ResponseBody> getPointsRaw(@Query("username") String username);

    @GET("get_points.php")
    Call<User> getPoints(@Query("username") String username);
}

