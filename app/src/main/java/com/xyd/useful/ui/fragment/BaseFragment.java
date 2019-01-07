package com.xyd.useful.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.uber.autodispose.AutoDisposeConverter;
import com.xyd.useful.R;
import com.xyd.useful.ui.BaseActivity;
import com.xyd.useful.util.RxLifecycleUtils;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    private View mRootView;
    protected Context mAppContext;
    protected Activity mActivity;
    protected LayoutInflater mInflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mAppContext = getActivity().getApplicationContext();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView != null) {
            return mRootView;
        }
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mInflater = getActivity().getLayoutInflater();
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        initBefore();
        ButterKnife.bind(this, mRootView);
        initView();
        return mRootView;
    }

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected void initBefore() {
    }

    @SuppressLint("ResourceType")
    @Nullable
    public final View findViewById(@IdRes int id) {
        if (id < 0) {
            return null;
        }
        return mRootView.findViewById(id);
    }

    @Nullable
    protected View find(@IdRes int id, View.OnClickListener onClickListener) {
        View view = findViewById(id);
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
        return view;
    }

    @Override
    public void startActivity(Intent intent) {
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.activity_stay);
    }

    @Override
    public void startActivityForResult(Intent intent, int code) {
        getActivity().startActivityForResult(intent, code);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.activity_stay);
    }

    /**
     * 是否显示
     */
    public boolean isLoadingPopShowing() {
        if (getActivity().isFinishing()) {
            return false;
        }
        FragmentActivity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            return ((BaseActivity) activity).isLoadingPopShowing();
        }
        return false;
    }

    /**
     * 隐藏加载窗口
     */
    public void dismissLoadingPop() {
        if (null == getActivity() || getActivity().isFinishing()) {
            return;
        }
        final FragmentActivity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity && isLoadingPopShowing()) {
            activity.runOnUiThread(() -> ((BaseActivity) activity).dismissLoadingPop());
        }
    }

    /**
     * 显示加载窗口
     */
    public void showLoadingPop() {
        if (getActivity().isFinishing()) {
            return;
        }
        final FragmentActivity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            activity.runOnUiThread(() -> ((BaseActivity) activity).showLoadingPop("正在查询版本信息"));
        }
    }

    public void showLoadingPop(final String message) {
        if (getActivity().isFinishing()) {
            return;
        }
        final FragmentActivity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity && !isLoadingPopShowing()) {
            activity.runOnUiThread(() -> ((BaseActivity) activity).showLoadingPop(message));
        }
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
    }
}
