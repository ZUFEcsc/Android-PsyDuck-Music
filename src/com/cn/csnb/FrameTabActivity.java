package com.cn.csnb;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

public class FrameTabActivity extends TabActivity {
	public void onCreate(Bundle savedIntanceState) {
		super.onCreate(savedIntanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		final TabHost tabHost =this.getTabHost();
		Intent itNormal=new Intent(this,NormalActivity.class);
		Intent itContact=new Intent(this,RadioActivity.class);
		Intent itDiary=new Intent(this,Find.class);
		Intent itAlbum=new Intent(this,Prend.class);
		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator(getResources().getText(R.string.tabtitle1)
						,getResources().getDrawable(R.drawable.ic_normal))
				.setContent(itNormal));
		tabHost.addTab(tabHost.newTabSpec("tab2")
				.setIndicator(getResources().getText(R.string.tabtitle2)
						,getResources().getDrawable(R.drawable.ic_radio))
				.setContent(itContact));
		tabHost.addTab(tabHost.newTabSpec("tab3")
				.setIndicator(getResources().getText(R.string.tabtitle3)
						,getResources().getDrawable(R.drawable.ic_find))
				.setContent(itDiary));
		tabHost.addTab(tabHost.newTabSpec("tab4")
				.setIndicator(getResources().getText(R.string.tabtitle4)
						,getResources().getDrawable(R.drawable.ic_prend))
				.setContent(itAlbum));
	}
}
