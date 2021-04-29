package com.zeen.zeendemo.bottomnav;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zeen.mvplibrary.bus.MessageEvent;
import com.zeen.zeendemo.R;
import com.zeen.zeendemo.ToastUtils;
import com.zeen.zeendemo.bottomnav.fragments.Fragment1;
import com.zeen.zeendemo.bottomnav.fragments.Fragment1_2;
import com.zeen.zeendemo.bottomnav.fragments.Fragment2;
import com.zeen.zeendemo.bottomnav.fragments.Fragment3;
import com.zeen.zeendemo.bottomnav.fragments.Fragment4;
import com.zeen.zeendemo.utils.LogUtils;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavHelper.OnTabChangeListener {

    @BindView(R.id.lay_container) FrameLayout mLayContainer;
    @BindView(R.id.navigation) BottomNavigationView mNavigation;

    private NavHelper mNavHelper;
    private Menu mMenu;

    private int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        ButterKnife.bind(this);

        mNavHelper = new NavHelper(this, R.id.lay_container, getSupportFragmentManager(), this);

        mNavHelper.add(R.id.navigation_today, new NavHelper.Tab<>(Fragment1.class, "fragment1"
        )).add(R.id.navigation_past, new NavHelper.Tab<>(Fragment2.class, "fragment2"))
                .add(R.id.navigation_relax, new NavHelper.Tab<>(Fragment3.class, "fragment3"))
                .add(R.id.navigation_mine, new NavHelper.Tab<>(Fragment4.class, "fragment4"));


        mNavigation.setOnNavigationItemSelectedListener(this);

        NavInit();
    }

    private void NavInit() {
        mMenu = mNavigation.getMenu();
        mMenu.performIdentifierAction(R.id.navigation_today, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        LogUtils.e("click: " + item.getTitle());

        return mNavHelper.performClickMenu(item.getItemId());
    }

    @Override
    public void onTabChange(NavHelper.Tab newTab, NavHelper.Tab oldTab) {
        if (newTab != null)
            LogUtils.e("new tab: " + newTab.clx.getName());
        if (oldTab != null)
            LogUtils.e("old tab: " + oldTab.clx.getName());
    }

    public void goQuiz(View view) {
        startActivity(new Intent(this, Activity_quiz.class));
    }

    @Override
    protected void onEventReceived(MessageEvent event) {
        super.onEventReceived(event);
        switch (event.getEventType()) {
            case MessageEvent.EVENT_TYPE_QUIZ_PROCESS_END://问答流程结束
                ToastUtils.showToastShort(getApplicationContext(), "问答流程结束");
                //bottomNav 改变Fragment1- > 1_1  replace
                mNavHelper.replace(R.id.navigation_today, new NavHelper.Tab<>(Fragment1_2.class, "fragment1_2"));
                code = 2;
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("code: " + code);
        if (code == 2){
            mNavigation.setSelectedItemId(R.id.navigation_today);
            mMenu.performIdentifierAction(R.id.navigation_today, 0);

        }
    }
}