package com.example.myimageload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView mImg;

    private String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530040974682&di=5cdbdd2911bf4fc90a390bf54a601a6e&imgtype=0&src=http%3A%2F%2Fwww.netded.com%2Fuploads%2Fallimg%2F151207%2F1_151207125144_1.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImg = (ImageView) findViewById(R.id.img);

        ImageLoad imageLoad = new ImageLoad();
        imageLoad.setImageCache(new DiskCache());
        imageLoad.displayImage(url,mImg);
    }
}
