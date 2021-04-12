package com.zeen.zeendemo.answer_views.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.zeen.zeendemo.R;

import androidx.appcompat.widget.AppCompatSeekBar;

/**
 * Created by Clark Chen. on 3/11/21.
 * e-mail:altman29@foxmail.com
 * Desc: 数值型
 */
public class QA_SeekBar extends AppCompatSeekBar {

    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private float mTitleTextSize;
    private String mTitleText;//文字的内容

    /**
     * 背景图片
     */
    private int img;
    private Bitmap map;
    //bitmap对应的宽高
    private float img_width, img_height;
    Paint paint;

    private float numTextWidth;
    //测量seekbar的规格
    private Rect rect_seek;
    private Paint.FontMetrics fm;

    public static final int TEXT_ALIGN_LEFT = 0x00000001;
    public static final int TEXT_ALIGN_RIGHT = 0x00000010;
    public static final int TEXT_ALIGN_CENTER_VERTICAL = 0x00000100;
    public static final int TEXT_ALIGN_CENTER_HORIZONTAL = 0x00001000;
    public static final int TEXT_ALIGN_TOP = 0x00010000;
    public static final int TEXT_ALIGN_BOTTOM = 0x00100000;
    /**
     * 文本中轴线X坐标
     */
    private float textCenterX;
    /**
     * 文本baseline线Y坐标
     */
    private float textBaselineY;
    /**
     * 文字的方位
     */
    private int textAlign;
    private int mScale;
    private int mBeginInt;
    private int mEndInt;

    public QA_SeekBar(Context context) {
        this(context, null);
    }

    public QA_SeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QA_SeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Qa_SeekBar, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.Qa_SeekBar_textSize:
                    mTitleTextSize = array.getDimension(attr, 15f);
                    break;
                case R.styleable.Qa_SeekBar_textColor:
                    mTitleTextColor = array.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.Qa_SeekBar_img:
                    img = array.getResourceId(attr, R.mipmap.ic_launcher);
                    break;
            }
        }
        array.recycle();
        getImgWH();
        paint = new Paint();
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setTextSize(mTitleTextSize);//设置文字大小
        paint.setColor(mTitleTextColor);//设置文字颜色
        //设置控件的padding 给提示文字留出位置  注top是seekbar与top的padding，更img无关 此处top:100测试用
        setPadding((int) Math.ceil(img_width) / 2, (int) Math.ceil(img_height) + 100, (int) Math.ceil(img_height) / 2, (int) Math.ceil(img_height));
        textAlign = TEXT_ALIGN_CENTER_HORIZONTAL | TEXT_ALIGN_CENTER_VERTICAL;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 获取图片的宽高
     */
    private void getImgWH() {
        map = BitmapFactory.decodeResource(getResources(), img);
        img_width = map.getWidth();
        img_height = map.getHeight();
    }

    /**
     * 40 , 10 暂定
     *
     * @param canvas
     */
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setTextLocation();//定位文本绘制的位置
        rect_seek = this.getProgressDrawable().getBounds();
        //定位文字背景图片的位置
        float bm_x = rect_seek.width() * getProgress() / getMax();
        float bm_y = rect_seek.height() + getPaddingTop() - img_height - 40;
        //计算文字的中心位置在bitmap
        float text_x = rect_seek.width() * getProgress() / getMax() + (img_width - numTextWidth) / 2;
        canvas.drawBitmap(map, bm_x, bm_y, paint);//画背景图
        canvas.drawText(mTitleText, text_x, (float) (textBaselineY + bm_y - 10), paint);//画文字

        //画俩个最值
        int width = getWidth();
        int height = getHeight();
        canvas.drawText(mBeginInt + "", rect_seek.width() * 9 / getMax(), height, paint);
        canvas.drawText(mEndInt + "", rect_seek.width(), height, paint);
    }

    public String getTitleText() {
        return mTitleText;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        invalidate();//监听手势滑动，不断重绘文字和背景图的显示位置
        return super.onTouchEvent(event);
    }

    /**
     * 定位文本绘制的位置
     */
    private void setTextLocation() {

        fm = paint.getFontMetrics();
        //文本的宽度
        mTitleText = getProgress() / mScale + "";

        numTextWidth = paint.measureText(mTitleText);

        float textCenterVerticalBaselineY = img_height / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
        switch (textAlign) {
            case TEXT_ALIGN_CENTER_HORIZONTAL | TEXT_ALIGN_CENTER_VERTICAL:
                textCenterX = img_width / 2;
                textBaselineY = textCenterVerticalBaselineY;
                break;
            case TEXT_ALIGN_LEFT | TEXT_ALIGN_CENTER_VERTICAL:
                textCenterX = numTextWidth / 2;
                textBaselineY = textCenterVerticalBaselineY;
                break;
            case TEXT_ALIGN_RIGHT | TEXT_ALIGN_CENTER_VERTICAL:
                textCenterX = img_width - numTextWidth / 2;
                textBaselineY = textCenterVerticalBaselineY;
                break;
            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_CENTER_HORIZONTAL:
                textCenterX = img_width / 2;
                textBaselineY = img_height - fm.bottom;
                break;
            case TEXT_ALIGN_TOP | TEXT_ALIGN_CENTER_HORIZONTAL:
                textCenterX = img_width / 2;
                textBaselineY = -fm.ascent;
                break;
            case TEXT_ALIGN_TOP | TEXT_ALIGN_LEFT:
                textCenterX = numTextWidth / 2;
                textBaselineY = -fm.ascent;
                break;
            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_LEFT:
                textCenterX = numTextWidth / 2;
                textBaselineY = img_height - fm.bottom;
                break;
            case TEXT_ALIGN_TOP | TEXT_ALIGN_RIGHT:
                textCenterX = img_width - numTextWidth / 2;
                textBaselineY = -fm.ascent;
                break;
            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_RIGHT:
                textCenterX = img_width - numTextWidth / 2;
                textBaselineY = img_height - fm.bottom;
                break;
        }
    }

    public void setRange(int begin, int end) {
        mBeginInt = begin;
        mEndInt = end;
        if (end == 100) {
            mScale = 1;
        } else if (end == 10) {
            mScale = 10;
        } else {
            mScale = 1;
        }
    }
}
