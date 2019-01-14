package com.xyd.useful.ui.fragment.mdview.coordinator;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xyd.useful.R;
import com.xyd.useful.ui.adapter.ListFgVgAdapter;
import com.xyd.useful.ui.fragment.BaseFragment;
import com.xyd.useful.ui.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CoordinatorLayoutDemo3Fragment extends BaseFragment {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager_one)
    ViewPager viewpagerOne;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    Unbinder unbinder;

    List<Fragment> mFragments;
    List<String> mTitles;

    @Override
    protected void initView() {
        setupViewPager();
    }

    private void setupViewPager() {
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        mTitles.add("微博");
        mTitles.add("微信");
        mTitles.add("QQ");
        for (int i = 0; i < mTitles.size(); i++) {
            ListFragment listFragment = ListFragment.newInstance(mTitles.get(i));
            mFragments.add(listFragment);
        }
        // 第二步：为ViewPager设置适配器
        ListFgVgAdapter adapter =
                new ListFgVgAdapter(getContext(), getFragmentManager(), mTitles, mFragments, null);

        viewpagerOne.setOffscreenPageLimit(mFragments.size());
        viewpagerOne.setAdapter(adapter);
        //  第三步：将ViewPager与TableLayout 绑定在一起
        tabs.setupWithViewPager(viewpagerOne);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coordinator_demo3;
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
    }
}
