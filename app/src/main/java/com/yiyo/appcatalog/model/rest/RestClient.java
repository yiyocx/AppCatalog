package com.yiyo.appcatalog.model.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yiyo on 24/02/16.
 */
public class RestClient {

    private static final String BASE_URL = "https://itunes.apple.com/us/rss/";
    private static APIService apiService;

    public static synchronized APIService getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(APIService.class);
        }
        return apiService;
    }
}
