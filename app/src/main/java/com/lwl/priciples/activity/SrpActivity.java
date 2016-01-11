package com.lwl.priciples.activity;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import com.lwl.priciples.R;
import com.lwl.priciples.SinpleResponsibilityPrinciple.ImageLoader;

public class SrpActivity extends Activity {

    public static String [ ] urls = new String[]{
            "http://pic2.sc.chinaz.com/Files/pic/pic9/201512/apic17727_s.jpg",
            "http://pic2.sc.chinaz.com/Files/pic/pic9/201512/apic17708_s.jpg",
            "http://pic2.sc.chinaz.com/Files/pic/pic9/201512/apic17700_s.jpg"
    } ;

    public static ImageLoader imageLoader = new ImageLoader();
    private static  int i=0;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srp);
        imageView = (ImageView)findViewById(R.id.imageView);


    }


    public  void displayImages(View view)
    {

    //    Log.e("photo",urls[i++%urls.length]);
        imageLoader.displayImage(urls[i++%urls.length],imageView,SrpActivity.this);



    }





}
