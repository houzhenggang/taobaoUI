package com.qeebu.teamin.ui;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.qeebu.teamin.tools.IBtnCallListener;

/**
 * 底层主框架
 * 
 * @author lsj
 *
 */

public class MainActivity extends FragmentActivity implements OnClickListener {

	/**
	 * 主界面
	 */
	private HomeActivity home;
	/**
	 * 案例
	 */
	private CasesActivity cases;
	/**
	 * 项目管理
	 */
	private ProjectActivity project;
	/**
	 * 个人信息
	 */
	private UserActivity user;

	// 底部按钮资源
	private ImageView[] bt_menu = new ImageView[4];
	// 底部按钮ID
	private int[] bt_menu_id = { R.id.iv_menu_0, R.id.iv_menu_1,
			R.id.iv_menu_2, R.id.iv_menu_3 };
	// 底部选中按钮资源
	private int[] select_on = { R.drawable.guide_home_on,
			R.drawable.guide_tfaccount_on, R.drawable.guide_discover_on,
			R.drawable.guide_account_on };
	// 底部未选中按钮资源
	private int[] select_off = { R.drawable.bt_menu_0_select,
			R.drawable.bt_menu_1_select, R.drawable.bt_menu_2_select,
			R.drawable.bt_menu_3_select };

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main_qeebu_activity);
		initView();
	}

	private void initView() {
		// 找到底部菜单的按钮并设置监听
		for (int i = 0; i < bt_menu.length; i++) {
			bt_menu[i] = (ImageView) findViewById(bt_menu_id[i]);
			bt_menu[i].setOnClickListener(this);
		}

		// 初始化默认显示的界面
		if (home == null) {
			home = new HomeActivity();
			addFragment(home);
			showFragment(home);
		} else {
			showFragment(home);
		}
		// 设置默认首页为点击时的图片
		bt_menu[0].setImageResource(select_on[0]);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_menu_0:
			// 主界面
			if (home == null) {
				home = new HomeActivity();
				// 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
				addFragment(home);
				showFragment(home);
			} else {
				if (home.isHidden()) {
					showFragment(home);
				}
			}
			break;
		case R.id.iv_menu_1:
			// 案例界面
			if (cases == null) {
				cases = new CasesActivity();
				// 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
				if (!cases.isHidden()) {
					addFragment(cases);
					showFragment(cases);
				}
			} else {
				if (cases.isHidden()) {
					showFragment(cases);
				}
			}
			break;
		case R.id.iv_menu_2:
			//项目管理界面
			if (project != null) {
				removeFragment(project);
				project = null;
			}
			project = new ProjectActivity();
			// 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
			addFragment(project);
			showFragment(project);
			break;
		case R.id.iv_menu_3:
			// 个人信息界面
			if (user == null) {
				user = new UserActivity();
				// 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
				if (!user.isHidden()) {
					addFragment(user);
					showFragment(user);
				}
			} else {
				if (user.isHidden()) {
					showFragment(user);
				}
			}

			break;
		
		default:
			break;
		}
		// 设置按钮的选中和未选中资源
		for (int i = 0; i < bt_menu.length; i++) {
			bt_menu[i].setImageResource(select_off[i]);
			if (v.getId() == bt_menu_id[i]) {
				bt_menu[i].setImageResource(select_on[i]);
			}
		}
	}

	// 添加Fragment
	public void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.show_layout, fragment);
		ft.commit();
	}

	// 删除Fragment
	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}

	/** 显示Fragment **/
	public void showFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		// 设置Fragment的切换动画
		ft.setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);

		// 判断页面是否已经创建，如果已经创建，那么就隐藏掉
		if (home != null) {
			ft.hide(home);
		}
		if (cases != null) {
			ft.hide(cases);
		}
		if (project != null) {
			ft.hide(project);
		}
		if (user != null) {
			ft.hide(user);
		}

		ft.show(fragment);
		ft.commitAllowingStateLoss();

	}

	/** Fragment的回调函数 */
	@SuppressWarnings("unused")
	private IBtnCallListener btnCallListener;

	@Override
	public void onAttachFragment(Fragment fragment) {
		try {
			btnCallListener = (IBtnCallListener) fragment;
		} catch (Exception e) {
		}

		super.onAttachFragment(fragment);
	}

	/**
	 * 响应从Fragment中传过来的消息
	 */
	public void transferMsg() {
		if (home == null) {
			home = new HomeActivity();
			addFragment(home);
			showFragment(home);
		} else {
			showFragment(home);
		}
		bt_menu[3].setImageResource(select_off[3]);
		bt_menu[0].setImageResource(select_on[0]);

	}
	
	/**
	 * 双击退出
	 */
	private static Boolean isQuit = false;
	Timer timer = new Timer();

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (isQuit == false) {
				isQuit = true;
				Toast.makeText(getApplicationContext(), "连续点击两次退出", Toast.LENGTH_SHORT)
						.show();
				TimerTask task = null;
				task = new TimerTask() {
					@Override
					public void run() {
						isQuit = false;
					}
				};
				timer.schedule(task, 2000);
			} else {
				System.exit(0);
			}
		}
		return false;
	}
	
	

}
