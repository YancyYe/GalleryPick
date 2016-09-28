package com.yancy.gallerypickdemo;

import android.app.Application;

import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;

/**
 * BaseApplication
 * Created by Yancy on 2016/1/27.
 */
public class BaseApplication extends Application {

    private final static String TAG = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        

        GalleryConfig galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new ImageLoader())
                .build();

        GalleryPick.getInstance().setGalleryConfig(galleryConfig);

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