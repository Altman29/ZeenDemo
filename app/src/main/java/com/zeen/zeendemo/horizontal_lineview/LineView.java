package com.zeen.zeendemo.horizontal_lineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.zeen.zeendemo.R;

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
    private int[] mYLables = {0, 1, 2, 3, 4};//Y轴数字
    private float minValueY = 0;//y轴最小值
    private float maxValueY = 4;//y轴最大值
    private int mLableCountY = mYLables.length;//y轴刻度个数
    private List<String> xValues = new ArrayList<>();
    private List<Float> yValues = new ArrayList<>();
    private int mWidth;
    private int mHeight;
    private int originX;//原点X坐标
    private int originY;//原点Y坐标
    private int intervalX;//x轴刻度的间隔
    private int intervalY = 80;//y轴刻度的间隔
    private int paddingTop = 140;//上下左右的padding
    private int paddingLeft = dp2px(20);
    private int paddingRight = dp2px(20);
    private int paddingBottom = 160;
    private int firstPointX;//第一个点的x坐标
    private int xScaleHeight = dp2px(6);//x轴刻度线高度(可隐藏)
    private int xyTextSize = sp2px(10);//xy轴文字大小

    private int bigCircleR = 12;//折线图中的圆圈
    private int smallCircleR = 9;//折线图中为了避免折线穿透的圆圈
    private GestureDetector mGestureDetector;//滑动手势
    private int firstMinX;//移动时第一个点的最小x值
    private int firstMaxX;//移动时第一个点的最大x值
    private int backGroundColor = Color.parseColor("#FFFFFF");//view的背景颜色
    private Paint mPaintText, mPaintDash, mPaintLine, mPaintSmallCircle;//画笔

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetector(context, new MyOnGestureListener());
        initPaint();
    }

    private void initPaint() {

        mPaintSmallCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSmallCircle.setColor(Color.WHITE);
        mPaintSmallCircle.setStyle(Paint.Style.FILL);

        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLine.setColor(Color.parseColor("#36cB99"));
        mPaintLine.setStrokeWidth(dp2px(2));
        mPaintLine.setStyle(Paint.Style.STROKE);

        //x轴下方文字画笔
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(Color.BLACK);
        mPaintText.setTextSize(xyTextSize);
        mPaintText.setStrokeWidth(2f);
        //虚线画笔
        mPaintDash = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintDash.setColor(Color.parseColor("#778899"));
        mPaintDash.setStyle(Paint.Style.STROKE);
        mPaintDash.setStrokeWidth(1f);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            mWidth = getWidth();
            mHeight = getHeight();
            intervalX = mWidth / 6;
            System.out.println("mHeight: " + mHeight);//根据ui设计暂定220dp
            originX = 0;
            originY = mHeight - paddingBottom;

            firstPointX = 0;
            firstMinX = mWidth - originX - (xValues.size() - 1) * intervalX;

            //滑动时，第一个点x值最大为paddingLeft，在大于这个值就不能滑动了
            firstMaxX = firstPointX;
            setBackgroundColor(backGroundColor);
            setBackground(getResources().getDrawable(R.drawable.lineview));
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawX(canvas);
        drawLine(canvas);
    }

    public void setData(ArrayList<ViewPoint> pointList) {
        for (ViewPoint viewPoint : pointList) {
            xValues.add(viewPoint.x);
            yValues.add(viewPoint.y);
        }
    }

    private void drawLine(Canvas canvas) {
        // 画折线
        float aver = (mLableCountY - 1) * intervalY / (maxValueY - minValueY); //y轴最小单位的距离
//        float aver = 1; //y轴最小单位的距离
        Path path = new Path();
        //先移动到第一个点的位置
        path.moveTo(firstPointX, mHeight - paddingBottom - yValues.get(0) * aver + minValueY * aver);
        //绘制折线
        for (int i = 0; i < yValues.size(); i++) {
            path.lineTo(firstPointX + i * intervalX, mHeight - paddingBottom - yValues.get(i) * aver + minValueY * aver);
        }
        canvas.drawPath(path, mPaintLine);

        // 折线中的圆点
        for (int i = 0; i < yValues.size(); i++) {
            canvas.drawCircle(firstPointX + i * intervalX,
                    mHeight - paddingBottom - yValues.get(i) * aver + minValueY * aver, bigCircleR, mPaintLine);
            //小圆的颜色和view背景颜色相同，这样看上去折线是没有穿透圆的
            canvas.drawCircle(firstPointX + i * intervalX,
                    mHeight - paddingBottom - yValues.get(i) * aver + minValueY * aver, smallCircleR, mPaintSmallCircle);
        }
    }

    private void drawX(Canvas canvas) {

        for (int i = 0; i < xValues.size(); i++) {
            // x轴上的文字
            canvas.drawText(xValues.get(i), firstPointX + i * intervalX - getTextWidth(mPaintText, "23") / 2,
                    originY + dp2px(20), mPaintText);
        }

        //虚线
        Path path1 = new Path();
        DashPathEffect dash = new DashPathEffect(new float[]{8, 10, 8, 10}, 0);
        mPaintDash.setPathEffect(dash);
        for (int i = 0; i < mLableCountY; i++) {
            path1.moveTo(originX, mHeight - paddingBottom - i * intervalY);
            path1.lineTo(mWidth, mHeight - paddingBottom - i * intervalY);
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
//            if (e1.getX() > originX && e1.getX() < mWidth - paddingRight &&
            if (e1.getX() > originX && e1.getX() < mWidth &&
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

    private Shader getShader() {
        int[] shadeColors = {Color.argb(1, 83, 210, 168), Color.argb(1, 54, 203, 153)};
        return new LinearGradient((getMeasuredWidth() / 2), getMeasuredHeight(), (getMeasuredWidth() / 2), 0f, shadeColors, null, Shader.TileMode.CLAMP);
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