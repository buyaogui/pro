package com.xyd.useful.ui.widget.pop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;

public abstract class BasePopupWindow extends PopupWindow {

    private View maskLayer; //  遮罩层
    private View focusView; //  当前焦点view
    private int mMaskColor = Color.parseColor("#10212121"); //  遮罩层的填充颜色  默认:透明

    private Context mContext; //  上下文关系
    private boolean tweenAnimation; //  是否显示动画
    private boolean mClosable = true; //  窗口是否可以关闭

    private AnimatorSet showAnimator;
    private AnimatorSet dismissAnimator;

    private OnCancelListener mCancelListener;

    private boolean mShowMaskLayout = false;

    private long mDurationFadeIn = 320;  // 遮罩层淡入效果 默认：420ms
    private long mDurationFadeOut = 230;  // 遮罩层淡出效果 默认：230ms

    /**
     * 构造方法
     * */
    public BasePopupWindow(Context context) {
        this(context, true);
    }

    /**
     * 构造方法
     * */
    public BasePopupWindow(Context context, boolean tweenAnimation) {
        this.tweenAnimation = tweenAnimation;
        this.mContext = context;
        initBasePopupWindow();
    }

    /**
     * 初始化BasePopupWindow的一些信息
     * */
    private void initBasePopupWindow() {
        setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        //  注意:上下两者位置不可调换.
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setOutsideTouchable(true);
        setFocusable(true);
    }

    /**
     * 获得动画时长
     * */
    private void obtainAnimationDuration(int animationStyle) {
        try {
            int[] attrs = {android.R.attr.windowEnterAnimation, android.R.attr.windowExitAnimation};
            TypedArray typedArray = getContext().obtainStyledAttributes(animationStyle, attrs);
            if (typedArray == null) {
                return;
            }
            for (int i = 0, count = typedArray.getIndexCount(); i < count; i ++) {
                int resourceId = typedArray.getResourceId(i, 0);
                Animation animation = AnimationUtils.loadAnimation(getContext(), resourceId);
                if (animation == null) {
                    return;
                }
                if (0 == i) {
                    mDurationFadeIn = animation.getDuration();
                    return ;
                }
                if (1 == i) {
                    mDurationFadeOut = animation.getDuration();
                    return ;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否显示动画
     * */
    private boolean showTweenAnimation() {
        return tweenAnimation;
    }

    @Override
    public void setOutsideTouchable(boolean touchable) {
        super.setOutsideTouchable(touchable);
        setupClosable();
    }

    /**
     * 配置触摸事件
     * */
    private void setupClosable() {
        if(getContentView() != null) {
            getContentView().setOnTouchListener(new TouchCloseListener());
        }
    }

    /**
     * 设置遮罩层的颜色
     * */
    protected void setMaskColor(int color) {
        mMaskColor = color;
        if(maskLayer != null) {
            maskLayer.setBackground(new ColorDrawable(color));
        }
    }

    /**
     * 设置窗口能否关闭
     * 慎用！ 设置此参数的窗口除了主动调用dismiss()方法,将不再响应其他方式关闭窗口,包括返回功能键和外部点击事件关闭窗口的功能.
     * */
    public void setClosable(boolean closable) {
        this.mClosable = closable;
        setupClosable();
    }

    /**
     * 判断窗口能否关闭
     * */
    public boolean isClosable() {
        return mClosable;
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        setOutsideTouchable(isOutsideTouchable());
    }

    @Override
    public void setContentView(View contentView) {
        if(contentView == null) {
            return;
        }
        super.setContentView(contentView);
        setupKeyListener(contentView);
        contentView.getViewTreeObserver().addOnGlobalFocusChangeListener(new CustomGlobalListener());
        if(isClosable() && isOutsideTouchable()) {
            contentView.setOnTouchListener(new TouchCloseListener());
        }
    }

    /**
     * 获得上下文关系
     * */
    public Context getContext() {
        return mContext;
    }

    /**
     * 设置上下文关系
     * */
    public void setContext(Context context) {
        this.mContext = context;
    }

    /**
     * 显示动画的准备及开始
     * */
    protected void prepareShow() {
        setupMaskLayer();
        if(showAnimator == null) {
            showAnimator = ShowAnimator();
        }
        if(dismissAnimator != null && dismissAnimator.isRunning()) {
            dismissAnimator.cancel();
        }
        if(showAnimator.isRunning()) {
            return ;
        }
        showAnimator.start();
    }

    /**
     * 隐藏动画的准备及开始
     * */
    protected void prepareDismiss() {
        if (dismissAnimator == null) {
            dismissAnimator = DismissAnimator();
        }
        if (showAnimator != null && showAnimator.isRunning()) {
            showAnimator.cancel();
        }
        if (dismissAnimator.isRunning()) {
            return ;
        }
        dismissAnimator.start();
    }

    /**
     * 配置mask遮罩层.
     * */
    private void setupMaskLayer() {
        maskLayer = new View(getContext());
        maskLayer.setBackground(new ColorDrawable(mMaskColor));
    }

    /**
     * 窗口显示，窗口背景透明度渐变动画
     * */
    private AnimatorSet ShowAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator01 = ObjectAnimator.ofFloat(maskLayer, "alpha", 0.20f, 1.0f);
        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                addMaskLayerIfNecessary();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                maskLayer.setAlpha(1.0f);
            }
        });
        animatorSet.setDuration(mDurationFadeIn);
        animatorSet.playTogether(animator01);
        return animatorSet;
    }

    /**
     * 窗口隐藏，窗口背景透明度渐变动画
     * */
    private AnimatorSet DismissAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator01 = ObjectAnimator.ofFloat(maskLayer, "alpha", 1.0f, 0.20f);
        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeMaskLayerIfNecessary();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                removeMaskLayerIfNecessary();
                maskLayer.setAlpha(0.2f);
            }
        });
        animatorSet.playTogether(animator01);
        animatorSet.setDuration(mDurationFadeOut);
        return animatorSet;
    }

    /**
     * 添加遮罩层如果条件允许的话.
     * */
    private void addMaskLayerIfNecessary() {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new CustomAddMaskLayerHandler());
            return ;
        }
        addMaskLayer();
    }

    /**
     *
     * */
    private class CustomAddMaskLayerHandler implements Runnable {

        @Override
        public void run() {
            addMaskLayer();
        }
    }

    private void addMaskLayer() {
        if(maskLayer == null) {
            return;
        }
        ViewGroup decorView = (ViewGroup)((Activity) getContext()).getWindow().getDecorView();
        if(decorView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        decorView.addView(maskLayer, layoutParams);
        setShowMaskLayout(true);
    }

    private void removeMaskLayerIfNecessary() {
        if(!isShowMaskLayout()) {
            return ;  // 原本未添加maskLayout遮罩的. 不需要进行移除
        }
        if(Looper.myLooper() != Looper.getMainLooper()) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new CustomRemoveMaskLayerHandler());
            return ;
        }
        removeMaskLayer();
    }

    private class CustomRemoveMaskLayerHandler implements Runnable {

        @Override
        public void run() {
            removeMaskLayer();
        }
    }

    private void removeMaskLayer() {
        if(maskLayer == null) {
            return ;
        }
        ViewGroup decorView = (ViewGroup)((Activity) getContext()).getWindow().getDecorView();
        if(decorView == null) {
            return ;
        }
        decorView.removeView(maskLayer);
        setShowMaskLayout(false);
    }

    /**
     * 为窗体配置实体按钮点击事件
     * */
    private void setupKeyListener(View focusView) {
        if(focusView == null) {
            return;
        }
        focusView.setFocusable(true);
        focusView.setFocusableInTouchMode(true);
        focusView.setOnKeyListener(new CustomKeyListener());
    }

    /**
     * 自定义触摸监听事件， 实现点击关闭功能
     * */
    private class TouchCloseListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if(isClosable() && isOutsideTouchable()) {
                    dismiss();
                    if(mCancelListener != null) {
                        mCancelListener.onCancel();
                    }
                }
            }
            return false;
        }
    }

    /**
     * 自定义按键监听事件，实现返回键功能
     * */
    private class CustomKeyListener implements View.OnKeyListener {

        @Override
        public boolean onKey(View view, int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_BACK) {
                if(!isClosable() && focusView != null && focusView instanceof EditText) {
                    hideKeyboard();
                    return true;
                }
                if(isClosable()) {
                    dismiss();
                    if(mCancelListener != null) {
                        mCancelListener.onCancel();
                    }
                }
                return true;
            }
            return false;
        }
    }

    /**
     * 为视图中的View添加点击事件
     * */
    public void addOnClickCallBack(int resId, View.OnClickListener onClickListener) {
        if(getContentView() == null) {
            return;
        }
        View view = getContentView().findViewById(resId);
        if(view == null) {
            return;
        }
        view.setOnClickListener(onClickListener);
    }

    /**
     * 隐藏键盘keyboard
     * */
    protected void hideKeyboard() {
        ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(focusView.getWindowToken(), 0);
//        getContentView().setFocusableInTouchMode(true);
//        getContentView().setFocusable(true);
        getContentView().requestFocus(); // 让contentView获得焦点.
    }

    private class CustomGlobalListener implements ViewTreeObserver.OnGlobalFocusChangeListener {

        @Override
        public void onGlobalFocusChanged(View oldFocus, View newFocus) {
            setupKeyListener(newFocus);
            focusView = newFocus;
        }
    }

    public interface OnCancelListener {
        void onCancel();
    }

    public void setOnCancelListener(OnCancelListener cancelListener) {
        this.mCancelListener = cancelListener;
    }

    private boolean isShowMaskLayout() {
        return mShowMaskLayout;
    }

    private void setShowMaskLayout(boolean showMaskLayout) {
        this.mShowMaskLayout = showMaskLayout;
    }
}
