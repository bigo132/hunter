package com.tom.hunter.modules.detail.job;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tom.hunter.R;
import com.tom.hunter.model.Company;
import com.tom.hunter.model.Job;
import com.tom.hunter.model.Photo;
import com.tom.hunter.modules.common.AppConstact;
import com.tom.hunter.modules.common.JobListAdapter;
import com.tom.hunter.widget.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link JobDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobDetailFragment extends Fragment implements JobDetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.companyImgPager)
    ViewPager companyImgPager;

    @BindView(R.id.salaryText)
    TextView salaryText;

    @BindView(R.id.jobText)
    TextView jobText;

    @BindView(R.id.contactBtn)
    Button contactBtn;

    @BindView(R.id.companyLogo)
    ImageView companyLogo;

    @BindView(R.id.companyInfoText)
    TextView companyInfoText;

    @BindView(R.id.locationBtn)
    ImageView locationBtn;

    @BindView(R.id.companyDescText)
    TextView companyDescText;

    @BindView(R.id.jobDescText)
    TextView jobDescText;

    @BindView(R.id.recommendList)
    MyListView recommendList;

    private List<Photo> photos = new ArrayList<>();

    private MyAdapter imgAdapter;

    private JobListAdapter recommendAdapter;

    private JobDetailContract.Presenter mPresenter;

    private CompositeSubscription subscriptions;

    public JobDetailFragment() {
        // Required empty public constructor
    }

    public static JobDetailFragment newInstance(String jobId) {
        JobDetailFragment jobFragment = new JobDetailFragment();
        Bundle args = new Bundle();
        args.putString(AppConstact.KEY_JOB_ID, jobId);
        jobFragment.setArguments(args);
        return jobFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jobId = this.getArguments().getString(AppConstact.KEY_JOB_ID);
        mPresenter.loadJobDetail(jobId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_detail, container, false);
        ButterKnife.bind(this, view);

        imgAdapter = new MyAdapter();
        companyImgPager.setAdapter(imgAdapter);

        recommendAdapter = new JobListAdapter(this.getContext());
        recommendList.setAdapter(recommendAdapter);
        return view;
    }

    @Override
    public void setPresenter(@NonNull JobDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
    }

    @Override
    public void showLoadingTasksError() {
        showMessage(getString(R.string.loading_error));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showJob(Job job) {
        if (job != null) {
            toolbar.setTitle(job.getName());
            salaryText.setText(job.getSalary());
            jobText.setText(job.getSimpleInfo());
            jobDescText.setText(job.getDescription());

            Company company = job.getCompany();
            if (company != null) {
                photos = company.getPhotos();
                imgAdapter.notifyDataSetChanged();

                Glide.with(this).load(company.getLogo()).centerCrop().placeholder(R.drawable.company_logo).into(companyLogo);
                companyInfoText.setText(company.getSimpleInfo());
                companyDescText.setText(company.getDescription());
            }
        }
    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = container.getContext();
            ImageView imageView = new ImageView(context);
            container.addView(imageView);

            Photo photo = photos.get(position);
            if (photo != null) {
                String url = photo.getUrl();
                Glide.with(context).load(url).placeholder(R.drawable.company_logo).into(imageView);
            }
            return imageView;
        }

        @Override
        public int getCount() {
            return photos.size();
        }
    }
}
