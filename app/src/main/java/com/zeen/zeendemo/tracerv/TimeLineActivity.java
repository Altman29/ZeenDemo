package com.zeen.zeendemo.tracerv;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeen.zeendemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TimeLineActivity extends AppCompatActivity {
    private RecyclerView Rv;
    private List<TimeLineBean> listItem;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace);

        // 初始化显示的数据
        initData();

        // 绑定数据到RecyclerView
        initView();

    }

    // 初始化显示的数据
    public void initData() {
        listItem = new ArrayList<TimeLineBean>();

        for (int i = 0; i < 6; i++) {
            listItem.add(new TimeLineBean(1,"" ,i+"月"));
            for (int j = 0; j < new Random().nextInt(3) + 2; j++) {
                listItem.add(new TimeLineBean(2,"内容内容内容内容" + i + "-" +j,i+"月"));
            }
        }
    }

    // 绑定数据到RecyclerView
    public void initView() {
        Rv = findViewById(R.id.rv_trace);
        //使用线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        Rv.setLayoutManager(layoutManager);

        //用自定义分割线类设置分割线
        Rv.addItemDecoration(new TimeLineItemDecoration(this));

        //为ListView绑定适配器
        myAdapter = new MyAdapter(this, listItem);
        Rv.setAdapter(myAdapter);
    }

    static class TimeLineBean{
        public int type;
        public String msg;
        public String time;

        public TimeLineBean(int type, String msg, String time) {
            this.type = type;
            this.msg = msg;
            this.time = time;
        }
    }

    public static class MyAdapter extends RecyclerView.Adapter {
        private LayoutInflater inflater;
        private List<TimeLineBean> listItem;

        //构造函数，传入数据
        public MyAdapter(Context context, List<TimeLineBean> listItem) {
            inflater = LayoutInflater.from(context);
            this.listItem = listItem;
        }

        @Override
        public int getItemViewType(int position) {
            return listItem.get(position).type;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder holder;
            if (viewType == 1){
                holder = new Viewholder1(inflater.inflate(R.layout.trace_item_title, null));

            }else {
                holder = new Viewholder2(inflater.inflate(R.layout.trace_item_content, null));
            }
            return holder;
        }//在这里把ViewHolder绑定Item的布局

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof Viewholder1){
                Viewholder1 vh = (Viewholder1) holder;
                //type1 即是 新月份时.使用单一月份布局
                vh.Text.setText(listItem.get(position).time);
            }else {
                Viewholder2 vh = (Viewholder2) holder;
                vh.Title.setText(listItem.get(position).msg);
                vh.Text.setText(listItem.get(position).time);
            }
        }

        //返回Item数目
        @Override
        public int getItemCount() {
            return listItem.size();
        }


        //定义Viewholder
        static class Viewholder1 extends RecyclerView.ViewHolder {
            private TextView  Text;

            public Viewholder1(View root) {
                super(root);
                Text = (TextView) root.findViewById(R.id.tv_item_text);
            }

        }

        static class Viewholder2 extends RecyclerView.ViewHolder {
            private TextView Title, Text;

            public Viewholder2(View root) {
                super(root);
                Title = (TextView) root.findViewById(R.id.tv_item_title);
                Text = (TextView) root.findViewById(R.id.tv_item_text);
            }
        }



    }
}