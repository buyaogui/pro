package com.xyd.useful.ui.fragment.mdview.fab;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class FabDemo2Fragment extends BaseFragment {

    @BindView(R.id.lvContacts)
    ListView lvContacts;
    @BindView(R.id.fabAddContact)
    FloatingActionButton fabAddContact;//底部按钮，往下滑时隐藏，往上滑时显示
    @BindView(R.id.fabUp)
    FloatingActionButton fabUp;//滑动超过一定高度时候显示

    private AnimatorSet mHideFAB;
    private AnimatorSet mShowFAB;
    private boolean FAB_VISIBLE = true;
    private int mPreviousFirstVisibleItem;   //记录前面第一个Item
    private int mLastScrollY;            //记录ListView中最上面的Item(View)的上一次顶部Y坐标()
    private int mScrollThreshold = 2;   //阈值：单位px
    private String[] data = new String[100];

    @Override
    protected void initView() {
        for (int i = 0; i < 100; i++) {
            data[i] = "content-" + i;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, data);
        lvContacts.setAdapter(adapter);

        initAnimation();

        lvContacts.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {  //停止滚动
                //showFAB();
//                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(totalItemCount == 0) {
                    showFAB();
                    return;
                }
                //滚动过程中：ListView中最上面一个Item还是同一个Item
                if(isSameRow(firstVisibleItem)) {
                    int newScrollY = getTopItemScrollY();
                    boolean isExceedThreshold = Math.abs(mLastScrollY - newScrollY) > mScrollThreshold;
                    if (isExceedThreshold) {
                        if (mLastScrollY > newScrollY && FAB_VISIBLE == true) {
                            FAB_VISIBLE = false;
                            hideFAB();
                        } else if(mLastScrollY < newScrollY && FAB_VISIBLE == false){
                            FAB_VISIBLE = true;
                            showFAB();
                        }
                    }
                    mLastScrollY = newScrollY;
                } else {
                    if (firstVisibleItem > mPreviousFirstVisibleItem && FAB_VISIBLE == true){  //向下滚动
                        FAB_VISIBLE = false;
                        hideFAB();
                    } else if(firstVisibleItem < mPreviousFirstVisibleItem && FAB_VISIBLE == false){ //向上滚动
                        FAB_VISIBLE = true;
                        showFAB();
                    }
                    mLastScrollY = getTopItemScrollY();
                    mPreviousFirstVisibleItem = firstVisibleItem;
                }
                if (firstVisibleItem > (totalItemCount/4)) {//显示1/4以上时按钮显示
                    fabUp.setVisibility(View.VISIBLE);
                } else {
                    fabUp.setVisibility(View.GONE);
                }
            }
        });
    }

    private boolean isSameRow(int firstVisisbleItem){
        return mPreviousFirstVisibleItem == firstVisisbleItem;
    }
    /**
     * 滚动过程中，获得当前ListView中最上面的Item(View)的顶部的Y坐标(以px为单位)
     * @return
     */
    private int getTopItemScrollY() {
        if (lvContacts == null || lvContacts.getChildAt(0) == null) return 0;
        View topChild = lvContacts.getChildAt(0);
        return topChild.getTop();
    }

    private void initAnimation() {
        mHideFAB = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.scroll_hide_fab);
        mShowFAB = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.scroll_show_fab);
        mHideFAB.setTarget(fabAddContact);
        mShowFAB.setTarget(fabAddContact);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fab_demo2;
    }


    @OnClick({R.id.fabAddContact, R.id.fabUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fabAddContact:
                lvContacts.setSelection(0);
                fabAddContact.setVisibility(View.GONE);
                break;
            case R.id.fabUp:
                lvContacts.setSelection(0);
                fabUp.setVisibility(View.GONE);
                break;
        }
    }

    private void hideFAB() {
        mHideFAB.start();
    }
    private void showFAB(){
        mShowFAB.start();
    }
}
