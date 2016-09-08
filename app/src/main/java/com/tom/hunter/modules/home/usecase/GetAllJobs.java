package com.tom.hunter.modules.home.usecase;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.UseCase;
import com.tom.hunter.framework.data.DataRepository;
import com.tom.hunter.framework.data.IDataSource;
import com.tom.hunter.model.Job;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/5/2016.
 */
public class GetAllJobs extends UseCase<UseCase.RequestValues, GetAllJobs.ResponseValue> {

    private final DataRepository mDataRepository;

    public GetAllJobs(@NonNull DataRepository dataRepository) {
        mDataRepository = checkNotNull(dataRepository, "dataRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mDataRepository.getAllJobs(new IDataSource.LoadAllJobsCallback() {
            @Override
            public void onJobsLoaded(Map<Integer, List<Job>> jobs) {
                ResponseValue responseValue = new ResponseValue(jobs);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final Map<Integer, List<Job>> mJobs;

        public ResponseValue(@NonNull Map<Integer, List<Job>> jobs) {
            mJobs = checkNotNull(jobs, "tasks cannot be null!");
        }

        public Map<Integer, List<Job>> getAllJobs() {
            return mJobs;
        }
    }
}