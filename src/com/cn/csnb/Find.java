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
	private ListView listView;//�б�
	private SearchView searchView;//������
	private List<String> data = new ArrayList<String>();  //ListView����Դ
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
		 //������������
        searchView.setSearchWay(new SearchView.SearchWay<String>(){
 
            @Override
            public List<String> getData() {
                //��������Դ
                return data;
            }
 
            @Override
            public boolean matchItem(String item, String s) {
                //�����item�а�����s����ƥ��
                return item.contains(s);
            }
 
            @Override
            public void update(List<String> resultList) {
                //����ListView������
                setListViewData(resultList);
            }
        });
 
        initListView();
        
        //�������ĸ�������¼�
        listView.setOnItemClickListener(new MyOnClickListener());
	}

//     * ��ʼ��ListView

    private void initListView(){
        //��ʼ���ַ�����
//        for (int i = 0; i < 20; i++){
            data.add("Ruth B. - Slow Fade");
            data.add("Lexie - Manta");
            data.add("����Ѹ - �����(live)");
            data.add("�v����ˡ� (��8) - �o�޴�");
            data.add("10cc - Dreadlock Holiday");
            data.add("�i�Ȥ��Ф� - �ޤ���");    
            data.add("Midnight - Harder");         
//        }
 
        //��ʼ��ListView������
        setListViewData(data);
    }


//     * ����ListView������
    private void setListViewData(List<String> list){
        //����ListView��������
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
    
   //�л�����ҳ��MyOnClickListener�ڲ���
    private class MyOnClickListener implements OnItemClickListener{

		public void onItemClick(AdapterView<?> parent, View view, int positon, long id) {
			// TODO Auto-generated method stub
			Intent gdIntent = new Intent(Find.this,Songs.class);//��ת����������ҳ��
			startActivity(gdIntent);
		}		
	}

}
