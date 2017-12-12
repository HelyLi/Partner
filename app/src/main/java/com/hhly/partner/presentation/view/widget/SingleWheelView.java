package com.hhly.partner.presentation.view.widget;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.presentation.view.widget.wheelview.OnWheelChangedListener;
import com.hhly.partner.presentation.view.widget.wheelview.WheelView;
import com.hhly.partner.presentation.view.widget.wheelview.adapters.ArrayWheelAdapter;
import com.hhly.partner.presentation.view.widget.wheelview.adapters.WheelViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dell on 2017/4/18.
 */

public class SingleWheelView extends BottomSheetDialogFragment implements OnWheelChangedListener {
    @BindView(R.id.wheel_view)
    WheelView mWheelView;
    @BindView(R.id.confirm_tv)
    TextView mConfirmTv;
    @BindView(R.id.cancel_tv)
    TextView mCancelTv;
    Unbinder unbinder;
    private OnConfrimClickLisenter mConfrimClickLisenter;
    private int curIndex;
    private String[] datas;
    private WheelViewAdapter mWheelViewAdapter;


    public interface OnConfrimClickLisenter {
        void onConfirmClicked(String s);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.single_wheel_view, null);
        unbinder = ButterKnife.bind(this, contentView);
        mWheelViewAdapter = new ArrayWheelAdapter<>(getActivity(), datas != null ? datas : new String[]{});
        mWheelView.setViewAdapter(mWheelViewAdapter);
        mWheelView.addChangingListener(this);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(Color.TRANSPARENT);
        return dialog;
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        curIndex = newValue;
    }

    @OnClick(R.id.confirm_tv)
    public void onConfirmClicked(View v) {
        if (mConfrimClickLisenter != null) {
            mConfrimClickLisenter.onConfirmClicked(datas[curIndex]);
        }
        dismiss();
    }

    @OnClick(R.id.cancel_tv)
    public void onCancelClicked(View v) {
        dismiss();
    }

    public void setConfrimClickLisenter(OnConfrimClickLisenter confrimClickLisenter) {
        mConfrimClickLisenter = confrimClickLisenter;
    }

    public void setDatas(String[] datas) {
        this.datas = datas;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
