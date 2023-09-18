package com.apress.prospring5.ch6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 책에는 없는데 git에서 받은 내용에는 있음.
 */
public class CleanUp {
    private static Logger logger = LoggerFactory.getLogger(CleanUp.class);

    private JdbcTemplate jdbcTemplate;

    public CleanUp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void destroy() {
        logger.info(" =====... Deleting database files.");
        jdbcTemplate.execute("DROP ALL OBJECTS DELETE FILES;");
    }
}
