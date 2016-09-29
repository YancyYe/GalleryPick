package com.yancy.gallerypickdemo;

import android.content.Intent;
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

import com.yancy.gallerypick.activity.GalleryPickActivity;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button btn;
    private Switch swMulSelect;
    private Switch isShowCamera;
    private EditText etMulMaxSize;
    private RecyclerView rvResultPhoto;


    private PhotoAdapter photoAdapter;

    private final int GALLERY_CODE = 20;

    private List<String> path = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
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
                GalleryConfig galleryConfig = new GalleryConfig.Builder()
                        .imageLoader(new ImageLoader())
                        .multiSelect(swMulSelect.isChecked())
                        .isShowCamera(isShowCamera.isChecked())
                        .pathList(path)
                        .requestCode(GALLERY_CODE)
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(GalleryPickActivity.EXTRA_RESULT);

            path.clear();
            for (String s : pathList) {
                Log.i("ImagePathList", s);
                path.add(s);
            }
            photoAdapter.notifyDataSetChanged();

        }


    }
}
