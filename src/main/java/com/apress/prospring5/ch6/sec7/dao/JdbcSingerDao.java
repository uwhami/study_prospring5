package com.apress.prospring5.ch6.sec7.dao;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 예제 6-24. 간단한 SingerDao 구현체.
 *
 * 인터페이스에 dataSource를 공급받는 메서드를 정의하지 않고 구현 클래스에 dataSource 프로퍼티를 추가한 이유는 명확하다.
 * 어떻게 데이터를 조회하고 수정하는지를 인터페이스 레벨에서 알 필요가 없기 때문이다..
 * 인터페이스에 접근자(getter)와 수정자(setter)를 추가하면 모든 구현체에서 선언해야 한다.
 */
public class JdbcSingerDao implements SingerDao, InitializingBean {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String findNameById(Long id) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(dataSource == null){
            throw new BeanCreationException("==========DataSource must be configured on SingerDAO");
        }
    }


}
