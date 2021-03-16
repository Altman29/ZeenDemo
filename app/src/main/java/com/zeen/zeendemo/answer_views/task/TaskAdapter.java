package com.zeen.zeendemo.answer_views.task;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeen.zeendemo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Clark Chen. on 3/16/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskVH> {

    private onItemClickListener mOnItemClickListener;
    private List<TaskEntity> mList;

    public TaskAdapter(List<TaskEntity> list) {
        mList = list;
    }

    @NonNull
    @Override
    public TaskVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        TaskVH taskVH = new TaskVH(view);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int)
                (parent.getContext().getResources().getDisplayMetrics().widthPixels / 2.5),
                (int)
                        (parent.getContext().getResources().getDisplayMetrics().widthPixels / 2.3));
        taskVH.itemView.setLayoutParams(layoutParams);
        return taskVH;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskVH holder, int position) {
        holder.mTextTitle.setText(mList.get(position).title);
        holder.mTextContent.setText(mList.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface onItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class TaskVH extends RecyclerView.ViewHolder {
        private TextView mTextTitle;
        private TextView mTextContent;

        public TaskVH(@NonNull View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.item_title);
            mTextContent = itemView.findViewById(R.id.item_content);

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
