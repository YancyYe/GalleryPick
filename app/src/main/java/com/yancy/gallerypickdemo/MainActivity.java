package com.yancy.gallerypickdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.yancy.gallerypickdemo.loader.FrescoImageLoader;
import com.yancy.gallerypickdemo.loader.GlideImageLoader;
import com.yancy.gallerypickdemo.loader.PicassoImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "---Yancy---";
    private Context mContext;
    private Activity mActivity;

    private Button btn;
    private Button btnOpenCamera;
    private Switch swMulSelect;
    private Switch swShowCamera;
    private Switch swIsCrop;
    private EditText etMulMaxSize;
    private RecyclerView rvResultPhoto;
    private RadioGroup rgImageLoader;


    private PhotoAdapter photoAdapter;

    private List<String> path = new ArrayList<>();

    private GalleryConfig galleryConfig;
    private IHandlerCallBack iHandlerCallBack;

    private final int PERMISSIONS_REQUEST_READ_CONTACTS = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mActivity = this;

        initView();
        initGallery();
        init();

    }

    private void initView() {
        btn = (Button) super.findViewById(R.id.btn);
        btnOpenCamera = (Button) super.findViewById(R.id.btnOpenCamera);
        swMulSelect = (Switch) super.findViewById(R.id.swMulSelect);
        swShowCamera = (Switch) super.findViewById(R.id.swShowCamera);
        swIsCrop = (Switch) super.findViewById(R.id.swIsCrop);
        etMulMaxSize = (EditText) super.findViewById(R.id.etMulMaxSize);
        rvResultPhoto = (RecyclerView) super.findViewById(R.id.rvResultPhoto);
        rgImageLoader = (RadioGroup) super.findViewById(R.id.rgImageLoader);
    }


    private void init() {

        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                galleryConfig = new GalleryConfig.Builder()
//                        .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
//                        .filePath("/Gallery/Pictures")          // 图片存放路径
//                        .isOpenCamera(true)                    // 直接开启相机的标识位
//                        .build();
//                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(mActivity);

//                galleryConfig.getBuilder().isOpenCamera(true).build();

                // 如果已配置好  galleryConfig 不想修改：
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).openCamera(mActivity);

            }
        });

        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider("com.yancy.gallerypickdemo.fileprovider")   // provider(必填)
                .pathList(path)                         // 记录已选的图片
                .multiSelect(false)                      // 是否多选   默认：false
                .multiSelect(false, 9)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
                .crop(false)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .crop(false, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etMulMaxSize.getText().toString()) && Integer.valueOf(etMulMaxSize.getText().toString()) > 0) {
                    galleryConfig.getBuilder().maxSize(Integer.valueOf(etMulMaxSize.getText().toString())).build();
                }
                galleryConfig.getBuilder().isOpenCamera(false).build();
                initPermissions();
            }
        });

        // 是否多选
        swMulSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                galleryConfig.getBuilder().multiSelect(isChecked).build();
            }
        });

        // 是否显示相机
        swShowCamera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                galleryConfig.getBuilder().isShowCamera(isChecked).build();
            }
        });

        // 是否裁剪
        swIsCrop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                galleryConfig.getBuilder().crop(isChecked).build();
            }
        });


        // 选择图片加载框架
        rgImageLoader.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbGlide) {
                    galleryConfig.getBuilder().imageLoader(new GlideImageLoader()).build();
                } else if (checkedId == R.id.rbPicasso) {
                    galleryConfig.getBuilder().imageLoader(new PicassoImageLoader()).build();
                } else if (checkedId == R.id.rbFresco) {
                    galleryConfig.getBuilder().imageLoader(new FrescoImageLoader(mContext)).build();
                }
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvResultPhoto.setLayoutManager(gridLayoutManager);

        photoAdapter = new PhotoAdapter(this, path);
        rvResultPhoto.setAdapter(photoAdapter);

    }

    // 授权管理
    private void initPermissions() {
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "需要授权 ");
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.i(TAG, "拒绝过了");
                Toast.makeText(mContext, "请在 设置-应用管理 中开启此应用的储存授权。", Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "进行授权");
                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            Log.i(TAG, "不需要授权 ");
            GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(MainActivity.this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "同意授权");
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(MainActivity.this);
            } else {
                Log.i(TAG, "拒绝授权");
            }
        }
    }


    private void initGallery() {
        iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "onStart: 开启");
            }

            @Override
            public void onSuccess(List<String> photoList) {
                Log.i(TAG, "onSuccess: 返回数据");
                path.clear();
                for (String s : photoList) {
                    Log.i(TAG, s);
                    path.add(s);
                }
                photoAdapter.notifyDataSetChanged();
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

    }
}
