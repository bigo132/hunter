package com.tom.hunter.framework.data;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/2/2016.
 */
public class DataRepository implements IDataSource {

    private static DataRepository INSTANCE = null;

    private final IDataSource mRemoteDataSource;

    private final IDataSource mLocalDataSource;

    // Prevent direct instantiation.
    private DataRepository(@NonNull IDataSource tasksRemoteDataSource,
                           @NonNull IDataSource tasksLocalDataSource) {
        mRemoteDataSource = checkNotNull(tasksRemoteDataSource);
        mLocalDataSource = checkNotNull(tasksLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param remoteDataSource the backend data source
     * @param localDataSource  the device storage data source
     * @return the {@link DataRepository} instance
     */
    public static DataRepository getInstance(IDataSource remoteDataSource,
                                             IDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getPromptJobs(@NonNull final LoadJobsCallback callback) {
        checkNotNull(callback);
        mRemoteDataSource.getPromptJobs(callback);
    }

    @Override
    public void getAllJobs(@NonNull LoadAllJobsCallback callback) {
        checkNotNull(callback);
        mRemoteDataSource.getAllJobs(callback);
    }

    public void getDetailJob(String jobId, GetJobCallback callback) {
        checkNotNull(callback);
        mRemoteDataSource.getDetailJob(jobId, callback);
    }

    public void getDetailCompany(GetCompanyCallback callback) {
        checkNotNull(callback);
        mRemoteDataSource.getDetailCompany(callback);
    }
}
