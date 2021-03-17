package com.zeen.zeendemo.answer_views.withexra;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.zeen.zeendemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class WeightActivity extends AppCompatActivity {

    private RecyclerView mMultipleRv;
    private List<String> values2;
    private MultipleChoiceAdapter mMultipleChoiceAdapter;
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private Set<Integer> mSelect;
    private RadioGroup mRg;
    private RadioButton mRb1;
    private RadioButton mRb2;
    private RadioButton mRb3;
    private RadioButton mRb4;
    private int math;
    private EditText mEt1;
    private EditText mEt2;
    private EditText mEt3;
    private EditText mEt4;
    private TextView mResult1;
    private TextView mResult2;
    private TextView mResult3;
    private TextView mResult4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        initView();
        initMultipleRv();
    }

    private void initMultipleRv() {
        values2 = new ArrayList<>();
        values2.add("172,171,170,169,168,167");
        values2.add("166,165");
        values2.add("164,163");
        values2.add("162,161,160");
        mMultipleChoiceAdapter = new MultipleChoiceAdapter(this, values2);
        mMultipleChoiceAdapter.setOnItemClickListener(new MultipleChoiceAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                mMultipleChoiceAdapter.setDefSelectSet(pos);
                mSelect = mMultipleChoiceAdapter.getSelect();
                refreshAvailableEt(mSelect);
            }
        });
        //LayoutManager
//        mMultipleRv.setLayoutManager(new GridLayoutManager(this, 3));
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
        mMultipleRv.setLayoutManager(layoutManager);
        mMultipleRv.setAdapter(mMultipleChoiceAdapter);
    }

    private void refreshAvailableEt(Set<Integer> select) {
        if (select.isEmpty()) {
        } else {
            if (select.contains(0))
                et1.setVisibility(View.VISIBLE);
            else
                et1.setVisibility(View.GONE);
            if (select.contains(1))
                et2.setVisibility(View.VISIBLE);
            else
                et2.setVisibility(View.GONE);
            if (select.contains(2))
                et3.setVisibility(View.VISIBLE);
            else
                et3.setVisibility(View.GONE);
            if (select.contains(3))
                et4.setVisibility(View.VISIBLE);
            else
                et4.setVisibility(View.GONE);
        }
        refreshTv();
    }

    private void refreshTv() {

        if (et1.getVisibility() == View.GONE) {
            tv1.setVisibility(View.GONE);
        }
        if (et2.getVisibility() == View.GONE) {
            tv2.setVisibility(View.GONE);
        }
        if (et3.getVisibility() == View.GONE) {
            tv3.setVisibility(View.GONE);
        }
        if (et4.getVisibility() == View.GONE) {
            tv4.setVisibility(View.GONE);
        }

        et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && et1.getHint().toString() != "1*") {
                    tv1.setVisibility(View.VISIBLE);
                    tv1.setText("1* " + et1.getText().toString());
                }
            }
        });

        et2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && et2.getHint().toString() != "2*") {
                    tv2.setVisibility(View.VISIBLE);
                    tv2.setText("2* " + et2.getText().toString());
                }
            }
        });

        et3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && et3.getHint().toString() != "2*") {
                    tv3.setVisibility(View.VISIBLE);
                    tv3.setText("2* " + et3.getText().toString());
                }
            }
        });

        et4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && et4.getHint().toString() != "3*") {
                    tv4.setVisibility(View.VISIBLE);
                    tv4.setText("3* " + et4.getText().toString());
                }
            }
        });


    }

    private void initView() {
        mMultipleRv = findViewById(R.id.multipleRv);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et1.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        et2.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        et3.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        et4.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);


        mRg = findViewById(R.id.radio_group);
        mRb1 = findViewById(R.id.rb_1);
        mRb2 = findViewById(R.id.rb_2);
        mRb3 = findViewById(R.id.rb_3);
        mRb4 = findViewById(R.id.rb_4);

        mEt1 = findViewById(R.id.et_x);
        mEt2 = findViewById(R.id.et_y);
        mEt3 = findViewById(R.id.et_z);
        mEt4 = findViewById(R.id.et_r);

        mResult1 = findViewById(R.id.tv_result1);
        mResult2 = findViewById(R.id.tv_result2);
        mResult3 = findViewById(R.id.tv_result3);
        mResult4 = findViewById(R.id.tv_result4);

        mEt1.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        mEt2.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        mEt3.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        mEt4.setInputType(EditorInfo.TYPE_CLASS_NUMBER);

        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                switch (radioButtonId) {
                    case R.id.rb_1:
                        math = 1;
                        break;
                    case R.id.rb_2:
                        math = 2;
                        break;
                    case R.id.rb_3:
                        math = 3;
                        break;
                    case R.id.rb_4:
                        math = 4;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void getResult(View view) {

        int x = Integer.parseInt(mEt1.getText().toString());
        int y = Integer.parseInt(mEt2.getText().toString());
        int z = Integer.parseInt(mEt3.getText().toString());
        int r = Integer.parseInt(mEt4.getText().toString());


        switch (math) {
            case 1:
                mResult1.setTextColor(Color.RED);
                mResult2.setTextColor(Color.BLACK);
                mResult3.setTextColor(Color.BLACK);
                mResult4.setTextColor(Color.BLACK);
                break;
            case 2:
                mResult1.setTextColor(Color.BLACK);
                mResult2.setTextColor(Color.RED);
                mResult3.setTextColor(Color.BLACK);
                mResult4.setTextColor(Color.BLACK);
                break;
            case 3:
                mResult1.setTextColor(Color.BLACK);
                mResult2.setTextColor(Color.BLACK);
                mResult3.setTextColor(Color.RED);
                mResult4.setTextColor(Color.BLACK);
                break;
            case 4:
                mResult1.setTextColor(Color.BLACK);
                mResult2.setTextColor(Color.BLACK);
                mResult3.setTextColor(Color.BLACK);
                mResult4.setTextColor(Color.RED);
                break;


        }
        mResult1.setVisibility(View.VISIBLE);
        mResult2.setVisibility(View.VISIBLE);
        mResult3.setVisibility(View.VISIBLE);
        mResult4.setVisibility(View.VISIBLE);

        mResult1.setText("(Part1:172-167)" + (-x + y + z + r) + "");
        mResult2.setText("(Part1:166-165)" + (+x - 2 * y + z + r) + "");
        mResult3.setText("(Part1:164-163)" + (+x + y - 2 * z + r) + "");
        mResult4.setText("(Part1:162-160)" + (+x + y + z - 3 * r) + "");

    }
}