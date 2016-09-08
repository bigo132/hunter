package com.tom.hunter.modules.home;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.UseCase;
import com.tom.hunter.framework.UseCaseHandler;
import com.tom.hunter.framework.data.IDataSource;
import com.tom.hunter.model.Job;
import com.tom.hunter.modules.home.usecase.GetAllJobs;
import com.tom.hunter.modules.home.usecase.GetPromptJobs;
import com.tom.hunter.modules.home.usecase.JobRequestValues;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/2/2016.
 */
public class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View mHomeView;

    private final GetPromptJobs mPromptJobs;

    private final GetAllJobs mAllJobs;

    private final UseCaseHandler mUseCaseHandler;

    private boolean mFirstLoad = true;

    public HomePresenter(@NonNull UseCaseHandler useCaseHandler, @NonNull HomeContract.View homeView, @NonNull GetPromptJobs getPromptJobs, @NonNull GetAllJobs getAllJobs) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "useCaseHandler cannot be null");
        mPromptJobs = checkNotNull(getPromptJobs, "getPromptJobs cannot be null!");
        mAllJobs = checkNotNull(getAllJobs, "getAllJobs cannot be null!");
        mHomeView = checkNotNull(homeView, "tasksView cannot be null!");
        mHomeView.setPresenter(this);
    }

    @Override
    public void start() {
        loadAllJobs(false);
    }

    @Override
    public void loadAllJobs(boolean forceUpdate) {
        loadAllJobs(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    /**
     * @param forceUpdate   Pass in true to refresh the data in the {@link IDataSource}
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    private void loadAllJobs(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mHomeView.setLoadingIndicator(true);
        }

        mUseCaseHandler.execute(mAllJobs, new JobRequestValues(),
                new UseCase.UseCaseCallback<GetAllJobs.ResponseValue>() {
                    @Override
                    public void onSuccess(GetAllJobs.ResponseValue response) {
                        Map<Integer, List<Job>> jobs = response.getAllJobs();
                        // The view may not be able to handle UI updates anymore
                        if (!mHomeView.isActive()) {
                            return;
                        }
                        if (showLoadingUI) {
                            mHomeView.setLoadingIndicator(false);
                        }
                        processJobs(jobs);
                    }

                    @Override
                    public void onError() {
                        // The view may not be able to handle UI updates anymore
                        if (!mHomeView.isActive()) {
                            return;
                        }
                        mHomeView.showLoadingTasksError();
                    }
                });
    }

    private void processJobs(Map<Integer, List<Job>> jobs) {
        if (jobs.isEmpty()) {
            mHomeView.showNoJobs();
        } else {
            // Show the list of tasks
            mHomeView.showAllJobs(jobs.get(0), jobs.get(1));
        }
    }
}
