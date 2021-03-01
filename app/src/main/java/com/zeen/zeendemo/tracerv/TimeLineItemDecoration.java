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
 * @author : zhouwenhao
 * date         : 2021/2/26
 * description  :
 **/
public class TimeLineItemDecoration extends RecyclerView.ItemDecoration {

    private List<Integer> mStampList;

    // 写右边字的画笔(具体信息)
    private Paint mPaint;

    // 写左边日期字的画笔( 时间 + 日期)
    private Paint mPaint1;

    // 左 上偏移长度
    private int itemView_leftinterval;
    private int itemView_topinterval;

    // 轴点半径
    private int circle_radius;
    // 路径原点半径
    private int point_radius;

    // 图标
    private Bitmap mIcon;

    public TimeLineItemDecoration(Context context, List<Integer> list) {

        mStampList = list;//左侧时间list

        // 轴点画笔(红色)
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.red)); // 时间线颜色

        // 左边时间文本画笔(蓝色)
        // 此处设置了两只分别设置 时分 & 年月
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLACK);
        mPaint1.setTextSize(30);

        // 赋值ItemView的左偏移长度为200
        itemView_leftinterval = 200;

        // 赋值ItemView的上偏移长度为50
        itemView_topinterval = 50;

        // 赋值轴点圆的半径为10
        circle_radius = 16;
        point_radius = 5;

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

    boolean firstDraw = true;

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int viewCount = parent.getChildCount();
        View first = parent.getChildAt(0);
        View last = parent.getChildAt(viewCount - 1);



        float centerX = itemView_leftinterval / 2;



        float lineY = (float) first.getTop();

        while (lineY <= last.getBottom()) {
            lineY = lineY + 20;
            c.drawCircle(centerX, lineY, point_radius, mPaint);
        }


        for (int i = 0; i < viewCount; i++) {
            View child = parent.getChildAt(i);
            // 获取每个Item的位置
            int index = parent.getChildAdapterPosition(child);
            int type = parent.getAdapter().getItemViewType(index);
            if (type == 1) {
                float centerY = child.getTop() + (child.getHeight()) / 2;
                c.drawCircle(centerX, centerY, circle_radius, mPaint);
            }

        }


    }


}
