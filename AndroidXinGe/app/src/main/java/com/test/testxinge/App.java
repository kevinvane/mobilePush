package com.test.testxinge;

import android.app.Application;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

public class App extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		XGPushConfig.enableDebug(this, true);
		XGPushManager.registerPush(this, "samwen",new XGIOperateCallback() {
			@Override
			public void onSuccess(Object data, int flag) {
				Log.i("TPush", "注册成功，设备token为：" + data);
			}
			@Override
			public void onFail(Object data, int errCode, String msg) {
				Log.e("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
			}
		});
	}

}
