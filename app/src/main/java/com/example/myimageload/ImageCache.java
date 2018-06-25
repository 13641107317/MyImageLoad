package com.example.myimageload;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by WangPeng on 2018/6/25.
 */
class ImageCache {
    private LruCache<String, Bitmap> mImageCache = null;

    public ImageCache() {
        initImageCache();
    }

    private void initImageCache() {
        //计算可使用最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int memorySize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(memorySize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };

    }

    public Bitmap get(String url) {
        if (mImageCache != null) {
            return mImageCache.get(url);
        }
        return null;
    }

    public void put(String url, Bitmap downBitmap) {
        if (mImageCache != null) {
            mImageCache.put(url, downBitmap);

        }

    }
}
