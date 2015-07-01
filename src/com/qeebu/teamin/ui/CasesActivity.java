package com.qeebu.teamin.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
/**
 * °¸ÀýÒ³Ãæ
 * @author lsj
 *
 */

public class CasesActivity extends Fragment implements OnClickListener{
	private LinearLayout meeting;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
    	View view=LayoutInflater.from(getActivity()).inflate(R.layout.cases_activity, null);
    	initView(view);
		return view;
	}

	private void initView(View view){
		meeting = (LinearLayout) view.findViewById(R.id.ll_meeting);
    	meeting.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_meeting:
			Intent i = new Intent(getActivity(), CasesMeetingActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}
	}

}
