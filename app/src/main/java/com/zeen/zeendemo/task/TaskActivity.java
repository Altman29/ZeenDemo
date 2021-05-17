package com.zeen.zeendemo.task;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zeen.zeendemo.R;
import com.zeen.zeendemo.utils.ScreenAdaptUtil;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ScreenAdaptUtil.Companion.setCustomDensity(this, getApplication());
    }
}