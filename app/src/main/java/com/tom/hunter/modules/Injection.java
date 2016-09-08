package com.tom.hunter.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tom.hunter.JobApplication;
import com.tom.hunter.framework.UseCaseHandler;
import com.tom.hunter.framework.data.DataRepository;
import com.tom.hunter.framework.data.FakeJobsRemoteDataSource;
import com.tom.hunter.framework.data.local.LocalDataSource;
import com.tom.hunter.modules.detail.usecase.GetCompanyDetail;
import com.tom.hunter.modules.detail.usecase.GetJobDetail;
import com.tom.hunter.modules.home.usecase.GetAllJobs;
import com.tom.hunter.modules.home.usecase.GetPromptJobs;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/2/2016.
 */
public class Injection {

    public static DataRepository provideDataRepository(@NonNull Context context) {
        checkNotNull(context);
        return DataRepository.getInstance(FakeJobsRemoteDataSource.getInstance(),
                LocalDataSource.getInstance(context));
    }

    public static DataRepository provideDataRepository() {
        Context context = JobApplication.getInstance();
        return DataRepository.getInstance(FakeJobsRemoteDataSource.getInstance(),
                LocalDataSource.getInstance(context));
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static GetPromptJobs provideGetPromptJobs() {
        return new GetPromptJobs(provideDataRepository());
    }

    public static GetAllJobs provideGetRecentJobs() {
        return new GetAllJobs(provideDataRepository());
    }

    public static GetJobDetail provideGetDetailJob(@NonNull Context context) {
        return new GetJobDetail(provideDataRepository(context));
    }

    public static GetCompanyDetail provideGetDetailCompany(@NonNull Context context) {
        return new GetCompanyDetail(provideDataRepository(context));
    }
}
