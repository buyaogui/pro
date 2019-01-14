package com.xyd.useful.ui.fragment.mdview.fab;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FabDemo1Fragment extends BaseFragment{

    @BindView(R.id.cbDelay)
    CheckBox cbDelay;
    @BindView(R.id.miniFab01)
    FloatingActionButton miniFab01;
    @BindView(R.id.ll01)
    LinearLayout ll01;
    @BindView(R.id.miniFab02)
    FloatingActionButton miniFab02;
    @BindView(R.id.ll02)
    LinearLayout ll02;
    @BindView(R.id.miniFab03)
    FloatingActionButton miniFab03;
    @BindView(R.id.ll03)
    LinearLayout ll03;
    @BindView(R.id.miniFab04)
    FloatingActionButton miniFab04;
    @BindView(R.id.ll04)
    LinearLayout ll04;
    @BindView(R.id.miniFab05)
    FloatingActionButton miniFab05;
    @BindView(R.id.ll05)
    LinearLayout ll05;
    @BindView(R.id.miniFab06)
    FloatingActionButton miniFab06;
    @BindView(R.id.ll06)
    LinearLayout ll06;
    @BindView(R.id.rlAddBill)
    RelativeLayout rlAddBill;
    @BindView(R.id.fab01Add)
    FloatingActionButton fab01Add;

    /*
        app:elevation="10dp"   立体的阴影高度
        app:fabSize="mini"     按钮样式大小 分mini  normal
        app:layout_anchorGravity="right|center"  在layout_anchor绑定控件中的位置
        app:layout_anchor="@id/btnClick"         绑定控件
     */

    private boolean isAdd = false;
    private List<LinearLayout> ll = new ArrayList<>();
    private List<AnimatorSet> mAnimatorSetList = new ArrayList<>();

    @Override
    protected void initView() {
        ll.add(ll01);
        ll.add(ll02);
        ll.add(ll03);
        ll.add(ll04);
        ll.add(ll05);
        ll.add(ll06);
        for (int i=0;i<ll.size();i++){
            mAnimatorSetList.add((AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.add_bill_anim));
        }
    }

    private void hideFABMenu() {
        rlAddBill.setVisibility(View.GONE);
        fab01Add.setImageResource(R.drawable.tab_icon_normal_1);
        isAdd = false;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fab_demo1;
    }

    @OnClick({R.id.miniFab01, R.id.miniFab02, R.id.miniFab03, R.id.miniFab04, R.id.miniFab05, R.id.miniFab06, R.id.fab01Add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.miniFab01:
            case R.id.miniFab02:
            case R.id.miniFab03:
            case R.id.miniFab04:
            case R.id.miniFab05:
            case R.id.miniFab06:
                hideFABMenu();
                break;
            case R.id.fab01Add:
                fab01Add.setImageResource(isAdd ? R.drawable.tab_icon_normal_2 : R.drawable.tab_icon_normal_3);
                isAdd = !isAdd;
                rlAddBill.setVisibility(isAdd ? View.VISIBLE : View.GONE);
                if (isAdd) {
                    for (int i=0;i<mAnimatorSetList.size();i++) {
                        AnimatorSet mAnimatorSet = mAnimatorSetList.get(i);
                        mAnimatorSet.setTarget(ll.get(i));
                        if(i!=0){
                            mAnimatorSet.setStartDelay(cbDelay.isChecked()?((i+1)*50):0);
                        }
                        mAnimatorSet.start();
                    }
                }
                break;
        }
    }

}
