package com.tom.hunter.framework.data;

/**
 * Created by txu1 on 9/9/2016.
 */
public interface IDataSource {

    interface LoadCallback {
        void onDataNotAvailable();
    }
}
