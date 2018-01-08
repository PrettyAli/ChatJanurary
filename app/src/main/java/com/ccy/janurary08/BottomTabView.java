package com.ccy.janurary08;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by chanchaoyue on 2018/1/7.
 */

public class BottomTabView extends LinearLayout {

    public static final int ICON_INDEX_NORMAL = 0;
    public static final int ICON_INDEX_SELECTED = 1;
    public static final int DEFALUT_SELECTED_ITEM = 0;
    public static final String COLOR_TEXT_NORMAL = "#FF999999";
    public static final String COLOR_TEXT_SELECTED = "#FF46C01B";
    //文字颜色渐变类
    private ArgbEvaluator mColorEvaluator;
    //正常文本的颜色
    private int mTextNormalColor;
    //选中时文本的颜色
    private int mTextSelectedColor;
    //最后的位置
    private int mLastPosition;
    //选中的位置
    private int mSelectedPosition;
    //选择的偏移量
    private float mSelectionOffset;
    //文字
    private String tabTitles[] = {"微信", "通讯录", "发现", "我"};
    //图标
    private int mIconRes[][] = {
            {R.drawable.icon_main_home_normal, R.drawable.icon_main_home_selected},
            {R.drawable.icon_main_category_normal, R.drawable.icon_main_category_selected},
            {R.drawable.icon_main_service_normal, R.drawable.icon_main_service_selected},
            {R.drawable.icon_main_mine_normal, R.drawable.icon_main_mine_selected}};
    //Item数组
    private View[] mItemLayout;
    //关联的ViewPager
    private ViewPager mViewPager;

    public BottomTabView(Context context) {
        this(context, null);
    }

    public BottomTabView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomTabView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //实例化颜色渐变类
        mColorEvaluator = new ArgbEvaluator();
        //选中和未选中的文本的颜色
        mTextNormalColor = Color.parseColor(COLOR_TEXT_NORMAL);
        mTextSelectedColor = Color.parseColor(COLOR_TEXT_SELECTED);
    }

    public void setViewPager(ViewPager viewPager) {
        //清空所有的view
        removeAllViews();

        mViewPager = viewPager;
        //viewapger数据不为空
        if (viewPager != null && viewPager.getAdapter() != null) {
            //设置监听
            viewPager.addOnPageChangeListener(new ViewPagerListener());
            try {
                populateTabLayout();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void populateTabLayout() throws Exception {
        final PagerAdapter pagerAdapter = mViewPager.getAdapter();
        final OnClickListener tabClickListener = new TabClickListener();
        //根据adapter中item的数量生成数组
        mItemLayout = new View[pagerAdapter.getCount()];
        //遍历
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            final View tabView = LayoutInflater.from(getContext()).inflate(R.layout.layout_tab_item, this, false);
            if (tabView == null) {
                throw new IllegalStateException("tabView is null.");
            }
            mItemLayout[i] = tabView;
            BottomIconView bottomIconView = tabView.findViewById(R.id.bottom_icon);
            bottomIconView.init(mIconRes[i][ICON_INDEX_NORMAL], mIconRes[i][ICON_INDEX_SELECTED]);
            TextView textView = tabView.findViewById(R.id.bottom_text);
            textView.setText(tabTitles[i]);
            //改变宽度和权重item平分屏幕
            LayoutParams layoutParams = (LayoutParams) tabView.getLayoutParams();
            layoutParams.width = 0;
            layoutParams.weight = 1;
            tabView.setOnClickListener(tabClickListener);
            addView(tabView);
            if (i == mViewPager.getCurrentItem()) {
                bottomIconView.transformPage(DEFALUT_SELECTED_ITEM);
                tabView.setSelected(true);
                textView.setTextColor(mTextSelectedColor);
            }
        }
    }

    private class ViewPagerListener implements ViewPager.OnPageChangeListener {

        //状态
        private int mScrollState;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            onViewPagerPageChanged(position, positionOffset);
        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < getChildCount(); i++) {
                BottomIconView bottomIconView = mItemLayout[i].findViewById(R.id.bottom_icon);
                bottomIconView.transformPage(position == i ? 0 : 1);
                TextView textView = mItemLayout[i].findViewById(R.id.bottom_text);
                textView.setTextColor(position == i ? mTextSelectedColor : mTextNormalColor);
            }
            if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                onViewPagerPageChanged(position, 0f);
            }
            for (int i = 0, size = getChildCount(); i < size; i++) {
                getChildAt(i).setSelected(position == i);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;
        }
    }

    /**
     * ViewPager的item改变
     *
     * @param position
     * @param positionOffset
     */
    private void onViewPagerPageChanged(int position, float positionOffset) {
        mSelectedPosition = position;
        mSelectionOffset = positionOffset;
        if (positionOffset == 0f && mLastPosition != mSelectedPosition) {
            mLastPosition = mSelectedPosition;
        }
        invalidate();
    }

    /* 绘制方法
     *
     * @param canvas
     */
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int childCount = getChildCount();
        if (childCount > 0) {
            if (mSelectionOffset > 0f && mSelectedPosition < (getChildCount() - 1)) {
                View selectedTab = getChildAt(mSelectedPosition);
                View nextTab = getChildAt(mSelectedPosition + 1);
                View selectedIconView = getChildAt(0);
                View nextIconView = getChildAt(0);
                View selectedTextView = getChildAt(1);
                View nextTextView = getChildAt(1);
                if (selectedIconView instanceof BottomIconView && nextIconView instanceof BottomIconView) {
                    ((BottomIconView) selectedIconView).transformPage(mSelectionOffset);
                    ((BottomIconView) nextIconView).transformPage(1 - mSelectionOffset);
                }
                /**
                 * 使用ArgbEvaluator类来控制文本的颜色渐变
                 */
                //draw text color
                Integer selectedColor = (Integer) mColorEvaluator.evaluate(mSelectionOffset,
                        mTextSelectedColor,
                        mTextNormalColor);

                Integer nextColor = (Integer) mColorEvaluator.evaluate(1 - mSelectionOffset,
                        mTextSelectedColor,
                        mTextNormalColor);
                if (selectedTextView instanceof TextView && nextIconView instanceof TextView) {
                    ((TextView) selectedTextView).setTextColor(selectedColor);
                    ((TextView) nextTextView).setTextColor(nextColor);
                }
            }
        }
    }

    /**
     * Tab的Item点击
     */
    private class TabClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < getChildCount(); i++) {
                if (v == getChildAt(i)) {
                    mViewPager.setCurrentItem(i, false);
                    return;
                }
            }
        }
    }
}
