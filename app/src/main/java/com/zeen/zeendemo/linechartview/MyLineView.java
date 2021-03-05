package com.zeen.zeendemo.linechartview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Created by Clark Chen. on 3/5/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class MyLineView extends View {

    private Paint mBackPaint;
    private Paint mGridPaint;
    private float mSpace;
    private ArrayList<ViewPoint> mPointList;
    private Paint mLinePaint;
    private Paint mCirclePaint;

    public MyLineView(Context context) {
        super(context);
    }

    public MyLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mBackPaint = new Paint();
        mBackPaint.setStyle(Paint.Style.FILL);//Stroke空心，Fill实心
        mBackPaint.setColor(Color.RED);//画笔颜色
        mBackPaint.setStrokeWidth(10f);//画笔线宽
    }

    {
        mGridPaint = new Paint();
        mGridPaint.setStyle(Paint.Style.STROKE);
        mGridPaint.setColor(Color.BLACK);
        mGridPaint.setStrokeWidth(2f);
        mSpace = 100f;
    }

    {
        mLinePaint = new Paint();
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.argb(255, 34, 192, 255));
        mLinePaint.setStrokeWidth(10f);
    }

    {
        mCirclePaint = new Paint();
        mCirclePaint.setStrokeWidth(10f);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.argb(255, 34, 192, 255));
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
        /*
        Step1.
        绘制实心圆，因为圆心在左上角，而自身的宽度限制导致绘制出扇形，主要标识坐标系原点在左上角。
         */
        //此处画圆在右下角，因为canvas还未翻转
        //canvas.drawCircle(getMeasuredWidth(), -getMeasuredHeight(), 100f, backPaint);

        changeCanvaXY(canvas);

        /*
        Step2.
        绘制实心圆，因为已翻转坐标系，主要标识坐标系原点在左下角。
         */
//        canvas.drawCircle(0f, 0f, 100f, mBackPaint);
        //此处画圆在右上角
//        canvas.drawCircle(getMeasuredWidth(), getMeasuredHeight(), 100f, mBackPaint);

        /*
        Step3.画线(网格)
        设置我们每格子宽高都为40像素。y轴格子个数 =measuredHeight/DensityUtils.px2dp(context,100f)
        已知高度,我们每格子40像素。x轴格子个数  =measuredWidth/DensityUtils.px2dp(context,100f)
         */
//        Path pathY = new Path();
//        pathY.moveTo(DensityUtils.px2dp(getContext(), 100f), 0f);
//        pathY.lineTo(DensityUtils.px2dp(getContext(), 100f), getMeasuredHeight());
//        canvas.drawPath(pathY, gridPaint);
//        Path pathX = new Path();
//        pathX.moveTo(0f, DensityUtils.px2dp(getContext(), 100f));
//        pathX.lineTo(getMeasuredWidth(), DensityUtils.px2dp(getContext(), 100f));
//        canvas.drawPath(pathX, gridPaint);

        drawGrid(canvas);

        drawLine(canvas, mPointList);

    }

    private void flush() {
        this.invalidate();
    }


    public void setData(ArrayList<ViewPoint> pointList) {
        mPointList = pointList;
    }

    /**
     * 绘制折线图
     *
     * @param canvas
     * @param pointList
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    private void drawLine(Canvas canvas, ArrayList<ViewPoint> pointList) {
        Path linePath = new Path();
        //连线
        for (int index = 0; index < pointList.size(); index++) {
            linePath.lineTo(DensityUtils.px2dp(getContext(), pointList.get(index).x), DensityUtils.px2dp(getContext(), pointList.get(index).y));
        }
        canvas.drawPath(linePath, mLinePaint);
        //渐变色的填充：将折线形成一个闭合的区域，通过画笔设置Style.Fill然后设置shader即可变成你想要的渐变填充
        //连线
        for (int index = 0; index < pointList.size(); index++) {
            linePath.lineTo(DensityUtils.px2dp(getContext(), pointList.get(index).x), DensityUtils.px2dp(getContext(), pointList.get(index).y));
        }
        int endIndex = pointList.size() - 1;
        linePath.lineTo(DensityUtils.px2dp(getContext(), pointList.get(endIndex).x), 0f);
        linePath.close();
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setShader(getShader());
        canvas.drawPath(linePath, mLinePaint);

        //画point圆圈
        for (int index = 0; index < pointList.size(); index++) {
            canvas.drawCircle(DensityUtils.px2dp(getContext(), pointList.get(index).x), DensityUtils.px2dp(getContext(), pointList.get(index).y), 16f, mCirclePaint);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private Shader getShader() {
        int[] shadeColors = {Color.argb(255, 250, 49, 33), Color.argb(165, 234, 115, 9), Color.argb(200, 32, 208, 88)};
        return new LinearGradient((getMeasuredWidth() / 2), getMeasuredHeight(), (getMeasuredWidth() / 2), 0f, shadeColors, null, Shader.TileMode.CLAMP);
    }

    private void drawGrid(Canvas canvas) {
        /*
        Step4.画网格
        上面我们已经绘制出了两条线段。只需要计算出每条线段位置并绘制或者平移画布进行绘制。
         */
        //平行X轴的线段
        Path pathX = new Path();
        //平行Y轴的线段
        Path pathY = new Path();
        //x轴的个数
        int countX = (int) (getMeasuredWidth() / DensityUtils.px2dp(getContext(), mSpace));
        //y轴的个数
        int countY = (int) (getMeasuredHeight() / DensityUtils.px2dp(getContext(), mSpace));

        //方法1：
        //平行x轴的个线段
        for (int index = 0; index < countY; index++) {
            pathX.moveTo(0f, DensityUtils.px2dp(getContext(), mSpace) * (index + 1));
            pathX.lineTo(getMeasuredWidth(), DensityUtils.px2dp(getContext(), mSpace) * (index + 1));
            canvas.drawPath(pathX, mGridPaint);
        }
        //平行y轴的个线段
        for (int index = 0; index < countX; index++) {
            pathY.moveTo(DensityUtils.px2dp(getContext(), mSpace) * (index + 1), 0f);
            pathY.lineTo(DensityUtils.px2dp(getContext(), mSpace) * (index + 1), getMeasuredHeight());
            canvas.drawPath(pathY, mGridPaint);
        }

        //方法2：unComplete 移动画布进行绘制
//        canvas.save();
//        for (int index = 0; index < countY; index++) {
//            canvas.translate(0f, getMeasuredWidth());
//            canvas.drawPath(pathX, gridPaint);
//        }
//        canvas.restore();
////        canvas.save();
////        for (int index = 0; index < countX; index++) {
////            canvas.translate(space * (index + 1), 0f);
////            canvas.drawPath(pathY, gridPaint);
////        }
////        canvas.restore();


    }

    private void changeCanvaXY(Canvas canvas) {
        /*
        保存canvas状态；restore用来恢复canvas的缩放、旋转之后的状态。当和save一起使用时，恢复到save保存时的状态。
         */
        canvas.save();
        canvas.scale(1f, -1f);//x不变，y翻转
        canvas.translate(0f, -(getMeasuredHeight()));
    }

}
