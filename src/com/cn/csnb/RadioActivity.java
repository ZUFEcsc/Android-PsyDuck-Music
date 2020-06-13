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

public class RadioActivity extends Activity {
	private Button return_btn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio);
		
		//返回我的界面
		return_btn = (Button)this.findViewById(R.id.return_btn);
		return_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(RadioActivity.this, FrameTabActivity.class);
				startActivity(it);
			}
		});
		
		//显示电台信息
		ListView lvRadio = (ListView)this.findViewById(R.id.lvRadio);
		
		BaseAdapter adapter = new BaseAdapter() {
			String[] items = getResources().getStringArray(R.array.radioname);
			String[] imgIds = getResources().getStringArray(R.array.radioimg);
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
				LinearLayout layout = new LinearLayout(RadioActivity.this);
				
				layout.setOrientation(LinearLayout.HORIZONTAL);
				layout.setGravity(Gravity.LEFT);
				
				//歌曲封面
				ImageView iv = new ImageView(RadioActivity.this);
				iv.setAdjustViewBounds(true);
				iv.setImageDrawable(getResources()
						.getDrawable(getResources()
								.getIdentifier(imgIds[position],"drawable",getPackageName())));
				layout.addView(iv);
				
				LinearLayout layout_text = new LinearLayout(RadioActivity.this);
				
				layout_text.setOrientation(LinearLayout.VERTICAL);
				layout_text.setGravity(Gravity.LEFT);
								
				//歌曲信息
				TextView tv = new TextView(RadioActivity.this);
				tv.setPadding(10, 0, 0, 0);
				tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setTextAppearance(RadioActivity.this, R.style.text);
//				tv.setGravity(Gravity.BOTTOM);
				tv.setText(items[position]);
				layout_text.addView(tv);
				
				//获取更多信息
				TextView tv_more = new TextView(RadioActivity.this);
				tv_more.setPadding(10, 0, 0, 0);
				tv_more.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv_more.setTextAppearance(RadioActivity.this, R.style.text);
				tv_more.setGravity(Gravity.RIGHT);
				tv_more.setText("…");
				layout_text.addView(tv_more);
				
				layout.addView(layout_text);
				
				return layout;
			}			
		};
		lvRadio.setAdapter(adapter);
		
	}

}
