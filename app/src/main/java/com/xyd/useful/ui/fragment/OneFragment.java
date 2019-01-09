package com.xyd.useful.ui.fragment;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xyd.useful.R;
import com.xyd.useful.ui.adapter.ListFgVgAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OneFragment extends BaseFragment {


    @Override
    protected void initView(){

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

}
