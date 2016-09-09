package com.tom.hunter.modules.search.usecase;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.UseCase;
import com.tom.hunter.framework.data.DataRepository;
import com.tom.hunter.framework.data.local.ILocalDataSource;
import com.tom.hunter.model.History;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/5/2016.
 */
public class GetHistories extends UseCase<UseCase.RequestValues, GetHistories.ResponseValue> {

    private final DataRepository mDataRepository;

    public GetHistories(@NonNull DataRepository dataRepository) {
        mDataRepository = checkNotNull(dataRepository, "dataRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mDataRepository.getSearchHistory(new ILocalDataSource.LoadHistoryCallback(){
            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }

            @Override
            public void onHistoriesLoaded(List<History> histories) {
                ResponseValue responseValue = new ResponseValue(histories);
                getUseCaseCallback().onSuccess(responseValue);
            }
        });
    }

    public static class ResponseValue implements UseCase.ResponseValue {

        private final List<History> mHistories;

        public ResponseValue(@NonNull List<History> histories) {
            mHistories = checkNotNull(histories, "histories cannot be null!");
        }

        public List<History> getHistories() {
            return mHistories;
        }
    }
}