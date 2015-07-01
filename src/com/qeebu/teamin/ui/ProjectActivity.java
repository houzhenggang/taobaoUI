package com.qeebu.teamin.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 项目管理主界面
 * @author lsj
 *
 */
public class ProjectActivity extends Fragment implements OnClickListener {
	private TextView project_all, project_low, project_stock;

	private View show_project_all, show_project_low, show_project_stock;

	private AllProjectActivity all;
	private LowProjectActivity low;
	private StockProjectActivity stock;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.project_activity, null);
		initView(view);
		return view;
	}
	
	private void initView(View view) {
		project_all = (TextView) view.findViewById(R.id.bt_project_all) ;
		project_low = (TextView) view.findViewById(R.id.bt_project_low) ;
		project_stock = (TextView) view.findViewById(R.id.bt_project_stock) ;
		show_project_all = view.findViewById(R.id.show_project_all);
		show_project_low = view.findViewById(R.id.show_project_low);
		show_project_stock = view.findViewById(R.id.show_project_stock);
		
		project_all.setOnClickListener(this);
		project_low.setOnClickListener(this);
		project_stock.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_project_all:
			if (all == null) {
				all = new AllProjectActivity();
				addFragment(all);
				showFragment(all);
			} else {
				showFragment(all);
			}
			show_project_all.setBackgroundColor(getResources().getColor(R.color.bg_Black));
			show_project_low.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
			show_project_stock.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
			break;
		case R.id.bt_project_low:
			if (low == null) {
				low = new LowProjectActivity();
				addFragment(low);
				showFragment(low);
			} else {
				showFragment(low);
			}
			show_project_low.setBackgroundColor(getResources().getColor(R.color.bg_Black));
			show_project_all.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
			show_project_stock.setBackgroundColor(getResources().getColor(R.color.bg_Gray));

			break;
		case R.id.bt_project_stock:
			if (stock == null) {
				stock = new StockProjectActivity();
				addFragment(stock);
				showFragment(stock);
			} else {
				showFragment(stock);
			}
			show_project_stock.setBackgroundColor(getResources().getColor(R.color.bg_Black));
			show_project_all.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
			show_project_low.setBackgroundColor(getResources().getColor(R.color.bg_Gray));

			break;

		default:
			break;
		}
	}
	
	/** 添加Fragment **/
	public void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getFragmentManager().beginTransaction();
		ft.add(R.id.show_project_view, fragment);
		ft.commitAllowingStateLoss();
	}
	/** 删除Fragment **/
	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = this.getFragmentManager().beginTransaction();
		ft.remove(fragment);
		ft.commitAllowingStateLoss();
	}

	/** 显示Fragment **/
	public void showFragment(Fragment fragment) {
		FragmentTransaction ft = this.getFragmentManager().beginTransaction();
		if (all!= null) {
			ft.hide(all);
		}
		if (low!= null) {
			ft.hide(low);
		}
		if (stock!= null) {
			ft.hide(stock);
		}

		ft.show(fragment);
		ft.commitAllowingStateLoss();

	}

}
