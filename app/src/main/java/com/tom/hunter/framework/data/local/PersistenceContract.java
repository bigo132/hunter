package com.tom.hunter.framework.data.local;

import android.provider.BaseColumns;

/**
 * Created by txu1 on 9/9/2016.
 */
public final class PersistenceContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PersistenceContract() {}

    public static abstract class HistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "history";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_QUERY_CRITERIA = "queryCriteria";
        public static final String COLUMN_NAME_SEARCH_TIME = "searchTime";
    }

    public static abstract class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_JOB_ID = "jobId";
        public static final String COLUMN_NAME_COMPANY_ID = "companyId";
        public static final String COLUMN_NAME_USER_ID = "userId";
        public static final String COLUMN_NAME_CREATE_DATE = "createDate";
        public static final String COLUMN_NAME_TYPE = "type";
    }
}
