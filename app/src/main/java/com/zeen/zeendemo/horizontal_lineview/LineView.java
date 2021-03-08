package com.zeen.zeendemo.horizontal_lineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by Clark Chen. on 3/8/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class LineView extends View {

    public static final String TAG = "LineView";
    private int[] mYLables = {1, 6, 11, 16, 21, 26};//Y轴数字
    private List<String> xValues = new ArrayList<>();
    private List<Float> yValues = new ArrayList<>();
    private int mWidth;
    private int mHeight;
    private int originX;//原点X坐标
    private int originY;//原点Y坐标
    private int intervalX = 130;//x轴刻度的间隔
    private int intervalY = 80;//y轴刻度的间隔
    private int paddingTop = 140;//上下左右的padding
    private int paddingLeft = dp2px(80);
    private int paddingRight = dp2px(30);
    private int paddingBottom = 150;
    private int firstPointX;//第一个点的x坐标
    private int xScaleHeight = dp2px(6);//x轴刻度线高度(可隐藏)
    private int xyTextSize = sp2px(10);//xy轴文字大小
    private int mLableCountY = mYLables.length;//y轴刻度个数
    private int mLeftRightExtra = intervalX / 3;//x轴左右想外延伸的长度
    private float minValueY = 1;//y轴最小值
    private float maxValueY = 26;//y轴最大值
    private int bigCircleR = 7;//折线图中的圆圈
    private int smallCircleR = 5;//折线图中为了避免折线穿透的圆圈
    private GestureDetector mGestureDetector;//滑动手势
    private int firstMinX;//移动时第一个点的最小x值
    private int firstMaxX;//移动时第一个点的最大x值
    private int backGroundColor = Color.parseColor("#272727");//view的背景颜色
    private Paint mPaintWhite, mPaintBlue, mPaintRed, mPaintBack, mPaintText, mPaintDash;//画笔

    public LineView(Context context) {
        this(context,null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetector(context, new MyOnGestureListener());
        initPaint();
    }

    private void initPaint() {
        mPaintWhite = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintWhite.setColor(Color.WHITE);
        mPaintWhite.setStyle(Paint.Style.STROKE);

        mPaintBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBlue.setColor(Color.parseColor("#0198cd"));
        mPaintBlue.setStrokeWidth(3f);
        mPaintBlue.setStyle(Paint.Style.STROKE);

        mPaintBack = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBack.setColor(Color.parseColor("#272727"));
        mPaintBack.setStyle(Paint.Style.FILL);

        mPaintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintRed.setColor(Color.RED);
        mPaintRed.setStrokeWidth(3f);
        mPaintRed.setStyle(Paint.Style.STROKE);

        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(Color.WHITE);
        mPaintText.setTextSize(xyTextSize);
        mPaintText.setStrokeWidth(2f);

        mPaintDash = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintDash.setColor(Color.WHITE);
        mPaintDash.setStyle(Paint.Style.STROKE);
        mPaintDash.setStrokeWidth(1f);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            mWidth = getWidth();
            mHeight = getHeight();

            originX = paddingLeft - mLeftRightExtra;
            originY = mHeight - paddingBottom;

            firstPointX = paddingLeft;
            firstMinX = mWidth - originX - (xValues.size() - 1) * intervalX - mLeftRightExtra;

            //滑动时，第一个点x值最大为paddingLeft，在大于这个值就不能滑动了
            firstMaxX = firstPointX;
            setBackgroundColor(backGroundColor);
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //drawLine和drawY位置不能改变
        drawX(canvas);
        drawLine(canvas);
        drawY(canvas);
    }

    public void setXValues(List<String> values) {
        this.xValues = values;
    }

    public void setYValues(List<Float> values) {
        this.yValues = values;
        for (int i = 0; i < yValues.size(); i++) {
            // 找出y轴的最大最小值
            if (yValues.get(i) > maxValueY) {
                maxValueY = yValues.get(i);
            }
            if (yValues.get(i) < minValueY) {
                minValueY = yValues.get(i);
            }
        }
    }

    private void drawY(Canvas canvas) {
        Path path = new Path();
        path.moveTo(originX, originY);

        for (int i = 0; i < mLableCountY; i++) {
            // y轴线
            path.lineTo(originX, mHeight - paddingBottom - mLeftRightExtra - i * intervalY);
        }

        //y轴最后一个刻度的位置
        int lastPointY = mHeight - paddingBottom - mLeftRightExtra - (mLableCountY - 1) * intervalY;
        // 箭头位置，y轴最后一个点后，需要额外加上一小段，就是一个半leftRightExtra的长度
        int lastY = lastPointY - mLeftRightExtra - mLeftRightExtra / 2;
        // y轴箭头
        canvas.drawLine(originX, lastPointY, originX, lastY, mPaintWhite);
        canvas.drawLine(originX, lastY, originX - 10, lastY + 15, mPaintWhite);
        canvas.drawLine(originX, lastY, originX + 10, lastY + 15, mPaintWhite);

        canvas.drawPath(path, mPaintWhite);

        // y轴文字
        for (int i = 0; i < mYLables.length; i++) {
            canvas.drawText(String.valueOf(mYLables[i]), originX - dp2px(25),
                    mHeight - paddingBottom - mLeftRightExtra - i * intervalY + getTextHeight(mPaintText, "00.00") / 2, mPaintText);
        }

        // 截取折线超出部分（右边）
        mPaintBack.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        RectF rectF = new RectF(mWidth - paddingRight, 0, mWidth, mHeight);
        canvas.drawRect(rectF, mPaintBack);
    }

    private void drawLine(Canvas canvas) {
        // 画折线
        float aver = (mLableCountY - 1) * intervalY / (maxValueY - minValueY); //y轴最小单位的距离
        Path path = new Path();
        //先移动到第一个点的位置
        path.moveTo(firstPointX, mHeight - paddingBottom - mLeftRightExtra - yValues.get(0) * aver + minValueY * aver);
        //绘制折线
        for (int i = 0; i < yValues.size(); i++) {
            path.lineTo(firstPointX + i * intervalX, mHeight - paddingBottom - mLeftRightExtra - yValues.get(i) * aver + minValueY * aver);
        }
        canvas.drawPath(path, mPaintBlue);

        // 折线中的圆点
        for (int i = 0; i < yValues.size(); i++) {
            canvas.drawCircle(firstPointX + i * intervalX,
                    mHeight - paddingBottom - mLeftRightExtra - yValues.get(i) * aver + minValueY * aver, bigCircleR, mPaintBlue);
            //小圆的颜色和view背景颜色相同，这样看上去折线是没有穿透圆的
            canvas.drawCircle(firstPointX + i * intervalX,
                    mHeight - paddingBottom - mLeftRightExtra - yValues.get(i) * aver + minValueY * aver, smallCircleR, mPaintBack);
        }

        //将折线超出x轴坐标的部分截取掉（左边）
        mPaintBack.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        RectF rectF = new RectF(0, 0, originX, mHeight);
        canvas.drawRect(rectF, mPaintBack);
    }

    private void drawX(Canvas canvas) {
        Path path = new Path();
        path.moveTo(originX, originY);
        // x轴线
        path.lineTo(mWidth - paddingRight, originY);
        canvas.drawPath(path, mPaintWhite);

        // x轴箭头
        canvas.drawLine(mWidth - paddingRight, originY, mWidth - paddingRight - 15, originY + 10, mPaintWhite);
        canvas.drawLine(mWidth - paddingRight, originY, mWidth - paddingRight - 15, originY - 10, mPaintWhite);

        for (int i = 0; i < xValues.size(); i++) {
            // x轴线上的刻度线
            canvas.drawLine(firstPointX + i * intervalX, originY, firstPointX + i * intervalX, originY - xScaleHeight, mPaintWhite);
            // x轴上的文字
            canvas.drawText(xValues.get(i), firstPointX + i * intervalX - getTextWidth(mPaintText, "17.01") / 2,
                    originY + dp2px(20), mPaintText);
        }


        // x轴虚线
        Path path1 = new Path();
        DashPathEffect dash = new DashPathEffect(new float[]{8, 10, 8, 10}, 0);
        mPaintDash.setPathEffect(dash);
        for (int i = 0; i < mLableCountY; i++) {
            path1.moveTo(originX, mHeight - paddingBottom - mLeftRightExtra - i * intervalY);
            path1.lineTo(mWidth - paddingRight, mHeight - paddingBottom - mLeftRightExtra - i * intervalY);
        }
        canvas.drawPath(path1, mPaintDash);
    }

    /**
     * 手势事件
     */
    class MyOnGestureListener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            // 按下事件
            Log.i(TAG, "onDown: ");
            return false;
        }

        // 按下停留时间超过瞬时，并且按下时没有松开或拖动，就会执行此方法
        @Override
        public void onShowPress(MotionEvent motionEvent) {
            Log.i(TAG, "onShowPress: ");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            // 单击抬起
            Log.i(TAG, "onSingleTapUp: ");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // e1：第1个ACTION_DOWN MotionEvent
            // e2：最后一个ACTION_MOVE MotionEvent

            Log.i(TAG, "onScroll: ");
            if (e1.getX() > originX && e1.getX() < mWidth - paddingRight &&
                    e1.getY() > paddingTop && e1.getY() < mHeight - paddingBottom) {
                //注意：这里的distanceX是e1.getX()-e2.getX()
                Log.i(TAG, "onScroll distanceX : " + distanceX);
                distanceX = -distanceX;
                if (firstPointX + distanceX > firstMaxX) {
                    firstPointX = firstMaxX;
                } else if (firstPointX + distanceX < firstMinX) {
                    firstPointX = firstMinX;
                } else {
                    firstPointX = (int) (firstPointX + distanceX);
                }
                invalidate();
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            // 长按事件
            Log.i(TAG, "onLongPress: ");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(TAG, "onFling: ");
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将View上的触摸事件交给GesturDetector处理
        Log.i(TAG, "onTouchEvent: ");
        if (yValues.size() < 5) {
            return false;
        }
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    /**
     * 获取文字的高度
     */
    private int getTextHeight(Paint paint, String text) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }

    /**
     * 获取文字的宽度
     */
    private int getTextWidth(Paint paint, String text) {
        return (int) paint.measureText(text);
    }

    public int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    /**
     * sp转换px
     */
    public int sp2px(int spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
