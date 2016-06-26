package com.rong.rectcut.view;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.rong.rectcut.R;
import com.rong.rectcut.main.AtyBase;
import com.rong.rectcut.main.MyLog;

public class MainActivity extends AtyBase {

    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initFindView() {

    }

    @Override
    public void initListener() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AtyChooseImage.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, Pair.create(findViewById(R.id.button), "btn"), Pair.create(findViewById(R.id.reveal), "reveal")).toBundle());
                }else {
                    startActivity(intent);
                }
//                Intent intent = new Intent("com.main22");
//
//                startActivity(intent);
            }
        });
    }




}
