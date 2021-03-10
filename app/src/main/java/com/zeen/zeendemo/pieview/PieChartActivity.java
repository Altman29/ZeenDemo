package com.zeen.zeendemo.pieview;

import android.graphics.Color;
import android.os.Bundle;

import com.zeen.zeendemo.R;

import androidx.appcompat.app.AppCompatActivity;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        PieChartView pieChartView = findViewById(R.id.pie_chart_view);
        pieChartView.setHasInfo(false);
        pieChartView.addItemType(new PieChartView.ItemType("开心", 18, Color.parseColor("#FED48A")));
        pieChartView.addItemType(new PieChartView.ItemType("一般", 25, Color.parseColor("#AEDFBD")));
        pieChartView.addItemType(new PieChartView.ItemType("非常难过", 25, Color.parseColor("#A9B4CD")));
        pieChartView.addItemType(new PieChartView.ItemType("非常开心", 25, Color.parseColor("#FE988A")));
        pieChartView.addItemType(new PieChartView.ItemType("难过", 17, Color.parseColor("#AECBDF")));
        pieChartView.setCell(5);
        pieChartView.setInnerRadius(0.6f);
//        pieChartView.setPieCell(10);
//        pieChartView.setBackGroundColor(0xffFFFFE0);
//        pieChartView.setItemTextSize(30);
//        pieChartView.setTextPadding(10);
    }
}