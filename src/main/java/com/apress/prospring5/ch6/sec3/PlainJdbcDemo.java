package com.apress.prospring5.ch6.sec3;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 예제 6-10. PlainSingerDao 클래스를 테스트하기 위한 예제.
 *
 * 이 예제에서 알 수 있듯이 많은 양의 코드를 공통 유틸리티 클래스로 옮겨야 한다.
 * 그러지 않으면 DAO 클래스 간에 많은 중복 코드가 사용된다.
 * 이는 애플리케이션 개발자가 순수하게 JDBC 만을 사용할 때 겪는 단점이다.
 * 이런 이유로 JDBC를 사용하는 데이터 입출력에 DAO 프레임워크와 스프링을 사용한다.
 * 프레임워크가 각 코드에서 동일한 로직을 수행하는 중복 코드를 제거하고 수행해야 할 일반적인 작업을 대신한다.
 * 또한 스프링이 제공하는 광범위한 JDBC 기능을 사용하면 데이터베이스를 사용하는 애플리케이션 개발을 훨씬 수월하게 할 수 있다.
 */
public class PlainJdbcDemo {
    private static SingerDao singerDao = new PlainSingerDao();
    private static Logger logger = LoggerFactory.getLogger(PlainJdbcDemo.class);

    private static void listAllSingers(){
        List<Singer> singers = singerDao.findAll();

        for(Singer singer : singers){
            logger.info(singer.toString());
        }
    }

    public static void main(String[] args) {
        logger.info("==========Listing initial singer data:");

        listAllSingers();

        logger.info("==========Listing singer data after new singer created: -----------");
        logger.info("Register a new singer");

        Singer singer = new Singer();
        singer.setFirstName("==========Listing singer data after new singer created: Ed");
        singer.setLastName("==========Listing singer data after new singer created: Sheeran");
        singer.setBirthDate(new Date((new GregorianCalendar(1991,2,1991)).getTime().getTime()));
        singerDao.insert(singer);

        logger.info("==========Listing singer data after new singer created: ");
        listAllSingers();

        logger.info("==========Listing singer data after new singer created: -----------");
        logger.info("==========Listing singer data after new singer created: Deleting the previous created singer");

        singerDao.delete(singer.getId());

        logger.info("==========Listing singer data after new singer deleted: ");
        listAllSingers();

    }

}
