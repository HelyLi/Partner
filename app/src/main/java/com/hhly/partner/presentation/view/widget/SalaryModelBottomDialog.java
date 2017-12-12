package com.hhly.partner.presentation.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hhly.partner.R;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/27
 */

public class SalaryModelBottomDialog {

    private final Context context;
    private final Display display;
    private LinearLayout lLayout_content;
    private Dialog dialog;
    private OnBottomItemClickListener mListener;

    public SalaryModelBottomDialog(Context context, OnBottomItemClickListener onBottomItemClickListener) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        mListener = onBottomItemClickListener;
    }

    public SalaryModelBottomDialog builder() {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_dialog, null);
        view.setMinimumWidth(display.getWidth());
        lLayout_content = (LinearLayout) view.findViewById(R.id.content_container);
        dialog = new Dialog(context, R.style.BottomDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        return this;
    }

    public void setBottomItem(List<BottomDialogItem> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        lLayout_content.removeAllViews();
        int size = list.size();

        for (int i = 0; i < size; i++) {
            final int index = i;
            final BottomDialogItem item = list.get(i);
            String strItem = item.getName();

            TextView textView = new TextView(context);
            textView.setText(strItem);
            textView.setTextSize(18);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.color.color_f5f5f5);
            textView.setTextColor(context.getResources().getColorStateList(R.color.bottom_dialog_selector));
            textView.setSelected(item.isSelected());
            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int) (45 * scale + 0.5f);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(item.getValue());
                    dialog.dismiss();
                }
            });
            lLayout_content.addView(textView);
        }
    }

    public void show() {
        dialog.show();
    }

    public interface OnBottomItemClickListener {
        void onClick(String value);
    }

}
