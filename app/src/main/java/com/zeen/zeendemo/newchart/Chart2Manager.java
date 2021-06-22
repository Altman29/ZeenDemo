package com.zeen.zeendemo.newchart;

import android.graphics.Color;
import android.graphics.DashPathEffect;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

/**
 * Copyright©  2021
 * 正岸健康
 * author: csy
 * created on: 6/21/21 6:08 PM
 * description:
 */
public class Chart2Manager {

    private CombinedChart chart;
    private boolean isShowValues = false;//Bar,Line是否显示数值

    public Chart2Manager(CombinedChart chart) {
        this.chart = chart;
        init();
    }

    private void init() {

        //basic settings
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);
        chart.setScaleEnabled(false);
        chart.setTouchEnabled(false);
        chart.setExtraBottomOffset(14f);
        chart.animateY(500);

        // draw order
        chart.setDrawOrder(new DrawOrder[]{
                DrawOrder.BAR, DrawOrder.LINE
        });

        //设置图例相关
        Legend l = chart.getLegend();
        l.setEnabled(true);
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setForm(Legend.LegendForm.CIRCLE);//统一设置图例样式
        l.setDrawInside(false);
        l.setTextSize(12f);
        l.setFormToTextSpace(8f);//设置图例(形状)和标签的间距
        l.setXEntrySpace(8f);//X轴上图例间距
        l.setYEntrySpace(8f);//Y轴上图例间距
        //以百分比为单位，将整个图表视图相对整个父类View设置百分比。默认0.95f(95%).超过则换行。(但是换行后因为设置了xEntrySpace不再居左)
        l.setMaxSizePercent(0.95f);

        //right Y
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        rightAxis.setGranularity(1);
        rightAxis.setTextColor(Color.parseColor("#FFC4C4CC"));
        rightAxis.setTextSize(12f);
        rightAxis.setAxisMaximum(5.5f);//基本坐标0-5，真实值在setValuesFormatter中设置
        rightAxis.setYOffset(-10f);//right Y values向上偏移
        rightAxis.setDrawAxisLine(false);//只画值，不画线

        //left Y
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setGranularity(1f);
        leftAxis.setTextColor(Color.parseColor("#FFC4C4CC"));
        leftAxis.setTextSize(12f);
        leftAxis.setAxisMaximum(5.5f);//基本坐标0-5，真实值在setValuesFormatter中设置
        leftAxis.setGridLineWidth(0.5f);
        leftAxis.setYOffset(-10f);//left Y values向上偏移
        leftAxis.setGridDashedLine(new DashPathEffect(new float[]{8f, 8f}, 8f));
        leftAxis.setGridColor(Color.parseColor("#E1E1E1"));
        leftAxis.setDrawAxisLine(false);//只画值，不画线

        //X
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);//X轴设置在底部
        xAxis.setDrawGridLines(false);//只画值，不画线
        xAxis.setAxisMinimum(0f);//最小0
        xAxis.setGranularity(1f);//粒度1
        xAxis.setTextColor(Color.parseColor("#FFC4C4CC"));
        xAxis.setTextSize(12f);
        xAxis.setCenterAxisLabels(true);//针对每个X坐标，X的坐标显示居中
    }

    /**
     * 生成LindeData
     *
     * @param entries
     * @return
     */
    public LineData generateLineData(ArrayList<Entry> entries) {
        LineData d = new LineData();
        LineDataSet set = new LineDataSet(entries, "醒来次数");
        set.setColor(Color.parseColor("#FFFF9A9A"));
        set.setLineWidth(3.5f);
        set.setDrawCircles(false);
        // set.setCircleColor(Color.rgb(240, 238, 70));
        // set.setCircleRadius(0f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);//三次贝塞尔曲线
        set.setDrawValues(isShowValues);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.parseColor("#FFC4C4CC"));
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);
        return d;
    }

    /**
     * 生成BarData with stacked
     *
     * @param entries1
     * @return
     */
    public BarData generateBarData(ArrayList<BarEntry> entries1) {
        BarDataSet set1 = new BarDataSet(entries1, "醒来时长");
        set1.setColor(Color.parseColor("#FF7B72E1"));
        set1.setDrawValues(isShowValues);
        set1.setValueTextColor(Color.parseColor("#FFC4C4CC"));
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        float barWidth = 0.25f; // x2 dataset
        BarData d = new BarData(set1);
        d.setBarWidth(barWidth);
        return d;
    }

    public void fix(CombinedData data) {
        chart.setData(data);

        XAxis xAxis = chart.getXAxis();
        xAxis.setAxisMaximum(data.getXMax() + 0.5f);//just show nice
        //底部X轴显示
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "";
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        //左侧Y轴显示
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value * 2 + "";
            }
        });

        YAxis rightAxis = chart.getAxisRight();
        //右侧Y轴显示
        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value * 20 + "%";
            }
        });

    }

    public void show() {
        chart.invalidate();
    }
}
