package com.hashmup.demoapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private int cur = 0;
    private String[] nums = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private Button[] buttons = {};
    long tStart = 0, tEnd = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButton();
    }

    private void setButton() {
        Collections.shuffle(Arrays.asList(nums));
        Button startBtn = (Button)findViewById(R.id.startBtn);
        startBtn.setVisibility(View.GONE);
        for (int i=0;i<9;i++) {
            String id = "button" + Integer.toString(i+1);
            int resId = getResources().getIdentifier(id, "id", getPackageName());
            Button btn = (Button)findViewById(resId);
            btn.setBackgroundColor(Color.LTGRAY);
            btn.setText(nums[i]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tappedNum = ((Button)v).getText().toString();
                    if (cur == 0 && tStart == 0) {
                        tStart = System.currentTimeMillis();
                    }
                    if (tappedNum == Integer.toString(cur+1)) {
                        v.setBackgroundColor(Color.YELLOW);
                        cur++;
                    }
                    if (cur == 9) {
                        tEnd = System.currentTimeMillis();
                        showTime();
                    }
                    System.out.println(((Button)v).getText().toString());
                }
            });
        }
    }

    private void showTime() {
        final TextView tv = (TextView)findViewById(R.id.textView);
        Button btn = (Button)findViewById(R.id.startBtn);

        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        tv.setText(Double.toString(elapsedSeconds));
        final LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        if (elapsedSeconds > 5.0) {
            layout.setBackgroundColor(Color.RED);
        } else {
            layout.setBackgroundColor(Color.BLUE);
        }
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundColor(Color.WHITE);
                tv.setText("");
                setButton();
                cur = 0;
                tStart = 0;
            }
        });
    }
}
