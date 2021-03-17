package com.zeen.zeendemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zeen.zeendemo.answer_views.AnswerViewsActivity;
import com.zeen.zeendemo.answer_views.withexra.WeightActivity;
import com.zeen.zeendemo.countrypicker.CountryActivity;
import com.zeen.zeendemo.fragmentVpFragment.Fvpf;
import com.zeen.zeendemo.horizontal_lineview.NewLineActivity;
import com.zeen.zeendemo.horizontallist.HorizontalRvActivity;
import com.zeen.zeendemo.linechartview.LineChartActivity;
import com.zeen.zeendemo.ninepath.NinePathActivity;
import com.zeen.zeendemo.pieview.PieChartActivity;
import com.zeen.zeendemo.tracerv.TimeLineActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button but;
    private TextView tv;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        but = (Button) findViewById(R.id.but);
        tv = (TextView) findViewById(R.id.tv);
        tv1 = (TextView) findViewById(R.id.tv1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case 12:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String countryName = bundle.getString("countryName");
                    String countryNumber = bundle.getString("countryNumber");

                    tv.setText(countryNumber);
                    tv1.setText(countryName);

                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void goTrace(View view) {
        startActivity(new Intent(this, TimeLineActivity.class));
    }

    public void goHorizontalRv(View view) {
        startActivity(new Intent(this, HorizontalRvActivity.class));
    }

    public void goCountryPicker(View view) {
        startActivityForResult(new Intent(this, CountryActivity.class), 12);
    }

    public void goNine(View view) {
        startActivity(new Intent(this, NinePathActivity.class));
    }

    public void goFVpF(View view) {
        startActivity(new Intent(this, Fvpf.class));
    }

    public void goLineChart(View view) {
        startActivity(new Intent(this, LineChartActivity.class));
    }

    public void goLineView(View view) {
        startActivity(new Intent(this, NewLineActivity.class));
    }

    public void goRingView(View view) {
        startActivity(new Intent(this, PieChartActivity.class));
    }

    public void goQAViews(View view) {
        startActivity(new Intent(this, AnswerViewsActivity.class));
    }

    public void goWeight(View view) {
        startActivity(new Intent(this, WeightActivity.class));
    }
}