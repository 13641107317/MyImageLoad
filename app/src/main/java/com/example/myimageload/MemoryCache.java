package com.example.myimageload;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by WangPeng on 2018/6/25.
 */
class MemoryCache implements ImageCache {
    private LruCache<String, Bitmap> mMemoryCache ;

    public MemoryCache() {
        initImageCache();
    }

    private void initImageCache() {
        //计算可使用最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int memorySize = maxMemory / 4;
        mMemoryCache = new LruCache<String, Bitmap>(memorySize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };

    }


    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
    }
}
