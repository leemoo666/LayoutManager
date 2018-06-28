package com.lxm.layoutmanagerdemo.sticky;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lxm.layoutmanagerdemo.R;

import java.util.ArrayList;

public class StickyActivity extends AppCompatActivity {

    private RecyclerView rvlPic;
    private StickyAdapter secondAdapter;
    private  ArrayList<String> pics = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        rvlPic = findViewById(R.id.rvlPic);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvlPic.setLayoutManager(linearLayoutManager);
        rvlPic.addItemDecoration(new StickyItemDecoration(this, new StickyItemDecoration.DecorationCallback() {
            @Override
            public long getGroupId(int position) {
                return Character.toUpperCase(pics.get(position).charAt(0));
            }

            @Override
            public String getGroupFirstLine(int position) {
                return pics.get(position).substring(0, 1).toUpperCase();
            }
        }));

        secondAdapter = new StickyAdapter(this);
        rvlPic.setAdapter(secondAdapter);
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
