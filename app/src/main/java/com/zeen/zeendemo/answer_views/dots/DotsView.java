package com.zeen.zeendemo.answer_views.dots;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.zeen.zeendemo.R;

import androidx.annotation.Nullable;

/**
 * Created by Clark Chen. on 3/16/21.
 * e-mail:altman29@foxmail.com
 * Desc: Timer.
 */
public class DotsView extends LinearLayout {

    private View mDot1;
    private View mDot2;
    private View mDot3;

    public DotsView(Context context) {
        this(context, null);
    }

    public DotsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.dots_view, this);
        mDot1 = view.findViewById(R.id.dot_1);
        mDot2 = view.findViewById(R.id.dot_2);
        mDot3 = view.findViewById(R.id.dot_3);
        startLoadAnim();
    }

    public DotsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void startLoadAnim() {
        for (int i = 0; i < 3; i++) {
            mDot1.setBackground(getResources().getDrawable(R.drawable.login_dot_focused));
            mDot2.setBackground(getResources().getDrawable(R.drawable.login_dot_normal));
            mDot3.setBackground(getResources().getDrawable(R.drawable.login_dot_normal));

        }

        mDot1.setBackground(getResources().getDrawable(R.drawable.login_dot_focused));
        mDot1.setBackground(getResources().getDrawable(R.drawable.login_dot_normal));

        mDot2.setBackground(getResources().getDrawable(R.drawable.login_dot_focused));
        mDot2.setBackground(getResources().getDrawable(R.drawable.login_dot_normal));

        mDot3.setBackground(getResources().getDrawable(R.drawable.login_dot_focused));
        mDot3.setBackground(getResources().getDrawable(R.drawable.login_dot_normal));

    }

}
