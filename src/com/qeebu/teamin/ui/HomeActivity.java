package com.qeebu.teamin.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;
import com.qeebu.teamin.adapter.GridViewAdapter;
import com.qeebu.teamin.adapter.GridView_hot_Adapter;
import com.qeebu.teamin.custom.ui.AbOnItemClickListener;
import com.qeebu.teamin.custom.ui.SlidingPlayView;
import com.qeebu.teamin.global.Constants;
import com.zxing.activity.CaptureActivity;

/**
 * ��ҳ
 * 
 * @author lsj
 *
 */

public class HomeActivity extends Fragment {
	// ɨһɨ
	private ImageView iv_shao;
	
	// ��ҳ�ֲ�
	private SlidingPlayView viewPager;
	// �洢��ҳ�ֲ��Ľ���
	private ArrayList<View> allListView;
	// ��ҳ�ֲ��Ľ������Դ
	private int[] resId = { R.drawable.menu_viewpager_1,
			R.drawable.menu_viewpager_2, R.drawable.menu_viewpager_3,
			R.drawable.menu_viewpager_4, R.drawable.menu_viewpager_5,
			R.drawable.menu_viewpager_6 };

	// ����ľŹ���
	private GridView gridView_classify;
	private GridViewAdapter adapter_GridView_classify;
	// ����Ź������Դ�ļ�
	private static String[] text_classify = { "ͨѶ¼", "�������", "������Ѷ", "�ʾ����",
			"�������", "ѧϰ�ٱ���", "��־" };
	private int[] pic_path_classify = { R.drawable.ic_addressbook_logo,
			R.drawable.ic_meeting_launcher, R.drawable.ic_news_launcher,
			R.drawable.ic_questionnairesurvey_launcher,
			R.drawable.ic_self_task_launcher, R.drawable.ic_study_box_launcher,
			R.drawable.ic_task_track_launcher };

	// �����г��ľŹ���
	private GridView my_gridView_hot;
	private GridView_hot_Adapter adapter_GridView_hot;
	// �ֻ�����������Դ�ļ�
	private int[] pic_path_hot = { R.drawable.menu_1_1, R.drawable.menu_1_2,
			R.drawable.menu_1_3, R.drawable.menu_1_4};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.home_activity, null);
		
		initView(view);
		return view;
	}

	private void initView(View view) {
		iv_shao = (ImageView) view.findViewById(R.id.iv_shao);
		iv_shao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// ��ת����ά��ɨ��
				Intent intent = new Intent(getActivity(), CaptureActivity.class);
				startActivity(intent);
			}
		});
		viewPager = (SlidingPlayView) view.findViewById(R.id.viewPager_menu);
		// ���ò��ŷ�ʽΪ˳�򲥷�
		viewPager.setPlayType(1);
		// ���ò��ż��ʱ��
		viewPager.setSleepTime(3000);
		initViewPager();
		setGirdView(view);
	}

	private void setGirdView(View view) {
		gridView_classify = (GridView) view.findViewById(R.id.my_gridview);
		adapter_GridView_classify = new GridViewAdapter(getActivity(),
				pic_path_classify, text_classify);
		gridView_classify.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridView_classify.setAdapter(adapter_GridView_classify);
		gridView_classify.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getActivity(), "������Ŀ", 0).show();
			}
		});
		
		my_gridView_hot = (GridView) view.findViewById(R.id.my_gridview_hot);
		adapter_GridView_hot = new GridView_hot_Adapter(getActivity(),
				pic_path_hot);
		my_gridView_hot.setSelector(new ColorDrawable(Color.TRANSPARENT));
		my_gridView_hot.setAdapter(adapter_GridView_hot);
		my_gridView_hot.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getActivity(), "���Ƿ������", 0).show();
			}
		});
	}

	// ��ʼ��ViewPager
	private void initViewPager() {
		if (allListView != null) {
			allListView.clear();
			allListView = null;
		}
		allListView = new ArrayList<View>();

		for (int i = 0; i < resId.length; i++) {
			// ����ViewPager�Ĳ���
			View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.pic_item, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
			imageView.setImageResource(resId[i]);
			allListView.add(view);
		}

		viewPager.addViews(allListView);
		// ��ʼ�ֲ�
		viewPager.startPlay();
		viewPager.setOnItemClickListener(new AbOnItemClickListener() {
			@Override
			public void onClick(int position) {
				// ��ת���������
				Toast.makeText(getActivity(), "���ǹ����", 0).show();
			}
		});
	}

}
