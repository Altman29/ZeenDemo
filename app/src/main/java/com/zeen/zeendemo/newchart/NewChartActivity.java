package com.zeen.zeendemo.newchart;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.zeen.zeendemo.R;

import java.util.ArrayList;

public class NewChartActivity extends AppCompatActivity {
    private int count = 7;//7 or 14
    private CombinedChart chart1;
    private CombinedChart chart2;
    private CombinedChart chart3;
    private Chart1Manager chart1Manager;
    private Chart2Manager chart2Manager;
    private Chart3Manager chart3Manager;
    private MySwitchTabLayout timesTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chart);

        //chart1
        chart1 = findViewById(R.id.chart1);
        chart2 = findViewById(R.id.chart2);
        chart3 = findViewById(R.id.chart3);
        chart1Manager = new Chart1Manager(chart1);
        chart2Manager = new Chart2Manager(chart2);
        chart3Manager = new Chart3Manager(chart3);

        timesTab = findViewById(R.id.mytab);

        timesTab.setOnItemCheckedChangeListener(new MySwitchTabLayout.OnItemCheckedChangeListener() {
            @Override
            public void onItemCheckedChange(boolean isChecked) {
                if (isChecked) {
                    switchData(14);
                } else {
                    switchData(7);
                }
            }
        });
        getData();
    }

    public void getData() {
        //setData chart1
        CombinedData data1 = new CombinedData();
        data1.setData(fetch1_LineData());
        data1.setData(fetch1_BarData());
        //fix data(包括数据填充;x,y轴values显示,需加参数) & show； chart1
        chart1Manager.fix(data1);
        chart1Manager.show();

        //setData chart2
        CombinedData data2 = new CombinedData();
        data2.setData(fetch2_LineData());
        data2.setData(fetch2_BarData());
        //fix data(包括数据填充;x,y轴values显示,需加参数) & show； chart2
        chart2Manager.fix(data2);
        chart2Manager.show();

        //setData chart3
        CombinedData data3 = new CombinedData();
        data3.setData(fetch3_LineData());
        //fix data(包括数据填充;x,y轴values显示,需加参数) & show； chart3
        chart3Manager.fix(data3);
        chart3Manager.show();

    }

    private void clearData() {
        chart1.clear();
        chart1.animateY(500);
        chart2.clear();
        chart2.animateY(500);
        chart3.clear();
        chart3.animateY(500);
    }

    private void switchData(int count) {
        clearData();
        this.count = count;
        getData();
    }


    private LineData fetch1_LineData() {
        ArrayList<Entry> entries = new ArrayList<>();
        //需要数据转换，(网络)外部传入数据，并处理
        for (int index = 0; index < count; index++) {
            entries.add(new Entry(index + 0.5f, getRandom(5, 0)));
        }
        return chart1Manager.generateLineData(entries);
    }

    private BarData fetch1_BarData() {
        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        //需要数据转换，(网络)外部传入数据，并处理
        for (int index = 0; index < count; index++) {
            entries1.add(new BarEntry(index + 0.5f, getRandom(5, 0)));
            // stacked
            entries2.add(new BarEntry(index + 0.5f, new float[]{getRandom(2, 0), getRandom(3, 0)}));
        }
        return chart1Manager.generateBarData(entries1, entries2);
    }


    private LineData fetch2_LineData() {
        ArrayList<Entry> entries = new ArrayList<>();
        //需要数据转换，(网络)外部传入数据，并处理
        for (int index = 0; index < count; index++) {
            entries.add(new Entry(index + 0.5f, getRandom(5, 0)));
        }
        return chart2Manager.generateLineData(entries);
    }

    private BarData fetch2_BarData() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        //需要数据转换，(网络)外部传入数据，并处理
        for (int index = 0; index < count; index++) {
            entries.add(new BarEntry(index + 0.5f, getRandom(5, 0)));
        }
        return chart2Manager.generateBarData(entries);
    }


    private LineData fetch3_LineData() {
        ArrayList<Entry> entries1 = new ArrayList<>();
        ArrayList<Entry> entries2 = new ArrayList<>();
        //需要数据转换，(网络)外部传入数据，并处理
        for (int index = 0; index < count; index++) {
            entries1.add(new Entry(index + 0.5f, getRandom(4, 0)));
            entries2.add(new Entry(index + 0.5f, getRandom(4, 0)));
        }
        return chart3Manager.generateLineData1(entries1, entries2);
    }

    protected float getRandom(float range, float start) {
        return (float) (Math.random() * range) + start;
    }
}