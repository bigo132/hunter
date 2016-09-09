package com.tom.hunter.framework.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.tom.hunter.framework.model.History;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.tom.hunter.framework.data.local.PersistenceContract.HistoryEntry;

/**
 * Created by txu1 on 9/2/2016.
 */
public class LocalDataSourceImpl implements ILocalDataSource {

    private static LocalDataSourceImpl INSTANCE;

    private DbHelper mDbHelper;

    private SQLiteDatabase mDb;

    // Prevent direct instantiation.
    private LocalDataSourceImpl(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new DbHelper(context);
        mDb = mDbHelper.getWritableDatabase();
    }

    public static LocalDataSourceImpl getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSourceImpl(context);
        }
        return INSTANCE;
    }

    @Override
    public void saveHistory(@NonNull History history) {
        checkNotNull(history);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HistoryEntry.COLUMN_NAME_QUERY_CRITERIA, history.getQueryCriteria());
        values.put(HistoryEntry.COLUMN_NAME_TYPE, history.getType());

        db.insert(HistoryEntry.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void getSearchHistory(@NonNull LoadHistoryCallback callback) {
        List<History> histories = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {HistoryEntry.COLUMN_NAME_ID, HistoryEntry.COLUMN_NAME_QUERY_CRITERIA, HistoryEntry.COLUMN_NAME_TYPE, HistoryEntry.COLUMN_NAME_SEARCH_TIME};
        Cursor c = db.query(HistoryEntry.TABLE_NAME, projection, null, null, null, null, HistoryEntry.COLUMN_NAME_SEARCH_TIME + " DESC");
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(HistoryEntry.COLUMN_NAME_ID));
                String queryCriteria = c.getString(c.getColumnIndexOrThrow(HistoryEntry.COLUMN_NAME_QUERY_CRITERIA));
                int type = c.getInt(c.getColumnIndexOrThrow(HistoryEntry.COLUMN_NAME_TYPE));
                History history = new History(id, queryCriteria, type);
                histories.add(history);
            }
        }
        if (c != null) {
            c.close();
        }

        db.close();
        callback.onHistoriesLoaded(histories);
    }
}
