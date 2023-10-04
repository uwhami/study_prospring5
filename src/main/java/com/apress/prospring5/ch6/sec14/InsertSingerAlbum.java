package com.apress.prospring5.ch6.sec14;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * 6.14 BatchSqlUpdate를 사용하는 배치 조작.
 *
 * SqlUpdate와 BatchSqlUpdate의 주요 차이점은 BatchSqlUpdate는 수행할 등록 조작을 어느정도 모아뒀다가
 * 데이터베이스에 보내 일괄적으로 실행한다는 점.
 * 모인 레코드 수가 배치 크기에 도달하면 스프링은 대기 중인 레코드를 대량으로 데이터베이스에 등록.
 *
 * 배치 크기에 도달하지 않았지만 대기 중인 등록 조작을 일괄 처리하도록 BatchSqlUpdate.flush() 메서드를 호출할 수 있다.
 */
public class InsertSingerAlbum extends BatchSqlUpdate {

    private static final String SQL_INSERT_SINGER_ALBUM =
            "insert into album (singer_id, title, release_date) values (:singer_id, :title, :release_date)";

    private static final int BATCH_SIZE = 10;

    public InsertSingerAlbum(DataSource dataSource){
        super(dataSource, SQL_INSERT_SINGER_ALBUM);

        declareParameter(new SqlParameter("singer_id", Types.INTEGER));
        declareParameter(new SqlParameter("title", Types.VARCHAR));
        declareParameter(new SqlParameter("release_date", Types.DATE));

        setBatchSize(BATCH_SIZE);
    }

}
