package com.yancy.gallerypickdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.widget.GalleryPickView;

public class MainActivity extends AppCompatActivity {


    private Button btn;
    private GalleryPickView galleryPickView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button) super.findViewById(R.id.btn);
        galleryPickView = (GalleryPickView) super.findViewById(R.id.galleryPickView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryPick.getInstance().open(MainActivity.this);
            }
        });

        Activity activity = this;


        galleryPickView.init(this);

    }
}
