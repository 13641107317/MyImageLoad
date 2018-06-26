package com.example.myimageload;

import android.graphics.Bitmap;

/**
 * Created by WangPeng on 2018/6/26.
 */
public class DoubleCache implements ImageCache {
    private MemoryCache mMemoryCache;
    private DiskCache mDiskCache;

    public DoubleCache() {
        mMemoryCache = new MemoryCache();
        mDiskCache = new DiskCache();
    }


    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            mDiskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}
