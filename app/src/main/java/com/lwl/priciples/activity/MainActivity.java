package com.lwl.priciples.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lwl.priciples.R;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }






    public  void gotoSrpModule(View view)
    {
        startActivity(new Intent(MainActivity.this,SrpActivity.class));

    }




}
