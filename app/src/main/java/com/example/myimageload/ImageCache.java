package com.example.myimageload;

import android.graphics.Bitmap;

/**
 * Created by WangPeng on 2018/6/26.
 */
public interface ImageCache {
    Bitmap get(String url);
    void put(String url,Bitmap bitmap);
}
