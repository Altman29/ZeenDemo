package com.zeen.zeendemo.horizontal_lineview;

import android.os.Bundle;

import com.zeen.zeendemo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class NewLineActivity extends AppCompatActivity {
    LineView chartView;
    List<String> xValues = new ArrayList<>();   //x轴数据集合
    List<Float> yValues = new ArrayList<>();  //y轴数据集合
    ArrayList<ViewPoint> mPointList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_line);


        initData();

        chartView = findViewById(R.id.lineview);

        // xy轴集合自己添加数据
        chartView.setData(mPointList);
    }

    private void initData() {
        mPointList = new ArrayList<>();
        mPointList.add(new ViewPoint("12月1日", 1f));
        mPointList.add(new ViewPoint("2", 0f));
        mPointList.add(new ViewPoint("3", 4f));
        mPointList.add(new ViewPoint("4", 1f));
        mPointList.add(new ViewPoint("5", 4f));
        mPointList.add(new ViewPoint("6", 3f));
        mPointList.add(new ViewPoint("7", 2f));
        mPointList.add(new ViewPoint("8", 4f));
        mPointList.add(new ViewPoint("9", 1f));
        mPointList.add(new ViewPoint("10", 3f));
        mPointList.add(new ViewPoint("11", 1f));
    }
}