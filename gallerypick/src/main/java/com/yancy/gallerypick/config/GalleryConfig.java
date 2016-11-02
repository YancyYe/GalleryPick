package com.yancy.gallerypick.config;

import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.yancy.gallerypick.inter.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * GalleryPick 配置器
 * Created by Yancy on 2016/1/27.
 */
public class GalleryConfig {

    private static final String TAG = "GalleryConfig";

    private ImageLoader imageLoader;    // 图片加载器
    private IHandlerCallBack iHandlerCallBack;   // GalleryPick 生命周期接口

    private boolean multiSelect;        // 是否开启多选  默认 ： false
    private int maxSize;                // 配置开启多选时 最大可选择的图片数量。   默认：9
    private boolean isShowCamera;       // 是否开启相机 默认：true
    private String filePath;            // 拍照以及截图后 存放的位置。    默认：/Gallery/Pictures
    private ArrayList<String> pathList;      // 已选择照片的路径
    private boolean isOpenCamera;             // 是否直接开启相机    默认：false

    private Builder builder;


    private GalleryConfig(Builder builder) {
        setBuilder(builder);
    }


    public static class Builder implements Serializable {

        private static GalleryConfig galleryConfig;

        private ImageLoader imageLoader;
        private IHandlerCallBack iHandlerCallBack;

        private boolean multiSelect = false;
        private int maxSize = 9;
        private boolean isShowCamera = true;
        private String filePath = "/Gallery/Pictures";
        private ArrayList<String> pathList = new ArrayList<>();

        private boolean isOpenCamera = false;


        public Builder imageLoader(ImageLoader imageLoader) {
            this.imageLoader = imageLoader;
            return this;
        }

        public Builder iHandlerCallBack(IHandlerCallBack iHandlerCallBack) {
            this.iHandlerCallBack = iHandlerCallBack;
            return this;
        }


        public Builder multiSelect(boolean multiSelect) {
            this.multiSelect = multiSelect;
            return this;
        }

        public Builder multiSelect(boolean multiSelect, int maxSize) {
            this.multiSelect = multiSelect;
            this.maxSize = maxSize;
            return this;
        }

        public Builder maxSize(int maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public Builder isShowCamera(boolean isShowCamera) {
            this.isShowCamera = isShowCamera;
            return this;
        }

        public Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder isOpenCamera(boolean isOpenCamera) {
            this.isOpenCamera = isOpenCamera;
            return this;
        }


        public Builder pathList(List<String> pathList) {
            this.pathList.clear();
            this.pathList.addAll(pathList);
            return this;
        }

        public GalleryConfig build() {
            if (galleryConfig == null) {
                galleryConfig = new GalleryConfig(this);
            } else {
                galleryConfig.setBuilder(this);
            }
            return galleryConfig;
        }

    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public boolean isShowCamera() {
        return isShowCamera;
    }

    public ArrayList<String> getPathList() {
        return pathList;
    }

    public String getFilePath() {
        return filePath;
    }

    public IHandlerCallBack getIHandlerCallBack() {
        return iHandlerCallBack;
    }

    public Builder getBuilder() {
        return builder;
    }

    public boolean isOpenCamera() {
        return isOpenCamera;
    }

    private void setBuilder(Builder builder) {
        this.imageLoader = builder.imageLoader;
        this.iHandlerCallBack = builder.iHandlerCallBack;
        this.multiSelect = builder.multiSelect;
        this.maxSize = builder.maxSize;
        this.isShowCamera = builder.isShowCamera;
        this.pathList = builder.pathList;
        this.filePath = builder.filePath;
        this.isOpenCamera = builder.isOpenCamera;
        this.builder = builder;
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