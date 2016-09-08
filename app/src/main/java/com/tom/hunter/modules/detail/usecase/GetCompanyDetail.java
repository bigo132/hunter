package com.tom.hunter.modules.detail.usecase;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.UseCase;
import com.tom.hunter.framework.data.DataRepository;
import com.tom.hunter.framework.data.IDataSource;
import com.tom.hunter.model.Company;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/6/2016.
 */
public class GetCompanyDetail extends UseCase<UseCase.RequestValues, GetCompanyDetail.ResponseValue> {

    private final DataRepository mDataRepository;

    public GetCompanyDetail(@NonNull DataRepository dataRepository) {
        mDataRepository = checkNotNull(dataRepository, "dataRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mDataRepository.getDetailCompany(new IDataSource.GetCompanyCallback() {
            @Override
            public void onCompanyLoaded(Company company) {
                ResponseValue responseValue = new ResponseValue(company);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static class ResponseValue implements UseCase.ResponseValue {

        private final Company mCompany;

        public ResponseValue(@NonNull Company company) {
            mCompany = checkNotNull(company, "Company cannot be null!");
        }

        public Company getCompany() {
            return mCompany;
        }
    }
}
