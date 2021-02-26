package com.zeen.zeendemo.tracerv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.zeen.zeendemo.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Clark C. on 2/26/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class TimeLine extends RecyclerView.ItemDecoration {
    private List<String> mStampList;

    // 写右边字的画笔(具体信息)
    private Paint mPaint;

    // 写左边日期字的画笔( 时间 + 日期)
    private Paint mPaint1;

    // 左 上偏移长度
    private int itemView_leftinterval;
    private int itemView_topinterval;

    // 轴点半径
    private int circle_radius;

    // 图标
    private Bitmap mIcon;


    public TimeLine(Context context, List<String> list) {

        mStampList = list;//左侧时间list

        // 轴点画笔(红色)
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.trace_point)); // 时间线颜色

        // 左边时间文本画笔(蓝色)
        // 此处设置了两只分别设置 时分 & 年月
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLACK);
        mPaint1.setTextSize(40);

        // 赋值ItemView的左偏移长度为200
        itemView_leftinterval = 200;

        // 赋值ItemView的上偏移长度为50
        itemView_topinterval = 50;

        // 赋值轴点圆的半径为10
        circle_radius = 16;

        // 获取图标资源
        mIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo);
    }

    // 重写getItemOffsets（）方法
    // 作用：设置ItemView 左 & 上偏移长度
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // 设置ItemView的左 & 上偏移长度分别为200 px & 50px,即此为onDraw()可绘制的区域
        outRect.set(itemView_leftinterval, itemView_topinterval, 0, 0);

    }

    // 重写onDraw（）
    // 作用:在间隔区域里绘制时光轴线 & 时间文本
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        // 获取RecyclerView的Child view的个数
        int childCount = parent.getChildCount();

        // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
        for (int i = 0; i < childCount; i++) {

            // 获取每个Item对象
            View child = parent.getChildAt(i);

            /**
             * 绘制轴点
             */
            // 轴点 = 圆 = 圆心(x,y)
            float centerx = child.getLeft() - itemView_leftinterval / 3;
            float centery = child.getTop() - itemView_topinterval + (itemView_topinterval + child.getHeight()) / 2;
            // 绘制轴点圆
            c.drawCircle(centerx, centery, circle_radius, mPaint);
            // 通过Canvas绘制角标
            // c.drawBitmap(mIcon,centerx - circle_radius ,centery - circle_radius,mPaint);

            /**
             * 绘制上半轴线
             */
            // 上端点坐标(x,y)
            float upLine_up_x = centerx;
            float upLine_up_y = child.getTop() - itemView_topinterval;

            // 下端点坐标(x,y)
            float upLine_bottom_x = centerx;
            float upLine_bottom_y = centery - circle_radius;

            //绘制上半部轴线
            c.drawLine(upLine_up_x, upLine_up_y, upLine_bottom_x, upLine_bottom_y, mPaint);

            /**
             * 绘制下半轴线
             */
            // 上端点坐标(x,y)
            float bottomLine_up_x = centerx;
            float bottom_up_y = centery + circle_radius;

            // 下端点坐标(x,y)
            float bottomLine_bottom_x = centerx;
            float bottomLine_bottom_y = child.getBottom();

            //绘制下半部轴线
            c.drawLine(bottomLine_up_x, bottom_up_y, bottomLine_bottom_x, bottomLine_bottom_y, mPaint);


            /**
             * 绘制左边时间文本
             */
            // 获取每个Item的位置
            int index = parent.getChildAdapterPosition(child);
            // 设置文本起始坐标
            float Text_x = child.getLeft() - itemView_leftinterval * 5 / 6;
            float Text_y = upLine_bottom_y;

            // 根据Item位置设置时间文本
            switch (index) {
                case (0):
                    // 设置日期绘制位置
                    c.drawText("13:40", Text_x, Text_y, mPaint1);
                    break;
                case (1):
                    // 设置日期绘制位置
                    c.drawText("17:33", Text_x, Text_y, mPaint1);
                    break;
                case (2):
                    // 设置日期绘制位置
                    c.drawText("20:13", Text_x, Text_y, mPaint1);
                    break;
                case (3):
                    // 设置日期绘制位置
                    c.drawText("11:40", Text_x, Text_y, mPaint1);
                    break;
                case (4):
                    // 设置日期绘制位置
                    c.drawText("13:20", Text_x, Text_y, mPaint1);
                    break;
                case (5):
                    // 设置日期绘制位置
                    c.drawText("22:40", Text_x, Text_y, mPaint1);
                    break;
                default:c.drawText("已签收", Text_x, Text_y, mPaint1);
            }
        }
    }

}

