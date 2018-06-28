package com.lxm.layoutmanagerdemo.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.lxm.layoutmanagerdemo.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvlPic;
    private PictureAdapter pictureAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvlPic = findViewById(R.id.rvlPic);
        PicLayoutManager picLayoutManager = new PicLayoutManager(this);

        rvlPic.setLayoutManager(picLayoutManager);
        pictureAdapter = new PictureAdapter(this);
        rvlPic.setAdapter(pictureAdapter);
        init();
    }

    private void init(){
        ArrayList<String> pics = new ArrayList<>();
        pics.add("http://img1.imgtn.bdimg.com/it/u=3920398476,1501488149&fm=27&gp=0.jpg");
        pics.add("http://img1.imgtn.bdimg.com/it/u=3920398476,1501488149&fm=27&gp=0.jpg");
        pics.add("http://fd.topitme.com/d/a8/1d/11315383988791da8do.jpg");
        pics.add("http://pic14.photophoto.cn/20100127/0036036848818577_b.jpg");
        pics.add("http://pic14.photophoto.cn/20100127/0036036848818577_b.jpg");
        pics.add("http://f2.topitme.com/2/6a/bc/113109954583dbc6a2o.jpg");
        pics.add("http://image.tupian114.com/20130521/15235862.jpg");
        pics.add("http://img.taopic.com/uploads/allimg/140702/240404-140F20QG490.jpg");
        pics.add("http://pic9.nipic.com/20100905/2531170_095210291877_2.jpg");
        pics.add("http://pic13.nipic.com/20110326/2883687_105551511198_2.jpg");
        pics.add("http://pic9.nipic.com/20100919/5123760_093408576078_2.jpg");
        pictureAdapter.setPicUrls(pics);
    }
}
