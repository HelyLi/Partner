package com.hhly.partner.presentation.view.me;

/**
 * 个人item
 * Created by dell on 2017/4/17.
 */

public class MeInfoItem {
    private int mIcon;
    private int mTitle;
    private String mContent;
    private boolean mIsFirstItemOfGroup;
    private Runnable mAction;

    MeInfoItem() {
    }

    MeInfoItem(int icon, int title, String content, boolean isFirstItemOfGroup, Runnable action) {
        mIcon = icon;
        mTitle = title;
        mContent = content;
        mIsFirstItemOfGroup = isFirstItemOfGroup;
        mAction = action;
    }

    private MeInfoItem(Builder builder) {
        setIcon(builder.mIcon);
        setTitle(builder.mTitle);
        setContent(builder.mContent);
        setAction(builder.mAction);
        setFirstItemOfGroup(builder.mIsFirstItemOfGroup);
    }

    public int getTitle() {
        return mTitle;
    }

    public void setTitle(int title) {
        mTitle = title;
    }

    public Runnable getAction() {
        return mAction;
    }

    public void setAction(Runnable action) {
        mAction = action;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }


    public boolean isFirstItemOfGroup() {
        return mIsFirstItemOfGroup;
    }

    public void setFirstItemOfGroup(boolean firstItemOfGroup) {
        mIsFirstItemOfGroup = firstItemOfGroup;
    }

    public static final class Builder {
        private int mIcon;
        private int mTitle;
        private String mContent;
        private boolean mIsFirstItemOfGroup;
        private Runnable mAction;

        public Builder() {
        }

        public Builder icon(int val) {
            mIcon = val;
            return this;
        }

        public Builder title(int val) {
            mTitle = val;
            return this;
        }

        public Builder content(String val) {
            mContent = val;
            return this;
        }

        public Builder action(Runnable val) {
            mAction = val;
            return this;
        }


        public Builder isFirstItemOfGroup(boolean isFirstItemOfGroup) {
            mIsFirstItemOfGroup = isFirstItemOfGroup;
            return this;
        }

        public MeInfoItem build() {
            return new MeInfoItem(this);
        }
    }
}
