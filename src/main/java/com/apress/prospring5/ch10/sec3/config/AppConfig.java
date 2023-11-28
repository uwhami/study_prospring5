package com.apress.prospring5.ch10.sec3.config;

import com.apress.prospring5.ch10.sec3.Singer;
import com.apress.prospring5.ch10.sec3.StringToDateTimeConverter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan(basePackages = "com.apress.prospring5.ch10.sec3")
public class AppConfig {

    @Value("${date.format.pattern}")
    private String dateFormatPattern;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Singer john(@Value("${countrySinger.firstName}") String firstName,
                       @Value("${countrySinger.lastName}") String lastName,
                       @Value("${countrySinger.birthDate}") DateTime birthDate,
                       @Value("${countrySinger.personalSite}") URL personalSite){
        Singer singer = new Singer();
        singer.setFirstName(firstName);
        singer.setLastName(lastName);
        singer.setBirthDate(birthDate);
        singer.setPersonalSite(personalSite);
        return singer;
    }

    /* 해당 빈 이름은 반드시 conversionService 여야 함. */
    @Bean
    public ConversionServiceFactoryBean conversionService(){
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter<String,DateTime>> convs = new HashSet<>();
        convs.add(converter());
        conversionServiceFactoryBean.setConverters(convs);
        conversionServiceFactoryBean.afterPropertiesSet();
        return conversionServiceFactoryBean;
    }

    @Bean
    StringToDateTimeConverter converter(){
        StringToDateTimeConverter conv = new StringToDateTimeConverter();
//        conv.setDatePattern(dateFormatPattern);
        conv.init();
        return conv;
    }

}
