package com.yiyo.appcatalog.domain;

import android.content.Context;

import com.yiyo.appcatalog.model.database.AppsCatalogDatasource;
import com.yiyo.appcatalog.model.entities.EntryApp;

import java.util.List;

/**
 * Created by sumset on 26/02/16.
 */
public class AppsCategoryUseCase {

    private AppsCatalogDatasource datasource;

    public AppsCategoryUseCase(Context context) {
        datasource = new AppsCatalogDatasource(context);
    }

    public List<EntryApp> getAppsByCategory(String category) {
        datasource.open();
        List<EntryApp> entryApps = datasource.listAppsByCategory(category);
        datasource.close();
        return entryApps;
    }
}
