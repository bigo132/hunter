package com.tom.hunter.modules.detail.job;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.UseCase;
import com.tom.hunter.framework.UseCaseHandler;
import com.tom.hunter.model.Job;
import com.tom.hunter.modules.detail.usecase.GetJobDetail;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/2/2016.
 */
public class JobDetailPresenter implements JobDetailContract.Presenter {

    private final JobDetailContract.View mDetailView;

    private final GetJobDetail mDetailJob;

    private final UseCaseHandler mUseCaseHandler;

    public JobDetailPresenter(@NonNull UseCaseHandler useCaseHandler, @NonNull JobDetailContract.View detailView, @NonNull GetJobDetail getJobDetail) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "useCaseHandler cannot be null");
        mDetailJob = checkNotNull(getJobDetail, "getJobDetail cannot be null!");
        mDetailView = checkNotNull(detailView, "detailView cannot be null!");
        mDetailView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void loadJobDetail(String id) {
        loadJobDetail(id, true);
    }

    private void loadJobDetail(String jobId, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mDetailView.setLoadingIndicator(true);
        }

        mUseCaseHandler.execute(mDetailJob, new GetJobDetail.RequestValues(jobId),
                new UseCase.UseCaseCallback<GetJobDetail.ResponseValue>() {
                    @Override
                    public void onSuccess(GetJobDetail.ResponseValue response) {
                        Job job = response.getJob();
                        // The view may not be able to handle UI updates anymore
                        if (!mDetailView.isActive()) {
                            return;
                        }
                        if (showLoadingUI) {
                            mDetailView.setLoadingIndicator(false);
                        }
                        mDetailView.showJob(job);
                    }

                    @Override
                    public void onError() {
                        // The view may not be able to handle UI updates anymore
                        if (!mDetailView.isActive()) {
                            return;
                        }
                        mDetailView.showLoadingTasksError();
                    }
                });
    }
}
