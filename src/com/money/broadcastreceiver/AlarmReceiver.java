package com.money.broadcastreceiver;

import com.book.utils.ToastUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		ToastUtils.showToast(context, "闹钟响了");
		Log.i("闹钟广播", "gogo");
	}

}
