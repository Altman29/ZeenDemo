package com.zeen.zeendemo.bottomnav;

import android.os.Bundle;
import android.view.View;

import com.zeen.mvplibrary.bus.AlBus;
import com.zeen.mvplibrary.bus.MessageEvent;
import com.zeen.zeendemo.R;
import com.zeen.zeendemo.utils.LogUtils;

public class Activity_quiz extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

    }

    public void post(View view) {
        LogUtils.e("post event");
        AlBus.Companion.get().post(new MessageEvent<>(MessageEvent.EVENT_TYPE_QUIZ_PROCESS_END));
    }
}