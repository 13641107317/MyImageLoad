package com.example.myimageload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by WangPeng on 2018/6/25.
 */
public class ImageLoad {
    private static final String TAG = "ImageLoad";
    private ImageCache mImageCache;

    public void setImageCache(ImageCache mImageCache) {
        this.mImageCache = mImageCache;
    }

    //线程池,线程数量为cpu的数量
    private ExecutorService mExecutorService = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    //加载图片
    public void displayImage(final String imageUrl, final ImageView imageView) {
        if (mImageCache == null) {
            throw new RuntimeException("ImageCache IS NULL!");
        }
        Bitmap bitmap = mImageCache.get(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        submitLoadRequest(imageUrl, imageView);
    }

    private void submitLoadRequest(final String imageUrl, final ImageView imageView) {
        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downLoadBitmap(imageUrl);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(imageUrl)) {
                    imageView.setImageBitmap(bitmap);
                }
                Log.i(TAG, "run: ");
                mImageCache.put(imageUrl, bitmap);
            }
        });
    }

    private Bitmap downLoadBitmap(String imageUrl) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(imageUrl);
            connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return bitmap;
    }

}
