package com.lwl.priciples.SinpleResponsibilityPrinciple;

import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jonn on 2016/1/10.
 */
public class ImageLoader {

    /**
     * 图片缓存
     */
    ImageCache mImageCache = new ImageCache();

    /**
     * 线程池
     */
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    /**
     * runOnUiThread是Activity内部的方法
     * @param imageView
     */
    public void updateImage(final ImageView imageView, final Bitmap bitmap, Activity activity)
    {
        activity.runOnUiThread(new Runnable()
        {
            public void run()
            {
                imageView.setImageBitmap(bitmap);
            }

        });

    }


    //加载图片
    public void displayImage(final String url, final ImageView imageView,final Activity activity){


        Bitmap bitmap = mImageCache.get(url);

        if(bitmap != null)
        {
            Log.e("1",url);

           updateImage(imageView,bitmap,activity);
            return ;

        }


        //View中的setTag(Onbect)表示给View添加一个格外的数据，以后可以用getTag()将这个数据取出来。
        imageView.setTag(url);

        //下载线程
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap  =  downloadImage(url);

                if(bitmap == null)
                {
                    return ;
                }
                /**
                 * 因为一个imageView可以在不同时刻显示不同的bitmap，所以同一个imageView所有的bitmap都要缓存起来，
                 * 但是显示的时候一定是Tag对应的bitmap，而且这里不同的imageView不能同时缓存同一个URL；
                 */
                if(imageView.getTag().equals(url))
                {

                    updateImage(imageView,bitmap,activity);

                }

                mImageCache.put(url,bitmap);


            }
        });




    }

    /**
     * 下载图片
     * @param imageUrl
     * @return
     */
    public  Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {

            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }


}
