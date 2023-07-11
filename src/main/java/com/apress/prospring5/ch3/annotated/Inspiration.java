package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


//3-41. String 타입 멤버 변수를 가진 빈 클래스(field-injection)
@Component
public class Inspiration {
	
	public String lyric = "I can keep the door cracked open, to let light through";
	
	public Inspiration(@Value("For all my running, I can understand")String lyric) {
		this.lyric = lyric;
	}
	
	public String getLyric() {
		return lyric;
	}
	
	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

}
