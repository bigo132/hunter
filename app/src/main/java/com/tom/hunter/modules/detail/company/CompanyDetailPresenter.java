package com.tom.hunter.modules.detail.company;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.UseCase;
import com.tom.hunter.framework.UseCaseHandler;
import com.tom.hunter.model.Company;
import com.tom.hunter.modules.detail.usecase.GetCompanyDetail;
import com.tom.hunter.modules.home.usecase.JobRequestValues;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/2/2016.
 */
public class CompanyDetailPresenter implements CompanyDetailContract.Presenter {

    private final CompanyDetailContract.View mDetailView;

    private final GetCompanyDetail mDetailCompany;

    private final UseCaseHandler mUseCaseHandler;

    private boolean mFirstLoad = true;

    public CompanyDetailPresenter(@NonNull UseCaseHandler useCaseHandler, @NonNull CompanyDetailContract.View detailView, @NonNull GetCompanyDetail getCompanyDetail) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "useCaseHandler cannot be null");
        mDetailCompany = checkNotNull(getCompanyDetail, "getJobDetail cannot be null!");
        mDetailView = checkNotNull(detailView, "detailView cannot be null!");
        mDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        loadCompanyDetail(false);
    }

    @Override
    public void loadCompanyDetail(boolean forceUpdate) {
        loadCompanyDetail(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadCompanyDetail(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mDetailView.setLoadingIndicator(true);
        }

        mUseCaseHandler.execute(mDetailCompany, new JobRequestValues(),
                new UseCase.UseCaseCallback<GetCompanyDetail.ResponseValue>() {
                    @Override
                    public void onSuccess(GetCompanyDetail.ResponseValue response) {
                        Company company = response.getCompany();
                        // The view may not be able to handle UI updates anymore
                        if (!mDetailView.isActive()) {
                            return;
                        }
                        if (showLoadingUI) {
                            mDetailView.setLoadingIndicator(false);
                        }
                        mDetailView.showCompany(company);
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
