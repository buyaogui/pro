package com.xyd.useful.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.useful.R;
import com.xyd.useful.ui.activity.FragmentDemoListActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class ThreeFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> mDatas;
    private String[] items = {"CoordinatorLayoutDemo","MdSimpleViewDemo", "FloatingActionButtonDemo",
            "PaletteDemo", "SnackbarDemo", "TabLayoutDemo"};
    private BaseQuickAdapter mBaseQuickAdapter;

    @Override
    protected void initView() {
        mDatas = Arrays.asList(items);
        mBaseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.recycle_item_grid_tv, mDatas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                setRecyclerData(helper, item);
            }
        };
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
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

    private void toActivity(String item) {
        Intent intent = new Intent(getContext(), FragmentDemoListActivity.class);
        intent.putExtra(FragmentDemoListActivity.TYPE, item);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview_layout;
    }

}
