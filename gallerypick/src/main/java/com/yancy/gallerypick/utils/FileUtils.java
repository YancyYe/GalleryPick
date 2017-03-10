package com.yancy.gallerypick.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * FileUtils
 * Created by Yancy on 2015/12/2.
 */
public class FileUtils {


    private final static String PATTERN = "yyyyMMddHHmmss";    // 时间戳命名


    /**
     * 创建文件
     *
     * @param context  context
     * @param filePath 文件路径
     * @return file
     */
    public static File createTmpFile(Context context, String filePath) {

        String timeStamp = new SimpleDateFormat(PATTERN, Locale.CHINA).format(new Date());

        String externalStorageState = Environment.getExternalStorageState();

        File dir = new File(Environment.getExternalStorageDirectory() + filePath);

        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return new File(dir, timeStamp + ".jpg");
        } else {
            File cacheDir = context.getCacheDir();
            return new File(cacheDir, timeStamp + ".jpg");
        }

    }


    /**
     * 创建初始文件夹。保存拍摄图片和裁剪后的图片
     *
     * @param filePath 文件夹路径
     */
    public static void createFile(String filePath) {
        String externalStorageState = Environment.getExternalStorageState();

        File dir = new File(Environment.getExternalStorageDirectory() + filePath);
        File cropFile = new File(Environment.getExternalStorageDirectory() + filePath + "/crop");

        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            if (!cropFile.exists()) {
                cropFile.mkdirs();
            }

            File file = new File(cropFile, ".nomedia");    // 创建忽视文件。   有该文件，系统将检索不到此文件夹下的图片。
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public static String getFilePath(Context context) {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getPath();
        } else {
            return context.getCacheDir().getAbsolutePath();
        }
    }


    /**
     * @param filePath 文件夹路径
     * @return 截图完成的 file
     */
    public static File getCorpFile(String filePath) {
        String timeStamp = new SimpleDateFormat(PATTERN, Locale.CHINA).format(new Date());
        return new File(Environment.getExternalStorageDirectory() + filePath + "/crop/" + timeStamp + ".jpg");
    }


}