package com.zeen.zeendemo.horizontallist;

import android.os.Bundle;

import com.zeen.zeendemo.R;
import com.zeen.zeendemo.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalRvActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<DataBean> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_rv);
        initView();
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        recyclerViewadapter adapter = new recyclerViewadapter(lists, this);
        adapter.setOnItemClickListerner(new recyclerViewadapter.OnItemClickListerner() {
            @Override
            public void onItemClick(int position) {
                //go details
                ToastUtils.showToastShort(HorizontalRvActivity.this, lists.get(position).getText1() + " >>>");
            }
        });
        int space = 20;
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(space));
        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        lists = new ArrayList<>();
        lists.add(new DataBean(R.mipmap.ic_launcher, "111", "111", R.color.design_default_color_error));
        lists.add(new DataBean(R.mipmap.ic_launcher, "222", "222", R.color.design_default_color_primary));
        lists.add(new DataBean(R.mipmap.ic_launcher, "333", "333", R.color.design_default_color_secondary));
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rv_relax);

    }

}