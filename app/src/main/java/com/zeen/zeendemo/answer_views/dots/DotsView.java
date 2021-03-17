package com.zeen.zeendemo.answer_views.dots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by Clark Chen. on 3/16/21.
 * e-mail:altman29@foxmail.com
 * Desc: Timer.
 */
public class DotsView extends View {

    private final int COUNT = 3;
    private Paint mPaint1;
    private Paint mPaint2;
    private int current;

    public DotsView(Context context) {
        super(context);
        init(context);
    }

    public DotsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DotsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint1 = new Paint();
        mPaint1.setColor(Color.parseColor("#36CB99"));
        mPaint2 = new Paint();
        mPaint2.setColor(Color.parseColor("#D1DADF"));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int space = width / (COUNT + 1);

        for (int i = 0; i < COUNT; i++) {
            canvas.drawCircle(space * (i + 1.2f), height / 2, Math.min(width, height) / 8, i == current ? mPaint1 : mPaint2);
        }

        postDelayed(new Runnable() {
            @Override
            public void run() {
                current = (++current) % COUNT;
                invalidate();
            }
        }, 200);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }
}
