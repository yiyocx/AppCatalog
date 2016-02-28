package com.yiyo.appcatalog.domain;

import android.content.Context;

import com.yiyo.appcatalog.model.database.AppsCatalogDatasource;
import com.yiyo.appcatalog.model.entities.EntryApp;

/**
 * Created by yiyo on 27/02/16.
 */
public class FindAppUseCase {

    private AppsCatalogDatasource datasource;

    public EntryApp execute(Long appId, Context context) {
        datasource = new AppsCatalogDatasource(context);
        datasource.open();
        EntryApp entryApp = datasource.findApp(appId);
        datasource.close();
        return entryApp;
    }
}
