package com.tom.hunter.framework.data.remote;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.data.IDataSource;
import com.tom.hunter.model.Company;
import com.tom.hunter.model.Job;

import java.util.List;
import java.util.Map;

/**
 * Created by txu1 on 9/2/2016.
 */
public interface IRemoteDataSource extends IDataSource {

    void getPromptJobs(@NonNull LoadJobsCallback callback);

    void getAllJobs(@NonNull LoadAllJobsCallback callback);

    void getDetailJob(@NonNull final String jobId, @NonNull GetJobCallback callback);

    void getDetailCompany(@NonNull GetCompanyCallback callback);

    interface LoadJobsCallback extends LoadCallback {
        void onJobsLoaded(List<Job> jobs);
    }

    interface LoadAllJobsCallback extends LoadCallback {
        void onJobsLoaded(Map<Integer, List<Job>> jobs);
    }

    interface GetJobCallback extends LoadCallback {
        void onJobLoaded(Job job);
    }

    interface GetCompanyCallback extends LoadCallback {
        void onCompanyLoaded(Company company);
    }
}
