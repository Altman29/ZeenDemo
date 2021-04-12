package com.zeen.zeendemo.scroll_rv;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import com.zeen.zeendemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScrollRvActivity extends AppCompatActivity {

    //    private RecyclerView mRecyclerView;
    private ScrollView mRecyclerScrollView;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_rv);

        mRecyclerScrollView = findViewById(R.id.scrollview);
//        mRecyclerView = findViewById(R.id.rsv);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);


    }

    protected void test() {
        mRecyclerScrollView.smoothScrollBy(0, 200);

//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                mRecyclerScrollView.scrollTo(0, 200);
//            }
//        });
    }

    public void clickScroll(View view) {
        Log.e("click","onclick");
        test();
    }

//
//    public void clickScroll(View view) {
//        test();
//    }
}