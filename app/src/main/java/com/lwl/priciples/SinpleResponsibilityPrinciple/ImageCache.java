package com.lwl.priciples.SinpleResponsibilityPrinciple;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


/**
 * Created by jonn on 2016/1/10.
 */
public class ImageCache {

    /**
     * 图片lru缓存
     */
   LruCache<String,Bitmap> mImageCache;

    public ImageCache()
    {
     initImageCache();
    }

    public  void initImageCache()
    {
        //计算可以使用的最大内存
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        //取1/4的可用内存作为缓存
        final  int cacheSize = maxMemory / 8;
        mImageCache = new LruCache<String, Bitmap>(cacheSize){

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;

            }
        };

    }

    public  void put(String url,Bitmap bitmap)
    {
        mImageCache.put(url,bitmap);

    }


    public Bitmap get(String url)
    {
        return  mImageCache.get(url);
    }




}
