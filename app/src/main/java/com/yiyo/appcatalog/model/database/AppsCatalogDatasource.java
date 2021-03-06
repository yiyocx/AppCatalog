package com.yiyo.appcatalog.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.yiyo.appcatalog.R;
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
    private Context context;

    public AppsCatalogDatasource(Context context) {
        this.context = context;
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
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
            statement.bindString(11, entry.getImImage().get(0).getLabel());
            statement.bindString(12, entry.getImImage().get(1).getLabel());
            statement.bindString(13, entry.getImImage().get(2).getLabel());
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

        if (cursor.getCount() > 0) {
            String title = cursor.getString(cursor.getColumnIndex(Feed.COLUMN_TITLE));
            cursor.close();
            return title;
        }
        return context.getString(R.string.categories_title);
    }

    public List<EntryApp> listAppsByCategory(String category) {
        Cursor cursor;
        if (category.equals(context.getResources().getString(R.string.all_categories))) {
            cursor = database.query(EntryApp.TABLE_NAME, null, null, null, null, null, null);
        } else {
            cursor = database.query(EntryApp.TABLE_NAME, null, "category=?", new String[]{category},
                    null, null, null);
        }
        List<EntryApp> apps = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            EntryApp entryApp = cursorToEntryApp(cursor);
            apps.add(entryApp);
            cursor.moveToNext();
        }
        cursor.close();
        return apps;
    }

    private EntryApp cursorToEntryApp(Cursor cursor) {
        EntryApp entryApp = new EntryApp();
        entryApp.id = cursor.getLong(cursor.getColumnIndex(EntryApp.COLUMN_ID));
        entryApp.name = cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_NAME));
        entryApp.summary = cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_SUMMARY));
        entryApp.rights = cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_RIGHTS));
        entryApp.title = cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_TITLE));
        entryApp.artist = cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_ARTIST));
        entryApp.category = cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_CATEGORY));
        entryApp.releaseDate = cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_RELEASE_DATE));
        entryApp.releaseDateLabel = cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_RELEASE_DATE_LABEL));
        entryApp.feedId = cursor.getLong(cursor.getColumnIndex(EntryApp.COLUMN_FEED_ID));
        entryApp.img53 = cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_IMG_53));
        entryApp.img75 = cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_IMG_75));
        entryApp.img100 = cursor.getString(cursor.getColumnIndex(EntryApp.COLUMN_IMG_100));
        return entryApp;
    }

    public EntryApp findApp(Long appId) {
        Cursor cursor = database.query(EntryApp.TABLE_NAME, null, EntryApp.COLUMN_ID + "=?", new String[]{String.valueOf(appId)},
                null, null, null);
        cursor.moveToFirst();
        return cursorToEntryApp(cursor);
    }
}
