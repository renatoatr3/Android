package br.com.k19.android.cap08_02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneReceiver extends BroadcastReceiver {
	private static final String TAG = "PhoneReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent){
	Bundle extra = intent.getExtras();
	if (extra !=null) {
		String state = extra.getString(TelephonyManager.EXTRA_STATE);
		Log.w(TAG, state);
		if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
			String phoneNumber = extra.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
			Log.w(TAG, phoneNumber);
		}
	}
	}
}
