package com.cn.csnb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class PsyDuckMusicActivity extends Activity {
	public static final String SP_INFO = "zscd_data";
	public static final String USERID = "UserId";
	public static final String USERPASS = "UserPass";
	private EditText etUid;
	private EditText etUpwd;
	private String uidStr;
	private String upwdStr;
	private CheckBox cb;
	
	String sq;
	Connection conn;
	Statement st;
	ResultSet rs;
	final String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	final String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ZSCD";
	final String userName="sa";
	final String userPwd="123";
	String sqlStr;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.psy_duck_music);

        etUid = (EditText)this.findViewById(R.id.etUid);
        etUpwd = (EditText)this.findViewById(R.id.etUpwd);
        
        cb = (CheckBox)this.findViewById(R.id.cbRemember);
        
        checkIfRemember();
        
        Button btnLogin = (Button) this.findViewById(R.id.button1);
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userName = etUid.getText().toString().trim();
				String userPass = etUpwd.getText().toString().trim();
				
//				SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.cn.csnb/zscd.db", null);
//				Cursor qc = db.query("user", null, "uname=? and upwd=?", new String[] { userName, userPass }, null,
//						null, null);
//				if (qc.getCount() > 0) {
//					Intent it = new Intent(ZscdcsActivity.this, FrameTabActivity.class);
//					startActivity(it);
////					qc.close();
////					db.close();
//				}
				Intent it = new Intent(PsyDuckMusicActivity.this, FrameTabActivity.class);
				startActivity(it);
			}
		});
		Button btReg = (Button) this.findViewById(R.id.button2);
		btReg.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(PsyDuckMusicActivity.this, RegisterActivity.class);
				startActivity(it);
			}
		});

//        Button btLogin = (Button)this.findViewById(R.id.button1);
//        btLogin.setOnClickListener(new View.OnClickListener() {
//        	public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent it = new Intent(ZscdcsActivity.this,FrameTabActivity.class);
//				startActivity(it);
//			}
//        });
//        
//        Button bt = (Button)this.findViewById(R.id.button2);
//        bt.setOnClickListener(new View.OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent it = new Intent(ZscdcsActivity.this,RegisterActivity.class);
//				startActivity(it);
//			}
//		}); 
        Button btCreate = (Button)this.findViewById(R.id.button3);
        btCreate.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.cn.csnb/zscd.db", null);
				String sql = "create table user(uid integer primary key autoincrement,uname text,upwd text)";
				db.execSQL(sql);
			}
		});
        
        btCreate.setVisibility(View.GONE);
        
    }
    
    @Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(cb.isChecked())
		{
			uidStr = etUid.getText().toString().trim();
			upwdStr = etUpwd.getText().toString().trim();
			rememberMe(uidStr,upwdStr);
		}
	}

	//从SharedPreferences中读取用户的账号和密码
    public void checkIfRemember()
    {
    	SharedPreferences sp = getSharedPreferences(SP_INFO,MODE_PRIVATE);
    	uidStr = sp.getString(USERID, null);
    	upwdStr = sp.getString(USERPASS, null);
    	if(uidStr!=null && upwdStr!=null)
    	{
    		etUid.setText(uidStr);
    		etUpwd.setText(upwdStr);
    		cb.setChecked(true);
    	}
    }
    
    //将用户的id和密码存入SharedPreferences
    public void rememberMe(String uid,String upwd)
    {
    	SharedPreferences sp = getSharedPreferences(SP_INFO,MODE_PRIVATE);
    	SharedPreferences.Editor editor = sp.edit();
    	editor.putString(USERID,uid);
    	editor.putString(USERPASS, upwd);
    	editor.commit();
    }
}