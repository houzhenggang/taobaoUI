package com.qeebu.teamin.ui;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;
import com.qeebu.teamin.custom.view.MyListView;
import com.qeebu.teamin.custom.view.RefreshableView;
import com.qeebu.teamin.custom.view.RefreshableView.PullToRefreshListener;
import com.qeebu.teamin.domin.Cases;
import com.qeebu.teamin.global.Constants;
import com.qeebu.teamin.service.CaseService;

public class CasesMeetingActivity extends Activity{
	
	protected static final int SUCCESS = 1;
	private ListView listView_cases;
	private List<Cases> channels;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SUCCESS:
				//设置数据适配器更新界面。
				listView_cases.setAdapter(new ListView1Adapter());
				break; 
			default:
				break;
			}
		};
	};
	//下拉刷新
	private RefreshableView refreshableView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cases_activity_meeting);
		refreshableView = (RefreshableView)findViewById(R.id.refreshable_view);
		listView_cases=(ListView)findViewById(R.id.listView_cases);
		fillData();
//		getDataFromNet();
		init();
	}


	private void fillData() {
		new Thread(){
			public void run() {
				try {
					URL url = new URL(Constants.CASES_URL);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);
					int code = conn.getResponseCode();
					if(code == 200){
						InputStream is = conn.getInputStream();
						//已经得到了服务器端的数据
						channels = CaseService.getChannels(is);
						//设置ui界面。
						Message msg = Message.obtain();
						msg.what = SUCCESS;
						handler.sendMessage(msg);
					}else{
						//服务器返回数据错误
					}
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "获取数据失败", 0).show();
				}
				
			};
		}.start();
			
		
	}

	/**
	 * 初始化数据
	 */
	private void init() {
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);
		listView_cases.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Uri uri = Uri.parse("http://www.qeebu.com/Case.html"); 
				Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
				startActivity(intent);
			}
		});
	}
	
	private class ListView1Adapter extends BaseAdapter{

		@Override
		public int getCount() {
			return channels.size();
		}

		@Override
		public Object getItem(int postion) {
			return channels.get(postion);
		}

		@Override
		public long getItemId(int postion) {
			return postion;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			
			View view = null;
			if (convertView == null) {
				view  = View.inflate(CasesMeetingActivity.this,R.layout.cases_listview_item, null);
			}else{
				view = convertView;
			}
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			
			TextView tv_content = (TextView) view.findViewById(R.id.tv_adapter_list_content);
			TextView tv_time = (TextView) view.findViewById(R.id.time);
			SmartImageView iv_pic = (SmartImageView) view.findViewById(R.id.iv_adapter_list_pic);

			tv_name.setText(channels.get(position).getName());
			tv_content.setText(channels.get(position).getContent());
			tv_time.setText(channels.get(position).getTime());
			iv_pic.setImageUrl(channels.get(position).getIcon());

			return view;
		}
	}

}
