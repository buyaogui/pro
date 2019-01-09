package com.xyd.useful.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.useful.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class CoordinatorLayoutDemoListActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> mDatas = new ArrayList<>();
//    private String[] items = {"demo1", "demo2","demo3","demo4"};
    private BaseQuickAdapter mBaseQuickAdapter;

    @Override
    protected void initView() {
//        mDatas = Arrays.asList(items);
        for (int i=1;i<40;i++){
            mDatas.add("demo"+i);
        }
        mBaseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.recycle_item_grid_tv, mDatas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                setRecyclerData(helper, item);
            }
        };
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mBaseQuickAdapter);
    }

    private void setRecyclerData(BaseViewHolder helper, String item) {
        helper.setText(R.id.grid_item_do_tv, item);
        helper.getView(R.id.grid_item_do_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(item);
            }
        });
    }

    private void toActivity(String type) {
        Intent intent = new Intent(this, CoordinatorLayoutDemoShowActivity.class);
        intent.putExtra(CoordinatorLayoutDemoShowActivity.TYPE, type);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview_layout;
    }
}
