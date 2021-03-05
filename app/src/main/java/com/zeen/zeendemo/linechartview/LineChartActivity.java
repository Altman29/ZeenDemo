package com.zeen.zeendemo.linechartview;

import android.os.Bundle;

import com.zeen.zeendemo.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class LineChartActivity extends AppCompatActivity {
    private MyLineView mMyLineView;

    //    _LineChartView chartView;
//    List<String> xValues;   //x轴数据集合
//    List<Integer> yValues;  //y轴数据集合
    ArrayList<ViewPoint> mPointList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        mMyLineView = findViewById(R.id.mylineview);
        mPointList = new ArrayList<>();
        mPointList.add(new ViewPoint(0, 0));
        mPointList.add(new ViewPoint(600, 500));
        mPointList.add(new ViewPoint(1500, 700));
        mPointList.add(new ViewPoint(2100, 1400));
        mPointList.add(new ViewPoint(2600, 1200));
        mMyLineView.setData(mPointList);


    }
}