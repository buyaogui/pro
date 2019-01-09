package com.xyd.useful.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;


import com.xyd.useful.R;
import com.xyd.useful.ui.activity.BaseActivity;
import com.xyd.useful.ui.adapter.MyPagerAdapter;
import com.xyd.useful.ui.fragment.FourFragment;
import com.xyd.useful.ui.fragment.OneFragment;
import com.xyd.useful.ui.fragment.ThreeFragment;
import com.xyd.useful.ui.fragment.TwoFragment;
import com.xyd.useful.ui.widget.CustomViewPager;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeLinearLayout;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<Integer> tabIconsList = new ArrayList<>();
    private List<String> tabTextsList = new ArrayList<>();
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void initView() {
        initData();
        setupTabView();
        setBGABadgeFrameLayoutCount(1,3);
    }

    private void setupTabView() {
        viewPager.setScrollble(false);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        myPagerAdapter = new MyPagerAdapter(this, getSupportFragmentManager(), tabTextsList, fragmentList, tabIconsList);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        for(int i=0;i<tabLayout.getTabCount();i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(myPagerAdapter.getTabView(i));
        }
    }

    private void initData() {
        fragmentList.add(new OneFragment());
        tabTextsList.add(getString(R.string.first_tab));
        tabIconsList.add(R.drawable.selector_main_tab_icon1);

        fragmentList.add(new TwoFragment());
        tabTextsList.add(getString(R.string.two_tab));
        tabIconsList.add(R.drawable.selector_main_tab_icon2);

        fragmentList.add(new ThreeFragment());
        tabTextsList.add(getString(R.string.three_tab));
        tabIconsList.add(R.drawable.selector_main_tab_icon3);

        fragmentList.add(new FourFragment());
        tabTextsList.add(getString(R.string.four_tab));
        tabIconsList.add(R.drawable.selector_main_tab_icon3);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void setBGABadgeFrameLayoutCount(int position, int count) {
        BGABadgeLinearLayout mBGABadgeLinearLayout = tabLayout.getTabAt(position).getCustomView().findViewById(R.id.badgeView);
        if (count > 0) {
            mBGABadgeLinearLayout.showTextBadge(String.valueOf(count));
        } else {
            mBGABadgeLinearLayout.hiddenBadge();
            mBGABadgeLinearLayout.showCirclePointBadge();
        }
    }
}
