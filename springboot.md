## Spring IoC (Inversion of Control , 控制反轉)
### ==「將 object 的控制權交給了外部的 Spring 容器來管理」==

唔洗理d Object , 專心Coding
```java
// without IoC
public class Cooking{
  public void prepareDinner(){
    Chicken chicken  = new Chicken();
    Vegetable vegetable = new Vegetable();
    Seasoning seasoning  = new Seasoning();

    System.out.prinln("Start Dinner");
  }
}
```

```java
@Component
public class DinnerIngredients{

}

@Component
public class Cooking{
  private DinnerIngredients dinnerIngredients;

  public Cooking(DinnerIngredients dinnerIngredients){
    this.dinnerIngredients=dinnerIngredients;
  }

  public void(){
    System.out.println("Ready Dinner;");
  }
}
```
### Main Concept
1. 將 object 的控制權交比 Spring 容器來管理
2. Dependency Injection (依賴注入) spring透過DI呢個動作，完成IoC

---

## Dependency Injection (依賴注入)
### ==SpringContext 入面搵番你要用既Bean出黎==
```java
// With IoC
public class Cooking {
    private Chicken chicken;
    private Vegetables vegetables;
    private Seasoning seasoning;

    public Cooking() {
        this.chicken = new Chicken();
        this.vegetables = new Vegetables();
        this.seasoning = new Seasoning();
    }

    public void prepareDinner() {
        System.out.println("Ready Dinner");
    }
}
```

```java
@Component
public class Chicken {

}

@Component
public class Vegetables {

}

@Component
public class Seasoning {

}

@Component
public class Cooking {
    private final Chicken chicken;
    private final Vegetables vegetables;
    private final Seasoning seasoning;

    public Cooking(Chicken chicken, Vegetables vegetables, Seasoning seasoning) {
        this.chicken = chicken;
        this.vegetables = vegetables;
        this.seasoning = seasoning;
    }

    public void prepareDinner() {
        System.out.println("Ready Dinner");
    }
}
```

### Benefit of DI
1.靈活
2.減少重複代碼
3.專注business logic

---
## Bean
##### ==Bean 就像是一個「零件」或「工具」, Spring IoC 容器負責管理和提供給你的程式使用==
### ==「由 Spring 容器來管理的 object，就叫做 Bean」==

## How to Create Bean ? 
### Approach 1
```java
@Component
public class Scissors {
    public void cut() {
        System.out.println("Start");
    }
}
```

### Approach 2
```java
@Configuration
public class ToolConfig {
    @Bean
    public Scissors scissors() {
        return new Scissors();
    }
}
```

### @Component 與 @Bean + @Configuration 分別
特點|	@Component	|@Bean + @Configuration|
|-|-|-|
使用場景 | 自己寫既Class : Controller , Service ...|Third Party's Class : RestTemplate , Redis ,Object Mapper|


Bean 的生命週期

Bean 的「生命週期」：
	1.	建立（Instantiation）：當程式啟動時，Spring 會根據你的設定建立 Bean。
	2.	初始化（Initialization）：Spring 可能會對 Bean 做一些額外的設定（例如自動注入依賴）。
	3.	使用（Usage）：你的程式會用到這些 Bean。
	4.	銷毀（Destruction）：當程式結束時，Spring 會負責釋放資源。

---

## How can we use Bean?
## using Bean by ==@Autowired==
1.Autowired條件1 : class must a Bean(@Component , @RestController , @Service , @Repository , @Configuration)
2.Autowired條件2 : base on polymorphism to find a Bean

### What is polymorphism?
#### ==「一個method可以有不同的行為」==
```java
public interface Grocery {
    void buy();
}

@Component
public class Chicken implements Grocery {
    @Override
    public void buy() {
        System.out.println("Buy a chicken");
    }
}

@Component
public class Fish implements Grocery {
    @Override
    public void buy() {
        System.out.println("Buy a fish");
    }
}
```

```java
public class Kitchen{
  @Autowired
  private Grocery grocery;

  public void cook(){
    grocery.buy();
    System.out.println("Start.");
  }
}
```


  ## 問題：在 Spring 容器中同時有 chicken 和 fish 這兩個 Bean 存在，how to solve in Spring Boot ?

  ### @Qualifier
  ####  ==將粒Bean改名==

  ```java
  public interface Grocery {
    void buy();
}

@Component("chickenGrocery")
public class Chicken implements Grocery {
    @Override
    public void buy() {
        System.out.println("Buy a chicken");
    }
}

@Component("fishGrocery")
public class Fish implements Grocery {
    @Override
    public void buy() {
        System.out.println("Buy a fish");
    }
}
```

 ```java
 @Component
public class Kitchen {
    @Autowired
    @Qualifier("chickenGrocery")
    private Grocery chickenGrocery;

    @Autowired
    @Qualifier("fishGrocery")
    private Grocery fishGrocery;

    public void cookChicken() {
        chickenGrocery.buy();
        System.out.println("Start cooking chicken");
    }

    public void cookFish() {
        fishGrocery.buy();
        System.out.println("Start cooking fish");
    }
}
```
---
# Exception
1. checked Exception(必須要解決 -> mvn spring-boot:run 都fail)
- IOException (input/output -> read file)
- SQLException (login fail)

2. unchecked Exception(Runtime時先會有機會見到,不一定要處理，但會在程式執行時fail -> 500 InternalServer Error)
- NullPointerException (incase , call Third Party's API , will get null)
- ArrayIndexOutOfBoundsException
- ArithmeticException

## Exception 和 Spring Boot 的配合
1. RestControllerAdvice
2. ExceptionHandler

```java
@RestController
@RequestMapping("/product")
public class ProductController{
    @GetMapping("/{id}") // localhost:8080/product/{id}
    public String getProductById(@PathVariable String id) {
        if (id.equals("123")) {
            return "Product: Apple";
        } else {
          throw new ProductNotFoundException("ProductNotFound");
}
    }
}
```

```java
public class ProductNotFoundException extends RuntimeException{
   public ProductNotFoundException(String message) {
        super(message);
    }
}
```
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ResponseEntity<>("Error：" + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
```

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleProductNotFoundException(ProductNotFoundException ex) {
        

    }
}
```

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorMessage handleProductNotFoundException(ProductNotFoundException ex) {
        return ErrorMessage.builder()//
        .code(ErrorCode.ProductNotFound.getCode())//
        .message(ErrorCode.ProductNotFound.getMessage())
        .build();
    }
}
```

```java
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage{
  private int code;
  private String message;
}
```
---

# Optional<T>
## During Runtime , call Third party's API return null
問題的根源：
	1.	some object in json is null 

  ```java
  private void test(){
    Optional<StockPrice> stockPrice =   stockPriceRepository.findById("0700.HK");
    stockPrice.get();

    if(stockPrice.isPresent()){ //return boolean 
    return stockPrice.get();
    } else {
        throw StockPriceNotFoundException("StockPrice not found");
    }

}
```

```java
 List<PostDTO> postDTOs = Optional.ofNullable(postService.getPost(postID))//
          .filter(post -> post.getUserId().equals(userID))//
          .map(post -> List.of(mapper.mapToDTO(post)))//
          .orElse(List.of());

        //  return null != return "" != return  List.of()
```

```java
@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
```

```java
public Optional<String> getUserEmail(Long userId) {
     return Optional.ofNullable(database.findEmailByUserId(userId));
}
```

```java
Optional<String> email = userService.getUserEmail(123L);

String validEmail = email.orElse("default@example.com");
```

### Approach 1
```java
String email = database.findEmailByUserId(userId);
if (email != null && email.contains("@")) {
    System.out.println("valid Email: " + email);
} else {
    System.out.println("Email not vaild");
}
```

### Approach 2
```java
database.findEmailByUserID(userId)//
.filter(email-> email.contains("@"))//
.ifPresentOrElse(
    email -> System.out.print("valid email" + email),
    ()-> System.out.print("invalid email")
);
```
---

## @Value & @PostConstruct

### @Value ==(將application.properties or application.yml既value input到attribute)==
#### for basic setting before =='mvn clean install / mvn spring-boot:run'==

```yml
jsonplaceholder:
  api:
    domain: www.jsonplaceholder.com
    endpoint:
      user: users
      post: posts
      comment: comments
```
```java
@Component
public class JsonPlaceHolerConfig {
    @Value("${jsonplaceholder.api.domain}") 
    private String domain;

    @Value("${jsonplaceholder.api.endpoint.user}") 
    private String usersEndpont;

    @Value("${jsonplaceholder.api.endpoint.post}") 
    private String postsEndpont;

    @Value("${jsonplaceholder.api.endpoint.comments}") 
    private String commentssEndpont;
}
```

```java
@Component
public class GroceryConfig {
    @Value("${meat}") 
    private String meat;

    @Value("${vegetable}") 
    private String vegetable;

    @Value("${budget}")
    private int budget;

    public void printGroceryList() {
        System.out.println("List");
        System.out.println("- meat" + meat);
        System.out.println("- vegetable" + vegetable);
        System.out.println("- budget" + budget + " 元");
    }
}
```

## @PostConstruct
### ==根據情況進行一些額外的處理==
```java
@Component
public class JsonPlaceHolerConfig {
    @Value("${jsonplaceholder.api.domain}") 
    private String domain;

    @Value("${jsonplaceholder.api.endpoint.user}") 
    private String usersEndpont;

    @Value("${jsonplaceholder.api.endpoint.post}") 
    private String postsEndpont;

    @Value("${jsonplaceholder.api.endpoint.comments}") 
    private String commentsEndpont;

    private  UserDTO[] userDTO ;
    
    @PostConstruct
    public void run(){
  String userUrl =  UriComponentBuild().newInstance()//
    .host(domain)//
    .path(usersEndpoint)//
    .toUriString();

    userDTO = restTemplate().getForObject(userUrl,UserDTO[].class)
    }

    public List<UserDTO> getUSerList(){
        return List.of(userDTO);
    }
}
```

```java
import java.io.File;

@PostConstruct
public void run(){
File file = new File("
/user/desktop/systemA/setting/setting.json");
}
```

([Java File Class for load excel / json / pdf etc...](https://www.w3schools.com/java/java_files.asp))

# Conclusion
### @Value : 之前的準備階段
### @PostConstruct :  @Value 之後既處理階段

---

