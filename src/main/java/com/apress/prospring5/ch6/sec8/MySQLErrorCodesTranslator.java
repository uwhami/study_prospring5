package com.apress.prospring5.ch6.sec8;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

/**
 * 예제 6-27. 새로 만든 SQLExceptionTranslator 구현체.
 */
public class MySQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {

    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
        if(sqlEx.getErrorCode() == -12345){
            return new DeadlockLoserDataAccessException(task, sqlEx);
        }
        return null;
    }

}
