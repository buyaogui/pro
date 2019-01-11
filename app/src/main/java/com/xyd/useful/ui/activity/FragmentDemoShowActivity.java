package com.xyd.useful.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.mdview.cardview.CardViewDemo1Fragment;
import com.xyd.useful.ui.fragment.mdview.coordinator.CoordinatorLayoutDemo1Fragment;
import com.xyd.useful.ui.fragment.mdview.coordinator.CoordinatorLayoutDemo2Fragment;
import com.xyd.useful.ui.fragment.mdview.coordinator.CoordinatorLayoutDemo3Fragment;
import com.xyd.useful.ui.fragment.mdview.coordinator.CoordinatorLayoutDemo4Fragment;

public class FragmentDemoShowActivity extends BaseActivity {

    public static String TYPE = "demo_sho_type";
    private String demo_show_type;
    private String demo_list_type;
    Fragment fragment = null;

    @Override
    protected void initView() {
        demo_show_type = getIntent().getStringExtra(FragmentDemoShowActivity.TYPE);
        demo_list_type = getIntent().getStringExtra(FragmentDemoListActivity.TYPE);
        if("CoordinatorLayoutDemo".equals(demo_list_type)) {
            if ("demo1".equals(demo_show_type)) {
                fragment = new CoordinatorLayoutDemo1Fragment();
            } else if ("demo2".equals(demo_show_type)) {
                fragment = new CoordinatorLayoutDemo2Fragment();
            } else if ("demo3".equals(demo_show_type)) {
                fragment = new CoordinatorLayoutDemo3Fragment();
            } else if ("demo4".equals(demo_show_type)) {
                fragment = new CoordinatorLayoutDemo4Fragment();
            }
        }else if("CardViewDemo".equals(demo_list_type)){
            if ("demo1".equals(demo_show_type)) {
                fragment = new CardViewDemo1Fragment();
            }
        }
        addFragment(fragment);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_layout;
    }


    private void addFragment(Fragment fragment) {
        if(fragment == null) return;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction  transaction=manager.beginTransaction();
        transaction.add(R.id.fragment,fragment);
        transaction.commit();
    }

}
