package com.tom.hunter.modules.detail.usecase;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.UseCase;
import com.tom.hunter.framework.data.DataRepository;
import com.tom.hunter.framework.data.remote.IRemoteDataSource;
import com.tom.hunter.model.Job;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/6/2016.
 */
public class GetJob extends UseCase<GetJob.RequestValues, GetJob.ResponseValue> {

    private final DataRepository mDataRepository;

    public GetJob(@NonNull DataRepository dataRepository) {
        mDataRepository = checkNotNull(dataRepository, "dataRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        String jobId = requestValues.getJobId();
        mDataRepository.getDetailJob(jobId, new IRemoteDataSource.GetJobCallback() {
            @Override
            public void onJobLoaded(Job job) {
                ResponseValue responseValue = new ResponseValue(job);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {

        private final String mJobId;

        public RequestValues(String jobId) {
            mJobId = jobId;
        }

        public String getJobId() {
            return mJobId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {

        private final Job mJob;

        public ResponseValue(@NonNull Job job) {
            mJob = checkNotNull(job, "Job cannot be null!");
        }

        public Job getJob() {
            return mJob;
        }
    }
}
