package com.cn.csnb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ViewPagerAdapter extends BaseAdapter {
	
//	public int getCount() {
//		return images.size();
//	}
 
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
 
//	public void destroyItem(ViewGroup view, int position, Object object) {
//		// TODO Auto-generated method stub
////			super.destroyItem(container, position, object);
////			view.removeView(view.getChildAt(position));
////			view.removeViewAt(position);
//		view.removeView(images.get(position));
//	}
// 
//	public Object instantiateItem(ViewGroup view, int position) {
//		// TODO Auto-generated method stub
//		view.addView(images.get(position));
//		return images.get(position);
//	}
//
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

}
