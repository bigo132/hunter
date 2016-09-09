package com.tom.hunter.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tom.hunter.JobApplication;
import com.tom.hunter.framework.UseCaseHandler;
import com.tom.hunter.framework.data.DataRepository;
import com.tom.hunter.framework.data.FakeJobsRemoteDataSource;
import com.tom.hunter.framework.data.local.LocalDataSourceImpl;
import com.tom.hunter.modules.detail.usecase.GetCompany;
import com.tom.hunter.modules.detail.usecase.GetJob;
import com.tom.hunter.modules.home.usecase.GetAllJobs;
import com.tom.hunter.modules.home.usecase.GetPromptJobs;
import com.tom.hunter.modules.search.usecase.GetHistories;
import com.tom.hunter.modules.search.usecase.SaveHistory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/2/2016.
 */
public class Injection {

    public static DataRepository provideDataRepository(@NonNull Context context) {
        checkNotNull(context);
        return DataRepository.getInstance(FakeJobsRemoteDataSource.getInstance(),
                LocalDataSourceImpl.getInstance(context));
    }

    public static DataRepository provideDataRepository() {
        Context context = JobApplication.getInstance();
        return DataRepository.getInstance(FakeJobsRemoteDataSource.getInstance(),
                LocalDataSourceImpl.getInstance(context));
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

    public static GetJob provideGetDetailJob(@NonNull Context context) {
        return new GetJob(provideDataRepository(context));
    }

    public static GetCompany provideGetDetailCompany(@NonNull Context context) {
        return new GetCompany(provideDataRepository(context));
    }

    public static GetHistories provideGetHistories(@NonNull Context context) {
        return new GetHistories(provideDataRepository(context));
    }

    public static SaveHistory provideSaveHistory(@NonNull Context context) {
        return new SaveHistory(provideDataRepository(context));
    }
}
