package com.xyd.useful.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.useful.R;
import com.xyd.useful.bean.DemoListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActivityDemoListActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static String TYPE = "activity_demo_list_type";
    private String demo_list_type;
    private List<DemoListBean> mDatas = new ArrayList<>();
    private BaseQuickAdapter mBaseQuickAdapter;

    @Override
    protected void initView() {
        demo_list_type = getIntent().getStringExtra(TYPE);
//        mDatas = Arrays.asList(items);
        if("NavigationDrawerDemo".equals(demo_list_type)) {
            addClass(NavigationDrawerDemo1Activity.class);
            addClass(NavigationDrawerDemo2Activity.class);
        }
        mBaseQuickAdapter = new BaseQuickAdapter<DemoListBean, BaseViewHolder>(R.layout.recycle_item_list_tv, mDatas) {
            @Override
            protected void convert(BaseViewHolder helper, DemoListBean item) {
                setRecyclerData(helper, item);
            }
        };
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mBaseQuickAdapter);
    }

    private void setRecyclerData(BaseViewHolder helper, DemoListBean item) {
        helper.setText(R.id.list_tv, item.getTitle());
        helper.getView(R.id.list_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(item.getItemClass());
            }
        });
    }

    private void toActivity(Class<?> mClass) {
        Intent intent = new Intent(this, mClass);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview_layout;
    }

    private void addClass(Class<?> mClass){
        DemoListBean mDemoListBean = new DemoListBean();
        mDemoListBean.setTitle(mClass.getSimpleName());
        mDemoListBean.setItemClass(mClass);
        mDatas.add(mDemoListBean);
    }
}
