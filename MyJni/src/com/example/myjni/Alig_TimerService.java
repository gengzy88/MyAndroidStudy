package com.example.myjni;

import com.example.myjni.MyService.MyBind;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class Alig_TimerService extends Service{

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		Intent lIntent = new Intent(this, MyService.class);
		lIntent.putExtra("name", "Alig_TimerService");
		bindService(lIntent, mConnection, Context.BIND_AUTO_CREATE);
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(true == mHasBind){
					Message message = new Message();
					message.what = 3;
					try {
						serviceMessage.send(message);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(null != Alig_TimerService.this.mHandler){
						mHandler.postDelayed(this, 5000);
					}
				}
			}
		};
		if(null == mHandler){
			mHandler = new Handler();
		}
		mHandler.postDelayed(runnable, 5000);
	}

	private Messenger serviceMessage;
	private ServiceConnection mConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			mHasBind = false;
		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			// TODO Auto-generated method stub
			if(arg1 != null)
			{
				mHasBind = true;
				serviceMessage = new Messenger(arg1);
				mServerService = ((MyBind)arg1).getService();
				
			}
			//int a = 0;
		}
	};
	boolean mHasBind;
	Service mServerService;
	Handler mHandler;
}
