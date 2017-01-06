package com.lyt.chart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.lyt.chart.chart.GridDisplayView;
import com.lyt.chart.chart.HorizontalScroller;
import com.lyt.chart.chart.VerticalScroller;

import java.util.ArrayList;
import static com.lyt.chart.chart.GridDisplayView.GRID_HEAD_TYPE;
import static com.lyt.chart.chart.GridDisplayView.REAL_TIME_DATA_TYPE;

public class MainActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


}
