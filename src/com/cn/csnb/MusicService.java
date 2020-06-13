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
		//��������
		public void plays(String path)
		{
			play(path);
		}
		
		//��ͣ����
		public void pauses()
		{
			pause();
		}
		
		//���²���
		public void replays()
		{
			replay();
		}
		
		//ֹͣ����
		public void stops()
		{
			stop();
		}
		
		//��ȡ��ǰ���Ž���
		public int getCurrentPosition()
		{
			return getCurrentProgress();
		}
		
		//��ȡ���ֳ���
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
	
	//��������
		public void play(String path)
		{
			try {
				if(this.mediaPlayer == null)
				{
					this.mediaPlayer = new MediaPlayer();
					this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
					//ָ������·��
					this.mediaPlayer.setDataSource(path);
					//׼������
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
		//��ͣ��������
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
		
		//���²���
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
		
		//ֹͣ����
		public void stop()
		{
			if(this.mediaPlayer != null) 
			{
				this.mediaPlayer.stop();
				this.mediaPlayer.release();
				this.mediaPlayer =  null;
			}
			else {
				Toast.makeText(getApplicationContext(), "��ֹͣ", 0).show();
			}
		}
		
		//��ȡ��Դ�ļ��ĳ���
		public int getMusicLength()
		{
			if(this.mediaPlayer != null)
				return this.mediaPlayer.getDuration();
			return 0;
		}
		
		//��ȡ��ǰ����
		public int getCurrentProgress() {
			if(this.mediaPlayer != null){
				return this.mediaPlayer.getCurrentPosition();
			}
			return 0;
		}

}
