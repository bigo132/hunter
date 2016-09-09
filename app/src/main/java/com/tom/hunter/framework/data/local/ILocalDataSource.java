package com.tom.hunter.framework.data.local;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.data.IDataSource;
import com.tom.hunter.framework.model.History;
import com.tom.hunter.model.Company;
import com.tom.hunter.model.Job;

import java.util.List;
import java.util.Map;

/**
 * Created by txu1 on 9/2/2016.
 */
public interface ILocalDataSource extends IDataSource {

    void saveHistory(@NonNull History history);

    void getSearchHistory(@NonNull LoadHistoryCallback callback);

    interface LoadHistoryCallback extends LoadCallback {
        void onHistoriesLoaded(List<History> histories);
    }
}
