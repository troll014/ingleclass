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
    Call<ResponseBody> getPoints(@Query("username") String username);

    @POST("save_points.php")
    @FormUrlEncoded
    Call<ResponseBody> savePoints(@Field("username") String username, @Field("points") int points);
}
