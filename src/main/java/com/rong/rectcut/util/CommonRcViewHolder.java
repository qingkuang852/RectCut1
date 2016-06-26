package com.rong.rectcut.util;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by wenming on 2015/7/8.
 */
public class CommonRcViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private SparseArray<View> views = new SparseArray<>();
    private MyItemOnclickListener myItemOnclickListener;
    private View view;
    public CommonRcViewHolder(View itemView, MyItemOnclickListener myItemOnclickListener) {
        super(itemView);
        this.myItemOnclickListener = myItemOnclickListener;
        itemView.setOnClickListener(this);
        view = itemView;
    }

    @Override
    public void onClick(View v) {
        if (myItemOnclickListener!=null){
            myItemOnclickListener.onItemClick(v,getLayoutPosition());
        }
    }

    public <T extends View> T getView(int viewId){
        View v = views.get(viewId);

        if (v==null){
            v = view.findViewById(viewId);
            views.put(viewId, v);
        }
        return (T)v;
    }

    public <T extends View> T getViewWithLayoutParams(int viewId,ViewGroup.LayoutParams lp){
        View v = views.get(viewId);

        if (v==null){
            v = view.findViewById(viewId);
            v.setLayoutParams(lp);
            views.put(viewId,v);
        }
        return (T)v;
    }

    public CommonRcViewHolder setText(int viewId,String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
}
