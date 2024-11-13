package edu.sjsu.android.newrecyclebuddy.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService{
    private Retrofit retrofit;
    public RetrofitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.250.54.104:8080") // using my current IP, will need to change to your own or whatever we figure out with mongodb?
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
