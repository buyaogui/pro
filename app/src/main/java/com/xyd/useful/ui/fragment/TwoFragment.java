package com.xyd.useful.ui.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.xyd.useful.R;
import com.xyd.useful.ui.adapter.ListFgVgAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TwoFragment extends BaseFragment {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_two;
    }
}
