package com.xyd.useful.ui.fragment.mdview.cardview;

import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.BaseFragment;

public class CardViewDemo1Fragment extends BaseFragment {

    /*
    CardView属性
    app:cardElevation 阴影的高度
    app:cardMaxElevation 阴影最大高度
    app:cardBackgroundColor 卡片的背景色
    app:cardCornerRadius 卡片的圆角大小
    app:contentPadding 卡片内容于边距的间隔
    app:contentPaddingBottom
    app:contentPaddingTop
    app:contentPaddingLeft
    app:contentPaddingRight
    app:contentPaddingStart
    app:contentPaddingEnd
    app:cardUseCompatPadding 设置内边距，V21+的版本和之前的版本仍旧具有一样的计算方式
    app:cardPreventConrerOverlap 在V20和之前的版本中添加内边距，这个属性为了防止内容和边角的重叠
     */

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cardview_demo1;
    }
}
