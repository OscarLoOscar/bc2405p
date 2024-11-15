package com.bootcamp.bc_yahoo_finance.infra.yahoofinance;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.bc_yahoo_finance.model.YahooQuoteDTO;
import com.bootcamp.bc_yahoo_finance.util.Scheme;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Approach 2
// call fc.yahoo.com -> get the Cookie -> Cookie save in to Redis
// call getCrumb -> get Cookie form Redis , save crumb into Redis

// Approach 1 , Thread safe 
public class YHRestClient {
  // https://query1.finance.yahoo.com/v7/finance/quote?symbols={symbols}&crumb={your_crumb}
  private static final String USER_AGENT = "Mozilla/5.0";
  private RestTemplate restTemplate;
  private CrumbManager crumbManager;
  private BasicCookieStore cookieStore;
  private final Object lock = new Object();

  public YHRestClient(RestTemplate restTemplate) {
    this.cookieStore = new BasicCookieStore();
    CloseableHttpClient httpClient = HttpClients.custom()//
        .setDefaultCookieStore(this.cookieStore)//
        .build();

    HttpComponentsClientHttpRequestFactory factory =
        new HttpComponentsClientHttpRequestFactory();
    factory.setHttpClient(httpClient);

    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    interceptors.add(new UserAgentInterceptor(USER_AGENT));

    this.restTemplate = new RestTemplateBuilder()//
        .setConnectTimeout(Duration.ofSeconds(5))//
        .setReadTimeout(Duration.ofSeconds(5))//
        .build();

    this.restTemplate.setRequestFactory(factory);
    this.restTemplate.setInterceptors(interceptors);
    this.crumbManager = new CrumbManager(this.restTemplate);

  }

  public YahooQuoteDTO getQuote(List<String> symbols)
      throws JsonMappingException, JsonProcessingException {

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.put("symbols", List.of(String.join(",", symbols)));// 0005.HK,0700.HK,0388.HK.....
    params.put("crumb", List.of(""));// symbols=0005.HK,0700.HK,0388.HK&crumb=

    String url = UriComponentsBuilder.newInstance()//
        .scheme(Scheme.HTTPS.name().toLowerCase())//
        .host(YahooFinance.CRUMB_DOMAINE)//
        .path(YahooFinance.VERSION_QUOTE)//
        .path(YahooFinance.ENDPOINT_QUOTE)//
        .queryParams(params)//
        .toUriString();

    synchronized (lock) {
      this.cookieStore.clear();
      String crumb = this.crumbManager.getCrumb();
      //pass by reference 
      url = url.concat(crumb);// symbols=0005.HK,0700.HK,0388.HK&crumb={validCrumb}
      System.out.println(url);
      ResponseEntity<String> response =
          this.restTemplate.getForEntity(url, String.class);

      return new ObjectMapper().readValue(response.getBody(),
          YahooQuoteDTO.class);
    }
  }

  private static class UserAgentInterceptor
      implements ClientHttpRequestInterceptor {

    private final String userAgent;

    public UserAgentInterceptor(String userAgent) {
      this.userAgent = userAgent;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
        ClientHttpRequestExecution execution) throws IOException {
      Objects.requireNonNull(request, "Request must not be null");
      Objects.requireNonNull(body, "Body must not be null");
      Objects.requireNonNull(execution, "Execution must not be null");
      request.getHeaders().set("User-Agent", userAgent);
      return execution.execute(request, body);
    }

  }
}
