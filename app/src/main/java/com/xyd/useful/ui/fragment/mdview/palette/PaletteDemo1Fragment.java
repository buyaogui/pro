package com.xyd.useful.ui.fragment.mdview.palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PaletteDemo1Fragment extends BaseFragment {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tvVibrantColor)
    TextView tvVibrantColor;
    @BindView(R.id.tvMutedColor)
    TextView tvMutedColor;
    @BindView(R.id.tvDarkVibrantColor)
    TextView tvDarkVibrantColor;
    @BindView(R.id.tvDarkMutedColor)
    TextView tvDarkMutedColor;
    @BindView(R.id.tvLightVibrantColor)
    TextView tvLightVibrantColor;
    @BindView(R.id.tvLightMutedColor)
    TextView tvLightMutedColor;

    private Bitmap bitmap;

    @Override
    protected void initView() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fj);
        imageView.setImageBitmap(bitmap);
        getPaletteColor();
    }

    private void getPaletteColor() {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // 获取图片中最活跃的颜色（也可以说整个图片出现最多的颜色）（可传默认值）
                int vibrantColor = palette.getVibrantColor(Color.BLUE);
                // 获取图片中一个最柔和的颜色（可传默认值）
                int mutedColor = palette.getMutedColor(Color.BLUE);
                // 获取到活跃的深色的颜色（可传默认值）
                int darkVibrantColor = palette.getDarkVibrantColor(Color.BLUE);
                // 获取到柔和的深色的颜色（可传默认值）
                int darkMutedColor = palette.getDarkMutedColor(Color.BLUE);
                // 获取到活跃的明亮的颜色（可传默认值）
                int lightVibrantColor = palette.getLightVibrantColor(Color.BLUE);
                // 获取到柔和的明亮的颜色（可传默认值）
                int lightMutedColor = palette.getLightMutedColor(Color.BLUE);

                tvVibrantColor.setBackgroundColor(vibrantColor);
                tvMutedColor.setBackgroundColor(mutedColor);
                tvDarkVibrantColor.setBackgroundColor(darkVibrantColor);
                tvDarkMutedColor.setBackgroundColor(darkMutedColor);
                tvLightVibrantColor.setBackgroundColor(lightVibrantColor);
                tvLightMutedColor.setBackgroundColor(lightMutedColor);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_palette_demo1;
    }

}
