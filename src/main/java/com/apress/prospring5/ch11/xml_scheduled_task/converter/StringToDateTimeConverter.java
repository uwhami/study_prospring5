package com.apress.prospring5.ch11.xml_scheduled_task.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


@Converter
public class StringToDateTimeConverter implements AttributeConverter<DateTime, String> {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dateFormat = DateTimeFormat.forPattern(DEFAULT_DATE_PATTERN);

    @Override
    public String convertToDatabaseColumn(DateTime attribute) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DEFAULT_DATE_PATTERN);
        return fmt.print(attribute);
    }

    @Override
    public DateTime convertToEntityAttribute(String dbData) {
        return dateFormat.parseDateTime(dbData);
    }


}
