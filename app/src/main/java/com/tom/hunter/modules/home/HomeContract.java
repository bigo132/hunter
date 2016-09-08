package com.tom.hunter.modules.home;

import com.tom.hunter.framework.IBasePresenter;
import com.tom.hunter.framework.IBaseView;
import com.tom.hunter.model.Job;

import java.util.List;

/**
 * Created by txu1 on 9/2/2016.
 */
public interface HomeContract {

    interface View extends IBaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showLoadingTasksError();

        boolean isActive();

        void showNoJobs();

        void showPromptJobs(List<Job> jobs);

        void showAllJobs(List<Job> promptJobs, List<Job> recentJobs);
    }

    interface Presenter extends IBasePresenter {

        void loadAllJobs(boolean forceUpdate);
    }
}
