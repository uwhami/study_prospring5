package com.apress.prospring5.ch6.sec12;

import com.apress.prospring5.ch6.entities.Singer;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 6.12 MappingSqlQuery<T>를 사용해 데이터 질의하기.
 * MappingSqlQuery 클래스는 SqlQuery 클래스를 상속한다.
 */
public class SelectAllSingers extends MappingSqlQuery<Singer> {

    private static final String SQL_SELECT_ALL_SINGER =
            "select id, first_name, last_name, birth_date from singer";

    public SelectAllSingers(DataSource dataSource){
        super(dataSource, SQL_SELECT_ALL_SINGER);
    }


    /**
     * ResultSet 레코드를 해당 도메인 객체로 변환하는 MappingSqlQuery<T>.mapRow() 메서드 구현.
     */
    @Override
    protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException{
        Singer singer = new Singer();
        singer.setId(rs.getLong("id"));
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_Date"));
        return singer;
    }

}
