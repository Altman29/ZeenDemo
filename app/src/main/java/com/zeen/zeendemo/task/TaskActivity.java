package com.zeen.zeendemo.task;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zeen.zeendemo.R;

import butterknife.ButterKnife;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.bind(this);
    }
}