package com.example.chenfengyao.slidemenulistview.wayone;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author baoyz
 * @date 2014-8-23
 * 
 */
public class SwipeMenu {

	private Context mContext;
	private List<SwipeMenuItem> mItems;

	public SwipeMenu(Context context) {
		mContext = context;
		mItems = new ArrayList<SwipeMenuItem>();
	}
	public Context getContext() {
		return mContext;
	}
	public void addMenuItem(SwipeMenuItem item) {
		mItems.add(item);
	}
	public List<SwipeMenuItem> getMenuItems() {
		return mItems;
	}
}
