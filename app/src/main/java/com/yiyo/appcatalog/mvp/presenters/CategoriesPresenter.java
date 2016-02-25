package com.yiyo.appcatalog.mvp.presenters;

import com.yiyo.appcatalog.domain.CategoriesUseCase;
import com.yiyo.appcatalog.model.rest.models.Feed_;
import com.yiyo.appcatalog.mvp.views.CategoriesView;

import java.util.List;

/**
 * Created by yiyo on 24/02/16.
 */
public class CategoriesPresenter {

    private CategoriesUseCase categoriesUseCase;
    private CategoriesView categoriesView;

    public CategoriesPresenter(CategoriesView view) {
        categoriesView = view;
        categoriesUseCase = new CategoriesUseCase(view.getContext());
    }

    public void saveAndLoadCategories(Feed_ feed) {
        categoriesUseCase.openDatabase();
        categoriesUseCase.save(feed);
        List<String> categories = categoriesUseCase.getAllCategories();
        categoriesUseCase.closeDatabase();
        categoriesView.fetchCategories(categories);
    }

    public String getTitle() {
        categoriesUseCase.openDatabase();
        String title = categoriesUseCase.getTitle();
        categoriesUseCase.closeDatabase();
        return title;
    }
}
