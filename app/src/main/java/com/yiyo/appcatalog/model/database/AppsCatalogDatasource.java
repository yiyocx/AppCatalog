package com.yiyo.appcatalog.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.yiyo.appcatalog.model.entities.EntryApp;
import com.yiyo.appcatalog.model.entities.Feed;
import com.yiyo.appcatalog.model.rest.models.Entry;
import com.yiyo.appcatalog.model.rest.models.Feed_;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiyo on 24/02/16.
 */
public class AppsCatalogDatasource {

    private SQLiteDatabase database;
    private AppsCatalogDBHelper dbHelper;

    public AppsCatalogDatasource(Context context) {
        dbHelper = new AppsCatalogDBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long saveFeed(Feed_ feed) {
        ContentValues values = new ContentValues();
        values.put(Feed.COLUMN_AUTHOR, feed.getAuthor().getName().getLabel());
        values.put(Feed.COLUMN_UPDATED, feed.getUpdated().getLabel());
        values.put(Feed.COLUMN_RIGHTS, feed.getRights().getLabel());
        values.put(Feed.COLUMN_TITLE, feed.getTitle().getLabel());
        values.put(Feed.COLUMN_ICON_URL, feed.getIcon().getLabel());
        values.put(Feed.COLUMN_FEED_ID, feed.getId().getLabel());
        long insertId = database.insert(Feed.TABLE_NAME, null, values);

        if (insertId > 0) {
            return  insertId;
        }
        throw new SQLException("Failed to insert answer ");
    }

    public void saveEntryApps(Long feedId, List<Entry> entries) {
        String sqlTemplate = "INSERT INTO " + EntryApp.TABLE_NAME +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        SQLiteStatement statement = database.compileStatement(sqlTemplate);
        database.beginTransaction();
        for (Entry entry : entries) {
            statement.clearBindings();
            statement.bindString(2, entry.getImName().getLabel());
            statement.bindString(3, entry.getSummary().getLabel());
            statement.bindString(4, entry.getRights().getLabel());
            statement.bindString(5, entry.getTitle().getLabel());
            statement.bindString(6, entry.getImArtist().getLabel());
            statement.bindString(7, entry.getCategory().getAttributes().getLabel());
            statement.bindString(8, entry.getImReleaseDate().getLabel());
            statement.bindString(9, entry.getImReleaseDate().getAttributes().getLabel());
            statement.bindLong(10, feedId);
            statement.execute();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void deleteAllFeed() {
        database.delete(EntryApp.TABLE_NAME, null, null);
        database.delete(Feed.TABLE_NAME, null, null);
    }

    public List<String> getAllCategories() {
        Cursor cursor = database.query(true, EntryApp.TABLE_NAME, new String[]{EntryApp.COLUMN_CATEGORY},
                null, null, null, null, null, null);
        List<String> categories = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            categories.add(cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_CATEGORY)));
            cursor.moveToNext();
        }
        cursor.close();
        return categories;
    }

    public String getTitleApp() {
        Cursor cursor = database.query(Feed.TABLE_NAME, null, null, null, null,
                null, null, null);
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex(Feed.COLUMN_TITLE));
        cursor.close();
        return title;
    }
}
