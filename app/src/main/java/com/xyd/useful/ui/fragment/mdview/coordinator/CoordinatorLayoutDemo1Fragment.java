package com.xyd.useful.ui.fragment.mdview.coordinator;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
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

public class CoordinatorLayoutDemo1Fragment extends BaseFragment {
    /*标签
    app:layout_scrollFlags属性介绍：
        1.scroll:表示该View可以被滑动出CoordinatorLayout的范围，所有想滚动出屏幕的view都需要设置这个flag，
        没有设置这个flag的view将被固定在屏幕顶部。例如，TabLayout 没有设置这个值，将会停留在屏幕顶部
        2.enterAlways:表示任意向下的滚动都会导致该View可见
        3.exitUntilCollapsed:滚动退出屏幕，最后折叠在顶端
        4.enterAlwaysCollapsed:当你的视图已经设置minHeight属性又使用此标志时，你的视图只能以最小高度进入，只有当滚动视图到达顶部时才扩大到完整高度

    给想滑动出范围的View设置属性，比如ToolBar：app:layout_scrollFlags=”scroll|enterAlways

    给发出滑动行为的View设置属性，比如ViewPager：app:layout_behavior="@string/appbar_scrolling_view_behavior"

    1.CoordinatorLayout作根控件，包裹AppBarLayout和可滚动的控件，比如ViewPager
    2.AppBarLayout包裹 ToolBar 及TabLayout，
      ToolBar要滑动，给其设置app:layout_scrollFlags
    3.ViewPager是发出滑动行为的控件，设置属性 app:layout_behavior
    注意：带layout_scrollFlags的view需要放在固定View的前面，这样滑动的view才能够正常退出，而固定的view继续留在顶部
     */
    //recyclerView滑动顶部的title跟随动画
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.my_list)
    RecyclerView myList;
    @BindView(R.id.title)
    TextView title;

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
        return R.layout.fragment_coordinator_demo1;
    }


}
