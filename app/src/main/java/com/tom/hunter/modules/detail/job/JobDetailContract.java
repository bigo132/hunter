package com.tom.hunter.modules.detail.job;

import com.tom.hunter.framework.IBasePresenter;
import com.tom.hunter.framework.IBaseView;
import com.tom.hunter.model.Job;

/**
 * Created by txu1 on 9/2/2016.
 */
public interface JobDetailContract {

    interface View extends IBaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showLoadingTasksError();

        boolean isActive();

        void showJob(Job job);
    }

    interface Presenter extends IBasePresenter {

        void loadJobDetail(String id);
    }
}
