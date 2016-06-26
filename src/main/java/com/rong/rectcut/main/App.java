package com.rong.rectcut.main;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.rong.rectcut.view.MainActivity;

/**
 * Created by wenming on 2016/3/16.
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(this);
    }

    /*
        初始化ImageLoader
     */
    private void initImageLoader(Context context){
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY-2)
                .denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50*1024*1024)
                .build();
        ImageLoader.getInstance().init(configuration);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        MyLog.v("onTerminate");
    }
}

