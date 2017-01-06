package com.lyt.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class VerticalScroller extends ScrollView {
    public interface ScrollViewListener {

        void onScrollChanged(VerticalScroller scrollView, int x, int y, int oldx, int oldy);

    }

    private ScrollViewListener scrollViewListener = null;

    public VerticalScroller(Context context) {
        super(context);
    }

    public VerticalScroller(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    public VerticalScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
