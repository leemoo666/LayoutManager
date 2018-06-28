package com.lxm.layoutmanagerdemo.touchhelp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.lxm.layoutmanagerdemo.R;

import java.util.ArrayList;

public class TouchHelpActivity extends AppCompatActivity {

    private RecyclerView rvlPic;
    private TouchHelpAdapter secondAdapter;
    private  ArrayList<String> pics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        rvlPic = findViewById(R.id.rvlPic);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvlPic.setLayoutManager(linearLayoutManager);

        secondAdapter = new TouchHelpAdapter(this);
        rvlPic.setAdapter(secondAdapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelpCallback(secondAdapter);

        /**
         * 实例化ItemTouchHelper对象,然后添加到RecyclerView
         */
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvlPic);

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
