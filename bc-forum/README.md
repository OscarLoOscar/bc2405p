# What we do on 30/10/2024  ? 

1. create bc-forum project
2. Target : jsonplaceholder 
     user + posts+ comments save in Database
    - @OneToOne + @OneToMany relationship in entity class :
    coding example in UserEntity class: 
    ```java=
        @OneToOne(mappedBy = "user", //
            cascade = {CascadeType.PERSIST, //
                    CascadeType.MERGE}, //
            fetch = FetchType.LAZY)
    private CompanyEntity companyEntity;

    @Builder.Default
    @OneToMany(mappedBy = "user", //
            cascade = {CascadeType.PERSIST, //
                    CascadeType.MERGE}, //
            fetch = FetchType.LAZY)
    private List<PostEntity> posts = new ArrayList<>();
    ```

     -  @Builder.Default >> need to create a List to add Object 
     - mappedBy > 同有關係既entity做Connect： User<->Address & User <-> Posts
     mappedBy 係放有關係既entity 既filed名

3. CommandLineRunner，令server start 個陣，就save data
   don't forget : implements CommandLineRunner
don't forget : @Configuration -> 令Spring可以 run 到 'run'呢個method： 
    ```
    @Override
  public void run(String... args) throws Exception
  ```

4. 小心  @Value >> 同application.yml 既path要相同，先搵到相應既String 
  小心 import : import org.springframework.beans.factory.annotation.Value;
  ==not import lombok.Value==
```java= 
  @Value("${api.jsonplaceholder.endpoint.users}")
  private String userEndpoint;

  @Value("${api.jsonplaceholder.endpoint.posts}")
  private String postEndpoint;

  @Value("${api.jsonplaceholder.endpoint.comments}")
  private String commentEndpoint;
  ```

  ```yml=
api:
  jsonplaceholder:
    domain: jsonplaceholder.typicode.com
    endpoint:
      users: users
      posts: posts
      comments: comments
  ```

