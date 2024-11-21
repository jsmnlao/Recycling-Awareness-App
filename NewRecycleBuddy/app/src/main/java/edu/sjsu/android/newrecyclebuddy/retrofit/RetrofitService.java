package edu.sjsu.android.newrecyclebuddy.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.OffsetDateTime;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService{
    private Retrofit retrofit;
    public RetrofitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit(){
        // Adapter for OffsetDateTime type -- used to properly take in current local time for registration date
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.250.197.210:8080") // using my current IP, will need to change to your own or whatever we figure out with mongodb?
                .addConverterFactory(GsonConverterFactory.create(gson)) // new Gson() if not using adapter above
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
