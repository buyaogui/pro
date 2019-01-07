package com.xyd.useful.ui.widget.pop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xyd.useful.R;


public class LoadingWindow extends BasePopupWindow {

    public ProgressBar progress;
    public TextView text_message;
    private LoadingWindowParams params;

    public LoadingWindow(LoadingWindowParams params) {
        super(params.context);
        this.params = params;
        setOutsideTouchable(false);
        initLoadingWindow();
    }

    private void initLoadingWindow() {
        View view = LayoutInflater.from(params.context).inflate(R.layout.pop_loading_layout, null);
        setContentView(view);
        progress = (ProgressBar) view.findViewById(R.id.custom_id_pop_progress);
        text_message = (TextView) view.findViewById(R.id.custom_id_pop_text01);
        progress.getIndeterminateDrawable().setColorFilter(params.progressColor != -1 ? params.progressColor : Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
        text_message.setTextColor(params.messageColor != -1 ? params.messageColor : Color.parseColor("#FFFFFF"));
        text_message.setText(params.message != null ? params.message : "加载中...");
        setOutsideTouchable(params.outsideTouchable);
    }

    public void show(View parent) {
        super.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }

    @Override
    public void showAsDropDown(View anchor) {
        throw new ClassCastException("Method call error, call method refresh(), Please ! ");
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        throw new ClassCastException("Method call error, call method refresh(), Please ! ");
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        throw new ClassCastException("Method call error, call method refresh(), Please ! ");
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        throw new ClassCastException("Method call error, call method refresh(), Please ! ");
    }

    public static class Builder {

        private LoadingWindowParams params;

        public Builder(Context context) {
            params = new LoadingWindowParams(context);
        }

        public Builder setOutsizeTouchable(boolean outsizeTouchable) {
            params.outsideTouchable = outsizeTouchable;
            return this;
        }

        public Builder setMessageText(CharSequence message) {
            params.message = message;
            return this;
        }

        public Builder setMessageTextColor(int color) {
            params.messageColor = color;
            return this;
        }

        public Builder setClosable(boolean closable) {
            params.closable = closable;
            return this;
        }

        public Builder setProgressColor(int color) {
            params.progressColor = color;
            return this;
        }

        public LoadingWindow build() {
            return new LoadingWindow(params);
        }
    }

    public void setMessageText(CharSequence message) {
        text_message.setText(message);
    }

    public String getMessageText() {
        return text_message.getText() == null ? "" : text_message.getText().toString();
    }

    private static class LoadingWindowParams {

        public boolean outsideTouchable = true;
        public CharSequence message = null;
        public boolean closable = true;
        public int progressColor = -1;
        public int messageColor = -1;
        public Context context;

        public LoadingWindowParams(Context context) {
            this.context = context;
        }
    }
}
