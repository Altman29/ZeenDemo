package com.zeen.zeendemo.answer_views.task;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Csy on 2/24/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int size;

    public SpacesItemDecoration(int space, int size) {
        this.space = space;
        this.size = size;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);//item position
        if (0 == position) {
            outRect.left = space * 2;
        } else {
            outRect.left = space;
        }
        if (position == size - 1) {
            outRect.right = space * 2;
        } else {
            outRect.right = space;
        }
        outRect.bottom = space * 2;
        outRect.top = space * 2;

// Add top margin only for the first item to avoid double space between items
    }
}
