package com.qeebu.teamin.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.qeebu.teamin.domin.Cases;

public class CaseService {
	public static List<Cases> getChannels(InputStream is) {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(is, "utf-8");
			List<Cases> channels = new ArrayList<Cases>();
			int type = parser.getEventType();
			Cases channel = null;
			// Ω‚ŒˆXML
			while (type != XmlPullParser.END_DOCUMENT) {
				switch (type) {
				case XmlPullParser.START_TAG:
					if ("channel".equals(parser.getName())) {
						channel = new Cases();
						String id = parser.getAttributeValue(0);
						channel.setId(id);
					} else if ("name".equals(parser.getName())) {
						String name = parser.nextText();
						channel.setName(name);
					} else if ("time".equals(parser.getName())) {
						String time = parser.nextText();
						channel.setTime(time);
					} else if ("content".equals(parser.getName())) {
						String content = parser.nextText();
						channel.setContent(content);
					} else if ("icon".equals(parser.getName())) {
						String icon = parser.nextText();
						channel.setIcon(icon);
					}
					break;

				case XmlPullParser.END_TAG:
					if ("channel".equals(parser.getName())) {
						channels.add(channel);
						channel = null;
					}
					break;
				}
				type = parser.next();
			}
			return channels;
		} catch (Exception e) {
			return null;
		}
	}
}
