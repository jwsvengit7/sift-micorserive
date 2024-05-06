package com.sifts.Sifts.Auth.Module.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.hc.client5.http.HttpRoute;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;

@Component
public class Restemplates {
    @Value("${customer.service.baseUrl}")
    private String customerServiceBaseUrl;
    @Value("${read.timeout}")
    private int readTimeoutSeconds;
    @Value("${max.connection}")
    private int maxConnections;
    @Value("${customer.service.authorization}")
    private String customerServiceAuthorization;
    @Value("${max.connection.perroute}")
    private int maxConnectionPerRoute;
    @Value("${connect.timeout.seconds}")
    private int connectTimeoutSeconds;
    private RestTemplate restTemplate;


    public RestTemplate restTemplate()  {
        String apiHost = customerServiceBaseUrl;

        if (restTemplate == null) {
            HttpHost httpHost = new HttpHost(apiHost);
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(maxConnections);
            connectionManager.setValidateAfterInactivity(TimeValue.ofDays(20));
            connectionManager.setDefaultMaxPerRoute(maxConnectionPerRoute);
            connectionManager.setMaxPerRoute(new HttpRoute(httpHost), maxConnectionPerRoute);


            HttpClient httpclient = HttpClients.custom().setConnectionManager(connectionManager).build();

            HttpComponentsClientHttpRequestFactory httpReqFactory = new HttpComponentsClientHttpRequestFactory(httpclient);
//            httpReqFactory.setReadTimeout(readTimeoutSeconds * 1000);
            httpReqFactory.setConnectionRequestTimeout(connectTimeoutSeconds * 1000);
            httpReqFactory.setConnectTimeout(connectTimeoutSeconds * 1000);

            restTemplate = new RestTemplateBuilder(rt -> rt.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().add("Authorization-key", customerServiceAuthorization);
                return execution.execute(request, body);
            })).uriTemplateHandler(new DefaultUriBuilderFactory(apiHost))
                    .requestFactory(() -> httpReqFactory)
                    .setConnectTimeout(Duration.ofSeconds(connectTimeoutSeconds))
                    .setReadTimeout(Duration.ofSeconds(readTimeoutSeconds))
                    .defaultHeader("Content-Type", "application/json")
                    .build();
        }
        return restTemplate;
    }
}
