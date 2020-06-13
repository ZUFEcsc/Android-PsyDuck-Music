package com.cn.csnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PsyDuckItemActivity extends Activity {

	private Button logout_btn; 
	
	public void onCreate(Bundle savedIntanceState) {
		super.onCreate(savedIntanceState);
		setContentView(R.layout.psy_duck_item);
		//退出登录按钮事件
		logout_btn = (Button)this.findViewById(R.id.logout_btn);
		logout_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(PsyDuckItemActivity.this, PsyDuckMusicActivity.class);
				startActivity(it);
			}
		});
	}

}
