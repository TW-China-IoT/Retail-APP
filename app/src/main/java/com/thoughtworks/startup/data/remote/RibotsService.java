package com.thoughtworks.startup.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.startup.data.model.Ribot;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface RibotsService {

    String ENDPOINT = "https://api.ribot.io/";

    @GET("ribots")
    Observable<List<Ribot>> getRibots();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static RibotsService newRibotsService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RibotsService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RibotsService.class);
        }
    }
}
