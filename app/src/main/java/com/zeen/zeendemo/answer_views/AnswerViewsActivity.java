package com.zeen.zeendemo.answer_views;

import android.os.Bundle;
import android.view.View;

import com.zeen.zeendemo.R;
import com.zeen.zeendemo.ToastUtils;
import com.zeen.zeendemo.answer_views.widget.QA_SeekBar;

import androidx.appcompat.app.AppCompatActivity;

/*
问答页面
 */
public class AnswerViewsActivity extends AppCompatActivity {

    private QA_SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        initView();

    }

    private void initView() {

        mSeekBar = findViewById(R.id.qa_seekbar);
    }

    public void submit(View view) {

        ToastUtils.showToastShort(this, mSeekBar.getTitleText());
    }
}