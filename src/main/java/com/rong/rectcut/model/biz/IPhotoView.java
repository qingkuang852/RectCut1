package com.rong.rectcut.model.biz;

import android.database.Cursor;

import com.rong.rectcut.model.bean.ImageFolder;

import java.util.List;

/**
 * Created by wenming on 2016/3/17.
 */
public interface IPhotoView {
    void setListAdapter(List<ImageFolder> folderList);
}
