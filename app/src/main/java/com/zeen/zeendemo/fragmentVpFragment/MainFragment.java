package com.zeen.zeendemo.fragmentVpFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeen.zeendemo.R;
import com.zeen.zeendemo.fragmentVpFragment.widget.CustomFragmentPagerAdapter;
import com.zeen.zeendemo.fragmentVpFragment.widget.ModifyTabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by Clark Chen. on 3/3/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class MainFragment extends Fragment {


    private ViewPager mVp;
    private ModifyTabLayout mTabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainFragment", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MainFragment", "onCreateView");
        View inflate = inflater.inflate(R.layout.mainfragment, null);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {

    }

    private void initView(View view) {

        mVp = view.findViewById(R.id.vp_container);
        mTabLayout = view.findViewById(R.id.tabLayout);
        mTabLayout.setViewHeight(dp2px(35));
        mTabLayout.setBottomLineWidth(dp2px(20));//indicator 宽高
        mTabLayout.setBottomLineHeight(dp2px(6));
        mTabLayout.setItemInnerPaddingLeft(dp2px(6));//tab 左右padding 紧凑或铺开可超屏
        mTabLayout.setItemInnerPaddingRight(dp2px(6));
        mTabLayout.setmTextColorSelect(ContextCompat.getColor(getContext(), R.color.black));//选中时颜色
        mTabLayout.setmTextColorUnSelect(ContextCompat.getColor(getContext(), R.color.color_666666));//未选中时颜色
        mTabLayout.setTextSize(16);
        mTabLayout.setBottomLineHeightBgResId(R.mipmap.smile);
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getFragmentManager());
        adapter.addFrag(new FirstFragment(), "冥想");
        adapter.addFrag(new FirstFragment(), "呼吸");
        adapter.addFrag(new FirstFragment(), "场景");
        adapter.addFrag(new FirstFragment(), "文章");
        mVp.setAdapter(adapter);
        mVp.setOffscreenPageLimit(adapter.getCount());
        mTabLayout.setupWithViewPager(mVp);

    }


    /**
     * dp转换成px
     */
    public int dp2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MainFragment", "onResume");

    }
}
