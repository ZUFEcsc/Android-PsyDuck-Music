package com.cn.csnb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class NormalActivity extends Activity { 
	private TextView tv_guanzhu;
	private TextView tv_fans;
	private ImageView psyduck_item;
	
	public void onCreate(Bundle savedIntanceState) {
		super.onCreate(savedIntanceState);
		setContentView(R.layout.normal);
		
		//点击个人信息
		psyduck_item = (ImageView)this.findViewById(R.id.iv_psyduck);
		psyduck_item.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent itpsyduck = new Intent(NormalActivity.this, PsyDuckItemActivity.class);
				startActivity(itpsyduck);
			}
		});
				
		//显示歌单
		ListView lvPublish = (ListView)this.findViewById(R.id.lvPublish);
		
		BaseAdapter adapter = new BaseAdapter() {
			String[] items = getResources().getStringArray(R.array.lvtexts);
			String[] imgIds = getResources().getStringArray(R.array.lvicons);
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
				LinearLayout layout = new LinearLayout(NormalActivity.this);
				layout.setOrientation(LinearLayout.HORIZONTAL);
//				layout.setGravity(Gravity.LEFT);
				ImageView iv = new ImageView(NormalActivity.this);
				iv.setAdjustViewBounds(true);
				iv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				iv.setImageDrawable(getResources()
						.getDrawable(getResources()
								.getIdentifier(imgIds[position],"drawable",getPackageName())));
				layout.addView(iv);
				TextView tv = new TextView(NormalActivity.this);
				tv.setPadding(10, 0, 0, 0);
				tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setTextAppearance(NormalActivity.this, R.style.text);
				tv.setGravity(Gravity.BOTTOM);
				tv.setText(items[position]);
				layout.addView(tv);
				return layout;
			}
			
		};
		lvPublish.setAdapter(adapter);
		
		//切换歌单显示页面
		lvPublish.setOnItemClickListener(new MyOnClickListener());
			
		//跳转页面：我的关注
		tv_guanzhu = (TextView)this.findViewById(R.id.tv_guanzhu);
		tv_guanzhu.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent it_guanzhu = new Intent(NormalActivity.this,Guanzhu.class);
				startActivity(it_guanzhu);
			}
		});
		
		//跳转页面：我的粉丝
		tv_fans = (TextView)this.findViewById(R.id.tv_fans);
		tv_fans.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent it_fans = new Intent(NormalActivity.this,Fans.class);
				startActivity(it_fans);
			}
		});
		
	};
	
	private class MyOnClickListener implements OnItemClickListener{

		public void onItemClick(AdapterView<?> parent, View view, int positon, long id) {
			// TODO Auto-generated method stub
			Intent gdIntent = new Intent(NormalActivity.this,Songs.class);
			startActivity(gdIntent);
		}
		
	}
}
