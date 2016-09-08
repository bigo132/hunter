package com.tom.hunter.modules.home.usecase;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.UseCase;
import com.tom.hunter.framework.data.DataRepository;
import com.tom.hunter.framework.data.IDataSource;
import com.tom.hunter.model.Job;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/5/2016.
 */
public class GetPromptJobs extends UseCase<UseCase.RequestValues, GetPromptJobs.ResponseValue> {

    private final DataRepository mDataRepository;

    public GetPromptJobs(@NonNull DataRepository dataRepository) {
        mDataRepository = checkNotNull(dataRepository, "dataRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mDataRepository.getPromptJobs(new IDataSource.LoadJobsCallback() {
            @Override
            public void onJobsLoaded(List<Job> jobs) {
                ResponseValue responseValue = new ResponseValue(jobs);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static class ResponseValue implements UseCase.ResponseValue {

        private final List<Job> mJobs;

        public ResponseValue(@NonNull List<Job> jobs) {
            mJobs = checkNotNull(jobs, "jobs cannot be null!");
        }

        public List<Job> getJobs() {
            return mJobs;
        }
    }
}