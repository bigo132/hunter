package com.tom.hunter.framework.data.remote;

import android.support.annotation.NonNull;

/**
 * Created by txu1 on 9/2/2016.
 */
public class RemoteDataSourceImpl implements IRemoteDataSource {

    private static RemoteDataSourceImpl INSTANCE;

    public static RemoteDataSourceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSourceImpl();
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
