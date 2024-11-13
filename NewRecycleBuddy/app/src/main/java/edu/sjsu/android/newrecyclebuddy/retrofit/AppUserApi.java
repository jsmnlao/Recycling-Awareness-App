package edu.sjsu.android.newrecyclebuddy.retrofit;

import java.util.List;

import edu.sjsu.android.newrecyclebuddy.springmodels.AppUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppUserApi {
    @GET("/appuser/get-all")
    Call<List<AppUser>> getAllAppUsers();

    @POST("/appuser/save")
    Call<AppUser> save(@Body AppUser appUser);
}
