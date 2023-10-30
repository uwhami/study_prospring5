package com.apress.prospring5.ch3.annotated;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


import jakarta.annotation.Resource;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

/*
 * 3-67. name property를 지정한 JSR-250 애너테이션인 @Resource 애너테이션을 적용.
 * **@Autowired 대신 @Resource 를 사용한 이유
 * : @Autowired 애너테이션이 배열, 컬렉션, 맵을 해당 컬렉션의 값 타입에서 파생된 대상 빈 타입을 가져와 처리하기 때문이다.
 *   예를들어, Autowired가 적용되어 있었다면, <util:list> 대신에 해당하는 타입에 맞는 모든 빈을 애트리뷰트에 주입하려고 시도할 것(p.145 참고)
 *   그 결과 의도하지 않은 의존성이 주입되거나 에러를 던질 수 있다.
 *   그래서컬렉션 타입을 주입할 때, 빈 이름을 지정할 수 있도록 지원하는 @Resource 애너테이션을 사용하여 명시적으로 의존성을 알맞게 주입하도록 해야한다.
 *   
 *   @Autowired 와 @Qualifier 애너테이션을 조합해 사용해도 동일한 목적을 달성할 수는 있지만,
 *   하나의 애너테이션만 사용하는것이 바람직하다.
 */
@Service("injectCollection")
public class CollectionInjection {
	
	@Resource(name="map")
	private Map<String, Object> map;
	
	@Resource(name="props")
	private Properties props;
	
	@Resource(name="set")
	private Set set;
	
	@Resource(name="list")
	private List list;

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/ch3/app-context-annotation.xml");
		ctx.refresh();
		
		CollectionInjection instance = (CollectionInjection)ctx.getBean("injectCollection");
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

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	
	
}
