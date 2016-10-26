package com.yancy.gallerypick.config;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.yancy.gallerypick.activity.GalleryPickActivity;

/**
 * GalleryPick 启动类 (单例)
 * Created by Yancy on 2016/1/27.
 */
public class GalleryPick {

    private final static String TAG = "GalleryPick";

    private static GalleryPick galleryPick;

    private GalleryConfig galleryConfig;

    public static GalleryPick getInstance() {
        if (galleryPick == null) {
            galleryPick = new GalleryPick();
        }
        return galleryPick;
    }


    public void open(Activity activity) {
        if (galleryPick.galleryConfig == null) {
            Log.e(TAG, "请配置 GalleryConfig");
            return;
        }
        if (galleryPick.galleryConfig.getImageLoader() == null) {
            Log.e(TAG, "请配置 ImageLoader");
            return;
        }
        if (galleryPick.galleryConfig.getIHandlerCallBack() == null) {
            Log.e(TAG, "请配置 IHandlerCallBack");
            return;
        }
        Intent intent = new Intent(activity, GalleryPickActivity.class);
        activity.startActivity(intent);
    }


    public GalleryPick setGalleryConfig(GalleryConfig galleryConfig) {
        this.galleryConfig = galleryConfig;
        return this;
    }

    public GalleryConfig getGalleryConfig() {
        return galleryConfig;
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