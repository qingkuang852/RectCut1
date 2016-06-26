package com.rong.rectcut.view;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rong.rectcut.R;
import com.rong.rectcut.main.AtyBase;
import com.rong.rectcut.main.Config;
import com.rong.rectcut.model.bean.ImageFolder;
import com.rong.rectcut.model.biz.IPhotoView;
import com.rong.rectcut.presenter.LoadPhotoPresenter;
import com.rong.rectcut.util.CommonRcViewHolder;
import com.rong.rectcut.util.CommonViewHolder;
import com.rong.rectcut.util.DensityUtil;
import com.rong.rectcut.util.MyItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenming on 2016/3/15.
 */
public class AtyChooseImage extends AtyBase implements IPhotoView{

    private static final int STEP_ONE = 1;
    private static final int STEP_TWO = 2;
    private static final int STEP_THREE = 3;

    private int curStep = STEP_ONE;
    //显示照片的控件
    private ListView listView;
    private RecyclerView gridView;
    private Button title;
    private LoadPhotoPresenter loadPhotoPresenter;

    @Override
    public int getLayoutView() {
        return R.layout.activity_chooseimg;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initFindView() {
//        getWindow().setEnterTransition(new Slide());
        title = (Button) findViewById(R.id.titlebar);
        listView = (ListView) findViewById(R.id.listview);
        gridView = (RecyclerView) findViewById(R.id.gridview);
        gridView.setLayoutManager(new GridLayoutManager(this,3));
        loadPhotoPresenter = new LoadPhotoPresenter(this,this);
        loadPhotoPresenter.initListView();


    }


    @Override
    public void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyAdapter adapter = (MyAdapter) parent.getAdapter();
                ArrayList<String> list = (ArrayList<String>) adapter.getItem(position).getImages();
                gridView.setVisibility(View.VISIBLE);
                gridView.setAdapter(new MyRcAdapter(list));
                title.setText(adapter.getItem(position).name);
                curStep = STEP_TWO;
            }
        });
    }

    @Override
    public void setListAdapter(List<ImageFolder> folderList) {
        listView.setAdapter(new MyAdapter(folderList));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            if (curStep == STEP_TWO){
                gridView.setVisibility(View.GONE);
                gridView.removeAllViews();
                curStep = STEP_ONE;
                title.setText("选择照片");
            }else if (curStep == STEP_ONE){
                return super.onKeyDown(keyCode, event);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
           图片文件适配器
        */
    class MyAdapter extends BaseAdapter {

        private List<ImageFolder> folderList;

        public MyAdapter(List<ImageFolder> mFolderList){
            this.folderList = mFolderList;
        }

        @Override
        public int getCount() {
            return folderList.size();
        }

        @Override
        public ImageFolder getItem(int position) {
            return folderList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CommonViewHolder holder = CommonViewHolder.init(AtyChooseImage.this, convertView, position, parent, R.layout.item_loader_photo);
            ImageFolder folder = folderList.get(position);
            holder.setText(R.id.item_lv_imageselect_folder_tv,folder.name+"  ("+folder.images.size()+")");
            ImageView iv = holder.getView(R.id.item_lv_imageselect_folder_img);
            String url = "file://"+folder.images.get(0);
//            ImageLoader.getInstance().displayImage(url,iv, Config.getImageOptions());
            return holder.getConvertView();
        }
    }

    class MyRcAdapter extends RecyclerView.Adapter<CommonRcViewHolder>{

        private ArrayList<String> mDatas;
        private int imgWidth;
        public MyRcAdapter(ArrayList<String> mDatas){
            this.mDatas = mDatas;
            imgWidth = DensityUtil.getScreenWidth(AtyChooseImage.this)/3;
        }

        @Override
        public CommonRcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loader_photo_grid,parent,false);
            CommonRcViewHolder holder = new CommonRcViewHolder(view, new MyItemOnclickListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(AtyChooseImage.this,AtyImageEdit.class);
                    intent.putExtra("path", mDatas.get(position));
                    view.setTransitionName("img");
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                            AtyChooseImage.this,
                            view,"img"
                    ).toBundle());
                }
            });
            ImageView iv = holder.getView(R.id.item_fg_headimgchoose2_img);
            iv.setLayoutParams(new RelativeLayout.LayoutParams(imgWidth,imgWidth));
            return holder;
        }

        @Override
        public void onBindViewHolder(CommonRcViewHolder holder, int position) {
            ImageView iv = holder.getView(R.id.item_fg_headimgchoose2_img);
            String url = "file://"+mDatas.get(position);
//            ImageLoader.getInstance().displayImage(url,iv,Config.getImageOptions());
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }
}
