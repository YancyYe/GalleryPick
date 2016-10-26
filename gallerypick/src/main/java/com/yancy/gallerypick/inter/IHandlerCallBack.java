package com.yancy.gallerypick.inter;

import java.util.List;

/**
 * IHandlerCallBack
 * Created by Yancy on 2016/10/26.
 */

public interface IHandlerCallBack {

    void onStart();

    void onSuccess(List<String> photoList);

    void onCancel();

    void onFinish();

    void onError();

}
