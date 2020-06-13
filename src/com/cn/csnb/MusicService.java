package com.cn.csnb;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MusicService extends Service {
	private static final String TAG = "MusicService";
	public MediaPlayer mediaPlayer;
	class MyBinder extends Binder{
		//播放音乐
		public void plays(String path)
		{
			play(path);
		}
		
		//暂停播放
		public void pauses()
		{
			pause();
		}
		
		//重新播放
		public void replays()
		{
			replay();
		}
		
		//停止播放
		public void stops()
		{
			stop();
		}
		
		//获取当前播放进度
		public int getCurrentPosition()
		{
			return getCurrentProgress();
		}
		
		//获取音乐长度
		public int getMusicWidth()
		{
			return getMusicLength();
		}
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return new MyBinder();
	}
	
	//播放音乐
		public void play(String path)
		{
			try {
				if(this.mediaPlayer == null)
				{
					this.mediaPlayer = new MediaPlayer();
					this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
					//指定播放路径
					this.mediaPlayer.setDataSource(path);
					//准备播放
					this.mediaPlayer.prepare();
					this.mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

						public void onPrepared(MediaPlayer mp) {
							// TODO Auto-generated method stub
							mediaPlayer.start();
						}					
					});
				}
				else 
				{
					int position = getCurrentProgress();
					this.mediaPlayer.seekTo(position);
					try {
						this.mediaPlayer.prepare();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					this.mediaPlayer.start();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//暂停播放音乐
		public void pause()
		{
			if(this.mediaPlayer != null && this.mediaPlayer.isPlaying())
			{
				this.mediaPlayer.pause();
			}
			else 
			{
				if(this.mediaPlayer != null && !this.mediaPlayer.isPlaying())
				{
					this.mediaPlayer.start();	
				}			
			}
		}
		
		//重新播放
		public void replay()
		{
			if(this.mediaPlayer != null)
			{
				this.mediaPlayer.seekTo(0);
				try {
					this.mediaPlayer.prepare();
					this.mediaPlayer.start();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		//停止播放
		public void stop()
		{
			if(this.mediaPlayer != null) 
			{
				this.mediaPlayer.stop();
				this.mediaPlayer.release();
				this.mediaPlayer =  null;
			}
			else {
				Toast.makeText(getApplicationContext(), "已停止", 0).show();
			}
		}
		
		//获取资源文件的长度
		public int getMusicLength()
		{
			if(this.mediaPlayer != null)
				return this.mediaPlayer.getDuration();
			return 0;
		}
		
		//获取当前进度
		public int getCurrentProgress() {
			if(this.mediaPlayer != null){
				return this.mediaPlayer.getCurrentPosition();
			}
			return 0;
		}

}
