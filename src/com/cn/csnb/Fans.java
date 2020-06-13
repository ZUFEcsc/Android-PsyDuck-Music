package com.cn.csnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Fans extends Activity {
private Button return_btn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fan);
		
		//返回我的界面
		return_btn = (Button)this.findViewById(R.id.return_btn);
		return_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Fans.this,FrameTabActivity.class);
				startActivity(it);				
			}
		});
		
		//显示fan ListView
		ListView lvFans = (ListView)this.findViewById(R.id.lvFans);
		
		BaseAdapter adapter = new BaseAdapter() {
			String[] items = getResources().getStringArray(R.array.fansname);
			String[] imgs = getResources().getStringArray(R.array.fansimg);
			
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
				LinearLayout layout = new LinearLayout(Fans.this);
				
				layout.setOrientation(LinearLayout.HORIZONTAL);
				layout.setGravity(Gravity.LEFT);
				
				ImageView iv = new ImageView(Fans.this);
				iv.setAdjustViewBounds(true);
				iv.setImageDrawable(getResources().getDrawable(getResources()
						.getIdentifier(imgs[position], "drawable", getPackageName())));
				layout.addView(iv);
				
				TextView tv = new TextView(Fans.this);
				tv.setPadding(10, 0, 0, 0);
				tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setTextAppearance(Fans.this, R.style.text);
				tv.setGravity(Gravity.BOTTOM);//为什么字放不到底下？？？？
				tv.setText(items[position]);
				layout.addView(tv);
				
				return layout;
			}			
		};
		lvFans.setAdapter(adapter);
	}
}
