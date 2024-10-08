package com.bootcamp.demo_api.infra;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration // -> @Bean -> SpringContext -> Autowired
public class ApiUtil {

  @Value("${api.domain}") // before server start , base on the path , scan application.yml
  String domain;

  
  public String getUrl(Scheme scheme, String endpoint) {
    String url = UriComponentsBuilder.newInstance()
    .scheme(scheme.name())//
    .host(domain)//
    .path(endpoint)//
    .toUriString();
    return UriComponentsBuilder.newInstance()// 組合API
        .scheme(scheme.name())//
        .host(domain)//
        .path(endpoint)//
        .toUriString();
  }

  public String getUrl(Scheme scheme, String endpoint,Map<String,String> requestParam) {
    return UriComponentsBuilder.newInstance()// 組合API
        .scheme(scheme.name())//
        .host(domain)//
        .path(endpoint)//
        .queryParam("", requestParam.get(""))//
        .toUriString();
  }

}
