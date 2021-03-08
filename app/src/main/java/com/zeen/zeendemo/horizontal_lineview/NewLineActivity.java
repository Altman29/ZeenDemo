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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_line);
        initData();

        chartView = findViewById(R.id.lineview);


        // xy轴集合自己添加数据
        chartView.setXValues(xValues);
        chartView.setYValues(yValues);
    }

    private void initData() {
        xValues.add("12.01");
        xValues.add("12.02");
        xValues.add("12.03");
        xValues.add("12.04");
        xValues.add("12.05");
        xValues.add("12.06");
        xValues.add("12.07");
        xValues.add("12.08");
        xValues.add("12.09");
        xValues.add("12.10");
        xValues.add("12.11");
        yValues.add(5f);
        yValues.add(14f);
        yValues.add(8f);
        yValues.add(12f);
        yValues.add(7f);
        yValues.add(17f);
        yValues.add(17f);
        yValues.add(17f);
        yValues.add(17f);
        yValues.add(17f);
        yValues.add(17f);
    }
}