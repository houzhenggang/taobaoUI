package com.qeebu.teamin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qeebu.teamin.ui.R;

public class GridViewAdapter extends BaseAdapter {
	private Context context;
	private int[] data;
	private String[] name;

	public GridViewAdapter(Context context, int[] data,String[] name) {

		this.context = context;
		this.data = data;
		this.name = name;
	}

	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View currentView, ViewGroup arg2) {
		HolderView holderView = null;
		if (currentView == null) {
			holderView = new HolderView();
			currentView = LayoutInflater.from(context).inflate(
					R.layout.adapter_grid_home, null);
			holderView.iv_pic = (ImageView) currentView.findViewById(R.id.iv_adapter_grid_pic);
			holderView.iv_text=(TextView) currentView.findViewById(R.id.tv_adapter_gird_text);
			currentView.setTag(holderView);
		} else {
			holderView = (HolderView) currentView.getTag();
		}

		holderView.iv_pic.setImageResource(data[position]);
		holderView.iv_text.setText(name[position]);

		return currentView;
	}

	public class HolderView {

		private ImageView iv_pic;
		private TextView iv_text;

	}

}
