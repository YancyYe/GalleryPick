package com.yancy.gallerypick.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.yancy.gallerypick.R;
import com.yancy.gallerypick.adapter.MiniPhotoAdapter;
import com.yancy.gallerypick.bean.PhotoInfo;
import com.yancy.gallerypick.config.GalleryPick;

import java.util.ArrayList;
import java.util.List;

/**
 * 迷你图片选择器
 * Created by Yancy on 2016/2/3.
 */
public class GalleryPickView extends RelativeLayout {

    private Context context;
    private Activity activity;
    private final static String TAG = "GalleryPickView";

    private List<PhotoInfo> photoInfoList = new ArrayList<>();
    private MiniPhotoAdapter miniPhotoAdapter;

    private Button btnGallery;   // 相册按钮

    public GalleryPickView(Context context) {
        super(context);
        this.context = context;
        init();
    }


    public GalleryPickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public GalleryPickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @TargetApi(21)
    public GalleryPickView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init();
    }

    private void init() {
        View galleryPickView = LayoutInflater.from(context).inflate(R.layout.gallery_mini, this, true);

        btnGallery = (Button) galleryPickView.findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryPick.getInstance().open(activity);
            }
        });
        RecyclerView rvMiniImage = (RecyclerView) galleryPickView.findViewById(R.id.rvGalleryMiniImage);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMiniImage.setLayoutManager(linearLayoutManager);

        miniPhotoAdapter = new MiniPhotoAdapter(context, photoInfoList);
        rvMiniImage.setAdapter(miniPhotoAdapter);
    }

    public void init(FragmentActivity fragmentActivity) {
        activity = fragmentActivity;
        miniPhotoAdapter.setmActivity(fragmentActivity);


        LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

            private final String[] IMAGE_PROJECTION = {
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATE_ADDED,
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.SIZE
            };

            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                if (id == 1) {
                    return new CursorLoader(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION, null, null, IMAGE_PROJECTION[2] + " DESC");
                }
                return null;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                if (data != null) {
                    int count = data.getCount();
                    if (count > 0) {
                        List<PhotoInfo> tempPhotoList = new ArrayList<>();
                        data.moveToFirst();
                        do {
                            String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                            String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                            long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                            int size = data.getInt(data.getColumnIndexOrThrow(IMAGE_PROJECTION[4]));
                            boolean showFlag = size > 1024 * 5;                           //是否大于5K
                            PhotoInfo photoInfo = new PhotoInfo(path, name, dateTime);
                            if (showFlag) {
                                tempPhotoList.add(photoInfo);
                            }
                        } while (data.moveToNext());

                        photoInfoList.clear();
                        photoInfoList.addAll(tempPhotoList);
                        miniPhotoAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {

            }
        };


        fragmentActivity.getSupportLoaderManager().restartLoader(1, null, mLoaderCallback);          // 扫描手机中的图片
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