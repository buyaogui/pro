package com.xyd.useful.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.useful.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ListFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private BaseQuickAdapter mBaseQuickAdapter;
    private String mTitle;
    private List<String> mDatas = new ArrayList<>();

    @Override
    protected void initView() {
        mTitle = getArguments().getString("title");

        for (int i=0;i<30;i++){
            mDatas.add(mTitle+"--"+i);
        }
        mBaseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.recycle_item_list_tv, mDatas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                setRecyclerData(helper, item);
            }
        };

        mBaseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(), "点击了第" + position + "项", Toast.LENGTH_SHORT).show();
            }
        });
        //需要先在convert中 helper.addOnClickListener
        mBaseQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String str = (String) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.list_tv:
                        Toast.makeText(getContext(), "点击："+str, Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
        mBaseQuickAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(), "长按了第" + position + "项", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //需要先在convert中 helper.addOnClickListener
        mBaseQuickAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                String str = (String) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.list_tv:
                        Toast.makeText(getContext(), "长按："+str, Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mBaseQuickAdapter);
    }

    private void setRecyclerData(BaseViewHolder holder, String str) {
//        Log.v("fsaasf","po1="+holder.getAdapterPosition());
//        Log.v("fsaasf","po2="+holder.getPosition());
        holder.setText(R.id.list_tv, str)
                .addOnClickListener(R.id.list_tv)
                .addOnLongClickListener(R.id.list_tv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    public static ListFragment newInstance(String title) {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

}
