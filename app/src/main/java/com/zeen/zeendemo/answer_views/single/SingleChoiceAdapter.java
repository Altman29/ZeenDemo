package com.zeen.zeendemo.answer_views.single;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeen.zeendemo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Clark Chen. on 3/11/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class SingleChoiceAdapter extends RecyclerView.Adapter<SingleChoiceAdapter.SingleVH> {

    private List<String> values;
    private onItemClickListener mOnItemClickListener;
    private int defItem = -1;//默认值
    private Context mContext;

    public SingleChoiceAdapter(Context ctx, List<String> values) {
        this.values = values;
        mContext = ctx;
    }

    @NonNull
    @Override
    public SingleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.single_item, parent, false);
        SingleVH holder = new SingleVH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SingleVH holder, int position) {
        holder.mTv.setText(values.get(position));
        if (defItem != -1) {
            /*点的位置跟点击的textview位置一样设置点击后的不同样式*/
            if (defItem == position) {/*设置选中的样式*/
                holder.mTv.setBackground(mContext.getResources().getDrawable(R.drawable.radio_selected));
                holder.mTv.setTextColor(Color.parseColor("#36CB99"));
            } else {
              /*其他的变为未选择状态
                 *设置未选中的样式
                */
                holder.mTv.setBackground(mContext.getResources().getDrawable(R.drawable.radio_unselected));
                holder.mTv.setTextColor(Color.parseColor("#000000"));
            }
        }

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    /**
     * 获取当前点击位置
     * onclick 点击后刷新background & textColor
     * @param position
     */
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }

    public interface onItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class SingleVH extends RecyclerView.ViewHolder {
        private TextView mTv;
        private RelativeLayout mRl;

        public SingleVH(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_tv);
            mRl = itemView.findViewById(R.id.rl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(itemView, getLayoutPosition());
                    }
                }
            });
        }
    }
}
