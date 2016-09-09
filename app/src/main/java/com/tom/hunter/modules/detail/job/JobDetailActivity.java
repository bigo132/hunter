package com.tom.hunter.modules.detail.job;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tom.hunter.R;
import com.tom.hunter.framework.ActivityUtils;
import com.tom.hunter.modules.Injection;
import com.tom.hunter.modules.common.AppConstact;

/**
 * 包含的功能包括:
 * --- 描述类：
 * 公司图片的viewpager, 公司内容（LOGO, 公司名称，公司简介，LOCATION，Website链接）， 职位名称，发布时间，发布人，职位类型（兼职/全职）月薪范围， 职位具体描述. 职位申请者的评论列表，
 * 申请者人数
 * --- 操作类：
 * 职位推荐， 职位分享， 电话发布人，添加收藏，简历上传
 */
public class JobDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Activity LifeCycle", "onCreate");
        setContentView(R.layout.activity_detail);

        String jobId = this.getIntent().getStringExtra(AppConstact.KEY_JOB_ID);
        JobDetailFragment detailFragment = (JobDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (detailFragment == null) {
            detailFragment = JobDetailFragment.newInstance(jobId);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    detailFragment, R.id.contentFrame);
        }

        // Create the presenter
        new JobDetailPresenter(
                Injection.provideUseCaseHandler(),
                detailFragment,
                Injection.provideGetDetailJob(getApplicationContext()));
    }
}
