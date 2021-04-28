package com.zeen.zeendemo.mpchart;

import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.zeen.zeendemo.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LineChartActivity extends AppCompatActivity {

    @BindView(R.id.line_chart) LineChart mLineChart;

    List<Entry> list = new ArrayList<>();
    @BindView(R.id.start) Button mStart;
    @BindView(R.id.pause) Button mPause;
    private Boolean loadMore;

    public static final int MSG_START = 1; // handler消息，开始添加点

    private Random mRandom = new Random(); // 随机产生点
    private DecimalFormat mDecimalFormat = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart2);
        ButterKnife.bind(this);

        mLineChart.getDescription().setEnabled(false);//隐藏右下描述

        mLineChart.setDrawBorders(false);//显示边界

        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entries.add(new Entry(i, (float) (Math.random()) * 50));
        }
        //一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "mood");
        lineDataSet.setDrawValues(false);//隐藏每个坐标的值

        LineData lineData = new LineData(lineDataSet);
        mLineChart.setData(lineData);

        //不显示每个x的竖线
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE); // 设置X轴的位置
        xAxis.setDrawGridLines(false);

        //Y
        YAxis leftAxis = mLineChart.getAxisLeft();
        YAxis rightAxis = mLineChart.getAxisRight();
        leftAxis.setLabelCount(5, false);
        rightAxis.setEnabled(false);

    }


    /**
     * 动态添加数据
     * 在一个LineChart中存放的折线，其实是以索引从0开始编号的
     *
     * @param yValues y值
     */
    public void addEntry(LineData lineData, LineChart lineChart, float yValues, int index) {

        // 通过索引得到一条折线，之后得到折线上当前点的数量
        int xCount = lineData.getDataSetByIndex(index).getEntryCount();

        Entry entry = new Entry(xCount, yValues); // 创建一个点
        lineData.addEntry(entry, index); // 将entry添加到指定索引处的折线中

        //通知数据已经改变
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();

        //把yValues移到指定索引的位置
        lineChart.moveViewToAnimated(xCount - 4, yValues, YAxis.AxisDependency.LEFT, 1000);// TODO: 2019/5/4 内存泄漏，异步 待修复
        lineChart.invalidate();
    }


}