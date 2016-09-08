package com.tom.hunter.framework.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.tom.hunter.framework.data.IDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/2/2016.
 */
public class LocalDataSource implements IDataSource {

    private static LocalDataSource INSTANCE;

    private DbHelper mDbHelper;

    private SQLiteDatabase mDb;

    // Prevent direct instantiation.
    private LocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new DbHelper(context);
        mDb = mDbHelper.getWritableDatabase();
    }

    public static LocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getPromptJobs(@NonNull LoadJobsCallback callback) {
    }

    @Override
    public void getAllJobs(@NonNull LoadAllJobsCallback callback) {
    }

    @Override
    public void getDetailJob(@NonNull String jobId, @NonNull GetJobCallback callback) {
    }

    @Override
    public void getDetailCompany(@NonNull GetCompanyCallback callback) {
    }
}
