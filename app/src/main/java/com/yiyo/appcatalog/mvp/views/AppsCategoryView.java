package com.yiyo.appcatalog.mvp.views;

import com.yiyo.appcatalog.model.entities.EntryApp;

import java.util.List;

/**
 * Created by yiyo on 25/02/16.
 */
public interface AppsCategoryView extends View {

    void showProgress();

    void hideProgress();

    void showApps(List<EntryApp> apps);
}
