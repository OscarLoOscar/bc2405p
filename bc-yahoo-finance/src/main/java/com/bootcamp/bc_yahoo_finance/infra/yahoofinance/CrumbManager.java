package com.bootcamp.bc_yahoo_finance.infra.yahoofinance;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.bc_yahoo_finance.util.Scheme;

public class CrumbManager {
  private CookieManager cookieManager;
  private RestTemplate restTemplate;

  public CrumbManager(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    this.cookieManager = new CookieManager(restTemplate);
  }

  public String getCrumb() {
    try {
      String cookie = this.cookieManager.getCookie();
      HttpHeaders headers = new HttpHeaders();
      headers.add("Cookie", cookie);
      headers.add("User-Agent", "Mozilla/5.0");
      HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

      String crumbUrl = UriComponentsBuilder.newInstance()//
          .scheme(Scheme.HTTPS.name().toLowerCase())//
          .host(YahooFinance.CRUMB_DOMAINE)//
          .path(YahooFinance.VERSION_CRUMB)//
          .path(YahooFinance.ENDPOINT_CRUMB)//
          .toUriString()//
          .toString();
      return restTemplate.exchange(crumbUrl, //
          HttpMethod.GET, //
          entity, //
          String.class)//
          .getBody();
    } catch (RestClientException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}
