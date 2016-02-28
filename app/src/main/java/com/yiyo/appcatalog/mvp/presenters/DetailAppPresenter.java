package com.yiyo.appcatalog.mvp.presenters;

import android.content.Context;

import com.yiyo.appcatalog.domain.FindAppUseCase;
import com.yiyo.appcatalog.model.entities.EntryApp;

/**
 * Created by yiyo on 27/02/16.
 */
public class DetailAppPresenter {

    public EntryApp findApp(Long appId, Context context) {
        EntryApp entryApp = new FindAppUseCase().execute(appId, context);
        return entryApp;
    }
}
