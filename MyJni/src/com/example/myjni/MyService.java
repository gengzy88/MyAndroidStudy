package com.example.myjni;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;


public class MyService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		//return null;
		String bindName = arg0.getStringExtra("name");
		if(bindName!=null && bindName.equals("Alig_TimerService")){
			mDisplayName = "Alig_TimerService";
		}
		return mMessenger.getBinder();
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
//		new Thread() {
//			public void run() {
//				
//			}
//		}.start();
		
//		Timer timer = new Timer(true);
//		timer.schedule(task, 1000);
		mMessenger = new Messenger(myHandler);
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message message = new Message();      
			    message.what = 1;      
			    myHandler.sendMessage(message); 
			}
		};
		myHandler.postDelayed(runnable, 5000);
	}
	
//	TimerTask task = new TimerTask(){  
//		       public void run() {  
//		       Message message = new Message();      
//		       message.what = 1;      
//		       myHandler.sendMessage(message);    
//		    }  
//		 };
		
	public void sendMessage(){
//		Messenger messenger = new Messenger(myHandler);
		Message message = new Message();
		message.what = 1;
		try {
			mMessenger.send(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Handler myHandler = new Handler(){
		public void handleMessage(Message msg){
//			while(msg.what){
//				
//			}
			switch(msg.what){
			case 1:
//				Intent intent = new Intent(MyService.this, SecondActivity.class);
//				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				startActivity(intent);
				Runnable runnablea = new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MyService.this, SecondActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
					}
				};
				this.postDelayed(runnablea, 2000);
				break;
			case 2:
				
				Runnable runnable = new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MyService.this, SecondActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
					}
				};
				this.postDelayed(runnable, 5000);
				break;
			case 3:
				
				break;
			default:
				break;
			}
		}
	};
	
	class MyBind extends Binder{
		public Service getService(){
			return MyService.this;
		}
	}
	
	private MyBind myBind = new MyBind();
	Messenger mMessenger;
	String mDisplayName;
}


