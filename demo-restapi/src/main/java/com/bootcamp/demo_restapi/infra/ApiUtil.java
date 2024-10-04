package com.bootcamp.demo_restapi.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class ApiUtil {

  @Value("${api.url.domain}")
  private String domain;

  public  String getUrl(Scheme scheme, String endpoint) {
    return UriComponentsBuilder.newInstance()//
        .scheme(scheme.getProtocol())//
        .host(domain)//
        .path(endpoint)//
        .toUriString();
  }

}
