package com.xyd.useful.ui.fragment.mdview.coordinator;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CoordinatorLayoutDemo5Fragment extends BaseFragment {
    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    //app:layout_scrollFlags="scroll|enterAlways" enterAlways则滑动后隐藏 exitUntilCollapsed则滑动后仍显示

    @Override
    protected void initView() {
        initActionBar();
        initCollapsingToolbar();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coordinator_demo5;
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Snackbar.make(fab, "正在打开邮箱", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void initActionBar(){
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initCollapsingToolbar(){
        toolbarLayout.setTitle("杨幂");
    }
}
