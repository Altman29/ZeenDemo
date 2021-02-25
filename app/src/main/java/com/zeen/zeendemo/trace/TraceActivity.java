package com.zeen.zeendemo.trace;

import android.os.Bundle;

import com.zeen.zeendemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TraceActivity extends AppCompatActivity {
    private RecyclerView Rv;
    private ArrayList<HashMap<String, Object>> listItem;
    private MyAdapter myAdapter;
    private List<String> mListStamp;

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
        listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        HashMap<String, Object> map6 = new HashMap<String, Object>();
        HashMap<String, Object> map7 = new HashMap<String, Object>();
        HashMap<String, Object> map8 = new HashMap<String, Object>();
        HashMap<String, Object> map9 = new HashMap<String, Object>();
        HashMap<String, Object> map10 = new HashMap<String, Object>();
        HashMap<String, Object> map11 = new HashMap<String, Object>();
        HashMap<String, Object> map12 = new HashMap<String, Object>();
        HashMap<String, Object> map13 = new HashMap<String, Object>();
        HashMap<String, Object> map14 = new HashMap<String, Object>();
        HashMap<String, Object> map15 = new HashMap<String, Object>();

        map1.put("ItemTitle", "美国谷歌公司已发出");
        map1.put("ItemText", "发件人:谷歌 CEO Sundar Pichai");
        map1.put("TimeStamp", "11月");
        listItem.add(map1);

        map2.put("ItemTitle", "国际顺丰已收入");
        map2.put("ItemText", "等待中转");
        map2.put("TimeStamp", "11月");
        listItem.add(map2);

        map3.put("ItemTitle", "国际顺丰转件中");
        map3.put("ItemText", "下一站中国");
        map3.put("TimeStamp", "11月");
        listItem.add(map3);

        map4.put("ItemTitle", "中国顺丰已收入");
        map4.put("ItemText", "下一站广州华南理工大学");
        map4.put("TimeStamp", "10月");
        listItem.add(map4);

        map5.put("ItemTitle", "中国顺丰派件中");
        map5.put("ItemText", "等待派件");
        map5.put("TimeStamp", "10月");
        listItem.add(map5);

        map6.put("ItemTitle", "华南理工大学已签收");
        map6.put("ItemText", "收件人:Carson");
        map6.put("TimeStamp", "10月");
        listItem.add(map6);

        map7.put("ItemTitle", "华南理工大学已签收");
        map7.put("ItemText", "收件人:Carson");
        map7.put("TimeStamp", "10月");
        listItem.add(map7);

        map8.put("ItemTitle", "华南理工大学已签收");
        map8.put("ItemText", "收件人:Carson");
        map8.put("TimeStamp", "9月");
        listItem.add(map8);

        map9.put("ItemTitle", "华南理工大学已签收");
        map9.put("ItemText", "收件人:Carson");
        map9.put("TimeStamp", "9月");
        listItem.add(map9);

        map10.put("ItemTitle", "华南理工大学已签收");
        map10.put("ItemText", "收件人:Carson");
        map10.put("TimeStamp", "8月");
        listItem.add(map10);

        map11.put("ItemTitle", "华南理工大学已签收");
        map11.put("ItemText", "收件人:Carson");
        map11.put("TimeStamp", "7月");
        listItem.add(map11);

        map12.put("ItemTitle", "华南理工大学已签收");
        map12.put("ItemText", "收件人:Carson");
        map12.put("TimeStamp", "7月");
        listItem.add(map12);

        map13.put("ItemTitle", "华南理工大学已签收");
        map13.put("ItemText", "收件人:Carson");
        map13.put("TimeStamp", "6月");
        listItem.add(map13);

        map14.put("ItemTitle", "华南理工大学已签收");
        map14.put("ItemText", "收件人:Carson");
        map14.put("TimeStamp", "6月");
        listItem.add(map14);

        map15.put("ItemTitle", "华南理工大学已签收");
        map15.put("ItemText", "收件人:Carson");
        map15.put("TimeStamp", "5月");
        listItem.add(map15);


        mListStamp = new ArrayList<>();
        for (int i = 0; i < listItem.size(); i++) {
            String stamp = listItem.get(i).get("TimeStamp").toString();
            mListStamp.add(stamp);
        }
    }

    // 绑定数据到RecyclerView
    public void initView() {
        Rv = findViewById(R.id.rv_trace);
        //使用线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);

        //用自定义分割线类设置分割线
        Rv.addItemDecoration(new DividerItemDecoration(this, mListStamp));

        //为ListView绑定适配器
        myAdapter = new MyAdapter(this, listItem);
        Rv.setAdapter(myAdapter);
    }

}