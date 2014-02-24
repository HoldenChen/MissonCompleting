package com.holden.missioncompleting;

import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.missioncompleting.R;
import com.holden.missioncompleting.util.MyAppalication;

public class ShowAlarmActivity extends Activity{
	private Button stopBtn = null;
	AlarmThread mythread = new AlarmThread(this);
	public void onCreate(Bundle saveInstancestate){
		super.onCreate(saveInstancestate);
		 MyAppalication.getInstance().addActivity(ShowAlarmActivity.this);
		setContentView(R.layout.showalarm);
		stopBtn = (Button)findViewById(R.id.stopalarm);
		stopBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//mythread.StopAlarmRing();
				System.out.println(mythread.getState());
				
					System.out.println("stop the ring ");
					mythread.StopAlarmRing();
					mythread.interrupt();
					ShowAlarmActivity.this.finish();
					
				
				// TODO Auto-generated method stub
				
			}});
		
	}
	
	public void onStart(){
		super.onStart();
		if(!mythread.isAlive()){		
			mythread.start();
		}	
	}
	
}

class AlarmThread extends Thread{
	MediaPlayer mMediaPlayer;
	Context mcontext;
	boolean flag = true;
	public AlarmThread(Context mcontext){
		mMediaPlayer = new MediaPlayer();
		this.mcontext = mcontext;
	}
	
	
	
		@Override
		public void run() {
			
			Uri alarmAlert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
			try{
			try {
				mMediaPlayer.setDataSource(mcontext,alarmAlert);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			final AudioManager audioManager = (AudioManager)mcontext.getSystemService(Context.AUDIO_SERVICE);
			if(audioManager.getStreamVolume(AudioManager.STREAM_ALARM)!=0){
				mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
				mMediaPlayer.setLooping(true);
				try {
					mMediaPlayer.prepare();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}}catch(IllegalStateException e){
				e.printStackTrace();
			}
			
			mMediaPlayer.start();
		}
	
	
	
	public void StopAlarmRing(){
		mMediaPlayer.stop();
		mMediaPlayer.release();
	}
	

	
	
}
