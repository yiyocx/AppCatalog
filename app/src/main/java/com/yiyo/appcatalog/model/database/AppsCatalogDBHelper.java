package com.yiyo.appcatalog.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yiyo.appcatalog.model.entities.EntryApp;
import com.yiyo.appcatalog.model.entities.Feed;

/**
 * Created by yiyo on 24/02/16.
 */
public class AppsCatalogDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "apps_catalog.db";
    private static final int DATABASE_VERSION = 1;

    public AppsCatalogDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Feed.DDL);
        db.execSQL(EntryApp.DDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(AppsCatalogDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + Feed.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EntryApp.TABLE_NAME);
        onCreate(db);
    }
}
