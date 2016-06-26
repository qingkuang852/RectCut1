package com.rong.rectcut.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rong.rectcut.R;
import com.rong.rectcut.main.AtyBase;
import com.rong.rectcut.main.Config;
import com.rong.rectcut.main.MyLog;
import com.rong.rectcut.util.DensityUtil;
import com.rong.rectcut.util.PhotoUtil;

/**
 * Created by wenming on 2016/3/17.
 */
public class AtyImageEdit extends AtyBase {

    private ImageView imageView;
    private RelativeLayout titleBar;
    @Override
    public int getLayoutView() {
        return R.layout.activity_imageedit;
    }

    @Override
    public void initFindView() {
        titleBar = (RelativeLayout) findViewById(R.id.titlebar);
        imageView = (ImageView) findViewById(R.id.imageview);
        int screenW = DensityUtil.getScreenWidth(this);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        lp.width = screenW;
        lp.height = screenW;
        imageView.requestLayout();
        String path = getIntent().getStringExtra("path");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        bmp = BitmapFactory.decodeFile(path, options);
        int degree = PhotoUtil.getBitmapDegree(path);
        if (degree!=0){
            bmp = PhotoUtil.rotateBitmapByDegree(bmp, degree);
        }
        imageView.setImageBitmap(bmp);

        Palette.from(bmp).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int color = palette.getLightMutedColor(getResources().getColor(R.color.backGroundColor));
                titleBar.setBackgroundColor(color);
                getWindow().setStatusBarColor(color);
            }
        });
    }
    Bitmap bmp;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!bmp.isRecycled()){
            MyLog.v("bmp not recycle");
            bmp.recycle();
        }
    }

    @Override
    public void initListener() {

    }
}
