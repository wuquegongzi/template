package com.leon.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/*
 * @Author minglei.chen
 * @Description 集成RestTemplate
 * @Date 17:13 2020-05-27
 **/
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(
            @Qualifier("simpleClientHttpRequestFactory")
                    ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean("simpleClientHttpRequestFactory")
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);

        return factory;
    }
}
