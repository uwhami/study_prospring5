package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//3-40. 필드 주입을 이용한 빈 클래스(field-injection)
@Service("singer")
public class Singer {

	@Autowired
	private Inspiration inspirationBean;
	
	public void main(String[] args) {
		System.out.println("... " + inspirationBean.getLyric());
	}

}
