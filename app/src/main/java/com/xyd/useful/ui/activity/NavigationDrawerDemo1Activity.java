package com.xyd.useful.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
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
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_camera:
//                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new FragmentOne()).commit();可以做动态替换fragment
                    Snackbar.make(navView, menuItem.getTitle(), Snackbar.LENGTH_SHORT).show();
                    break;
                    case R.id.nav_gallery:
                        Snackbar.make(navView, menuItem.getTitle(), Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_slideshow:
                    case R.id.nav_manage:
                    case R.id.nav_share:
                    case R.id.nav_send:
                        Snackbar.make(navView, menuItem.getTitle(), Snackbar.LENGTH_SHORT).show();
                        break;
                }
                menuItem.setChecked(true);//点击了把它设为选中状态
                drawerLayout.closeDrawers();//关闭抽屉
//                navView.setCheckedItem(menuItem.getItemId());
//                drawerLayout.closeDrawer(GravityCompat.START);
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
