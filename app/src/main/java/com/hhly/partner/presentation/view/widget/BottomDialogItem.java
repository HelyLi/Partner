package com.hhly.partner.presentation.view.widget;

/**
 * description :
 * Created by Flynn
 * 2017/4/27
 */

public class BottomDialogItem {

    private String name;
    private String value;
    private boolean isSelected;

    public BottomDialogItem(String name, String value, boolean isSelected) {
        this.name = name;
        this.value = value;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
