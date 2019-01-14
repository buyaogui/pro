package com.xyd.useful.ui.fragment.mdview.fab;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FabDemo3Fragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.list_fab)
    FloatingActionButton listFab;

    private BaseQuickAdapter adapter;
    private List<String> data = new ArrayList<>();
    @Override
    protected void initView() {
        for (int i=0;i<100;i++){
            data.add("content-"+i);
        }
        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.recycle_item_list_tv, data) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                setRecyclerData(helper, item);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void setRecyclerData(BaseViewHolder helper, String item) {
        helper.setText(R.id.list_tv, item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fab_demo3;
    }

}
