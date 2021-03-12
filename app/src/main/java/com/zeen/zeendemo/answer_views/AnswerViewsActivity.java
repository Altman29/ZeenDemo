package com.zeen.zeendemo.answer_views;

import android.os.Bundle;
import android.view.View;

import com.zeen.zeendemo.R;
import com.zeen.zeendemo.ToastUtils;
import com.zeen.zeendemo.answer_views.derocation.GridSpaceDecoration;
import com.zeen.zeendemo.answer_views.multiple.MultipleChoiceAdapter;
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
    private RecyclerView mMultipleRv;
    private List<String> values;
    private MultipleChoiceAdapter mMultipleChoiceAdapter;
    private SingleChoiceAdapter mSingleChoiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        initView();
        initData();
        initSingleRv();
        initMultipleRv();

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
        mSingleChoiceAdapter = new SingleChoiceAdapter(this, values);
        mSingleChoiceAdapter.setOnItemClickListener(new SingleChoiceAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                mSingleChoiceAdapter.setDefSelect(pos);
                ToastUtils.showToastShort(v.getContext(), values.get(pos));
            }
        });
        mSingleRv.setLayoutManager(new GridLayoutManager(this, 3));
        mSingleRv.setAdapter(mSingleChoiceAdapter);
        initDecorationPara();

    }

    private void initDecorationPara() {
        //一行需要显示4个item
        int count = values.size();
        //recyclerView的宽度
        int rvWidth = mSingleRv.getWidth();
        //每个item的宽度
//        int itemWidth = mSingleChoiceAdapter.getItemWidth();
        int itemWidth = 200;
        //计算间距
        int space = (rvWidth - count * itemWidth) / (count - 1);
        mSingleRv.addItemDecoration(new GridSpaceDecoration(count, rvWidth, itemWidth, space));
    }


    private void initMultipleRv() {
        values = new ArrayList<>();
        values.add("愤怒");
        values.add("悲伤");
        values.add("厌烦");
        values.add("无奈");
        values.add("愧疚");
        values.add("不安");
        values.add("无助");
        values.add("绝望");
        values.add("尴尬");
        values.add("愤怒");
        values.add("悲伤");
        values.add("厌烦");
        values.add("垂头丧气");
        values.add("垂头丧气");
        mMultipleChoiceAdapter = new MultipleChoiceAdapter(this, values);
        mMultipleChoiceAdapter.setOnItemClickListener(new MultipleChoiceAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                mMultipleChoiceAdapter.setDefSelectSet(pos);
                ToastUtils.showToastShort(v.getContext(), values.get(pos));
            }
        });
        mMultipleRv.setLayoutManager(new GridLayoutManager(this, 3));
        mMultipleRv.setAdapter(mMultipleChoiceAdapter);
    }

    private void initView() {
        mSeekBar = findViewById(R.id.qa_seekbar);
        mSingleRv = findViewById(R.id.singleRv);
        mMultipleRv = findViewById(R.id.multipleRv);

    }

    //数值
    public void submit(View view) {
        ToastUtils.showToastShort(this, mSeekBar.getTitleText());
    }

    //单选
    public void submit1(View view) {
        mSingleChoiceAdapter.reset();
    }

    //多选
    public void submit2(View view) {
        List<String> values = mMultipleChoiceAdapter.getSelectedValues();
        String result = "";
        for (String s : values) {
            result += s + " ";
        }

        ToastUtils.showToastShort(this, result);
    }
}