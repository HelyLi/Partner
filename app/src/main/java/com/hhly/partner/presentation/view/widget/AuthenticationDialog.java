package com.hhly.partner.presentation.view.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.DisplayUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dell on 2017/4/19.
 */

public class AuthenticationDialog extends DialogFragment {
    Unbinder unbinder;
    private OnAuthNowClickListener mOnAuthNowClickListener;

    public interface OnAuthNowClickListener {
        void onClick();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext(), R.style.BottomDialogStyle);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.authentication_dialog, null);
        view.setMinimumWidth(DisplayUtil.getScreenWidth(getContext()) - DisplayUtil.dip2px(getContext(), 30));
        unbinder = ButterKnife.bind(this, view);
        dialog.setContentView(view);
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.auth_now_btn, R.id.auth_later_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.auth_now_btn:
                dismiss();
                if (mOnAuthNowClickListener != null) {
                    mOnAuthNowClickListener.onClick();
                }
                break;
            case R.id.auth_later_btn:
                dismiss();
                break;
        }
    }

    public void setOnAuthNowClickListener(OnAuthNowClickListener onAuthNowClickListener) {
        mOnAuthNowClickListener = onAuthNowClickListener;
    }
}
