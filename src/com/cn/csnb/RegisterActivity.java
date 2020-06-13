package com.cn.csnb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	private EditText etUserName;
	private EditText etUserPass;
	
	String sq;
	Connection conn;
	Statement st;
	ResultSet rs;
	final String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	final String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ZSCD";
	final String userName="sa";
	final String userPwd="123";
	String sqlStr; 
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.register);
	        
	        Button btReg = (Button)this.findViewById(R.id.button1);
	        Button btBack = (Button)this.findViewById(R.id.button2);
	        btReg.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
//					SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.cn.csnb/zscd.db", null);
					String userName = etUserName.getText().toString().trim();
					String userPass = etUserPass.getText().toString().trim();
//					String sql = "insert into user(uname,upwd) values('"+userName+"','"+userPass+"')";
//					db.execSQL(sql);
//					db.close();
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						conn=DriverManager.getConnection(dbURL,userName,userPwd);
						sqlStr="insert into users values('"+userName+"','"+userPass+"')";
						st=conn.createStatement();
						st.execute(sqlStr);
						
					}
					catch(Exception e1) {
						System.out.print("数据库连接失败"+e1.getMessage());
					}
					finish();
				}
			});
	        btBack.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
	    }
}

//private EditText etUserName;
//private EditText etUserPass;
//public void onCreate(Bundle savedInstanceState) {
//	super.onCreate(savedInstanceState);
//	setContentView(R.layout.register);
//	this.etUserName = (EditText)this.findViewById(R.id.etUserName);
//	this.etUserPass = (EditText)this.findViewById(R.id.etUserPass);
//	Button btReg = (Button)this.findViewById(R.id.button1);
//	btReg.setOnClickListener(new View.OnClickListener() {
//		
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.zufe/zscd.db", null);
//			String userName = etUserName.getText().toString().trim();
//			String userPass = etUserPass.getText().toString().trim();
//			String sql = "insert into user(uname,upwd) values('"+userName+"','"+userPass+"')";
//			db.execSQL(sql);
//			db.close();
//			finish();
//		}
//	});
//	Button btReg2 = (Button)this.findViewById(R.id.button2);
//    btReg2.setOnClickListener(new View.OnClickListener() {
//		
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			Intent it = new Intent(RegisterActivity.this,ZscdActivity.class);
//			startActivity(it);
//		}
//	});
//}
