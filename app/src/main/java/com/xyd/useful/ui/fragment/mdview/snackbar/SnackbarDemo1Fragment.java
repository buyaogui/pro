package com.xyd.useful.ui.fragment.mdview.snackbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xyd.useful.R;
import com.xyd.useful.ui.activity.MainActivity;
import com.xyd.useful.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SnackbarDemo1Fragment extends BaseFragment {


    @BindView(R.id.rbOne)
    RadioButton rbOne;
    @BindView(R.id.rbTwo)
    RadioButton rbTwo;
    @BindView(R.id.rbThree)
    RadioButton rbThree;
    @BindView(R.id.rbFour)
    RadioButton rbFour;
    @BindView(R.id.rbFive)
    RadioButton rbFive;
    @BindView(R.id.rbGroup)
    RadioGroup rbGroup;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_snackbar_demo1;
    }

    @OnClick({R.id.rbOne, R.id.rbTwo, R.id.rbThree, R.id.rbFour, R.id.rbFive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbOne:
                Snackbar.make(button, "第一次使用SnackBar", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.rbTwo:
                Snackbar.make(button, "第一次使用SnackBar", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                break;
            case R.id.rbThree:
                Snackbar.make(button, "第一次使用SnackBar", Snackbar.LENGTH_INDEFINITE).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                break;
            case R.id.rbFour:
                Snackbar.make(button, "第一次使用SnackBar", Snackbar.LENGTH_INDEFINITE).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).addCallback(new Snackbar.Callback() {
                    @Override
                    public void onShown(Snackbar sb) {
                        super.onShown(sb);
                        Toast.makeText(getContext(), "弹出了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        Toast.makeText(getContext(), "消失了", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.rbFive:
                Snackbar snackbar = Snackbar.make(button, "第一次使用SnackBar", Snackbar.LENGTH_INDEFINITE).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                snackbar.setActionTextColor(Color.GREEN);
                snackbar.getView().setBackgroundResource(R.color.colorPrimaryDark);
                /* snackbar 并没有提供修改提示文字颜色的方法。不过可以通过找到snackbar的布局design_layout_snackbar_include
                   通过布局可以找到textview的id。
                   在通过snackbar.getView().findViewById(R.id.snackbar_text);
                 */
                TextView textView = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
                textView.setTextColor(getResources().getColor(R.color.colorAccent));
                snackbar.show();
                break;

        }
    }
}
