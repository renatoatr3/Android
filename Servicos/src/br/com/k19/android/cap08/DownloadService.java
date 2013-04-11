package br.com.k19.android.cap08;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class DownloadService extends IntentService {
	
	private int result = Activity.RESULT_CANCELED;
	private InputStream reader;
	
	public DownloadService(){
		super("DownloadServices");
	}
	@Override
	protected void onHandleIntent(Intent intent) {
		Uri data = intent.getData();
		String urlPash = intent.getStringExtra("urlPash");
		String fileName = data.getPath();
		File output =  new File(Environment.getExternalStorageDirectory(),
		fileName);
		if (output.exists()) {
			output.delete();
		}
		InputStream stream = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL(urlPash);
			stream = url.openConnection().getInputStream();
			fos = new FileOutputStream(output.getPath());
			int next = -1;
			while ((next = reader.read()) != -1){
				fos.write(next);
			}
			result = Activity.RESULT_OK;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (stream != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Bundle extras = intent.getExtras();
		if (extras != null) {
			Messenger messenger = (Messenger) extras.get("messenger");
			Message msg = Message.obtain();
			msg.arg1 = result;
			msg.obj = output.getAbsolutePath();
			try {
				messenger.send(msg);
			} catch (android.os.RemoteException e1) {
				Log.e("DownladService", "Erroao enviar mensagem", e1);
			}
		}
	}
}
