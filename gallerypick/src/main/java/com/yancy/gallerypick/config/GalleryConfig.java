package com.yancy.gallerypick.config;

import com.yancy.gallerypick.inter.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * GalleryPick 配置器
 * Created by Yancy on 2016/1/27.
 */
public class GalleryConfig {

    private ImageLoader imageLoader;    // 图片加载器

    private boolean multiSelect;        // 是否开启多选  默认 ： false
    private int maxSize;                // 配置开启多选时 最大可选择的图片数量。   默认：9
    private boolean isShowCamera;       // 是否开启相机 默认：true
    private ArrayList<String> pathList;      // 已选择照片的路径

    private int REQUEST_CODE;           // 返回的code标识， 默认 800

    private GalleryConfig(Builder builder) {
        this.imageLoader = builder.imageLoader;
        this.multiSelect = builder.multiSelect;
        this.maxSize = builder.maxSize;
        this.isShowCamera = builder.isShowCamera;
        this.REQUEST_CODE = builder.REQUEST_CODE;
        this.pathList = builder.pathList;
    }


    public static class Builder implements Serializable {

        private ImageLoader imageLoader;

        private boolean multiSelect = false;
        private int maxSize = 9;
        private boolean isShowCamera = true;
        private ArrayList<String> pathList = new ArrayList<>();

        private int REQUEST_CODE = 800;

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

        public Builder requestCode(int REQUEST_CODE) {
            this.REQUEST_CODE = REQUEST_CODE;
            return this;
        }

        public Builder pathList(List<String> pathList) {
            this.pathList.clear();
            this.pathList.addAll(pathList);
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

    public int getRequestCode() {
        return REQUEST_CODE;
    }

    public ArrayList<String> getPathList() {
        return pathList;
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