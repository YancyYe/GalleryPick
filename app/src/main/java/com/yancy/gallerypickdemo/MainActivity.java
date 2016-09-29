package com.yancy.gallerypickdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;

public class MainActivity extends AppCompatActivity {


    private Button btn;
    private Switch swMulSelect;
    private Switch isShowCamera;

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
        isShowCamera = (Switch) super.findViewById(R.id.isShowCamera);
    }

    private void init() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryConfig galleryConfig = new GalleryConfig.Builder()
                        .imageLoader(new ImageLoader())
                        .multiSelect(swMulSelect.isChecked())
                        .isShowCamera(isShowCamera.isChecked())
                        .build();
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(MainActivity.this);
            }
        });


    }
}
