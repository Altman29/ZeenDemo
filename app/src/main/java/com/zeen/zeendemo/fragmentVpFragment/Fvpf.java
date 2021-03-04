package com.zeen.zeendemo.fragmentVpFragment;

import android.os.Bundle;

import com.zeen.zeendemo.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Fvpf extends AppCompatActivity {

    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fvpf);

        initTrans();
        initFirstFragment();
        initView();
        initData();
    }

    private void initTrans() {
        FragmentManager manager = getSupportFragmentManager();
        mTransaction = manager.beginTransaction();
    }

    private void initFirstFragment() {
        MainFragment mainFragment = new MainFragment();
        mTransaction.add(R.id.rl_fvpf, mainFragment, "mainFragment");
        mTransaction.commit();
    }

    private void initData() {

    }

    private void initView() {
    }
}