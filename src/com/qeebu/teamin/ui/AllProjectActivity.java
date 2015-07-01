package com.qeebu.teamin.ui;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qeebu.teamin.tools.IBtnCallListener;


/**
 * 项目管理界面中的全部项目界面
 * @author lsj
 * 
 */
@SuppressLint("ValidFragment")
public class AllProjectActivity extends Fragment implements IBtnCallListener, OnClickListener {
	IBtnCallListener btnCallListener;
	private TextView tv_goShop;
	private LinearLayout ll_cart;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.project_all_activity, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		ll_cart = (LinearLayout) view.findViewById(R.id.ll_cart);
		tv_goShop = (TextView) view.findViewById(R.id.tv_goShop);
		tv_goShop.setOnClickListener(this);
	}

//	@Override
//	public void onAttach(Activity activity) {
//		btnCallListener = (IBtnCallListener) activity;
//		super.onAttach(activity);
//	}

	@Override
	public void transferMsg() {
		// 这里响应在FragmentActivity中的控件交互
		System.out.println("由Activity中传送来的消息");
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_goShop://点击去逛逛
			btnCallListener.transferMsg();
			break;
		
		default:
			break;
		}
		
	}

}
