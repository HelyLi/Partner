package com.hhly.partner.presentation.view.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhly.partner.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Simon on 2016/11/19.
 */

public class BottomBar extends LinearLayout implements View.OnClickListener {

    private static final String STATE_CURRENT_SELECTED_TAB = "STATE_CURRENT_SELECTED_TAB";

    private int mCurrentTabPosition = 0;

    private int mIndex;

    private OnTabSelectListener mTabSelectListener;

    public BottomBar(Context context) {
        super(context);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addTab(int id, int tabIconResId, int tabTextResId, @Tab.NotifyType int notifyType) {
        Tab tab = new Tab.Builder().context(getContext()).tabIconResId(tabIconResId).tabTextResId(tabTextResId).tabNotifyType(notifyType).build();
        tab.setId(id);
        tab.setIndexInContainer(mIndex++);
        tab.setOnClickListener(this);
        addView(tab, new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
    }

    public void addTab(int id, int tabIconResId, int tabTextResId) {
        addTab(id, tabIconResId, tabTextResId, Tab.NOTIFY_NONE);
    }

    public void setTabSelectListener(OnTabSelectListener listener) {
        mTabSelectListener = listener;
    }

    @Override
    public void removeAllViews() {
        super.removeAllViews();
        mIndex = 0;
    }

    @Override
    public void onClick(View v) {
        handleClick(v);
    }

    private void handleClick(View view) {
        Tab oldTab = getCurrentTab();
        Tab currentTab = (Tab) view;
        if (oldTab != currentTab) {
            oldTab.deselect();
            currentTab.select();
        }

        updateSelectTab(currentTab.getIndexInContainer());
    }

    private void updateSelectTab(int newPosition) {
        int newTabId = getTabAtPosition(newPosition).getId();

        if (mCurrentTabPosition != newPosition) {
            //select callback
            if (mTabSelectListener != null) {
                mTabSelectListener.onTabSelected(newTabId);
            }
        }

        mCurrentTabPosition = newPosition;
    }

    public Tab getCurrentTab() {
        return getTabAtPosition(getCurrentTabPosition());
    }

    public int getCurrentTabPosition() {
        return mCurrentTabPosition;
    }

    public Tab getTabWithId(int tabId) {
        return (Tab) findViewById(tabId);
    }

    public Tab getTabAtPosition(int position) {
        View view = getChildAt(position);
        if (view != null && view instanceof Tab) {
            return (Tab) view;
        }
        throw new IllegalStateException("Cannot found tab");
    }

    public interface OnTabSelectListener {
        void onTabSelected(int tabId);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = saveState();
        bundle.putParcelable("superstate", super.onSaveInstanceState());
        return bundle;
    }

    Bundle saveState() {
        Bundle outState = new Bundle();
        outState.putInt(STATE_CURRENT_SELECTED_TAB, mCurrentTabPosition);

        return outState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            restoreState(bundle);

            state = bundle.getParcelable("superstate");
        }
        super.onRestoreInstanceState(state);
    }

    void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {

            int restoredPosition = savedInstanceState.getInt(STATE_CURRENT_SELECTED_TAB, mCurrentTabPosition);
            selectTabAtPosition(restoredPosition);
        }
    }

    public void selectTabAtPosition(int position) {
        if (position > getChildCount() - 1 || position < 0) {
            throw new IndexOutOfBoundsException("Can't select tab at position " +
                    position + ". This BottomBar has no items at that position.");
        }

        Tab oldTab = getCurrentTab();
        Tab newTab = getTabAtPosition(position);
        oldTab.deselect();
        newTab.select();

        updateSelectTab(newTab.getIndexInContainer());

    }

    public void selectTabWithId(int tabId) {
        int tabPosition = findPositionForTabWithId(tabId);
        selectTabAtPosition(tabPosition);
    }

    public int findPositionForTabWithId(int tabId) {
        return getTabWithId(tabId).getIndexInContainer();
    }


    public static class Tab extends RelativeLayout {

        private ImageView tabIcon;
        private TextView tabText;
//        private TextView tabNotify;

        private boolean isEnableNotify = false;

        private int mIndexInContainer = 0;

        private AtomicInteger mCount = new AtomicInteger(0);

        public static final int NOTIFY_NONE = 0;
        public static final int NOTIFY_NUM = 1;
        public static final int NOTIFY_POINT = 2;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef(value = {NOTIFY_NONE, NOTIFY_NUM, NOTIFY_POINT})
        public @interface NotifyType{}

        private int mNotifyType;

        private int mNotifyDefaultWidth = 0;
        private int mNotifyDefaultHeight = 0;


        Tab(Context context) {
            super(context);
        }

        void config(Builder builder) {
            prepareLayout();
            mNotifyType = builder.tabNotifyType;
            mNotifyDefaultWidth = getResources().getDimensionPixelSize(R.dimen.tab_notify_width);
            mNotifyDefaultHeight = getResources().getDimensionPixelSize(R.dimen.tab_notify_height);
            tabIcon.setImageResource(builder.tabIconResId);
            tabText.setText(builder.tabTextResId);

//            updateTab();
        }

        void prepareLayout() {
            inflate(getContext(), R.layout.main_tab_item, this);
            setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            tabIcon = (ImageView) findViewById(R.id.tab_icon);
            tabText = (TextView) findViewById(R.id.tab_text);
//            tabNotify = (TextView) findViewById(R.id.tab_notify_text);

        }

//        void updateTab() {
//            updateNotify();
//        }
//
//        void updateNotify() {
//            if (isEnableNotify) {
//                if (mCount.get() <= 0) {
//                    mCount.set(0);
//                    tabNotify.setVisibility(GONE);
//                } else {
//                    String notifyString;
//                    if (mCount.get() > 99) {
//                        notifyString = "99+";
//                    } else {
//                        notifyString = String.valueOf(mCount);
//                    }
//                    LayoutParams lp = (LayoutParams) tabNotify.getLayoutParams();
//                    lp.width = LayoutParams.WRAP_CONTENT;
//                    lp.height = LayoutParams.WRAP_CONTENT;
//                    tabNotify.setLayoutParams(lp);
//                    tabNotify.setText(notifyString);
//                    tabNotify.setVisibility(VISIBLE);
//                }
//            } else {
//                tabNotify.setVisibility(GONE);
//            }
//            switch (mNotifyType) {
//                case NOTIFY_NONE:
//                    tabNotify.setVisibility(GONE);
//                    break;
//                case NOTIFY_NUM:
//                    if (mCount.get() <= 0) {
//                        mCount.set(0);
//                        tabNotify.setVisibility(GONE);
//                    } else {
//                        String notifyString;
//                        if (mCount.get() > 99) {
//                            notifyString = "99+";
//                        } else {
//                            notifyString = String.valueOf(mCount);
//                        }
//                        LayoutParams lp = (LayoutParams) tabNotify.getLayoutParams();
//                        lp.width = LayoutParams.WRAP_CONTENT;
//                        lp.height = LayoutParams.WRAP_CONTENT;
//                        tabNotify.setLayoutParams(lp);
//                        tabNotify.setText(notifyString);
//                        tabNotify.setVisibility(VISIBLE);
//                    }
//                    break;
//                case NOTIFY_POINT:
//                    if (mCount.get() > 0) {
//                        LayoutParams lp = (LayoutParams) tabNotify.getLayoutParams();
//                        lp.width = mNotifyDefaultWidth;
//                        lp.height = mNotifyDefaultHeight;
//                        tabNotify.setLayoutParams(lp);
//                        tabNotify.setText("");
//                        tabNotify.setVisibility(VISIBLE);
//                    } else {
//                        tabNotify.setVisibility(GONE);
//                    }
//                    break;
//            }
//        }

//        public void setNotifyType(@NotifyType int notifyType) {
//            mNotifyType = notifyType;
//            post(new Runnable() {
//                @Override
//                public void run() {
//                    updateNotify();
//                }
//            });
//        }
//
//        public void setCount(int count) {
//            mCount.set(count);
//            post(new Runnable() {
//                @Override
//                public void run() {
//                    updateNotify();
//                }
//            });
//        }
//
//        public void incrementCount(int count) {
//            mCount.addAndGet(count);
//            post(new Runnable() {
//                @Override
//                public void run() {
//                    updateNotify();
//                }
//            });
//        }
//
//        public void increment() {
//            mCount.incrementAndGet();
//            post(new Runnable() {
//                @Override
//                public void run() {
//                    updateNotify();
//                }
//            });
//        }
//
//        public void decrement() {
//            mCount.set(Math.max(mCount.decrementAndGet(), 0));
//            post(new Runnable() {
//                @Override
//                public void run() {
//                    updateNotify();
//                }
//            });
//        }

        public void setImageUrl(String url) {
            Glide.with(getContext()).load(url).asBitmap().into(tabIcon);
        }

        public void setImageResource(@DrawableRes int resId) {
            tabIcon.setImageResource(resId);
        }

        public void setTabText(String tabText) {
            this.tabText.setText(tabText);
        }

        void select() {
            setSelected(true);
        }

        void deselect() {
            setSelected(false);
        }

        public int getIndexInContainer() {
            return mIndexInContainer;
        }

        public void setIndexInContainer(int indexInContainer) {
            mIndexInContainer = indexInContainer;
        }

        public static class Builder {
            private int tabIconResId;
            private int tabTextResId;
            private int tabNotifyType;
            private Context context;

            Builder() {

            }

            public Builder context(Context context) {
                this.context = context;
                return this;
            }

            public Builder tabIconResId(int tabIconResId) {
                this.tabIconResId = tabIconResId;
                return this;
            }

            public Builder tabTextResId(int tabTextResId) {
                this.tabTextResId = tabTextResId;
                return this;
            }

            public Builder tabNotifyType(int tabNotifyType) {
                this.tabNotifyType = tabNotifyType;
                return this;
            }

            public Tab build() {
                Tab tab = new Tab(context);
                tab.config(this);
                return tab;
            }


        }

    }

}
