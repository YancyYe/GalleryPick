package com.yancy.gallerypick.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.yancy.gallerypick.R;
import com.yancy.gallerypick.adapter.FolderAdapter;

/**
 * 文件夹列表选择
 * Created by Yancy on 2016/11/1.
 */
public class FolderListPopupWindow extends PopupWindow {

    private final static String TAG = "FolderListPopupWindow";

    private RecyclerView rvFolderList;

    private Context mContext;
    private Activity mActivity;
    private View popupWindow;

    private FolderAdapter folderAdapter;   // 文件夹适配器


    public FolderListPopupWindow(Activity mActivity, Context mContext, FolderAdapter folderAdapter) {
        super(mContext);
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.folderAdapter = folderAdapter;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupWindow = inflater.inflate(R.layout.gallery_popup_folder, null);

        initView();
        init();

    }

    private void initView() {
        rvFolderList = (RecyclerView) popupWindow.findViewById(R.id.rvFolderList);
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFolderList.setLayoutManager(linearLayoutManager);
        rvFolderList.setAdapter(folderAdapter);

        //设置PopupWindow的View
        this.setContentView(popupWindow);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(false);
        //设置PopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popupAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(mContext.getResources().getColor(R.color.gallery_folder_bg));
        //设置PopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

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