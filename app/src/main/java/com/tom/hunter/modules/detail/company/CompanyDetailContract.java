package com.tom.hunter.modules.detail.company;

import com.tom.hunter.framework.IBasePresenter;
import com.tom.hunter.framework.IBaseView;
import com.tom.hunter.model.Company;

/**
 * Created by txu1 on 9/2/2016.
 */
public interface CompanyDetailContract {

    interface View extends IBaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showLoadingTasksError();

        boolean isActive();

        void showCompany(Company company);
    }

    interface Presenter extends IBasePresenter {

        void loadCompanyDetail(boolean forceUpdate);
    }
}
