# GalleryPick 图片选择器 [![](https://jitpack.io/v/YancyYe/GalleryPick.svg)](https://jitpack.io/#YancyYe/GalleryPick)

GalleryPick 是 Android 自定义相册，实现了拍照、图片选择（单选/多选）、裁剪、ImageLoader无绑定 任由开发者选择

##### 各位的 star 就是对我最大的支持。

### [GitHub 项目地址](https://github.com/YancyYe/GalleryPick)
### [下载 APK](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/apk/GalleryPick.apk)


#### 图片展示
![功能](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/picture/image_1.jpg) ![多选页面](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/picture/image_2.jpg)
![文件夹选择](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/picture/image_3.jpg) ![截图](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/picture/image_4.jpg)

#### Gif展示
![单选](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/picture/gif_1.gif) ![多选](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/picture/gif_2.gif)
![截图](https://raw.githubusercontent.com/YancyYe/GalleryPick/master/picture/gif_3.gif)


## GalleryPick 功能
* UI重改
* 所有功能可配置
* 解决OOM情况
* 图片多选、单选
* ImageLoader 无绑定，任由开发者选择
* 可直接开启相机
* 可裁剪、可旋转


## 版本说明
### 1.2.1
* 优化部分代码。


## 使用说明

### 步骤一：
本开源代码以关联到 [`jitpack`](https://jitpack.io/#YancyYe/GalleryPick) 网站 ，使用者可以用以下几种方式进行抓取。


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
      compile 'com.github.YancyYe:GalleryPick:1.2.1'
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
    <version>1.2.1</version>
</dependency>
```

### 步骤二：创建 图片加载器 (其中可以按照 喜好  使用不同的 第三方图片)

#### 示例：

#### [使用Glide加载](https://github.com/YancyYe/GalleryPick/blob/master/app/src/main/java/com/yancy/gallerypickdemo/loader/GlideImageLoader.java)

#### [使用Picasso加载](https://github.com/YancyYe/GalleryPick/blob/master/app/src/main/java/com/yancy/gallerypickdemo/loader/PicassoImageLoader.java)

#### [使用Fresco加载](https://github.com/YancyYe/GalleryPick/blob/master/app/src/main/java/com/yancy/gallerypickdemo/loader/FrescoImageLoader.java)

### 步骤三：设置 Provider
请在 app 中的 `AndroidManifest` 中的`application`标签下添加
```groovy
 <provider
    android:name="android.support.v4.content.FileProvider"
    android:authorities="com.yancy.gallerypickdemo.fileprovider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/filepaths" />
</provider>
```
`provider` 中的 `authorities` 可以自己定义为 provider所在的包的名字+provider本身定义的名称  （如果一个设备中出现两个同样的`authorities`会出现无法安装的情况）

在`res` 中创建`xml`文件夹，在其中创建文件。文件名自己定义。demo中定义了`filepaths.xml`。
```groovy
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <paths>
        <external-path
            name="external"
            path="" />
        <files-path
            name="files"
            path="" />
        <cache-path
            name="cache"
            path="" />
    </paths>
</resources>
```
`FileProvider` 中设置的内容我就不介绍了，网上一搜一大片。

最后，在设置`GalleryConfig` 的时候，设置`provider`，内容为你之前在`AndroidManifest`中的`provider`中的`authorities`设置的值。demo中为：`com.yancy.gallerypickdemo.fileprovider`

[GalleryConfig代码示例](https://github.com/YancyYe/GalleryPick/blob/master/app/src/main/java/com/yancy/gallerypickdemo/MainActivity.java)

[AndroidManifest代码示例](https://github.com/YancyYe/GalleryPick/blob/master/app/src/main/AndroidManifest.xml)

[filepaths.xml代码示例](https://github.com/YancyYe/GalleryPick/blob/master/app/src/main/res/xml/filepaths.xml)

（[使用方法参考](https://github.com/YancyYe/GalleryPick#一裁剪功能使用说明)）

### 步骤四：申请权限
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
}
```

以下是用户授权反馈
```groovy
Override
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

### 步骤五：创建监听接口`IHandlerCallBack `
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

### 步骤六：配置 `GalleryConfig`
可先进行初始配置，除了`ImageLoader` 和 `IHandlerCallBack`之外，其他都是选填，都有默认值。
```groovy
GalleryConfig galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider("com.yancy.gallerypickdemo.fileprovider")   // provider (必填)
                .pathList(path)                         // 记录已选的图片
                .multiSelect(false)                      // 是否多选   默认：false
                .multiSelect(false, 9)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
                .crop(false)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .crop(false, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
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

### 步骤七：启动`GalleryPick`图片选择器
```groovy
GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(mActivity);
```

## 其他功能使用方法说明：
#### 一：裁剪功能使用说明
注意：裁剪功能只能在单选时、直接开启相机时生效。
```groovy
GalleryConfig galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider("com.yancy.gallerypickdemo.fileprovider")   // provider (必填)
                .pathList(path)                         // 记录已选的图片
                .crop(true)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();
GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(mActivity);
```
如果需要自定义裁剪框的比例，可按照以下方法设置：
```groovy
GalleryConfig galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider("com.yancy.gallerypickdemo.fileprovider")   // provider (必填)
                .pathList(path)                         // 记录已选的图片
                .crop(true, 1, 1, 500, 500)           // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();
GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(mActivity);
```
裁剪图片存放在 `filePath` 文件夹下的 `crop` 目录下。内部创建了忽略文件，手机系统扫描不到此文件目录下的媒体文件，防止裁剪图片显示在相册中，影响心情。

#### 二：直接开启相机，其中有三种方法。
##### 方法一：
在 `GalleryConfig` 中设置直接开启相机的标识位。
```groovy
GalleryConfig galleryConfig = new GalleryConfig.Builder()
          .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
          .filePath("/Gallery/Pictures")          // 图片存放路径   （选填）
          .isOpenCamera(true)                  // 直接开启相机的标识位
          .build();

GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(mActivity);
```
##### 方法二：
如果已经设置好了 `GalleryConfig` 可以使用单项参数修改的方法来开启相机。
```groovy
galleryConfig.getBuilder().isOpenCamera(true).build();
GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(mActivity);
```
##### 方法三：
为了方便使用，在不变动开启相册的`GalleryConfig`的情况下，我还添加了以下方法。
```groovy
 GalleryPick.getInstance().setGalleryConfig(galleryConfig).openCamera(mActivity);
```
这个方法可以直接使用。不需要在`GalleryConfig`中添加标志位，或者进行单项修改。方便用户使用。

#### 三：深度自定义UI方法
可能有很多用户对 `GalleryPick` 里面的界面还有些不满意。没关系，接下来我来教大家如何自己定义其中的颜色。

下面举个简单的例子：
##### 1）
我在 `GalleryPick` 中的 `gallery_title.xml` 中设置了标题栏的颜色为 `@color/gallery_blue` 用户可以在 app 中的 `colors.xml` 中定义一个名为 `gallery_blue` 的颜色：
```groovy
<resources>
    <color name="gallery_blue">#FF4081</color>
</resources>
```
这样就达到了覆盖资源文件的效果。从而达到自定义UI。

##### 2）
有些朋友会问，我标题栏设置了白色，但是标题栏的字体和图标也是白色的，那该怎么办？
下面来讲一下方法，因为是覆盖资源文件，所以在 app 中创建 `gallery_title.xml` , 先将 `GalleryPick` 中的 `gallery_title.xml`   代码copy过去，然后就简单了。将`TextView`的`textColor`中的颜色颜色换一下就好了。同样，返回按钮可以改变一下 `ImageView`的`src`，很简单。





## 旧版本记录
### 1.2.0
* 忽略裁剪图片，返回相机拍摄的照片。

### 1.1.8
* 优化 `Provider` 防止两个以上 App 使用 `GalleryPick`会出现安装不上的问题。
（[修改详情](https://github.com/YancyYe/GalleryPick#步骤三设置-provider)）

### 1.1.7
* 修复android 4.x 打开相机崩溃的bug

### 1.1.6
* 修复android 7.x 打开相机崩溃的bug

### 1.1.4
* 优化相册系统。拍摄、裁剪的图片，只有选中时才在相册显示。

### 1.1.3
* 修改裁剪所存在的bug

### 1.1.2
* 添加通过覆盖资源的方式，修改截图页面的配色。（[使用方法参考](https://github.com/YancyYe/GalleryPick#三深度自定义ui方法)）

### 1.1.1
* 修复直接开启相机所存在的问题

### 1.1.0
* 修复直接开启相机所隐藏的bug
* 新增裁剪功能。（[使用方法参考](https://github.com/YancyYe/GalleryPick#一裁剪功能使用说明)）

### 1.0.4
* 应使用者需求添加直接开启相机的方法。 （[使用方法参考](https://github.com/YancyYe/GalleryPick#二直接开启相机其中有三种方法)）

### 1.0.3
* 提供单选、多选、以及相机拍照功能。
* 自定义ImageLoader


## 感谢（Thanks）
* 图片裁剪 [UCrop](https://github.com/jdamcd/android-crop)


## 关于作者
* Email: [yancy_world@outlook.com](mailto:yancy_world@outlook.com)


### [旧项目地址](https://github.com/YancyYe/ImageSelector)