package com.yiyo.appcatalog.mvp.presenters;

import com.yiyo.appcatalog.domain.AppsCategoryUseCase;
import com.yiyo.appcatalog.mvp.views.AppsCategoryView;

/**
 * Created by yiyo on 25/02/16.
 */
public class AppsCategoryPresenter {

    private AppsCategoryView view;
    private AppsCategoryUseCase appsCategoryUseCase;

    public AppsCategoryPresenter(AppsCategoryView view) {
        this.view = view;
        appsCategoryUseCase = new AppsCategoryUseCase(view.getContext());
    }

    public void onResume(String category) {
        view.showProgress();
        view.showApps(appsCategoryUseCase.getAppsByCategory(category));
        view.hideProgress();
    }
}
