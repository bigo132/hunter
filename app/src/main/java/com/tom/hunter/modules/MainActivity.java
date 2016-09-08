package com.tom.hunter.modules;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.tom.hunter.R;
import com.tom.hunter.modules.home.HomeFragment;
import com.tom.hunter.modules.profile.ProfileFragment;
import com.tom.hunter.modules.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by txu1 on 9/8/2016.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private long lastBackTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
        initBottomBar();
    }

    private void initBottomBar() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {
                    viewPager.setCurrentItem(0, true);
                }
                else if (tabId == R.id.tab_search) {
                    viewPager.setCurrentItem(1, true);
                }
                else if (tabId == R.id.tab_profile) {
                    viewPager.setCurrentItem(2, true);
                }
            }
        });
    }

    private void initViewPager() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(SearchFragment.newInstance());
        fragments.add(ProfileFragment.newInstance());
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mBottomBar.selectTabAtPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        long currentBackTime = System.currentTimeMillis();
        if (currentBackTime - lastBackTime > 2 * 1000) {
            Toast.makeText(this, "Back Application", Toast.LENGTH_SHORT).show();
            lastBackTime = currentBackTime;
        } else {
            System.exit(0);
        }
    }
}
