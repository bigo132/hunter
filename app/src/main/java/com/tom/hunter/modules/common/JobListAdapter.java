package com.tom.hunter.modules.common;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tom.hunter.R;
import com.tom.hunter.model.Job;
import com.tom.hunter.modules.detail.job.JobDetailActivity;
import com.tom.hunter.util.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by txu1 on 9/6/2016.
 */
public class JobListAdapter extends BaseAdapter {

    private List<Job> mJobs = new ArrayList<>();

    private Context mContext;

    public JobListAdapter(Context context) {
        mContext = context;
    }

    public void refreshData(List<Job> jobs) {
        mJobs.clear();
        mJobs.addAll(jobs);
    }

    @Override
    public int getCount() {
        return mJobs.size();
    }

    @Override
    public Job getItem(int i) {
        return mJobs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.job_list_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        final Job job = mJobs.get(i);
        holder.jobName.setText(job.getName());
        holder.postDate.setText(AppUtils.convertDateString(job.getPostDate()));
        holder.jobDesc.setText(job.getSimpleInfo());
        holder.companyInfo.setText(job.getCompany().getSimpleInfo());
        holder.locationView.setText(job.getCompany().getSimpleLocation());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, JobDetailActivity.class);
                intent.putExtra(AppConstact.KEY_JOB_ID, job.getId());
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.companyLogo)
        ImageView companyLogo;

        @BindView(R.id.jobName)
        TextView jobName;

        @BindView(R.id.postDate)
        TextView postDate;

        @BindView(R.id.jobDesc)
        TextView jobDesc;

        @BindView(R.id.companyInfo)
        TextView companyInfo;

        @BindView(R.id.locationView)
        TextView locationView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
