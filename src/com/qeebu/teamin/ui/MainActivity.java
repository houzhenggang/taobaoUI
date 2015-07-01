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
 * �ײ������
 * 
 * @author lsj
 *
 */

public class MainActivity extends FragmentActivity implements OnClickListener {

	/**
	 * ������
	 */
	private HomeActivity home;
	/**
	 * ����
	 */
	private CasesActivity cases;
	/**
	 * ��Ŀ����
	 */
	private ProjectActivity project;
	/**
	 * ������Ϣ
	 */
	private UserActivity user;

	// �ײ���ť��Դ
	private ImageView[] bt_menu = new ImageView[4];
	// �ײ���ťID
	private int[] bt_menu_id = { R.id.iv_menu_0, R.id.iv_menu_1,
			R.id.iv_menu_2, R.id.iv_menu_3 };
	// �ײ�ѡ�а�ť��Դ
	private int[] select_on = { R.drawable.guide_home_on,
			R.drawable.guide_tfaccount_on, R.drawable.guide_discover_on,
			R.drawable.guide_account_on };
	// �ײ�δѡ�а�ť��Դ
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
		// �ҵ��ײ��˵��İ�ť�����ü���
		for (int i = 0; i < bt_menu.length; i++) {
			bt_menu[i] = (ImageView) findViewById(bt_menu_id[i]);
			bt_menu[i].setOnClickListener(this);
		}

		// ��ʼ��Ĭ����ʾ�Ľ���
		if (home == null) {
			home = new HomeActivity();
			addFragment(home);
			showFragment(home);
		} else {
			showFragment(home);
		}
		// ����Ĭ����ҳΪ���ʱ��ͼƬ
		bt_menu[0].setImageResource(select_on[0]);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_menu_0:
			// ������
			if (home == null) {
				home = new HomeActivity();
				// �жϵ�ǰ�����Ƿ����أ�������ؾͽ��������ʾ��false��ʾ��ʾ��true��ʾ��ǰ��������
				addFragment(home);
				showFragment(home);
			} else {
				if (home.isHidden()) {
					showFragment(home);
				}
			}
			break;
		case R.id.iv_menu_1:
			// ��������
			if (cases == null) {
				cases = new CasesActivity();
				// �жϵ�ǰ�����Ƿ����أ�������ؾͽ��������ʾ��false��ʾ��ʾ��true��ʾ��ǰ��������
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
			//��Ŀ�������
			if (project != null) {
				removeFragment(project);
				project = null;
			}
			project = new ProjectActivity();
			// �жϵ�ǰ�����Ƿ����أ�������ؾͽ��������ʾ��false��ʾ��ʾ��true��ʾ��ǰ��������
			addFragment(project);
			showFragment(project);
			break;
		case R.id.iv_menu_3:
			// ������Ϣ����
			if (user == null) {
				user = new UserActivity();
				// �жϵ�ǰ�����Ƿ����أ�������ؾͽ��������ʾ��false��ʾ��ʾ��true��ʾ��ǰ��������
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
		// ���ð�ť��ѡ�к�δѡ����Դ
		for (int i = 0; i < bt_menu.length; i++) {
			bt_menu[i].setImageResource(select_off[i]);
			if (v.getId() == bt_menu_id[i]) {
				bt_menu[i].setImageResource(select_on[i]);
			}
		}
	}

	// ���Fragment
	public void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.show_layout, fragment);
		ft.commit();
	}

	// ɾ��Fragment
	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}

	/** ��ʾFragment **/
	public void showFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		// ����Fragment���л�����
		ft.setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);

		// �ж�ҳ���Ƿ��Ѿ�����������Ѿ���������ô�����ص�
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

	/** Fragment�Ļص����� */
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
	 * ��Ӧ��Fragment�д���������Ϣ
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
	 * ˫���˳�
	 */
	private static Boolean isQuit = false;
	Timer timer = new Timer();

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (isQuit == false) {
				isQuit = true;
				Toast.makeText(getApplicationContext(), "������������˳�", Toast.LENGTH_SHORT)
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
