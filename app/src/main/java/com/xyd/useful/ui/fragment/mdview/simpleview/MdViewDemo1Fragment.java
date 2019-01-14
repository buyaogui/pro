package com.xyd.useful.ui.fragment.mdview.simpleview;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyd.useful.R;
import com.xyd.useful.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MdViewDemo1Fragment extends BaseFragment {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.input_effect1)
    TextInputLayout inputEffect1;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.input_effect2)
    TextInputLayout inputEffect2;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;

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

    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void initView() {
        inputEffect1.setHint("请输入用户名");
        inputEffect1.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence content, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence content, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable content) {
                if (content.length() > 3) {
                    //一定要在setError方法之后执行才可
                    inputEffect1.setError("Account error");
                    inputEffect1.setErrorEnabled(true);

                } else {
                    //不满足条件需要设置为false
                    inputEffect1.setErrorEnabled(false);
                }
            }
        });
        inputEffect2.setHint("请输入密码");

        bottomSheetBehavior = BottomSheetBehavior.from(llBottom);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cardview_demo1;
    }

    @OnClick({R.id.card_view, R.id.bottom_behavior_btn, R.id.btnBottomSheetDialogFragment, R.id.btnBottomSheetDialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_view:
//              Toast.makeText(getContext(), "CardView", Toast.LENGTH_SHORT).show();
                Snackbar.make(cardView, "CardView", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.bottom_behavior_btn:
                OpenBottomBehavior();
                break;
            case R.id.btnBottomSheetDialogFragment:
                OpenBottomSheetDialogFragment();
                break;
            case R.id.btnBottomSheetDialog:
                OpenBottomSheetDialog();
                break;
        }
    }

    public void OpenBottomBehavior() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void OpenBottomSheetDialogFragment() {
        MyBottomSheetDialogFragment dialogFragment = new MyBottomSheetDialogFragment();
        dialogFragment.show(getFragmentManager(), "MyBottomSheetDialogFragment");
    }

    public void OpenBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.dialog_bottom);
        bottomSheetDialog.show();
    }
}
