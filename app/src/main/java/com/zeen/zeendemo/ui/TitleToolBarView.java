package com.zeen.zeendemo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.zeen.zeendemo.R;

/**
 * Copyright (c)2021
 * author: whs
 * created on: 2021/4/8 17:49
 * description: title
 */
public class TitleToolBarView extends Toolbar {
    private TextView title;
    private ImageView backImage;

    /**
     * 设置主标题字号
     */
    private float titleSize = 18;

    /**
     * 标题颜色
     */
    private @ColorInt
    int titleColor = getResources().getColor(R.color.black);
    /**
     * 默认返回图片
     */
    private int backImageId = R.mipmap.quiz_back;

    private onBackClickListener onBackClickListener = null;

    public TitleToolBarView(Context context) {
        super(context);
    }

    public TitleToolBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public TitleToolBarView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init(context, attributeSet);
    }

    public void init(Context context, AttributeSet attributeSet) {
        setContentInsetsAbsolute(6, 0);
        LayoutInflater.from(context).inflate(R.layout.title_toolbar, this);
        title = findViewById(R.id.quiz_tv_title);
        backImage = findViewById(R.id.quiz_iv_back);
        title.setMovementMethod(ScrollingMovementMethod.getInstance());

        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.TitleToolBarView);
        setTitleText(a.getString(R.styleable.TitleToolBarView_titleText));
        setTitleSize(a.getDimension(R.styleable.TitleToolBarView_titleTextSize, titleSize));
        setTitleColor(a.getColor(R.styleable.TitleToolBarView_titleTextColor, titleColor));
        setBackImageId(a.getResourceId(R.styleable.TitleToolBarView_backResId, backImageId));
        a.recycle();

        //返回点击事件
        backImage.setOnClickListener(view -> {
            if (onBackClickListener != null) {
                onBackClickListener.onClick();
            }
        });
    }

    /**
     * 点击回调
     */
    public interface onBackClickListener {
        void onClick();
    }

    public TitleToolBarView setOnBackClickListener(onBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
        return this;
    }

    public TitleToolBarView setTitleText(String titleText) {
        title.setText(titleText);
        return this;
    }

    public TitleToolBarView setTitleColor(@ColorInt int titleColor) {
        title.setTextColor(titleColor);
        return this;
    }

    public TitleToolBarView setTitleText(@StringRes int resid) {
        title.setText(resid);
        return this;
    }

    public TitleToolBarView setTitleSize(float titleSize) {
        if (titleSize != 0) {
            this.titleSize = titleSize;
            title.setTextSize(titleSize);
        }
        return this;
    }

    public TitleToolBarView setBackImageId(@DrawableRes int backImageId) {
        this.backImageId = backImageId;
        backImage.setImageResource(backImageId);
        return this;
    }

    public void setNonIcon() {
        backImage.setVisibility(GONE);
    }

}
