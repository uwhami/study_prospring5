package com.apress.prospring5.ch10.sec3;

import jakarta.annotation.PostConstruct;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

public class StringToDateTimeConverter implements Converter<String, DateTime> {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dateFormat;

    private String datePattern = DEFAULT_DATE_PATTERN;

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    public void init(){ /*JodaTime의 DateTimeFormat 클래스의 인스턴스를 생성.*/
        dateFormat = DateTimeFormat.forPattern(datePattern);
    }

    @Override
    public DateTime convert(String source) {
        return dateFormat.parseDateTime(source);
    }
}
