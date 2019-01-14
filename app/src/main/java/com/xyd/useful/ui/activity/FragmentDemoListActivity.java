package com.xyd.useful.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.useful.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FragmentDemoListActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static String TYPE = "fragment_demo_list_type";
    private String demo_list_type;
    private List<String> mDatas = new ArrayList<>();
//    private String[] items = {"demo1", "demo2","demo3","demo4"};
    private BaseQuickAdapter mBaseQuickAdapter;

    @Override
    protected void initView() {
        demo_list_type = getIntent().getStringExtra(TYPE);
//        mDatas = Arrays.asList(items);
        if("CoordinatorLayoutDemo".equals(demo_list_type)) {
            addDemoList(5);
        }else if("MdSimpleViewDemo".equals(demo_list_type)) {
            addDemoList(1);
        }else if("FloatingActionButtonDemo".equals(demo_list_type)){
            addDemoList(3);
        }else if("PaletteDemo".equals(demo_list_type)){
            addDemoList(2);
        }else if("SnackbarDemo".equals(demo_list_type)){
            addDemoList(1);
        }
        mBaseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.recycle_item_list_tv, mDatas) {
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
        helper.setText(R.id.list_tv, item);
        helper.getView(R.id.list_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(item);
            }
        });
    }

    private void toActivity(String demo_show_type) {
        Intent intent = new Intent(this, FragmentDemoShowActivity.class);
        intent.putExtra(FragmentDemoShowActivity.TYPE, demo_show_type);
        intent.putExtra(FragmentDemoListActivity.TYPE, demo_list_type);
        startActivity(intent);
    }

    private void addDemoList(int num){
        for (int i = 1; i <= num; i++) {
            mDatas.add("demo" + i);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview_layout;
    }
}
