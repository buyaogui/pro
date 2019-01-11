package com.xyd.useful.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.xyd.useful.R;

import butterknife.BindView;

public class NavigationDrawerDemo1Activity extends BaseActivity{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void initView() {
        //加了这句图标就会显示原有的颜色
        navView.setItemIconTintList(null);

        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.tab_icon_normal_1);自定义图标
        //这是带Home旋转开关按钮
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
//                drawerLayout, toolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //这是不带Home旋转开关按钮
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();//该方法会自动和Toolbar关联, 将开关的图片显示在了Toolbar上
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();
                Log.v("afsafsfassfa","id="+item.getItemId()+";title="+item.getTitle());
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_drawer_demo1;
    }
}
