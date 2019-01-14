package com.xyd.useful.ui.fragment.mdview.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TablayoutDemo1Fragment extends BaseFragment {

    @BindView(R.id.tabLayoutOne)
    TabLayout tabLayoutOne;
    @BindView(R.id.tabLayoutTwo)
    TabLayout tabLayoutTwo;
    @BindView(R.id.tabLayoutThree)
    TabLayout tabLayoutThree;
    @BindView(R.id.tabLayoutFive)
    TabLayout tabLayoutFive;
    @BindView(R.id.tabLayoutFour)
    TabLayout tabLayoutFour;

    @Override
    protected void initView() {
        initTabLayoutOne();
        initTabLayoutTwo();
        initTabLayoutThree();
        initTabLayoutFour();
        initTabLayoutFive();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_layout_demo1;
    }


    private void initTabLayoutOne() {
        tabLayoutOne.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayoutOne.addTab(tabLayoutOne.newTab().setText("one"));
        tabLayoutOne.addTab(tabLayoutOne.newTab().setText("Two"));
        tabLayoutOne.addTab(tabLayoutOne.newTab().setText("Three"));
        tabLayoutOne.addTab(tabLayoutOne.newTab().setText("four"));
        tabLayoutOne.addTab(tabLayoutOne.newTab().setText("five"));
        tabLayoutOne.addTab(tabLayoutOne.newTab().setText("six"));
        tabLayoutOne.addTab(tabLayoutOne.newTab().setText("seven"));
    }

    private void initTabLayoutTwo() {
        tabLayoutTwo.setTabMode(TabLayout.MODE_FIXED);
        tabLayoutTwo.addTab(tabLayoutTwo.newTab().setText("one"));
        tabLayoutTwo.addTab(tabLayoutTwo.newTab().setText("Two"));
        tabLayoutTwo.addTab(tabLayoutTwo.newTab().setText("Three"));
        tabLayoutTwo.addTab(tabLayoutTwo.newTab().setText("four"));
    }

    private void initTabLayoutThree() {
        tabLayoutThree.setTabMode(TabLayout.MODE_FIXED);
        tabLayoutThree.addTab(tabLayoutThree.newTab().setIcon(android.R.drawable.ic_menu_call));
        tabLayoutThree.addTab(tabLayoutThree.newTab().setIcon(android.R.drawable.ic_menu_add));
        tabLayoutThree.addTab(tabLayoutThree.newTab().setIcon(android.R.drawable.ic_menu_agenda));
        tabLayoutThree.addTab(tabLayoutThree.newTab().setIcon(android.R.drawable.ic_menu_camera));
        tabLayoutThree.addTab(tabLayoutThree.newTab().setIcon(android.R.drawable.ic_menu_close_clear_cancel));
    }

    private void initTabLayoutFour() {
        tabLayoutFour.setTabMode(TabLayout.MODE_FIXED);
        tabLayoutFour.addTab(tabLayoutFour.newTab().setCustomView(R.layout.tab_item));
        tabLayoutFour.addTab(tabLayoutFour.newTab().setCustomView(R.layout.tab_item));
        tabLayoutFour.addTab(tabLayoutFour.newTab().setCustomView(R.layout.tab_item));
        tabLayoutFour.addTab(tabLayoutFour.newTab().setCustomView(R.layout.tab_item));
        tabLayoutFour.addTab(tabLayoutFour.newTab().setCustomView(R.layout.tab_item));
    }

    private void initTabLayoutFive() {
        tabLayoutFive.setTabMode(TabLayout.MODE_FIXED);
        tabLayoutFive.addTab(tabLayoutFive.newTab().setIcon(android.R.drawable.ic_menu_call).setText("one"));
        tabLayoutFive.addTab(tabLayoutFive.newTab().setIcon(android.R.drawable.ic_menu_add).setText("Two"));
        tabLayoutFive.addTab(tabLayoutFive.newTab().setIcon(android.R.drawable.ic_menu_agenda).setText("Three"));
        tabLayoutFive.addTab(tabLayoutFive.newTab().setIcon(android.R.drawable.ic_menu_camera).setText("Four"));
        tabLayoutFive.addTab(tabLayoutFive.newTab().setIcon(android.R.drawable.ic_menu_close_clear_cancel).setText("Five"));
    }

    private void initTabListener(){
        tabLayoutOne.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
