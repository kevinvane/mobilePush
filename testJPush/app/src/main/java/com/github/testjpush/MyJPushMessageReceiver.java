package com.github.testjpush;

import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * Created by led on 2017/10/20.
 * 自定义JPush message 接收器,包括操作tag/alias的结果返回(仅仅包含tag/alias新接口部分)
 */

public class MyJPushMessageReceiver extends JPushMessageReceiver {

    private static final String TAG = "Receiver-Result";

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {

        Log.d(TAG, "onTagOperatorResult: "+jPushMessage.toString());
        super.onTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onCheckTagOperatorResult(Context context,JPushMessage jPushMessage){

        Log.d(TAG, "onTagOperatorResult: "+jPushMessage.toString());
        super.onCheckTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {

        Log.d(TAG, "onAliasOperatorResult: "+jPushMessage.toString());
        super.onAliasOperatorResult(context, jPushMessage);
    }
}
