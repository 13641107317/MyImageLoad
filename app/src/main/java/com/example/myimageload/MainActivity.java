package com.example.myimageload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView mImg;

    private String url = "http://img.taopic.com/uploads/allimg/121019/234917-121019231h258.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImg = (ImageView) findViewById(R.id.img);

        ImageLoad imageLoad = new ImageLoad();
        imageLoad.setImageCache(new MemoryCache());
        imageLoad.displayImage(url,mImg);
    }
}
