package com.xyd.useful.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.FourFragment;
import com.xyd.useful.ui.fragment.OneFragment;
import com.xyd.useful.ui.fragment.ThreeFragment;
import com.xyd.useful.ui.fragment.TwoFragment;

import java.util.List;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private List<String> mTitles;
    private List<Fragment> mFragments;
    private List<Integer>imageRes;

    public MyPagerAdapter(Context context, FragmentManager fm, List<String> mTitles,
                          List<Fragment> fragments, List<Integer> imageRes) {
        super(fm);
        this.context = context;
        this.mTitles = mTitles;
        this.mFragments = fragments;
        this.imageRes = imageRes;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    /**
     * 定义一个方法，来返回Tab的内容
     */
    public View getTabView(int position){
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView iconView = (ImageView) view.findViewById(R.id.iv_icon);
        iconView.setImageResource(imageRes.get(position % imageRes.size()));
        TextView textview = (TextView) view.findViewById(R.id.tab_textview);
        textview.setText(mTitles.get(position % mTitles.size()));
        return view;
    }

}
