package com.example.myimageload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by WangPeng on 2018/6/26.
 */
public class DiskCache implements ImageCache {
    public static final String mCacheDir = Environment.getExternalStorageState() + "cache";

    //从缓存中获取图片
    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(mCacheDir + url);
    }

    //将图片缓存到本地中
    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(mCacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
