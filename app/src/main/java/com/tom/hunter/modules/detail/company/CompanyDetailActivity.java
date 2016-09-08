package com.tom.hunter.modules.detail.company;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tom.hunter.R;
import com.tom.hunter.framework.ActivityUtils;
import com.tom.hunter.modules.Injection;

public class CompanyDetailActivity extends AppCompatActivity {

    private CompanyDetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CompanyDetailFragment detailFragment = (CompanyDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (detailFragment == null) {
            detailFragment = CompanyDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    detailFragment, R.id.contentFrame);
        }

        // Create the presenter
        detailPresenter = new CompanyDetailPresenter(
                Injection.provideUseCaseHandler(),
                detailFragment,
                Injection.provideGetDetailCompany(getApplicationContext()));
    }
}
