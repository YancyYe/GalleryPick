package com.yancy.gallerypick.bean;

/**
 * 图片信息
 * Created by Yancy on 2016/1/27.
 */
public class PhotoInfo {

    public String name;                 // 图片名
    public String path;                 // 图片路径
    public long time;                   // 图片添加时间

    public PhotoInfo(String path, String name, long time) {
        this.path = path;
        this.name = name;
        this.time = time;
    }





    @Override
    public String toString() {
        return "PhotoInfo{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        try {
            PhotoInfo other = (PhotoInfo) object;
            return this.path.equalsIgnoreCase(other.path);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return super.equals(object);
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