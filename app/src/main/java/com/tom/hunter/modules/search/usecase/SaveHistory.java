package com.tom.hunter.modules.search.usecase;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.UseCase;
import com.tom.hunter.framework.data.DataRepository;
import com.tom.hunter.model.History;
import com.tom.hunter.model.Job;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by txu1 on 9/5/2016.
 */
public class SaveHistory extends UseCase<SaveHistory.RequestValues, SaveHistory.ResponseValue> {

    private final DataRepository mDataRepository;

    public SaveHistory(@NonNull DataRepository dataRepository) {
        mDataRepository = checkNotNull(dataRepository, "dataRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mDataRepository.saveHistory(requestValues.getHistory());
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final History mHistory;

        public RequestValues(@NonNull History history) {
            mHistory = checkNotNull(history, "history cannot be null!");
        }

        public History getHistory() {
            return mHistory;
        }
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