# Different between flatmap() and map()
## Example Scenario
```
List<List<String>> data = Arrays.asList(Arrays.asList("ABC") , Arrays.asList("DEF"));
```

## Goal : 
- With ==map== : get a list of list  , keep original structure
- With ==flatMap== : get a flat list where each element from the nested lits is combined into a single List.


# Coding Example
### Using map

```
public static void main(String[] args){
  List<List<String>> data = Arrays.asList(Arrays.asList("ABC") , Arrays.asList("DEF"));

  // Using map
  List<List<String>> mapped = data.stream()//
  .map(list->list)  // keep the structure
  .collect(Collectors.toList());
}
```

### Output : 
```
[[ABC],[DEF]]
```
---
### Using flatMap

```
public static void main(String[] args){
  List<List<String>> data = Arrays.asList(Arrays.asList("ABC") , Arrays.asList("DEF"));

  // Using map 
  //Approach 1
  List<String> mapped = data.stream()//
  .flatMap(List::stream)  //flattens each inner list into a single list 
  .collect(Collectors.toList());

  // Approach 2
   List<String> mapped = data.stream()//
  .flatMap(list- >list.stream())  //flattens each inner list into a single list 
  .collect(Collectors.toList());
}
```

### Output : 
```
[ABC,DEF]
```