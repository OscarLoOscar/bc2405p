import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DemoLambda {
  public static void main(String[] args) {
    // FunctionalInterface (Interface only one method)
    // result : we can user '() -> {}'

    Map<String, Integer> productPrices = new HashMap<>();
    productPrices.put("Apple", 100);
    productPrices.put("Banana", 80);
    productPrices.put("Cherry", 120);
    System.out.println("Before discount : " + productPrices);
    // example of BiFunction
    BiFunction<String, Integer, Integer> applyDiscount =
        (product, price) -> (int) (price * 0.8);

    productPrices.replaceAll(applyDiscount);

    System.out.println("After discount : " + productPrices);

    // Before discount : {Apple=100, Cherry=120, Banana=80}
    // After discount : {Apple=80, Cherry=96, Banana=64}

    // example of BiConsumer
    BiConsumer<String, Integer> priceEntry =
        (product, price) -> System.out.println(product + " : $  " + price);

    productPrices.forEach(priceEntry);
    // output :
    // Apple : $ 80
    // Cherry : $ 96
    // Banana : $ 64

    // for (Map.Entry<String, Inger> entry : productPrices.entrySet()) {
    // System.out.println(entry);
    // }
    //

    BiFunction<Integer, Integer, Integer> add = (a, b) -> {
      int result = a + b;
      return result;
    };
    System.out.println(add.apply(10, 20));

    // example of Predicate
    List<Integer> list1 =
        Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 10, 11);
    // list1.add(7); can not use .add() because of Arrays.asList()
    // Approach 1
    Predicate<Integer> isEven = n -> n % 2 == 0;
    List<Integer> evenNumberLists = list1.stream()//
        .filter(isEven)//
        .collect(Collectors.toList());

    // Approach 2
    List<Integer> evenNumberLists2 = list1.stream()//
        .filter(num -> num % 2 == 0)//
        .collect(Collectors.toList());

    // example of Function
    List<String> evenNumberLists3 = list1.stream()//
        .map(num -> String.valueOf(num))// Spring boot mapper -> Entity convert to DTO or DTO map to Entity
        .collect(Collectors.toList());
    System.out.println("line 73 : ");
    System.out.println(evenNumberLists3.get(0) instanceof String);

    // example .collect(Collector)

    Set<Integer> evenNumberLists4 = list1.stream()//
        .collect(Collectors.toSet());
    System.out.println("Before stream : " + list1);
    System.out.println("After stream : " + evenNumberLists4);

    Set<String> evenNumberLists5 = list1.stream()//
        .filter(num -> num % 2 == 0)//// Stream<Integer>
        .map(num -> String.valueOf(num))// Integer to String
        .collect(Collectors.toSet());

    System.out.println("After map and filter to Set : " + evenNumberLists5);

    // example of Supplier
    Supplier<Double> randomSupplier = () -> Math.random();
    System.out.println(randomSupplier.get());// random double : 0.3676260268398036

    TreeMap<Double, Integer> stockPrice = new TreeMap<>();
    stockPrice.put(333.50, 300);
    stockPrice.put(333.30, 100);
    stockPrice.put(333.40, 200);
    System.out.println(stockPrice);

    Map<Double, Integer> stockPrice2 = new HashMap<>();
    stockPrice2.put(333.50, 300);
    stockPrice2.put(333.30, 100);
    stockPrice2.put(333.40, 200);
    System.out.println(stockPrice2);

    
  }
}
