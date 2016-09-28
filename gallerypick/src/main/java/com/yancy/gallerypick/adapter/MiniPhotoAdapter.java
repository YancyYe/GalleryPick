package com.yancy.gallerypick.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.yancy.gallerypick.R;
import com.yancy.gallerypick.bean.PhotoInfo;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;

import java.util.List;

/**
 * Mini选择器 适配器
 * Created by Yancy on 2016/2/3.
 */
public class MiniPhotoAdapter extends RecyclerView.Adapter<MiniPhotoAdapter.ViewHolder> {

    private Context context;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private List<PhotoInfo> photoInfoList;
    private final static String TAG = "MiniPhotoAdapter";

    private GalleryConfig galleryConfig = GalleryPick.getInstance().getGalleryConfig();

    public MiniPhotoAdapter(Context context, List<PhotoInfo> photoInfoList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.photoInfoList = photoInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.gallery_pick_mini_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        galleryConfig.getImageLoader().displayImage(mActivity, context, photoInfoList.get(position).path, holder.ivPhotoImage);
    }

    @Override
    public int getItemCount() {
        return photoInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPhotoImage;
        private CheckBox chkPhotoSelector;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhotoImage = (ImageView) itemView.findViewById(R.id.ivGalleryPhotoImage);
            chkPhotoSelector = (CheckBox) itemView.findViewById(R.id.chkGalleryPhotoSelector);
        }

    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }
}
/*
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *     ┃　　　┃
 *     ┃　　　┃
 *     ┃　　　┗━━━┓
 *     ┃　　　　　　　┣┓
 *     ┃　　　　　　　┏┛
 *     ┗┓┓┏━┳┓┏┛
 *       ┃┫┫　┃┫┫
 *       ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */