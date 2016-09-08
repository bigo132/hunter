package com.tom.hunter.modules.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tom.hunter.R;
import com.tom.hunter.framework.BaseFragment;
import com.tom.hunter.model.Job;
import com.tom.hunter.modules.Injection;
import com.tom.hunter.modules.common.JobListAdapter;
import com.tom.hunter.widget.MyListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    @BindView(R.id.promptList)
    MyListView promptList;

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.recentList)
    MyListView recentList;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private JobListAdapter promptAdapter;

    private JobListAdapter recentAdapter;

    private HomeContract.Presenter mPresenter;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new HomePresenter(
                Injection.provideUseCaseHandler(),
                this,
                Injection.provideGetPromptJobs(),
                Injection.provideGetRecentJobs());
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        promptAdapter = new JobListAdapter(this.getContext());
        promptList.setAdapter(promptAdapter);

        recentAdapter = new JobListAdapter(this.getContext());
        recentList.setAdapter(recentAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadAllJobs(true);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setPresenter(@NonNull HomeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        swipeRefreshLayout.setRefreshing(active);
    }

    @Override
    public void showLoadingTasksError() {
        showMessage(getString(R.string.loading_error));
        swipeRefreshLayout.setRefreshing(false);
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showNoJobs() {
        //No this case at moment.
    }

    @Override
    public void showPromptJobs(List<Job> jobs) {
        promptAdapter.refreshData(jobs);
        promptAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAllJobs(List<Job> promptJobs, List<Job> recentJobs) {
        promptAdapter.refreshData(promptJobs);
        promptAdapter.notifyDataSetChanged();

        recentAdapter.refreshData(recentJobs);
        recentAdapter.notifyDataSetChanged();
    }

}
