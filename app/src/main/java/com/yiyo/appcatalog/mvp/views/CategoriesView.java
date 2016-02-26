package com.yiyo.appcatalog.mvp.views;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiyo on 24/02/16.
 */
public interface CategoriesView extends View {

    void fetchCategories(List<String> categories);
}
