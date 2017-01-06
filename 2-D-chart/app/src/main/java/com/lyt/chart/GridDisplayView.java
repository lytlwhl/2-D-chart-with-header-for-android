package com.lyt.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;

public class GridDisplayView extends View {

    private static String TAG = "RealTimeDataDisplayView";
    private static int mTextSizeWidth = 0;
    private static int mTextSizeHeight = 0;

    private static int mPaintStrokeWidth = 3;
    private static int mPaintTextSize = 30;

    private static int mPaintRectColor = Color.WHITE;
    private static int mPaintTextColor = Color.GRAY;

    private Context mContext;

    private int mTextRowCount = 0;
    private int mTextColCount = 0;

    private int mHeight = 0;
    private int mWidth = 0;

    private int mStartX = 0;
    private int mStartY = 0;

    private int mType = 0;
    public static final int REAL_TIME_DATA_TYPE = 1;
    public static final int GRID_HEAD_TYPE = 2;

    private ArrayList<Short> mDataList;

    public GridDisplayView(Context context, int type, int TextSizeWidth, int TextSizeHeight) {
        super(context);
        this.mContext = context;
        this.mType = type;
        mTextSizeWidth = TextSizeWidth;
        mTextSizeHeight = TextSizeHeight;

    }

    public void setRowColCount(int mTextRowCount, int mTextColCount) {
        this.mTextRowCount = mTextRowCount;
        this.mTextColCount = mTextColCount;
        this.mWidth = mTextColCount * mTextSizeWidth;
        this.mHeight = mTextRowCount * mTextSizeHeight;
    }

    public void setTextRowCount(int mTextRowCount) {
        this.mTextRowCount = mTextRowCount;
    }

    public void setTextColCount(int mTextColCount) {
        this.mTextColCount = mTextColCount;
    }

    public void setData(ArrayList<Short> mList) {
        this.mDataList = mList;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(mPaintStrokeWidth);
        mPaint.setTextSize(mPaintTextSize);

        int index = -1;

        for (int row = 0; row < mTextRowCount; row++) {
            for (int col = 0; col < mTextColCount; col++) {
                index++;
                Rect targetRect = new Rect(mStartX + mTextSizeWidth * col, mStartY + mTextSizeHeight * row, mStartX
                        + mTextSizeWidth * (col + 1), mStartY + mTextSizeHeight * (row + 1));

                if (mType == REAL_TIME_DATA_TYPE || mType == GRID_HEAD_TYPE) {
                    mPaint.setColor(mPaintRectColor);
                    canvas.drawRect(targetRect, mPaint);
                }

                mPaint.setColor(getResources().getColor(R.color.colorPrimary));

                canvas.drawLine(mStartX + mTextSizeWidth * col, mStartY + mTextSizeHeight * (row + 1), mStartX
                        + mTextSizeWidth * (col + 1), mStartY + mTextSizeHeight * (row + 1), mPaint);
                canvas.drawLine(mStartX + mTextSizeWidth * (col + 1), mStartY + mTextSizeHeight * row, mStartX
                        + mTextSizeWidth * (col + 1), mStartY + mTextSizeHeight * (row + 1), mPaint);

                FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
                int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
                mPaint.setTextAlign(Paint.Align.CENTER);
                mPaint.setColor(mPaintTextColor);

                if (mType == GRID_HEAD_TYPE) {
                    canvas.drawText(" ", targetRect.centerX(), baseline, mPaint);

                } else if (mType == REAL_TIME_DATA_TYPE && mDataList != null && mDataList.size() >= (mTextRowCount * mTextColCount)) {
                    canvas.drawText("" + mDataList.get(index), targetRect.centerX(), baseline, mPaint);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth, mHeight);
    }

}

