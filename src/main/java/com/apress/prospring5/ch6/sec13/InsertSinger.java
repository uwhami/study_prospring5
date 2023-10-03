package com.apress.prospring5.ch6.sec13;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * 6.13 데이터 등록 및 생성된 키 조회하기.
 */
public class InsertSinger extends SqlUpdate {

    private static final String SQL_INSERT_SINGER =
            "insert into singer (first_name, last_name, birth_date) values (:first_name, :last_name, :birth_date)";

    public InsertSinger(DataSource dataSource){
        super(dataSource, SQL_INSERT_SINGER);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birth_date", Types.DATE));
        super.setGeneratedKeysColumnNames(new String[]{"id"});  //ID 컬럼의 이름을 선언.
        super.setReturnGeneratedKeys(true); //사용중인 JDBC 드라이버를 통해 데이터베이스에서 생성된 ID 값을 조회.
    }

}
