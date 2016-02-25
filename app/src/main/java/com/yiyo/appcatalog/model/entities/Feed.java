package com.yiyo.appcatalog.model.entities;

import java.util.Date;

/**
 * Created by yiyo on 24/02/16.
 */
public class Feed {

    public static final String TABLE_NAME = "feed";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_UPDATED = "updated";
    public static final String COLUMN_RIGHTS = "rights";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ICON_URL = "icon_url";
    public static final String COLUMN_FEED_ID = "feed_id";

    public Long id;
    public String author;
    public Date updated;
    public String rights;
    public String title;
    public String iconUrl;
    public String feedId;

    public static final String DDL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_AUTHOR + " TEXT, " +
                    COLUMN_UPDATED + " DATETIME, " +
                    COLUMN_RIGHTS + " TEXT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_ICON_URL + " TEXT, " +
                    COLUMN_FEED_ID + " TEXT" +
                    ");";
}
