package com.zeen.zeendemo.horizontallist;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeen.zeendemo.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Csy on 2/24/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class recyclerViewadapter extends RecyclerView.Adapter {
    private List<DataBean> lists;
    private Context context;
    private OnItemClickListerner mOnItemClickListerner;

    public recyclerViewadapter(List<DataBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myHolder holder = new myHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false));
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int)
                (context.getResources().getDisplayMetrics().widthPixels / 1.5),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.itemView.setLayoutParams(layoutParams);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: " + lists.get(position).getResId());
        ((myHolder) holder).iv.setImageDrawable(context.getResources().getDrawable(lists.get(position).getResId()));
        ((myHolder) holder).tv1.setText(lists.get(position).getText1());
        ((myHolder) holder).tv2.setText(lists.get(position).getText2());
        //动态改变background(shape)的color
        GradientDrawable background = (GradientDrawable) ((myHolder) holder).rl.getBackground();
        background.setColor(context.getResources().getColor(lists.get(position).getColor()));
        //点击事件触发
        ((myHolder) holder).rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListerner.onItemClick(position);
            }
        });
    }

    //点击事件
    public interface OnItemClickListerner {
        void onItemClick(int position);
    }

    public void setOnItemClickListerner(OnItemClickListerner itemClickListerner) {
        mOnItemClickListerner = itemClickListerner;
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class myHolder extends RecyclerView.ViewHolder {

        private TextView tv1, tv2;
        private ImageView iv;
        private RelativeLayout rl;

        public myHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_relax);
            tv1 = (TextView) itemView.findViewById(R.id.tv_relax_1);
            tv2 = (TextView) itemView.findViewById(R.id.tv_relax_2);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl_relax_item);

        }
    }
}