package com.yiyo.appcatalog.mvp.presenters;

import com.yiyo.appcatalog.mvp.views.AppsCategoryView;

/**
 * Created by yiyo on 25/02/16.
 */
public class AppsCategoryPresenter {

    private AppsCategoryView view;

    public AppsCategoryPresenter(AppsCategoryView view) {
        this.view = view;
    }

    public void onResume() {
        view.showProgress();
    }
}
