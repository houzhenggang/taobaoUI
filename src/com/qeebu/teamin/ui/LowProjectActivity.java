package com.qeebu.teamin.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ��Ŀ��������е������Ŀ����
 * @author lsj
 *
 */
public class LowProjectActivity extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=LayoutInflater.from(getActivity()).inflate(R.layout.project_low_activity, null);
		initView(view);
		return view;
	}
	
	private void initView(View view){
		
	}

}
