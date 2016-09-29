package com.yancy.gallerypick.config;

import com.yancy.gallerypick.inter.ImageLoader;

import java.io.Serializable;

/**
 * GalleryPick 配置器
 * Created by Yancy on 2016/1/27.
 */
public class GalleryConfig {

    private ImageLoader imageLoader;    // 图片加载器

    private boolean multiSelect;        // 是否开启多选  默认 ： false
    private int maxSize;                // 配置开启多选时 最大可选择的图片数量。   默认：9

    private boolean isShowCamera;       // 是否开启相机 默认：true

    private GalleryConfig(Builder builder) {
        this.imageLoader = builder.imageLoader;
        this.multiSelect = builder.multiSelect;
        this.maxSize = builder.maxSize;
        this.isShowCamera = builder.isShowCamera;
    }


    public static class Builder implements Serializable {

        private ImageLoader imageLoader;

        private boolean multiSelect = false;
        private int maxSize = 9;

        private boolean isShowCamera = true;


        public Builder imageLoader(ImageLoader imageLoader) {
            this.imageLoader = imageLoader;
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


        public GalleryConfig build() {
            return new GalleryConfig(this);
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