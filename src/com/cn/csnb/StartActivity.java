package com.cn.csnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		new Handler().postDelayed(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(StartActivity.this,PsyDuckMusicActivity.class);
				startActivity(intent);
				StartActivity.this.finish();				
			}
			
		},3000);
	}
	
}
