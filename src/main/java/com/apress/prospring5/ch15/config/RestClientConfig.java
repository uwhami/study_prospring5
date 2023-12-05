package com.apress.prospring5.ch15.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableJpaRepositories(basePackages = {"com.apress.prospring5.ch15.repos"})
@ComponentScan(basePackages  = {"com.apress.prospring5.ch15"} )
public class RestClientConfig {

    @Autowired
    ApplicationContext ctx;

    @Bean
    public HttpComponentsClientHttpRequestFactory httpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        httpRequestFactory.setHttpClient(httpClient);
        return httpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
        List<HttpMessageConverter<?>> mcvs = new ArrayList<>();
        mcvs.add(singerMessageConverter());
        restTemplate.setMessageConverters(mcvs);
        return restTemplate;
    }

    @Bean
    MarshallingHttpMessageConverter singerMessageConverter() {
        MarshallingHttpMessageConverter mc = new MarshallingHttpMessageConverter();
        mc.setMarshaller(marshaller());
        mc.setUnmarshaller(unmarshaller());
        List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mt = new MediaType("application", "xml");
        mediaTypes.add(mt);
        mc.setSupportedMediaTypes(mediaTypes);
        return mc;
    }

    @Bean
    Marshaller marshaller() {
        Marshaller marshaller = new XStreamMarshaller();
//        castorMarshaller.setMappingLocation(ctx.getResource("classpath:spring/ch15/oxm-mapping.xml"));
        return marshaller;
    }

    @Bean
    Unmarshaller unmarshaller(){
        Unmarshaller unmarshaller = new XStreamMarshaller();
        return unmarshaller;
    }
}
