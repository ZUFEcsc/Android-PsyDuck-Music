package com.cn.csnb;

import com.cn.csnb.MusicService.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Songs extends Activity {
	private Button return_btn;
	private EditText etPath;
	private Intent intent;
	private SeekBar mySeekBar;
	private Thread myThread;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what==100)
			{
				int currentPosition = (Integer)msg.obj;
				mySeekBar.setProgress(currentPosition);
			}
		}
	};
	
	MyBinder binder;
	private MyConn conn;
		
	public void onCreate(Bundle savedIntanceState) {
		super.onCreate(savedIntanceState);
		setContentView(R.layout.song);
		
		//返回我的界面
		return_btn = (Button)this.findViewById(R.id.return_btn);
		return_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Songs.this, FrameTabActivity.class);
				startActivity(it);
			}
		});
		
		//显示歌单内歌曲信息
		ListView lvSongs = (ListView)this.findViewById(R.id.lvSongs);
		
		BaseAdapter adapter = new BaseAdapter() {
			String[] items = getResources().getStringArray(R.array.songname);
			String[] imgIds = getResources().getStringArray(R.array.songimg);
			public int getCount() {
				// TODO Auto-generated method stub
				return items.length;
			}

			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout layout = new LinearLayout(Songs.this);
				
				layout.setOrientation(LinearLayout.HORIZONTAL);
				layout.setGravity(Gravity.LEFT);
				
				//歌曲封面
				ImageView iv = new ImageView(Songs.this);
				iv.setAdjustViewBounds(true);
				iv.setImageDrawable(getResources()
						.getDrawable(getResources()
								.getIdentifier(imgIds[position],"drawable",getPackageName())));
				layout.addView(iv);
				
				LinearLayout layout_text = new LinearLayout(Songs.this);
				
				layout_text.setOrientation(LinearLayout.VERTICAL);
				layout_text.setGravity(Gravity.LEFT);
								
				//歌曲信息
				TextView tv = new TextView(Songs.this);
				tv.setPadding(10, 0, 0, 0);
				tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setTextAppearance(Songs.this, R.style.text);
//				tv.setGravity(Gravity.BOTTOM);
				tv.setText(items[position]);
				layout_text.addView(tv);
				
				//获取更多信息
				TextView tv_more = new TextView(Songs.this);
				tv_more.setPadding(10, 0, 0, 0);
				tv_more.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv_more.setTextAppearance(Songs.this, R.style.text);
				tv_more.setGravity(Gravity.RIGHT);
				tv_more.setText("…");
				layout_text.addView(tv_more);
				
				layout.addView(layout_text);
				
				return layout;
			}			
		};
		lvSongs.setAdapter(adapter);
		
		lvSongs.setOnItemClickListener(new MyOnClickListener());
			
		//音乐播放
		mySeekBar = (SeekBar)this.findViewById(R.id.seekBar1);
//		etPath = (EditText)this.findViewById(R.id.editText1);
        
		TextView tvPlay = (TextView)this.findViewById(R.id.tvPlay);
		TextView tvPause = (TextView)this.findViewById(R.id.tvPause);
		TextView tvNext = (TextView)this.findViewById(R.id.tvNext);
		
		tvPlay.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String path = "data/data/com.cn.csnb/aa.mp3";
				binder.plays(path);
				initSeekBar();
				updateProgress();
			}
		});
		
		tvPause.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				binder.pauses();
			}
		});
		
		tvNext.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		this.conn = new MyConn();
        this.intent = new Intent(this,MusicService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
    	if(this.myThread != null)
    	{
    		if(!this.myThread.isInterrupted())
    		{
    			this.myThread.interrupt();
    		}
    	}
    	this.unbindService(conn);
		super.onDestroy();
	}

	//初始化进度条
    public void initSeekBar()
    {
    	int musicWidth = binder.getMusicWidth();
    	this.mySeekBar.setMax(musicWidth);
    }
    
    //跟新音乐播放的进度条
    private void updateProgress()
    {
    	this.myThread = new Thread() {
    		public void run() {
    			while(!interrupted()) {
    				int currentPosition = binder.getCurrentPosition();
    				Message message = Message.obtain();
    				message.obj = currentPosition;
    				message.what = 100;
    				handler.sendMessage(message);
    			}
    		}
    	};
    	this.myThread.start();
    }
	
	private class MyConn implements ServiceConnection{

		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			binder = (MyBinder)service;
		}

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
	
	//切换歌曲详细信息MyOnClickListener内部类
    private class MyOnClickListener implements OnItemClickListener{

		public void onItemClick(AdapterView<?> parent, View view, int positon, long id) {
			// TODO Auto-generated method stub
			Intent gdIntent = new Intent(Songs.this,SongItemActivity.class);//跳转到歌单页面
			startActivity(gdIntent);
		}
		
	}

}
