1. Supplier

應用場景：生成一個新物件或動態數據
```bash
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        // Supplier 產生一個隨機數
        Supplier<Double> randomSupplier = () -> Math.random();

        // 使用 Supplier 取得隨機數
        System.out.println("Random Value: " + randomSupplier.get());
    }
}
```
---
2. Consumer

應用場景：處理集合中的每個元素
```bash
import java.util.*;
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // 使用 Consumer 打印每個名字
        Consumer<String> printName = name -> System.out.println("Hello, " + name);
        names.forEach(printName);
    }
}
```
---
3. BiConsumer<T, U>

應用場景：操作鍵值對（Map 中的 key-value）
```bash
import java.util.*;
import java.util.function.BiConsumer;

public class BiConsumerExample {
    public static void main(String[] args) {
        Map<String, Integer> productPrices = new HashMap<>();
        productPrices.put("Apple", 100);
        productPrices.put("Banana", 80);

        // 使用 BiConsumer 打印 Map 的鍵值對
        BiConsumer<String, Integer> printEntry = (product, price) ->
                System.out.println(product + ": $" + price);
        productPrices.forEach(printEntry);
    }
}
```
---
4. Predicate

應用場景：過濾集合中的元素
```bash
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // 使用 Predicate 過濾偶數
        Predicate<Integer> isEven = n -> n % 2 == 0;
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(isEven)
                                           .collect(Collectors.toList());

        System.out.println("Even Numbers: " + evenNumbers);
    }
}
```
---
5. BiPredicate<T, U>

應用場景：檢查條件是否成立（Map 鍵值對是否滿足條件）
```bash
import java.util.*;
import java.util.function.BiPredicate;

public class BiPredicateExample {
    public static void main(String[] args) {
        Map<String, Integer> productPrices = new HashMap<>();
        productPrices.put("Apple", 100);
        productPrices.put("Banana", 80);

        // 使用 BiPredicate 判斷產品價格是否大於 90
        BiPredicate<String, Integer> isExpensive = (product, price) -> price > 90;

        productPrices.forEach((product, price) -> {
            if (isExpensive.test(product, price)) {
                System.out.println(product + " is expensive.");
            }
        });
    }
}
```

---
6. Function<T, R>

應用場景：將集合中的元素進行轉換
```bash
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // 使用 Function 將名字轉為大寫
        Function<String, String> toUpperCase = String::toUpperCase;
        List<String> upperCaseNames = names.stream()
                                           .map(toUpperCase)
                                           .collect(Collectors.toList());

        System.out.println("Uppercase Names: " + upperCaseNames);
    }
}
```
---
7. BiFunction<T, U, R>

應用場景：合併集合元素生成新結果
```bash
import java.util.*;
import java.util.function.BiFunction;

public class BiFunctionExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob");
        List<Integer> ages = Arrays.asList(25, 30);

        // 使用 BiFunction 將名字和年齡合併
        BiFunction<String, Integer, String> combineNameAndAge = (name, age) -> name + " is " + age + " years old";

        for (int i = 0; i < names.size(); i++) {
            System.out.println(combineNameAndAge.apply(names.get(i), ages.get(i)));
        }
    }
}
```
---
8. UnaryOperator

應用場景：修改集合中的每個元素
```bash
import java.util.*;
import java.util.function.UnaryOperator;

public class UnaryOperatorExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // 使用 UnaryOperator 每個數字加倍
        UnaryOperator<Integer> doubleValue = n -> n * 2;
        List<Integer> doubledNumbers = new ArrayList<>();
        numbers.forEach(n -> doubledNumbers.add(doubleValue.apply(n)));

        System.out.println("Doubled Numbers: " + doubledNumbers);
    }
}
```
---
9. BiUnaryOperator (模擬)

Java 沒有直接的 BiUnaryOperator，但可以用 BiFunction<T, T, T> 模擬，處理兩個相同類型的輸入並返回同類型的結果。

應用場景：合併數值
```bash
import java.util.function.BiFunction;

public class BiUnaryOperatorExample {
    public static void main(String[] args) {
        // 使用 BiFunction 合併兩個數字
        BiFunction<Integer, Integer, Integer> addNumbers = (a, b) -> a + b;

        int result = addNumbers.apply(10, 20);
        System.out.println("Sum: " + result); // 輸出：Sum: 30
    }
}
```