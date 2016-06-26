package com.rong.rectcut.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by wenming on 2016/3/15.
 */
public abstract class AtyBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(getLayoutView());
        initFindView();
        initListener();
    }

    public abstract int getLayoutView();
    public abstract void initFindView();
    public abstract void initListener();

    @Override
    protected void onStart() {
        super.onStart();
        MyLog.v(getLocalClassName()+".onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyLog.v(getLocalClassName() + ".onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyLog.v(getLocalClassName() + ".onPause()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MyLog.v(getLocalClassName() + ".onRestart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyLog.v(getLocalClassName() + ".onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyLog.v(getLocalClassName() + ".onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("data","time:"+System.currentTimeMillis());
        MyLog.v(getLocalClassName() + ".onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        MyLog.v(getLocalClassName() + ".onRestoreInstanceState()"+savedInstanceState.getString("data"));
    }
}
