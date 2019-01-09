package com.xyd.useful.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.uber.autodispose.AutoDisposeConverter;
import com.xyd.useful.R;
import com.xyd.useful.ui.manager.ActivityCollector;
import com.xyd.useful.ui.widget.pop.LoadingWindow;
import com.xyd.useful.util.RxLifecycleUtils;

import org.greenrobot.eventbus.EventBus;


import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected BaseActivity mActivity;
    protected LayoutInflater mInflater;
    protected LoadingWindow mLoadingWindow;
    protected boolean mIsOnCreateFinish;

    private Unbinder mUnbinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = getApplicationContext();
        mActivity = this;
        mInflater = getLayoutInflater();
        initBefore();
        if (shouldBindEvent()) {
            EventBus.getDefault().register(this);
        }
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        ActivityCollector.addAvtivity(this);
        initView();
    }

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected void initBefore() {
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mIsOnCreateFinish = true;
    }

    /**
     * 是否显示
     */
    public boolean isLoadingPopShowing() {
        return mLoadingWindow != null && mLoadingWindow.isShowing();
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    public void hideBottomUIMenu() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.slide_out_left);
    }

    public void finishDefault() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    public void startActivityDefault(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void startActivityForResultDefault(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(0, 0);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.activity_stay);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in_right, R.anim.activity_stay);
    }

    /**
     * 显示加载窗口
     */
    public void showLoadingPop(String message) {
        if (isFinishing()) {
            return;
        }
        if (!mIsOnCreateFinish) {
            return;
        }
        if (message == null) {
            dismissLoadingPop();
            return;
        }
        if (mLoadingWindow == null) {
            LoadingWindow.Builder builder = new LoadingWindow.Builder(this);
            mLoadingWindow = builder.setOutsizeTouchable(false).setProgressColor(Color.parseColor("#CFFFFFFF"))
                    .setMessageTextColor(Color.parseColor("#CFFFFFFF")).setMessageText(message).build();
        }
        if (!mLoadingWindow.getMessageText().equals(message)) {
            mLoadingWindow.setMessageText(message);
        }
        if (mLoadingWindow.isShowing()) {
            mLoadingWindow.dismiss();
        }
        Window window = getWindow();
        if (window == null) {
            return;
        }
        View decorView = window.getDecorView();
        if (decorView == null) {
            return;
        }
        mLoadingWindow.show(decorView);
    }

    /**
     * 隐藏加载窗口
     */
    public void dismissLoadingPop() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        if (mLoadingWindow != null && mLoadingWindow.isShowing()) {
            mLoadingWindow.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
        ActivityCollector.removeAvtivity(this);
        if (shouldBindEvent()) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected boolean shouldBindEvent() {
        return false;
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
    }
}
