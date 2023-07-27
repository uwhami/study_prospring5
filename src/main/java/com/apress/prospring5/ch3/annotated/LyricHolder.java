package com.apress.prospring5.ch3.annotated;

import org.springframework.stereotype.Service;

import com.apress.prospring5.ch3.ContentHolder;

@Service("lyricHolder")
public class LyricHolder implements ContentHolder{

	private String value = "'You be the DJ, I'll be the driver'";
	
	@Override
	public String toString() {
		return "LyricHOlder: { " + value + " }";
	}
	
}
