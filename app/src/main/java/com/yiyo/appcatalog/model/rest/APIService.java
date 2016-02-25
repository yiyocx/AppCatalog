package com.yiyo.appcatalog.model.rest;

import com.yiyo.appcatalog.model.rest.models.Feed;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yiyo on 24/02/16.
 */
public interface APIService {

    @GET("topfreeapplications/limit=20/json")
    Call<Feed> getTopFreeiOSApps();
}
