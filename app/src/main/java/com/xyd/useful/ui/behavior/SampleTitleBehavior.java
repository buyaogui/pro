package com.xyd.useful.ui.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SampleTitleBehavior extends CoordinatorLayout.Behavior<View> {
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public SampleTitleBehavior() {
    }

    public SampleTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();//第一次记录下来recyclerView和顶部title之间的总长度
            Log.v("sfasfasfasf","deltaY="+deltaY);
        }
        float dy = dependency.getY() - child.getHeight();//获取当前recyclerView和顶部title之间的长度
        dy = dy < 0 ? 0 : dy;
        float y = -(dy / deltaY) * child.getHeight();
        child.setTranslationY(y);


        /*透明度
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }
        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        float alpha = 1 - (dy / deltaY);
        child.setAlpha(alpha);
        */


        return true;
    }
}
