package com.xyd.useful.ui.fragment.mdview.coordinator;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CoordinatorLayoutDemo2Fragment extends BaseFragment {
    //list顶部有图片，滑动到图片位置会定住一下
    @BindView(R.id.my_list)
    RecyclerView myList;
    @BindView(R.id.header)
    TextView header;

    private List<String> mDatas;
    private BaseQuickAdapter mBaseQuickAdapter;

    @Override
    protected void initView() {
        mDatas = new ArrayList<>();
        for (int i=0;i<30;i++){
            mDatas.add("第"+i+"项");
        }
        mBaseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.recycle_item_list_tv, mDatas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                setRecyclerData(helper, item);
            }
        };
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        myList.setLayoutManager(manager);
        myList.setAdapter(mBaseQuickAdapter);
    }

    private void setRecyclerData(BaseViewHolder helper, String item) {
        helper.setText(R.id.list_tv, item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coordinator_demo2;
    }


}
