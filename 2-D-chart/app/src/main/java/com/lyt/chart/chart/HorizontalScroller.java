package com.lyt.chart.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

public class HorizontalScroller extends HorizontalScrollView {

    public interface HorizontalScrollViewListener {
        void onHorizontalScroll(HorizontalScroller scrollView, int x, int y, int oldx, int oldy);
    }

    private HorizontalScrollViewListener mHorizontalScrollViewListener = null;
    View mBindScrollView;

    public HorizontalScroller(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public HorizontalScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public HorizontalScroller(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void setBindScrollView(View view) {
        mBindScrollView = view;
    }

    public void setHorizontalScrollViewListener(HorizontalScrollViewListener horizontalScrollViewListener) {
        this.mHorizontalScrollViewListener = horizontalScrollViewListener;
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mBindScrollView != null) {
            mBindScrollView.scrollTo(l, t);
        }
        mHorizontalScrollViewListener.onHorizontalScroll(this, l, t, oldl, oldt);
    }

}
