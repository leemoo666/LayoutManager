package com.lxm.layoutmanagerdemo.animator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lxm.layoutmanagerdemo.R;

import java.util.ArrayList;

public class ItemAnimatorActivity extends AppCompatActivity {

    private RecyclerView rvlPic;
    private ItemAnimatorAdapter secondAdapter;
    private ArrayList<String> pics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        rvlPic = findViewById(R.id.rvlPic);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondAdapter.addData(1);
            }
        });
        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondAdapter.removeData(0);
            }
        });
        findViewById(R.id.swpie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondAdapter.swipe(3,5);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvlPic.setLayoutManager(linearLayoutManager);

        secondAdapter = new ItemAnimatorAdapter(this);
        rvlPic.setAdapter(secondAdapter);

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        MyItemAnimator defaultItemAnimator = new MyItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(1000);
        rvlPic.setItemAnimator(defaultItemAnimator);

        init();
    }

    private void init() {
        for (int i = 0; i < 3; i++) {
            pics.add("A " + i + "条");
        }
        for (int i = 0; i < 4; i++) {
            pics.add("B " + i + "条");
        }
        for (int i = 0; i < 5; i++) {
            pics.add("C " + i + "条");
        }
        for (int i = 0; i < 6; i++) {
            pics.add("D " + i + "条");
        }
        for (int i = 0; i < 7; i++) {
            pics.add("E " + i + "条");
        }
        for (int i = 0; i < 7; i++) {
            pics.add("F " + i + "条");
        }
        for (int i = 0; i < 4; i++) {
            pics.add("G " + i + "条");
        }
        for (int i = 0; i < 3; i++) {
            pics.add("H " + i + "条");
        }
        for (int i = 0; i < 2; i++) {
            pics.add("I " + i + "条");
        }
        secondAdapter.setPicUrls(pics);
    }
}
