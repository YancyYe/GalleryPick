package com.yancy.gallerypickdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

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

    private Button btn;
    private Switch swMulSelect;
    private Switch swShowCamera;
    private EditText etMulMaxSize;
    private RecyclerView rvResultPhoto;
    private RadioGroup rgImageLoader;


    private PhotoAdapter photoAdapter;

    private List<String> path = new ArrayList<>();

    private GalleryConfig galleryConfig;
    private IHandlerCallBack iHandlerCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        initView();
        initGallery();
        init();

    }

    private void initView() {
        btn = (Button) super.findViewById(R.id.btn);
        swMulSelect = (Switch) super.findViewById(R.id.swMulSelect);
        swShowCamera = (Switch) super.findViewById(R.id.swShowCamera);
        etMulMaxSize = (EditText) super.findViewById(R.id.etMulMaxSize);
        rvResultPhoto = (RecyclerView) super.findViewById(R.id.rvResultPhoto);
        rgImageLoader = (RadioGroup) super.findViewById(R.id.rgImageLoader);
    }

    private void init() {

        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())
                .iHandlerCallBack(iHandlerCallBack)
                .pathList(path)
                .build();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(MainActivity.this);
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

        // 修改多选数量
        etMulMaxSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && Integer.valueOf(s.toString()) > 0) {
                    galleryConfig.getBuilder().maxSize(Integer.valueOf(s.toString())).build();
                }
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
