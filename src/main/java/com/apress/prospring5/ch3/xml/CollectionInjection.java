package com.apress.prospring5.ch3.xml;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * 3-59~3-62. 빈으로 이루어진 컬렉션이나 다른 컬렉셔능로 이뤄진 컬렉션도 필요한 곳에 주입할 수 있음.
 * 애플리케이션을 모듈화하기 쉽고, 로직의 주요 부분을 사용자가 선택한 구현체로 제공할 수 있다.
 */
public class CollectionInjection {
	
	private Map<String, Object> map;
	private Properties props;
	private Set set;
	private List list;

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/ch3/app-context-xml.xml");
		ctx.refresh();
	
		CollectionInjection instance = (CollectionInjection) ctx.getBean("injectCollection");
		instance.displayInfo();
		
		ctx.close();
		
	}
	
	public void displayInfo() {
		
		System.out.println("Map 내용:\n");
		map.entrySet().stream().forEach(e -> System.out.println("KEY : " + e.getKey() + " - VALUE : " + e.getValue()));
		
		System.out.println("\nProperty 내용:\n");
		props.entrySet().stream().forEach(e -> System.out.println("KEY : " + e.getKey() + " - VALUE : " + e.getValue()));
		
		System.out.println("\nSet 내용:\n");
		set.forEach(obj -> System.out.println("값 : " + obj));

		System.out.println("\nList 내용:\n");
		list.forEach(obj -> System.out.println("값 : " + obj));	
	}
	
	public void setList(List list) {
		this.list = list;
	}
	
	public void setSet(Set set) {
		this.set = set;
	}
	
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public void setProps(Properties props) {
		this.props = props;
	}
	
	

}
