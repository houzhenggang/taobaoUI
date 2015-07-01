package com.qeebu.teamin.test;

import java.io.InputStream;
import java.util.List;

import com.qeebu.teamin.domin.Cases;
import com.qeebu.teamin.global.Constants;
import com.qeebu.teamin.service.CaseService;
import com.qeebu.teamin.service.NetUtil;

import android.test.AndroidTestCase;

public class TestNet extends AndroidTestCase {

	public void testGetChannels () throws Exception{
		InputStream is = NetUtil.getChannelStream(Constants.CASES_URL);
		
		List<Cases> channels = CaseService.getChannels(is);
		if(channels!=null){
			System.out.println(channels.size());
		}
		for(Cases cases : channels){
			System.out.println("###");
			System.out.println(cases.getContent());
		}
	}
}
