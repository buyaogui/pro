package com.xyd.useful.ui.fragment.coordinator;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.xyd.useful.R;
import com.xyd.useful.listener.AppBarStateChangeListener;
import com.xyd.useful.ui.activity.CoordinatorLayoutDemoShowActivity;
import com.xyd.useful.ui.activity.MainActivity;
import com.xyd.useful.ui.adapter.ListFgVgAdapter;
import com.xyd.useful.ui.fragment.BaseFragment;
import com.xyd.useful.ui.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CoordinatorLayoutDemo4Fragment extends BaseFragment {

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.main_backdrop)
    ImageView mainBackdrop;
    @BindView(R.id.toolbar2)
    Toolbar toolbar2;
    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout mainCollapsing;
    @BindView(R.id.tabs2)
    TabLayout tabs2;
    @BindView(R.id.main_appbar)
    AppBarLayout mainAppbar;
    @BindView(R.id.viewpager2)
    ViewPager viewpager2;
    @BindView(R.id.fab2)
    FloatingActionButton fab2;

    List<String> mTitles;
    List<Fragment> mFragments;

    @Override
    protected void initView() {
        setupViewPager();

        //沉浸式状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0之上
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        CoordinatorLayoutDemoShowActivity activity = (CoordinatorLayoutDemoShowActivity)getActivity();
        activity.setSupportActionBar(toolbar2);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
        mainCollapsing.setExpandedTitleColor(Color.BLACK);
        mainCollapsing.setCollapsedTitleTextColor(Color.WHITE);
        mainCollapsing.setContentScrimColor(Color.parseColor("#11B7F3"));
        mainCollapsing.setTitle("编程是一种信仰");


        mainAppbar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {

                    //展开状态
//                    Toast.makeText(getContext(), "展开", Toast.LENGTH_SHORT).show();
//                    Snackbar.make(coordinatorLayout, "展开", Snackbar.LENGTH_SHORT).show();
                    Snackbar.make(coordinatorLayout,"已删除一个会话",Snackbar.LENGTH_SHORT)
                            .setAction("撤销", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Toast.makeText(getContext(), "撤销了删除", Toast.LENGTH_SHORT).show();

                                }
                            }).show();

                } else if (state == State.COLLAPSED) {

                    //折叠状态
//                    Toast.makeText(getContext(), "折叠状态", Toast.LENGTH_SHORT).show();
                    Snackbar.make(coordinatorLayout, "折叠状态", Snackbar.LENGTH_SHORT).show();
                } else {

                    //中间状态
//                    Toast.makeText(getActivity(),"中间状态",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void setupViewPager() {
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        mTitles.add("故事");
        mTitles.add("小说");
        mTitles.add("博客");
        for (int i = 0; i < mTitles.size(); i++) {
            ListFragment listFragment = ListFragment.newInstance(mTitles.get(i));
            mFragments.add(listFragment);
        }
        // 第二步：为ViewPager设置适配器
        ListFgVgAdapter adapter2=
                new ListFgVgAdapter(getContext(), getActivity().getSupportFragmentManager(), mTitles, mFragments, null);

        viewpager2.setOffscreenPageLimit(mFragments.size());
        viewpager2.setAdapter(adapter2);
        //  第三步：将ViewPager与TableLayout 绑定在一起
        tabs2.setupWithViewPager(viewpager2);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coordinator_demo4;
    }

}
