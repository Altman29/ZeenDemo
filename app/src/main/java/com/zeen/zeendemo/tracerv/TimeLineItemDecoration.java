package com.zeen.zeendemo.tracerv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
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

    private Paint mPaint;
    private Path mPath;

    // 左 上偏移长度
    private int itemView_leftinterval;
    private int itemView_topinterval;

    // 轴点半径
    private int circle_radius;
    // 路径原点半径
    private int point_radius;

    // 图标
    private Bitmap mIcon;
    private final int mWidth;
    private final int mHeight;

    public TimeLineItemDecoration(Context context, List<Integer> list) {

        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.trace_point)); // 时间线颜色
        mPaint.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));
        mPath = new Path();

        // 赋值ItemView的左偏移长度为200
        itemView_leftinterval = 200;

        // 赋值ItemView的上偏移长度为50
        itemView_topinterval = 50;

        // 赋值轴点圆的半径为16 虚线点5
        circle_radius = 16;
        point_radius = 5;

        // 获取图标资源
        mIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo);
        mWidth = mIcon.getWidth();
        mHeight = mIcon.getHeight();
    }

    // 设置ItemView 左 & 上偏移长度
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 设置ItemView的左 & 上偏移长度分别为200 px & 50px,即此为onDraw()可绘制的区域
        outRect.set(itemView_leftinterval, itemView_topinterval, 0, 0);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int viewCount = parent.getChildCount();
        View first = parent.getChildAt(0);
        View last = parent.getChildAt(viewCount - 1);

        float centerX = itemView_leftinterval / 2;

        float lineY = (float) first.getTop();

        //画虚线点 lineY+30(间隔)
        while (lineY <= last.getBottom()) {
            lineY = lineY + 40;

            c.drawCircle(centerX, lineY, point_radius, mPaint);

        }

        //画月份Item点
        for (int i = 0; i < viewCount; i++) {
            View child = parent.getChildAt(i);
            // 获取每个Item的位置
            int index = parent.getChildAdapterPosition(child);
            int type = parent.getAdapter().getItemViewType(index);
            if (type == 1) {//需要显示月份的item
                float centerY = child.getTop() + (child.getHeight()) / 2;
                c.drawBitmap(mIcon, centerX - mWidth / 2, centerY - mHeight / 2, mPaint);
            }

        }


    }


}
