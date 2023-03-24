package com.vti.api;

import com.vti.dto.ComparativeReportDTO;
import org.slf4j.Logger;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ConnectWebAPI {

    private static volatile String apiVTPUrl = "https://10.240.192.148:8206/api/hddt-api/";


    public static Response getMethodNoToken(String method, Object data) {
        String url = apiVTPUrl + method;
        try {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.61.11.42", 3128));
//            requestFactory.setProxy(proxy);

            RestTemplate restTemplate = new RestTemplate(requestFactory);
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MDk4MzU5NzMsInVzZXJOYW1lIjoic21zRWR1IiwiZXhwIjoxNzM1NjY0NDAwfQ.LU1VslB2OuiepPubv2ckf7H-npfcWWG-hvVg_8Mq6Da7wkAcl7uW9WL592QXqSdCtKtzP4YYFjG-pb4GSM-_wA");

            ResponseEntity<Response> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(data, headers), Response.class);
            if (responseEntity.getStatusCodeValue() == 200 && responseEntity.getBody().getStatus() == 200L) {
                return responseEntity.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
//            log.error("Loi " + e.getMessage(), e);?
            return null;
        }
    }














}
