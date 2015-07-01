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
 * 主页
 * 
 * @author lsj
 *
 */

public class HomeActivity extends Fragment {
	// 扫一扫
	private ImageView iv_shao;
	
	// 首页轮播
	private SlidingPlayView viewPager;
	// 存储首页轮播的界面
	private ArrayList<View> allListView;
	// 首页轮播的界面的资源
	private int[] resId = { R.drawable.menu_viewpager_1,
			R.drawable.menu_viewpager_2, R.drawable.menu_viewpager_3,
			R.drawable.menu_viewpager_4, R.drawable.menu_viewpager_5,
			R.drawable.menu_viewpager_6 };

	// 分类的九宫格
	private GridView gridView_classify;
	private GridViewAdapter adapter_GridView_classify;
	// 分类九宫格的资源文件
	private static String[] text_classify = { "通讯录", "会议管理", "新闻资讯", "问卷调查",
			"任务管理", "学习百宝箱", "日志" };
	private int[] pic_path_classify = { R.drawable.ic_addressbook_logo,
			R.drawable.ic_meeting_launcher, R.drawable.ic_news_launcher,
			R.drawable.ic_questionnairesurvey_launcher,
			R.drawable.ic_self_task_launcher, R.drawable.ic_study_box_launcher,
			R.drawable.ic_task_track_launcher };

	// 热门市场的九宫格
	private GridView my_gridView_hot;
	private GridView_hot_Adapter adapter_GridView_hot;
	// 手机会议服务的资源文件
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
				// 跳转到二维码扫描
				Intent intent = new Intent(getActivity(), CaptureActivity.class);
				startActivity(intent);
			}
		});
		viewPager = (SlidingPlayView) view.findViewById(R.id.viewPager_menu);
		// 设置播放方式为顺序播放
		viewPager.setPlayType(1);
		// 设置播放间隔时间
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
				Toast.makeText(getActivity(), "我是条目", 0).show();
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
				Toast.makeText(getActivity(), "我是服务介绍", 0).show();
			}
		});
	}

	// 初始化ViewPager
	private void initViewPager() {
		if (allListView != null) {
			allListView.clear();
			allListView = null;
		}
		allListView = new ArrayList<View>();

		for (int i = 0; i < resId.length; i++) {
			// 导入ViewPager的布局
			View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.pic_item, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
			imageView.setImageResource(resId[i]);
			allListView.add(view);
		}

		viewPager.addViews(allListView);
		// 开始轮播
		viewPager.startPlay();
		viewPager.setOnItemClickListener(new AbOnItemClickListener() {
			@Override
			public void onClick(int position) {
				// 跳转到详情界面
				Toast.makeText(getActivity(), "我是广告条", 0).show();
			}
		});
	}

}
