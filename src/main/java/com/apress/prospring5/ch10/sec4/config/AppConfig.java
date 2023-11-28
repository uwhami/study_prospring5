package com.apress.prospring5.ch10.sec4.config;

import com.apress.prospring5.ch10.sec3.Singer;
import com.apress.prospring5.ch10.sec4.ApplicationConversionServiceFactoryBean;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.Locale;

@Configuration
@ComponentScan(basePackages = {"com.apress.prospring5.ch10.sec4","com.apress.prospring5.ch10.sec3"})
public class AppConfig {

    @Autowired
    ApplicationConversionServiceFactoryBean conversionService;

    @Bean
    public Singer john() throws Exception{
        Singer singer = new Singer();
        singer.setFirstName("firstName");
        singer.setLastName("lastName");
        singer.setBirthDate(conversionService.getDateTimeFormatter().parse("1922-10-16", Locale.ENGLISH));
        singer.setPersonalSite(new URL("http://johnmayer.com/"));
        return singer;
    }
}
