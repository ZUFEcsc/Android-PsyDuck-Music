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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Prend extends Activity {
	private Button return_btn;
	private Button addprend_btn;
	private EditText prendText;
	
	public void onCreate(Bundle savedIntanceState) {
		super.onCreate(savedIntanceState);
		setContentView(R.layout.prend);

		//返回我的界面
		return_btn = (Button)this.findViewById(R.id.return_btn);
		return_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Prend.this, FrameTabActivity.class);
				startActivity(it);
			}
		});
		
		//显示个人动态集合
		ListView lvPrend = (ListView)this.findViewById(R.id.lvPrend);
		BaseAdapter adapter = new BaseAdapter() {

			String[] times = getResources().getStringArray(R.array.prendtime);
			String[] items = getResources().getStringArray(R.array.prenditem);
						
			public int getCount() {
				// TODO Auto-generated method stub
				return times.length;
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
				LinearLayout layout = new LinearLayout(Prend.this);
				
				layout.setOrientation(LinearLayout.HORIZONTAL);
				layout.setGravity(Gravity.LEFT);
				TextView tvtime = new TextView(Prend.this);
				tvtime.setPadding(10, 0, 0, 0);
				tvtime.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 
						LayoutParams.WRAP_CONTENT));
				tvtime.setTextAppearance(Prend.this, R.style.prendtime);
				tvtime.setGravity(Gravity.TOP);
				tvtime.setText(times[position]);
				layout.addView(tvtime);
				
				TextView tvitem = new TextView(Prend.this);
				tvitem.setPadding(10, 0, 0, 0);
				tvitem.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				tvitem.setTextAppearance(Prend.this, R.style.prenditem);
				tvitem.setGravity(Gravity.BOTTOM);
				tvitem.setText(items[position]);
				layout.addView(tvitem);
				
				return layout;
			}
		};
		lvPrend.setAdapter(adapter);
		
		//点击生成动态
		addprend_btn = (Button)this.findViewById(R.id.addprend_btn);
		prendText = (EditText)this.findViewById(R.id.prendText);
		String prendStr = prendText.getText().toString().trim();
		
		addprend_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder  = new Builder(Prend.this);
				 builder.setTitle("恭喜") ;
				 builder.setMessage("发布动态成功 ~ " ) ;
				 builder.setPositiveButton("确定" ,null);
				 builder.show(); 
			}
		});
		
		//添加动态到ListView
		
		
	}
}
