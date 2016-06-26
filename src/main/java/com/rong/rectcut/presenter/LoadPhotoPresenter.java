package com.rong.rectcut.presenter;

import android.support.v4.app.FragmentActivity;

import com.rong.rectcut.model.bean.ImageFolder;
import com.rong.rectcut.model.biz.IPhotoView;
import com.rong.rectcut.model.biz.LoadPhoto;

import java.util.List;

/**
 * Created by wenming on 2016/3/17.
 */
public class LoadPhotoPresenter {
    private IPhotoView photoView;
    private LoadPhoto loadPhoto;

    public LoadPhotoPresenter(IPhotoView iPhotoView,FragmentActivity activity){
        photoView = iPhotoView;
        loadPhoto = new LoadPhoto(activity);
    }

    public void initListView(){
        loadPhoto.setLoadFinishCallback(new LoadPhoto.LoadFinishCallback() {
            @Override
            public void finish(List<ImageFolder> folderList) {
                photoView.setListAdapter(folderList);
            }
        });
    }

}
