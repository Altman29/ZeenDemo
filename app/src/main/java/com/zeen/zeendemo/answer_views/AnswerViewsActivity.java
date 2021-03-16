package com.zeen.zeendemo.answer_views;

import android.os.Bundle;
import android.view.View;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.zeen.zeendemo.R;
import com.zeen.zeendemo.ToastUtils;
import com.zeen.zeendemo.answer_views.derocation.GridSpaceDecoration;
import com.zeen.zeendemo.answer_views.multiple.MultipleChoiceAdapter;
import com.zeen.zeendemo.answer_views.single.SingleChoiceAdapter;
import com.zeen.zeendemo.answer_views.task.SpacesItemDecoration;
import com.zeen.zeendemo.answer_views.task.TaskAdapter;
import com.zeen.zeendemo.answer_views.task.TaskEntity;
import com.zeen.zeendemo.answer_views.widget.QA_SeekBar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
问答页面
 */
public class AnswerViewsActivity extends AppCompatActivity {

    private QA_SeekBar mSeekBar;
    private RecyclerView mSingleRv;
    private RecyclerView mMultipleRv;
    private RecyclerView mTaskRv;
    private List<String> values;
    private List<String> values2;
    private List<TaskEntity> mTaskEntities;
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
        initTaskRv();

    }

    private void initTaskRv() {
        mTaskEntities = new ArrayList<>();
        mTaskEntities.add(new TaskEntity("自责", "当事情出现一些不好的方面时，会认为是自己的错误导致了结果的发生"));
        mTaskEntities.add(new TaskEntity("贴标签", "给自己或他人下定论、作评价，这些评价和标签往往是草率的、片面的、消极的"));
        mTaskEntities.add(new TaskEntity("自责", "当事情出现一些不好的方面时，会认为是自己的错误导致了结果的发生"));
        mTaskEntities.add(new TaskEntity("贴标签", "给自己或他人下定论、作评价，这些评价和标签往往是草率的、片面的、消极的"));
        TaskAdapter taskAdapter = new TaskAdapter(mTaskEntities);
        taskAdapter.setOnItemClickListener(new TaskAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                ToastUtils.showToastShort(v.getContext(), mTaskEntities.get(pos).title);
            }
        });
//        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this,FlexDirection.ROW,FlexWrap.NOWRAP);
//        layoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
//        mTaskRv.setLayoutManager(layoutManager);
//        mTaskRv.setAdapter(taskAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        mTaskRv.setLayoutManager(manager);
        int space = 20;
        mTaskRv.addItemDecoration(new SpacesItemDecoration(space));
        mTaskRv.setAdapter(taskAdapter);
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
//                ToastUtils.showToastShort(v.getContext(), values.get(pos));
                View viewByPosition = mSingleRv.getLayoutManager().findViewByPosition(pos);
                ToastUtils.showToastShort(v.getContext(), "width: " + viewByPosition.getWidth() + " height: " + viewByPosition.getHeight());
            }
        });
        //LayoutManager
//        mSingleRv.setLayoutManager(new GridLayoutManager(this, 3));
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
        mSingleRv.setLayoutManager(layoutManager);
        mSingleRv.setAdapter(mSingleChoiceAdapter);
//        initDecorationPara();


    }

    private void initDecorationPara() {
        //一行需要显示4个item
        int count = 3;
        //recyclerView的宽度
        int rvWidth = mSingleRv.getWidth();
        //每个item的宽度
        int itemWidth = 200;
        //计算间距
        int space = (rvWidth - count * itemWidth) / (count - 1);
        mSingleRv.addItemDecoration(new GridSpaceDecoration(count, rvWidth, itemWidth, space));
    }


    private void initMultipleRv() {
        values2 = new ArrayList<>();
        values2.add("愤怒");
        values2.add("悲伤");
        values2.add("厌烦");
        values2.add("无奈");
        values2.add("愧疚");
        values2.add("不安");
        values2.add("无助");
        values2.add("绝望");
        values2.add("尴尬");
        values2.add("愤怒");
        values2.add("悲伤");
        values2.add("厌烦");
        values2.add("垂头丧气");
        values2.add("金桔柠檬");
        mMultipleChoiceAdapter = new MultipleChoiceAdapter(this, values2);
        mMultipleChoiceAdapter.setOnItemClickListener(new MultipleChoiceAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                mMultipleChoiceAdapter.setDefSelectSet(pos);
//                ToastUtils.showToastShort(v.getContext(), values2.get(pos));

                View viewByPosition = mMultipleRv.getLayoutManager().findViewByPosition(pos);
                ToastUtils.showToastShort(v.getContext(), "width: " + viewByPosition.getWidth() + " height: " + viewByPosition.getHeight());

            }
        });
        //LayoutManager
//        mMultipleRv.setLayoutManager(new GridLayoutManager(this, 3));
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
        mMultipleRv.setLayoutManager(layoutManager);
        mMultipleRv.setAdapter(mMultipleChoiceAdapter);
    }

    private void initView() {
        mSeekBar = findViewById(R.id.qa_seekbar);
        mSingleRv = findViewById(R.id.singleRv);
        mMultipleRv = findViewById(R.id.multipleRv);
        mTaskRv = findViewById(R.id.taskRv);

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