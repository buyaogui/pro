package com.xyd.useful.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.coordinator.CoordinatorLayoutDemo1Fragment;
import com.xyd.useful.ui.fragment.coordinator.CoordinatorLayoutDemo2Fragment;
import com.xyd.useful.ui.fragment.coordinator.CoordinatorLayoutDemo3Fragment;
import com.xyd.useful.ui.fragment.coordinator.CoordinatorLayoutDemo4Fragment;

public class CoordinatorLayoutDemoShowActivity extends BaseActivity {

    public static String TYPE;
    private String type;

    @Override
    protected void initView() {
        type = getIntent().getStringExtra(TYPE);
        Fragment fragment = null;
        if("demo1".equals(type)) {
            fragment = new CoordinatorLayoutDemo1Fragment();
        }else if("demo2".equals(type)){
            fragment = new CoordinatorLayoutDemo2Fragment();
        }else if("demo3".equals(type)){
            fragment = new CoordinatorLayoutDemo3Fragment();
        }else if("demo4".equals(type)){
            fragment = new CoordinatorLayoutDemo4Fragment();
        }else if("demo5".equals(type)){

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
