package com.bootcamp.bc_forum.util;

import java.util.Map;
// import lombok.Value; not this ,not under lombok
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UrlManager {

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  public String getUrl(String endpoint) {
    return UriComponentsBuilder.newInstance()//
        .scheme(Scheme.HTTPS.name()).host(domain)//
        .path(endpoint)//
        .toUriString();
  }

  // example
  // https://www.openrice.com/zh/hongkong/voting/top?_sUrl=https%3A%2F%2Fs.openrice.com%2FQrbXcQAAAA%3BBLfE9dWD2h1g0PBs5WFSL1exlTb1D4HWo96YBxWBtXBW-kmO2XBfq9Ecnxq_LZIk
  public String getUrl(String endpoint, Map<String, String> params) {
    return UriComponentsBuilder.newInstance()//
        .scheme(Scheme.HTTPS.name()).host(domain)//
        .path(endpoint)//
        .queryParam("_sUrl",
            "https%3A%2F%2Fs.openrice.com%2FQrbXcQAAAA%3BBLfE9dWD2h1g0PBs5WFSL1exlTb1D4HWo96YBxWBtXBW-kmO2XBfq9Ecnxq_LZIk")
        .toUriString();
  }

  // exmaple
  // /ab/cd/ef
  // mock to call this method : getUrl(endpoint,"ab","cd","ef")
  // avoid to public String getUrl(String endpoint,String pathA, String pathB,String pathC){
  public String getUrl(String endpoint, String... path) {

    return UriComponentsBuilder.newInstance()//
        .scheme(Scheme.HTTPS.name()).host(domain)//
        .path(endpoint)//
        .pathSegment(path)//
        .toUriString();
  }
  public String getUrl(String endpoint,Map<String,String> params,String... path) {
    return UriComponentsBuilder.newInstance()//
        .scheme(Scheme.HTTPS.name()).host(domain)//
        .path(endpoint)//
        .pathSegment(path)//
        .queryParam("_sUrl",
            "https%3A%2F%2Fs.openrice.com%2FQrbXcQAAAA%3BBLfE9dWD2h1g0PBs5WFSL1exlTb1D4HWo96YBxWBtXBW-kmO2XBfq9Ecnxq_LZIk")
        .toUriString();
  }
}

