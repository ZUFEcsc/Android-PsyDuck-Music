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

public class Guanzhu extends Activity {
	private Button return_btn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guanzhu);
		
		//返回我的界面
		return_btn = (Button)this.findViewById(R.id.return_btn);
		return_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Guanzhu.this,FrameTabActivity.class);
				startActivity(it);				
			}
		});
		
		//显示guanzhu ListView
		ListView lvGzs = (ListView)this.findViewById(R.id.lvGzs);
		
		BaseAdapter adapter = new BaseAdapter() {
			String[] items = getResources().getStringArray(R.array.guanzhuname);
			String[] imgs = getResources().getStringArray(R.array.guanzhuimg);
			
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
				LinearLayout layout = new LinearLayout(Guanzhu.this);
				
				layout.setOrientation(LinearLayout.HORIZONTAL);
				layout.setGravity(Gravity.LEFT);
				
				ImageView iv = new ImageView(Guanzhu.this);
				iv.setAdjustViewBounds(true);
				iv.setImageDrawable(getResources().getDrawable(getResources()
						.getIdentifier(imgs[position], "drawable", getPackageName())));
				layout.addView(iv);
				
				TextView tv = new TextView(Guanzhu.this);
				tv.setPadding(10, 0, 0, 0);
				tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setTextAppearance(Guanzhu.this, R.style.text);
				tv.setGravity(Gravity.BOTTOM);//为什么字放不到底下？？？？
				tv.setText(items[position]);
				layout.addView(tv);
				
				return layout;
			}			
		};
		lvGzs.setAdapter(adapter);
	}

}
