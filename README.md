# GalleryPick 图片选择器 [![](https://jitpack.io/v/YancyYe/GalleryPick.svg)](https://jitpack.io/#YancyYe/GalleryPick)

GalleryPick 是 Android自定义相册，实现了拍照、图片选择（单选/多选）、ImageLoader无绑定 任由开发者选择

###[GitHub 项目地址](https://github.com/YancyYe/GalleryPick)

#### 图片展示
![多选](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/picture/image_1.png) ![文件夹选择](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/picture/image_2.png)

#### Gif展示
![单选](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/picture/gif_1.gif) ![多选](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/picture/gif_2.gif)


## GalleryPick 功能
* UI重改
* 所有功能可配置
* 解决OOM情况
* 图片多选、单选
* ImageLoader 无绑定，任由开发者选择


## 版本说明
### 1.0.3 正式版本发布
* 提供单选、多选、以及相机拍照功能。
* 自定义ImageLoader

## 使用说明

### 步骤一：
本开源代码以关联到 [jitpack](https://jitpack.io/#YancyYe/GalleryPick) 网站 ，使用者可以用以下几种方式进行抓取。


#### 通过Gradle使用
在 `Project` 的 `build.gradle` 中 添加：
```groovy
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```
在 `app` 的 `build.gradle` 中 添加：
```groovy
dependencies {
      compile 'com.github.YancyYe:GalleryPick:1.0.2'
}
```

#### 通过maven使用
```groovy
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

```groovy
<dependency>
    <groupId>com.github.YancyYe</groupId>
    <artifactId>GalleryPick</artifactId>
    <version>1.0.2</version>
</dependency>
```

### 步骤二：创建 图片加载器 (其中可以按照 喜好  使用不同的 第三方图片)

####示例：
####[使用Glide加载](https://github.com/YancyYe/GalleryPick/blob/master/app/src/main/java/com/yancy/gallerypickdemo/loader/GlideImageLoader.java)
####[使用Picasso加载](https://github.com/YancyYe/GalleryPick/blob/master/app/src/main/java/com/yancy/gallerypickdemo/loader/PicassoImageLoader.java)
####[使用Fresco加载](https://github.com/YancyYe/GalleryPick/blob/master/app/src/main/java/com/yancy/gallerypickdemo/loader/FrescoImageLoader.java)

### 步骤三：申请权限
代码示例：
在点击开启相册按钮时：
```groovy
if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "需要授权 ");
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.i(TAG, "拒绝过了");
                // 提示用户如果想要正常使用，要手动去设置中授权。
                Toast.makeText(mContext, "请在 设置-应用管理 中开启此应用的储存授权。", Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "进行授权");
                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            Log.i(TAG, "不需要授权 ");
            // 进行正常操作
        }
```

以下是用户授权反馈
```groovy
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
	if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
		if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
		    Log.i(TAG, "同意授权");
		    // 进行正常操作。
		} else {
		    Log.i(TAG, "拒绝授权");
		}
	}
}
```

### 步骤四：创建监听接口`IHandlerCallBack `
```groovy
	IHandlerCallBack iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "onStart: 开启");
            }

            @Override
            public void onSuccess(List<String> photoList) {
                Log.i(TAG, "onSuccess: 返回数据");
                for (String s : photoList) {
                    Log.i(TAG, s);
                }
            }

            @Override
            public void onCancel() {
                Log.i(TAG, "onCancel: 取消");
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: 结束");
            }

            @Override
            public void onError() {
                Log.i(TAG, "onError: 出错");
            }
        };

```

### 步骤五：配置 `GalleryConfig`
可先进行初始配置，除了`ImageLoader` 和 `IHandlerCallBack`之外，其他都是选填，都有默认值。
```groovy
GalleryConfig galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .pathList(path)                         // 记录已选的图片
                .multiSelect(true)                      // 是否多选   默认：false
                .multiSelect(true, 9)                 // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();
```

可以按照需求进行单项配置，前提需要填好 `ImageLoader` 和 `IHandlerCallBack`，举例：
```groovy
  galleryConfig.getBuilder().multiSelect(true).build();   // 修改多选
  galleryConfig.getBuilder().isShowCamera(true).build();   // 修改显示相机
  galleryConfig.getBuilder().imageLoader(new PicassoImageLoader()).build(); // 修改图片加载框架
```

### 步骤六：启动`GalleryPick`图片选择器
```groovy
GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(mActivity);
```


## 关于作者
* Email: [yancy_world@outlook.com](mailto:yancy_world@outlook.com)


###[旧项目地址](https://github.com/YancyYe/ImageSelector)
