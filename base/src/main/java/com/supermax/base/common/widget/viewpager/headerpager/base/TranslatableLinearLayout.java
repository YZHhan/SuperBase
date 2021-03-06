package com.supermax.base.common.widget.viewpager.headerpager.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.supermax.base.common.widget.viewpager.headerpager.HeaderViewPager;

/*
 * @Author yinzh
 * @Date   2018/10/18 09:36
 * @Description
 */
public class TranslatableLinearLayout extends LinearLayout{
    public static final String TAG = "TranslatableLinearLayout";
    private HeaderViewPager mHeaderViewPager;

    public TranslatableLinearLayout(Context context) {
        super(context);
    }

    public TranslatableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB) public TranslatableLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
    }

    private void initView() {
        if (getParent() != null) {
            mHeaderViewPager = (HeaderViewPager) getParent();
        }
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mHeaderViewPager != null && mHeaderViewPager.isHeaderTallerThanScreen()) {
            heightMeasureSpec = MeasureSpec.UNSPECIFIED;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private boolean mProcessTouchEvent;

    /**
     * 对控件内的触摸进行分发控制，让它只处理看起来的边界内的事件
     * （control the touch event in this view, to let it
     * just deal with event located in area where this looks）
     */
    @Override public boolean dispatchTouchEvent(MotionEvent ev) {
        int visualBottom = getVisualBottom();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (ev.getY() < visualBottom && !mHeaderViewPager.intercept2InnerScroller) {
                    mProcessTouchEvent = true;
                    return super.dispatchTouchEvent(ev);
                } else {
                    mProcessTouchEvent = false;
                    return false;
                }
            case MotionEvent.ACTION_MOVE:
                if (mProcessTouchEvent) {
                    if (!mHeaderViewPager.intercept2InnerScroller) {
                        return super.dispatchTouchEvent(ev);
                    } else {
                        mProcessTouchEvent = false;
                        return false;
                    }
                } else {
                    return false;
                }
            case MotionEvent.ACTION_UP:
                return mProcessTouchEvent && !mHeaderViewPager.intercept2InnerScroller && super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_CANCEL:
                if (mProcessTouchEvent) {
                    mProcessTouchEvent = false;
                }
                return super.dispatchTouchEvent(ev);
            default:
                return false;
        }
    }

    public int getVisualBottom() {
        return getBottom() - getScrollY();
    }
}
