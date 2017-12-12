package com.hhly.partner.presentation.view.chart;

import com.github.mikephil.charting.data.Entry;

/**
 * description :
 * Created by Flynn
 * 2017/4/21
 */

public class CustomLineEntry extends Entry {

    private String mDate;
    private String mTitle;

    public CustomLineEntry(float x, float y, String date,String title) {
        super(x, y);
        mDate = date;
        mTitle = title;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }


}
