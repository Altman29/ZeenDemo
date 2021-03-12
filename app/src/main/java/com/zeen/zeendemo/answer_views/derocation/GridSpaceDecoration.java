package com.zeen.zeendemo.answer_views.derocation;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Clark Chen. on 3/12/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class GridSpaceDecoration extends RecyclerView.ItemDecoration {

    //列表item的总个数
    private int mTotalCount;
    //recyclerView的宽度
    private int mTotalWidth;
    //每个item的宽度
    private int mItemWidth;
    //间距
    private int mSpace;

    public GridSpaceDecoration(int totalCount, int totalWidth, int itemWidth, int space) {
        this.mTotalCount = totalCount;
        this.mTotalWidth = totalWidth;
        this.mItemWidth = itemWidth;
        this.mSpace = space;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();

        //获取每行的个数
        int spanCount = manager.getSpanCount();
        //获取当前位置
        int position = parent.getChildAdapterPosition(view);
        //当前列
        int column = position % spanCount;

        //计算垂直方向上的间距，当不是首行时
        if (position >= spanCount) {
            //添加垂直方向上的间距
            outRect.top = mSpace;
        }

        //获取左边距
        int leftMargin = getLeftMargin(position, spanCount);
        outRect.left = column * mSpace / spanCount + leftMargin;
        outRect.right = mSpace - (column + 1) * mSpace / spanCount;
    }

    /**
     * 根据位置，判断在第几行，0，1，2，3
     */
    private int getRow(int position, int spanCount) {
        return position / spanCount;
    }

    /**
     * 返回第N行会有多少个元素
     */
    private int getRowCount(int row, int spanCount) {
        if ((row + 1) * spanCount <= mTotalCount) {
            return spanCount;
        } else {
            return mTotalCount - row * spanCount;
        }
    }

    /**
     * 获取左侧的边距
     */
    private int getLeftMargin(int position, int spanCount) {
        //判断是第几行
        int row = getRow(position, spanCount);
        //该行有多少个元素
        int rowCount = getRowCount(row, spanCount);
        //如果该行元素都满了
        if (rowCount == spanCount) {
            return 0;
        } else {
            return (mTotalWidth - (rowCount * mItemWidth + (rowCount - 1) * mSpace)) / 2;
        }
    }
}

