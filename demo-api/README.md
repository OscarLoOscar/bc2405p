# My Step about create Spring-boot project 

1. delete .mvn folder 

---

2. change folder to  application.yml
2.1 you can define your port number : default 8080

---

3. create Folder under 'java/groupId/artifactId' example : java/com/bootcamp/demo_api
3.1 Create : controller + impl , service + impl , config ,infra , model

---

4. call third party API -> https://jsonplaceholder.typicode.com/users -> break down API in application.yml
4.1 In application.yml: 
    ```
    api:
  domain: jsonplaceholder.typicode.com
  endpoint:
    user: /users
    ```
    example2 :https://sg.openrice.com/en/singapore/restaurants?conditionId=10039&tmReservation=true

    api:
      domain: sg.openrice.com
      lan: en
      location: singapore
      restaurants: restaurants
    
    If you need to fetch API facing @RequestParam -> 
    you can use Map to handle it in restTemplate.getForObject()

---

5. make sure you have dependency : spring web , because we need RestTemplate to fetch third party API
5.1 restTemplate.getForObject()
5.2 so we need to make RestTemplate be @Bean first 
5.3 thats why we create a config folder to handle @Bean

```
@Configuration
public class AppConfig {
  
  //CAll API 
  @Bean
  RestTemplate restTemplate(){
   return new RestTemplate();
  }
}
```

---

6. Now we can go to Service  
==dont forget add Annotation @Service== ,
 create method to fetch api by restTemplate.getForObject()
6.1 before that , we need to use @Value from 'import org.springframework.beans.factory.annotation.Value;' , not from lombok
6.2 use UriComponentsBuilder to merge the URL
```
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.name())// HTTP/ HTTPS
        .host(domain)// xxx.yyy.com
        .path(endpoint)//
        .toUriString();
```
expect result : https://jsonplaceholder.typicode.com/users
actual result : https%3C%2D%2Djsonplaceholder.typicode.com%2Dusers
 when you face this problem , call .build(boolean encoded) inside class UriComponentsBuilder to solve it.

---

7. Go to Controller layer 
add Annotation ==@RestController== -> include @Controller && @ResponseBody
7.1 define your own RESTful API : 
  1.Get
  2.Post
  3.Put
  4.Patch
  5.Delete
7.2 3 type of Inputparam : 
  1. @PathVariable -> "{endpoint}/pathA/pathB "
  2. @RequestParam -> "?param1=xxx&param2=yyy"
  3. @RequestBody -> Input Json , so we need to create an Object in Java by Class
  example in Postman:
  ```
  {
    "name": "TESTING 2 ",
    "email": "TESTING 2  email",
    "phone": "TESTING 2  phone"
  }
  ```
  step to input Json in postman : Body -> raw -> JSON

```
@Getter
@Setter
....
public class UserForm{
  String name;
  String email;
  String phone;
}
```

# Make sure you server is running without ERROR 
command : 'mvn spring-boot:run'
 when you server can not run success 
 check keywords : 'Caused By'

## don't forget the @RestController , @Service annotation in these 2 layer
if you need to @Autowired , make sure it is @Bean -> @Controller/ @Service / @Repository / @Configuration / @Component in class Level
if you need to make an Onbejct to be Bean , @Bean : example : RestTemplate 


```
   ├── DemoApiApplication.java
│   │               ├── config
│   │               │   └── AppConfig.java
│   │               ├── controller
│   │               │   ├── UserController.java
│   │               │   └── impl
│   │               │       └── UserOperation.java
│   │               ├── infra
│   │               │   ├── ApiUtil.java
│   │               │   └── Scheme.java
│   │               ├── model
│   │               │   └── User.java
│   │               └── service
│   │                   ├── UserService.java
│   │                   └── impl
│   │                       └── UserServiceImpl.java
│   └── resources
│       ├── application.yml
│       ├── static
│       └── templates
└── test
    └── java
        └── com
            └── bootcamp
                └── demo_api
                    └── DemoApiApplicationTests.java
```