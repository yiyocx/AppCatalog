package com.yiyo.appcatalog.model.entities;

import java.util.Date;

/**
 * Created by yiyo on 24/02/16.
 */
public class EntryApp {

    public static final String TABLE_NAME = "entry_app";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SUMMARY = "summary";
    public static final String COLUMN_RIGHTS = "rights";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ARTIST = "artist";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_RELEASE_DATE_LABEL = "release_date_label";
    public static final String COLUMN_FEED_ID= "feed_id";
    public static final String COLUMN_IMG_53 = "img_53";
    public static final String COLUMN_IMG_75 = "img_75";
    public static final String COLUMN_IMG_100 = "img_100";

    public Long id;
    public String name;
    public String summary;
    public String rights;
    public String title;
    public String artist;
    public String category;
    public String releaseDate;
    public String releaseDateLabel;
    public Long feedId;
    public String img53;
    public String img75;
    public String img100;

    public static final String DDL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_SUMMARY + " TEXT, " +
                    COLUMN_RIGHTS + " TEXT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_ARTIST + " TEXT, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_RELEASE_DATE + " TEXT, " +
                    COLUMN_RELEASE_DATE_LABEL + " TEXT," +
                    COLUMN_FEED_ID + " INTEGER NOT NULL, " +
                    COLUMN_IMG_53 + " TEXT, " +
                    COLUMN_IMG_75 + " TEXT, " +
                    COLUMN_IMG_100 + " TEXT, " +
                    "FOREIGN KEY(" + COLUMN_FEED_ID + ") REFERENCES " +
                    Feed.TABLE_NAME + " (" + Feed.COLUMN_ID + ")" +
                    ");";
}
