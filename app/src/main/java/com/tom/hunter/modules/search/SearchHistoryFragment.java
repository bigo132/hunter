package com.tom.hunter.modules.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tom.hunter.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchHistoryFragment extends Fragment {

    @BindView(R.id.historyList)
    ListView historyList;

    public SearchHistoryFragment() {
        // Required empty public constructor
    }

    public static SearchHistoryFragment newInstance() {
        return new SearchHistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_history, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
