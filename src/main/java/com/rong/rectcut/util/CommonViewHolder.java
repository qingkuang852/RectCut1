package com.rong.rectcut.util;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wenming on 2016/3/16.
 */
public class CommonViewHolder {
    //存放View的数组
    private SparseArray<View> views;
    //序列数
    private int position;
    //回收的试图
    private View mConvertView;

    public CommonViewHolder(Context context,int position,ViewGroup parent,int layoutId){
        this.position = position;
        views = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }

    public static CommonViewHolder init(Context context,View convertView,int position,ViewGroup parent,int layoutId) {
        if (convertView == null) {
            return new CommonViewHolder(context, position, parent, layoutId);
        }else{
            CommonViewHolder holder = (CommonViewHolder)convertView.getTag();
            holder.position = position;
            return  holder;
        }
    }

    /*
        通过控件的Id获取控件
     */
    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if (view==null){
            view = mConvertView.findViewById(viewId);
            views.put(viewId,view);
        }

        return (T)view;
    }

    /*
        获得Item的整个布局的View
     */
    public View getConvertView(){
        return mConvertView;
    }

    /*
       针对TextView方法的文本设置
    */
    public CommonViewHolder setText(int viewId,String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
}
