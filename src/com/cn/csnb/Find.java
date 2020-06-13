package com.cn.csnb;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Find extends Activity {
	private ListView listView;//列表
	private SearchView searchView;//搜索框
	private List<String> data = new ArrayList<String>();  //ListView数据源
	private Button return_btn;
	
	public void onCreate(Bundle savedIntanceState) {
		super.onCreate(savedIntanceState);
		setContentView(R.layout.find_main);
		listView = (ListView)this.findViewById(R.id.listView_find);
		searchView = (SearchView)this.findViewById(R.id.searchView_find);

		return_btn = (Button)this.findViewById(R.id.return_btn);
		return_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Find.this, FrameTabActivity.class);
				startActivity(it);
			}
		});
		 //设置搜索方法
        searchView.setSearchWay(new SearchView.SearchWay<String>(){
 
            @Override
            public List<String> getData() {
                //返回数据源
                return data;
            }
 
            @Override
            public boolean matchItem(String item, String s) {
                //如果串item中包含串s，则匹配
                return item.contains(s);
            }
 
            @Override
            public void update(List<String> resultList) {
                //更新ListView的数据
                setListViewData(resultList);
            }
        });
 
        initListView();
        
        //搜索到的歌曲点击事件
        listView.setOnItemClickListener(new MyOnClickListener());
	}

//     * 初始化ListView

    private void initListView(){
        //初始化字符数组
//        for (int i = 0; i < 20; i++){
            data.add("Ruth B. - Slow Fade");
            data.add("Lexie - Manta");
            data.add("陈奕迅 - 达尔文(live)");
            data.add("関ジャニ∞ (关8) - 無限大");
            data.add("10cc - Dreadlock Holiday");
            data.add("渋谷すばる - 愚か者");    
            data.add("Midnight - Harder");         
//        }
 
        //初始化ListView的内容
        setListViewData(data);
    }


//     * 设置ListView的内容
    private void setListViewData(List<String> list){
        //设置ListView的适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
    
   //切换歌曲页面MyOnClickListener内部类
    private class MyOnClickListener implements OnItemClickListener{

		public void onItemClick(AdapterView<?> parent, View view, int positon, long id) {
			// TODO Auto-generated method stub
			Intent gdIntent = new Intent(Find.this,Songs.class);//跳转到歌曲详情页面
			startActivity(gdIntent);
		}		
	}

}
