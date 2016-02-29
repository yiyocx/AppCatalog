package com.yiyo.appcatalog.mvp.views;

import com.yiyo.appcatalog.model.entities.EntryApp;

/**
 * Created by yiyo on 28/02/16.
 */
public interface DetailAppView extends View {

    void populateApp(EntryApp entryApp);
}
