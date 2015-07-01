package com.qeebu.teamin.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 个人信息界面
 * @author lsj
 *
 */
public class UserActivity extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
    	View view=LayoutInflater.from(getActivity()).inflate(R.layout.user_activity, null);
		return view;
	}

    
}
