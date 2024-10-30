package com.bootcamp.bc_forum.util;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
// import lombok.Value; not this  ,not under lombok
import org.springframework.beans.factory.annotation.Value;

@Component
public class UrlManager {

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  public String  getUrl(String endpoint){
    return UriComponentsBuilder.newInstance()//
    .scheme(Scheme.HTTPS.name())
    .host(domain)//
    .path(endpoint)//
    .toUriString();
  }
}
