package com.lxm.layoutmanagerdemo.second;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lxm.layoutmanagerdemo.R;
import com.lxm.layoutmanagerdemo.main.PicLayoutManager;
import com.lxm.layoutmanagerdemo.main.PictureAdapter;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView rvlPic;
    private SecondAdapter secondAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        rvlPic = findViewById(R.id.rvlPic);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvlPic.setLayoutManager(linearLayoutManager);
        rvlPic.addItemDecoration(new PaddingItemDecoration());
        secondAdapter = new SecondAdapter(this);
        rvlPic.setAdapter(secondAdapter);
        init();
    }

    private void init() {
        ArrayList<String> pics = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            pics.add("第 " + i + "条");
        }
        secondAdapter.setPicUrls(pics);
    }
}
