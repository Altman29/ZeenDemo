package com.zeen.zeendemo.answer_views;

import android.os.Bundle;
import android.view.View;

import com.zeen.zeendemo.R;
import com.zeen.zeendemo.ToastUtils;
import com.zeen.zeendemo.answer_views.single.SingleChoiceAdapter;
import com.zeen.zeendemo.answer_views.widget.QA_SeekBar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
问答页面
 */
public class AnswerViewsActivity extends AppCompatActivity {

    private QA_SeekBar mSeekBar;
    private RecyclerView mSingleRv;
    private List<String> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        initView();
        initData();
        initSingleRv();

    }

    private void initData() {
        values = new ArrayList<>();
        values.add("商家");
        values.add("顾客");
        values.add("交警");
        values.add("门卫");
        values.add("陌生人");
    }

    private void initSingleRv() {
        SingleChoiceAdapter adapter = new SingleChoiceAdapter(this, values);
        adapter.setOnItemClickListener(new SingleChoiceAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                adapter.setDefSelect(pos);
                ToastUtils.showToastShort(v.getContext(), values.get(pos));
            }
        });
        mSingleRv.setLayoutManager(new GridLayoutManager(this, 3));
        mSingleRv.setAdapter(adapter);

    }

    private void initView() {
        mSeekBar = findViewById(R.id.qa_seekbar);
        mSingleRv = findViewById(R.id.singleRv);
    }

    public void submit(View view) {

        ToastUtils.showToastShort(this, mSeekBar.getTitleText());
    }
}