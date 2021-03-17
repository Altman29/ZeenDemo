package com.zeen.zeendemo.answer_views.withexra;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.zeen.zeendemo.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Clark Chen. on 3/11/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class MultipleChoiceAdapter extends RecyclerView.Adapter<MultipleChoiceAdapter.MultipleVH> {

    private List<String> values;
    private onItemClickListener mOnItemClickListener;
    private Set<Integer> defItems = new HashSet<>();
    private Context mContext;

    public MultipleChoiceAdapter(Context ctx, List<String> values) {
        this.values = values;
        mContext = ctx;
    }

    @NonNull
    @Override
    public MultipleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.multiple_item, parent, false);
        MultipleVH holder = new MultipleVH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MultipleVH holder, int position) {
        holder.mTv.setText(values.get(position));
        ViewGroup.LayoutParams layoutParams = holder.mTv.getLayoutParams();
        if (layoutParams instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) holder.mTv.getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
        }
        if (defItems != null) {
            /*点的位置跟点击的textview位置一样设置点击后的不同样式*/
            if (defItems.contains(position)) {/*设置选中的样式*/
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
     * 获取当前点击位置Set
     * onclick 点击后刷新background & textColor
     *
     * @param position
     */
    public void setDefSelectSet(int position) {
        if (defItems.contains(position))
            defItems.remove(position);
        else
            this.defItems.add(position);
        notifyDataSetChanged();
    }

    public Set<Integer> getSelect(){
        return defItems;
    }

    public List<String> getSelectedValues() {

        List<String> result = new ArrayList<>();

        for (int i : defItems) {
            result.add(values.get(i));
        }

        return null == result ? null : result;
    }

    public interface onItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class MultipleVH extends RecyclerView.ViewHolder {
        private TextView mTv;

        public MultipleVH(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_tv);

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
