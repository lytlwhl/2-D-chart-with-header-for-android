package com.lyt.chart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import java.util.ArrayList;
import static com.lyt.chart.GridDisplayView.GRID_HEAD_TYPE;
import static com.lyt.chart.GridDisplayView.REAL_TIME_DATA_TYPE;

public class MainActivity extends Activity{
    private HorizontalScroller mGridHorizontalScrollView;
    private HorizontalScroller mDataHorizontalScrollView;
    private VerticalScroller mVerticalScrollView;
    private LinearLayout mColumnIdLayout;
    private LinearLayout mTableIdLayout;

    private short mColIndex = 0;
    private short mRowIndex = 0;
    private short mColMaxIndex = 0;
    private short mRowMaxIndex = 0;
    private short mLastPageColNum = 0;
    private short mLastPageRowNum = 0;

    public final static int DATA_COL_NUM = 100;
    public final static int DATA_ROW_NUM = 100;

    private final static int mBoxHeight = 150;
    private final static int mBoxWidth = 150;

    GridDisplayView mRowCountView;
    GridDisplayView mColCountView;
    GridDisplayView mDataView;

    private Boolean mIsRowNumViewChanged = false;
    private Boolean mIsColNumViewChanged = false;

    ArrayList<Short> mDisplayData;
    ArrayList<Short> mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();

        initGrid();

        mDataHorizontalScrollView.setHorizontalScrollViewListener(new HorizontalScroller.HorizontalScrollViewListener() {
            @Override
            public void onHorizontalScroll(HorizontalScroller scrollView, int x, int y, int oldx, int oldy) {

            }
        });

        mGridHorizontalScrollView.setHorizontalScrollViewListener(new HorizontalScroller.HorizontalScrollViewListener() {
            @Override
            public void onHorizontalScroll(HorizontalScroller scrollView, int x, int y, int oldx, int oldy) {

                if (x > mBoxWidth * 18) {
                    if (mColIndex < mColMaxIndex - 1) {
                        mColIndex ++;

                        if (mIsColNumViewChanged) {
                            mIsColNumViewChanged = false;
                            mColCountView.setTextColCount(30);
                            mDataView.setTextColCount(30);
                        }

                        ArrayList<Short> colCountList = new ArrayList<Short>();
                        for (short i = (short) (1 + 10 * mColIndex); i <= 30 + 10 * mColIndex; i++) {
                            colCountList.add(i);
                        }
                        mColCountView.setData(colCountList);
                        mColCountView.invalidate();

                        redrawDataView();
                        mGridHorizontalScrollView.scrollTo(mBoxWidth*9, 0);

                    } else if (mColIndex == mColMaxIndex - 1) {

                        mColIndex ++;
                        mIsColNumViewChanged = true;

                        ArrayList<Short> colCountList = new ArrayList<Short>();
                        for (short i = (short) (1 + 10 * mColIndex); i <= DATA_COL_NUM; i++) {
                            colCountList.add(i);
                        }
                        mColCountView.setTextColCount(mLastPageColNum);
                        mColCountView.setData(colCountList);
                        mColCountView.invalidate();


                        mDataView.setTextColCount(mLastPageColNum);
                        redrawDataView();
                        mGridHorizontalScrollView.scrollTo(mBoxWidth*9, 0);
                    }

                } else if (x == 0) {
                    if (mColIndex > 0) {

                        mColIndex --;

                        if (mIsColNumViewChanged) {
                            mIsColNumViewChanged = false;
                            mColCountView.setTextColCount(30);
                            mDataView.setTextColCount(30);
                        }

                        ArrayList<Short> colCountList = new ArrayList<Short>();
                        for (short i = (short) (1 + 10 * mColIndex); i <= 30 + 10 * mColIndex; i++) {
                            colCountList.add(i);
                        }
                        mColCountView.setData(colCountList);
                        mColCountView.invalidate();

                        redrawDataView();
                        mGridHorizontalScrollView.scrollTo(mBoxWidth*9, 0);
                    }
                }

            }
        });

        mVerticalScrollView.setScrollViewListener(new VerticalScroller.ScrollViewListener() {
            @Override
            public void onScrollChanged(VerticalScroller scrollView, int x, int y, int oldx, int oldy) {

                if (y > mBoxHeight * 18) {
                    if (mRowIndex < mRowMaxIndex - 1) {

                        mRowIndex ++;
                        if (mIsRowNumViewChanged) {
                            mIsRowNumViewChanged = false;
                            mRowCountView.setTextRowCount(30);
                            mDataView.setTextRowCount(30);
                        }

                        ArrayList<Short> rowCountList = new ArrayList<Short>();
                        for (short i = (short) (1 + 10 * mRowIndex); i <= 30 + 10 * mRowIndex; i++) {
                            rowCountList.add(i);
                        }
                        mRowCountView.setData(rowCountList);
                        mRowCountView.invalidate();

                        redrawDataView();

                        mVerticalScrollView.scrollTo(0, mBoxHeight*9);

                    }else if (mRowIndex == mRowMaxIndex - 1) {

                        mRowIndex ++;
                        mIsRowNumViewChanged = true;

                        ArrayList<Short> rowCountList = new ArrayList<Short>();
                        for (short i = (short) (1 + 10 * mRowIndex); i <= DATA_ROW_NUM; i++) {
                            rowCountList.add(i);
                        }
                        mRowCountView.setTextRowCount(mLastPageRowNum);
                        mRowCountView.setData(rowCountList);
                        mRowCountView.invalidate();

                        mDataView.setTextRowCount(mLastPageRowNum);
                        redrawDataView();

                        mVerticalScrollView.scrollTo(0, mBoxHeight*9);
                    }
                } else if (y == 0) {
                    if (mRowIndex > 0) {
                        mRowIndex --;

                        if (mIsRowNumViewChanged) {
                            mIsRowNumViewChanged = false;
                            mRowCountView.setTextRowCount(30);
                            mDataView.setTextRowCount(30);
                        }

                        ArrayList<Short> rowCountList = new ArrayList<Short>();
                        for (short i = (short) (1 + 10 * mRowIndex); i <= 30 + 10 * mRowIndex; i++) {
                            rowCountList.add(i);
                        }
                        mRowCountView.setData(rowCountList);
                        mRowCountView.invalidate();

                        redrawDataView();

                        mVerticalScrollView.scrollTo(0, mBoxHeight*9);
                    }
                }

            }
        });

    }

    private void initData() {
        mDisplayData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++){

                mDisplayData.add((short) (i * 30 + j));
            }
        }

        mData = new ArrayList<>();
        for (int i = 0; i < DATA_COL_NUM * DATA_ROW_NUM; i ++) {
            mData.add((short) i);
        }
    }

    private void initView() {

        mRowMaxIndex = (short) ((DATA_ROW_NUM - 30) / 10 + 1);
        mColMaxIndex = (short) ((DATA_COL_NUM - 30) / 10 + 1);

        mLastPageColNum = (short) (DATA_COL_NUM - (10 * mColMaxIndex));
        mLastPageRowNum = (short) (DATA_ROW_NUM - (10 * mRowMaxIndex));

        mGridHorizontalScrollView = (HorizontalScroller) findViewById(R.id.real_time_data_col_count_horizontal_scrollView);
        mDataHorizontalScrollView = (HorizontalScroller) findViewById(R.id.real_time_data_display_data_horizontal_scrollview);
        mGridHorizontalScrollView.setBindScrollView(mDataHorizontalScrollView);
        mDataHorizontalScrollView.setBindScrollView(mGridHorizontalScrollView);
        mColumnIdLayout = (LinearLayout) findViewById(R.id.left_table);
        mTableIdLayout = (LinearLayout) findViewById(R.id.table_id);
        mVerticalScrollView = (VerticalScroller) findViewById(R.id.real_time_data_vertical_scrollView);

    }

    private void initGrid() {
        final GridDisplayView BlankView = new GridDisplayView(this, GRID_HEAD_TYPE, mBoxWidth, mBoxHeight);
        BlankView.setRowColCount(1, 1);
        mTableIdLayout.addView(BlankView);

        mColCountView = new GridDisplayView(this, REAL_TIME_DATA_TYPE, mBoxWidth, mBoxHeight);
        mColCountView.setRowColCount(1, 30);
        ArrayList<Short> colCountList = new ArrayList<Short>();
        for (short i = 1; i <= 30; i++) {
            colCountList.add(i);
        }
        mColCountView.setData(colCountList);
        mGridHorizontalScrollView.addView(mColCountView);

        mRowCountView = new GridDisplayView(this, REAL_TIME_DATA_TYPE, mBoxWidth, mBoxHeight);
        mRowCountView.setRowColCount(30, 1);
        ArrayList<Short> rowCountList = new ArrayList<Short>();
        for (short i = 1; i <= 30; i++) {
            rowCountList.add(i);
        }
        mRowCountView.setData(rowCountList);
        mColumnIdLayout.addView(mRowCountView);
        mDataView = new GridDisplayView(this, REAL_TIME_DATA_TYPE, mBoxWidth, mBoxHeight);
        mDataView.setRowColCount(30, 30);
        mDataView.setData(mDisplayData);
        mDataHorizontalScrollView.addView(mDataView);

    }


    @Override
    protected void onResume() {
        super.onResume();

        mIsRowNumViewChanged = false;
        mIsColNumViewChanged = false;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void redrawDataView() {

        mDisplayData = new ArrayList();
        for (int i = 0; i < (mIsRowNumViewChanged ? mLastPageRowNum : 30); i++) {
            for (int j = 0; j < (mIsColNumViewChanged ? mLastPageColNum : 30); j++){
                int subscript = (i + mRowIndex * 10) * DATA_COL_NUM + (j + mColIndex * 10);

                if (subscript >= mData.size()) {
                    mDisplayData.add((short) 0);

                }else {
                    short data = mData.get(subscript);
                    mDisplayData.add(data);
                }
            }
        }

        mDataView.setData(mDisplayData);
        mDataView.invalidate();

    }

}
