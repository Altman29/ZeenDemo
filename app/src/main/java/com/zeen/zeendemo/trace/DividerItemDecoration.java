package com.zeen.zeendemo.trace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.zeen.zeendemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Clark C. on 2/25/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

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
    // 路径原点半径
    private int point_radius;

    // 图标
    private Bitmap mIcon;

    // 在构造函数里进行绘制的初始化，如画笔属性设置等
    public DividerItemDecoration(Context context) {

        // 轴点画笔(红色)
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.trace_point)); // 时间线颜色

        // 左边时间文本画笔(蓝色)
        // 此处设置了两只分别设置 时分 & 年月
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setTextSize(30);

        // 赋值ItemView的左偏移长度为200
        itemView_leftinterval = 200;

        // 赋值ItemView的上偏移长度为50
        itemView_topinterval = 50;

        // 赋值轴点圆的半径为10
        circle_radius = 10;
        point_radius = 2;

        // 获取图标资源
        mIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo);
    }

    public DividerItemDecoration(Context context, List<String> list) {

        mStampList = list;//左侧时间list

        // 轴点画笔(红色)
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.trace_point)); // 时间线颜色

        // 左边时间文本画笔(蓝色)
        // 此处设置了两只分别设置 时分 & 年月
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
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


//        //第一个ItemView不需要在上面绘制分割线
//        if (parent.getChildAdapterPosition(view) == 0){
//            outRect.top = 0;
//        } else {
//            outRect.top = 2;
//        }
    }

    // 重写onDraw（）
    // 作用:在间隔区域里绘制时光轴线 & 时间文本
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        // 获取RecyclerView的Child view的个数
        int childCount = parent.getChildCount();

//        String temp = mStampList.get(0);
//        c.drawText(temp, 0, 0, mPaint1);

        //共同点，头元素，尾元素
        //需要画的点，不是一个item的上端和下端，而是中间填充着这些点!!!而drawCircle这些点的依据是根据item位置和数量决定的(目前)
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
//            c.drawCircle(centerx, centery, circle_radius, mPaint);
            // 通过Canvas绘制角标
//            c.drawBitmap(mIcon, centerx - 2 * circle_radius, centery - 2 * circle_radius, mPaint);
//            c.drawBitmap(mIcon, centerx, centery, mPaint);
            /**
             * 绘制上半轴线//如果是第一个item，上半不需要画point
             */
            // 上端点坐标(x,y)
            float upLine_up_x = centerx;
            float upLine_up_y = child.getTop();
            // 下端点坐标(x,y)
            float upLine_bottom_x = centerx;
            float upLine_bottom_y = centery;
            //绘制上半部轴线
//            c.drawLine(upLine_up_x, upLine_up_y, upLine_bottom_x, upLine_bottom_y, mPaint);
//            c.drawCircle(upLine_up_x, upLine_up_y, point_radius, mPaint);
//            c.drawCircle(upLine_bottom_x, upLine_bottom_y, point_radius, mPaint);

            /**
             * 绘制下半轴线//如果是最后一个item，下半不需要画point
             */
            // 上端点坐标(x,y)
            float bottomLine_up_x = centerx;
            float bottom_up_y = centery;

            // 下端点坐标(x,y)
            float bottomLine_bottom_x = centerx;
            float bottomLine_bottom_y = child.getBottom();

            //绘制下半部轴线
//            c.drawLine(bottomLine_up_x, bottom_up_y, bottomLine_bottom_x, bottomLine_bottom_y, mPaint);
            c.drawCircle(bottomLine_up_x, bottom_up_y, point_radius, mPaint);
            c.drawCircle(bottomLine_bottom_x, bottomLine_bottom_y, point_radius, mPaint);

            /**
             * 绘制左边时间文本
             */
            // 获取每个Item的位置
            int index = parent.getChildAdapterPosition(child);
            // 设置文本起始坐标
            float Text_x = child.getLeft() - itemView_leftinterval * 5 / 6;
            float Text_y = upLine_bottom_y;

            //左侧月份 需要处理 同月不显示text:("")
            if (index == 0) {//头
                c.drawText(mStampList.get(index), Text_x, Text_y, mPaint1);
//                c.drawBitmap(mIcon, centerx- 2 * circle_radius, centery- 2 * circle_radius, mPaint);
                c.drawCircle(centerx, centery, circle_radius, mPaint);
                //bottom
                c.drawCircle(bottomLine_up_x, bottom_up_y, point_radius, mPaint);
                c.drawCircle(bottomLine_bottom_x, bottomLine_bottom_y, point_radius, mPaint);

            } else if (index == mStampList.size()) {//尾
                //up
                c.drawCircle(upLine_up_x, upLine_up_y, point_radius, mPaint);
                c.drawCircle(upLine_bottom_x, upLine_bottom_y, point_radius, mPaint);

                if (mStampList.get(index - 1).equals(mStampList.get(index))) {
                    c.drawText("", Text_x, Text_y, mPaint1);
                } else {
                    c.drawText(mStampList.get(index), Text_x, Text_y, mPaint1);
//                    c.drawBitmap(mIcon, centerx- 2 * circle_radius, centery- 2 * circle_radius, mPaint);
                    c.drawCircle(centerx, centery, circle_radius, mPaint);
                }
            } else if (mStampList.get(index - 1).equals(mStampList.get(index))) {//中间equals
                //up
                c.drawCircle(upLine_up_x, upLine_up_y, point_radius, mPaint);
                c.drawCircle(upLine_bottom_x, upLine_bottom_y, point_radius, mPaint);
                //bottom
                c.drawCircle(bottomLine_up_x, bottom_up_y, point_radius, mPaint);
                c.drawCircle(bottomLine_bottom_x, bottomLine_bottom_y, point_radius, mPaint);
                c.drawText("", Text_x, Text_y, mPaint1);
            } else {//中间not equals
                //up
                c.drawCircle(upLine_up_x, upLine_up_y, point_radius, mPaint);
                c.drawCircle(upLine_bottom_x, upLine_bottom_y, point_radius, mPaint);
                //bottom
                c.drawCircle(bottomLine_up_x, bottom_up_y, point_radius, mPaint);
                c.drawCircle(bottomLine_bottom_x, bottomLine_bottom_y, point_radius, mPaint);
                c.drawText(mStampList.get(index), Text_x, Text_y, mPaint1);
//                c.drawBitmap(mIcon, centerx- 2 * circle_radius, centery- 2 * circle_radius, mPaint);
                c.drawCircle(centerx, centery, circle_radius, mPaint);
            }

//            if (temp.equals(mStampList.get(i))) {
//                c.drawText("", Text_x, Text_y, mPaint1);
//            } else {
//                temp = mStampList.get(i);
//                c.drawText(mStampList.get(i), Text_x, Text_y, mPaint1);
//            }
        }
    }

    /**
     * 使用TreeSet实现List去重(有序)
     *
     * @param list
     */
    public List removeDuplicationByTreeSet(List<String> list) {
        TreeSet set = new TreeSet(list);
        //把List集合所有元素清空
        list.clear();
        //把HashSet对象添加至List集合
        list.addAll(set);
        return list;
    }

    /**
     * 使用List集合contains方法循环遍历(有序)
     *
     * @param list
     */
    public List removeDuplicationByContains(List<Integer> list) {
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean isContains = newList.contains(list.get(i));
            if (!isContains) {
                newList.add(list.get(i));
            }
        }
        list.clear();
        list.addAll(newList);
        return list;
    }

    /**
     * 使用List集合contains方法循环遍历(有序)
     *
     * @param list
     */
    public List getPosition(List<String> list) {
        List<String> newList = new ArrayList<>();
        List<Integer> positionList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean isContains = newList.contains(list.get(i));
            if (!isContains) {
                newList.add(list.get(i));
            } else {
                positionList.add(i);
            }
        }
        list.clear();
        list.addAll(newList);
        return list;
    }
}
