package com.hhly.partner.presentation.view.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.DisplayUtil;

/**
 * Created by dell on 2017/4/19.
 */

public class PayPasswordDialog extends DialogFragment {
    Unbinder unbinder;
    private OnPayPwdSettingClickListener mOnAuthNowClickListener;

    public interface OnPayPwdSettingClickListener {
        void onClick();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext(), R.style.BottomDialogStyle);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pay_password_dialog, null);
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

    @OnClick({R.id.pwd_setting, R.id.cancel_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pwd_setting:
                dismiss();
                if (mOnAuthNowClickListener != null) {
                    mOnAuthNowClickListener.onClick();
                }
                break;
            case R.id.cancel_btn:
                dismiss();
                break;
        }
    }

    public void setOnPayPwdSettingClickListener(OnPayPwdSettingClickListener onAuthNowClickListener) {
        mOnAuthNowClickListener = onAuthNowClickListener;
    }
}
