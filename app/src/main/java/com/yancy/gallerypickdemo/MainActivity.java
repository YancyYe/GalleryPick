package com.yancy.gallerypickdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.yancy.gallerypickdemo.loader.FrescoImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "---Yancy---";

    private Button btn;
    private Switch swMulSelect;
    private Switch isShowCamera;
    private EditText etMulMaxSize;
    private RecyclerView rvResultPhoto;


    private PhotoAdapter photoAdapter;

    private List<String> path = new ArrayList<>();

    private GalleryConfig galleryConfig;
    private IHandlerCallBack iHandlerCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initGallery();
        init();

    }

    private void initView() {
        btn = (Button) super.findViewById(R.id.btn);
        swMulSelect = (Switch) super.findViewById(R.id.swMulSelect);
        isShowCamera = (Switch) super.findViewById(R.id.swShowCamera);
        rvResultPhoto = (RecyclerView) super.findViewById(R.id.rvResultPhoto);

    }

    private void init() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                galleryConfig = new GalleryConfig.Builder()
                        .imageLoader(new FrescoImageLoader(MainActivity.this))
                        .iHandlerCallBack(iHandlerCallBack)
                        .multiSelect(swMulSelect.isChecked())
                        .isShowCamera(isShowCamera.isChecked())
                        .pathList(path)
                        .build();
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(MainActivity.this);
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
