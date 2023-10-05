package com.apress.prospring5.ch6.sec15;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * 6.15 SqlFunction으로 저장 함수 호출하기.
 *
 * SqlFunction<String> 의 String은 반환 타입을 나타낸다.
 */
public class StoredFunctionFirstNameById extends SqlFunction<String> {

    private static final String SQL = "select getfirstnamebyid(?)";

    public StoredFunctionFirstNameById(DataSource dataSource){
        super(dataSource, SQL);
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }

}
