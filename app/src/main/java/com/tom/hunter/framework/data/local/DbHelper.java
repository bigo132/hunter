package com.tom.hunter.framework.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.tom.hunter.framework.data.local.PersistenceContract.FavoriteEntry;
import static com.tom.hunter.framework.data.local.PersistenceContract.HistoryEntry;

/**
 * Created by txu1 on 9/2/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "jobs.db";

    private static final String SQL_CREATE_FAVORITE = "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " ("
            + FavoriteEntry.COLUMN_NAME_ID + " INTEGER primary key autoincrement, "
            + FavoriteEntry.COLUMN_NAME_JOB_ID + " VARCHAR, "
            + FavoriteEntry.COLUMN_NAME_COMPANY_ID + " VARCHAR, "
            + FavoriteEntry.COLUMN_NAME_TYPE + " INTEGER DEFAULT 0, "
            + FavoriteEntry.COLUMN_NAME_USER_ID + " VARCHAR, "
            + FavoriteEntry.COLUMN_NAME_CREATE_DATE + " DATETIME DEFAULT (datetime('now', 'localtime')))";

    private static final String SQL_CREATE_HISTORY = "CREATE TABLE " + HistoryEntry.TABLE_NAME + " ("
            + HistoryEntry.COLUMN_NAME_ID + " INTEGER primary key autoincrement, "
            + HistoryEntry.COLUMN_NAME_TYPE + " INTEGER DEFAULT 0, "
            + HistoryEntry.COLUMN_NAME_QUERY_CRITERIA + " TEXT, "
            + HistoryEntry.COLUMN_NAME_SEARCH_TIME + " DATETIME DEFAULT (datetime('now', 'localtime')))";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        //Create favorite
        db.execSQL(SQL_CREATE_FAVORITE);

        //Create search history
        db.execSQL(SQL_CREATE_HISTORY);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
