package com.yiyo.appcatalog.mvp.presenters;

import android.content.Context;

import com.yiyo.appcatalog.domain.FindAppUseCase;
import com.yiyo.appcatalog.model.entities.EntryApp;
import com.yiyo.appcatalog.mvp.views.DetailAppView;

/**
 * Created by yiyo on 27/02/16.
 */
public class DetailAppPresenter {

    private DetailAppView view;

    public DetailAppPresenter(DetailAppView view) {
        this.view = view;
    }

    public void findApp(Long appId, Context context) {
        EntryApp entryApp = new FindAppUseCase().execute(appId, context);
        view.populateApp(entryApp);
    }
}
