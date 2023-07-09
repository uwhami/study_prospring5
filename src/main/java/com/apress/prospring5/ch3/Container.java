package com.apress.prospring5.ch3;

//3-2.문맥에 따른 의존성 룩업 
public interface Container {
	Object getDependency(String key);
}
