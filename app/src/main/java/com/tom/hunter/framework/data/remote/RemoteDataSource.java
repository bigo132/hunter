package com.tom.hunter.framework.data.remote;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.data.IDataSource;

/**
 * Created by txu1 on 9/2/2016.
 */
public class RemoteDataSource implements IDataSource {

    private static RemoteDataSource INSTANCE;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
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
