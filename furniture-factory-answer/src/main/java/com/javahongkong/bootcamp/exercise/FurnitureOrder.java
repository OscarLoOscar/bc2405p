package com.javahongkong.bootcamp.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FurnitureOrder implements FurnitureOrderInterface {
  /**
   * TODO: Create a map of Furniture items to order quantities
   */
  HashMap<Furniture, Integer> ordersOfFurnitures;

  /**
   * Initialize a new mapping of Furniture types to order quantities.
   */
  FurnitureOrder() {
    ordersOfFurnitures = new HashMap<>();
  }

  public void addToOrder(final Furniture type, final int furnitureCount) {
    this.ordersOfFurnitures.put(type, furnitureCount);
  }

  public HashMap<Furniture, Integer> getOrderedFurniture() {
    return this.ordersOfFurnitures;
  }

  public float getTotalOrderCost() {
    return this.ordersOfFurnitures.entrySet().stream()//
        .map(entry -> entry.getKey().cost() * entry.getValue())//
        .reduce(0.0f, Float::sum);
  }

  public int getTypeCount(Furniture type) {
    return this.ordersOfFurnitures.getOrDefault(type, 0);
  }

  public float getTypeCost(Furniture type) {
    return type.cost() * this.getTypeCount(type);
  }

  public int getTotalOrderQuantity() {
    int total = 0;
    // Approach 1
    for (Map.Entry<Furniture, Integer> entry : this.ordersOfFurnitures
        .entrySet()) {
      total += entry.getValue();
    }

    // Approach 2
    total = this.ordersOfFurnitures.values()//
        .stream()//
        .mapToInt(Integer::intValue)//
        .sum();

    // Approach 3
    Set<Furniture> furnitureList = this.ordersOfFurnitures.keySet();
    total = furnitureList.stream()//
        .map(e -> this.getTypeCount(e))//
        .mapToInt(Integer::intValue)//
        .sum();
    // Approach 4
    int countA = this.getTypeCount(Furniture.CHAIR);
    int countB = this.getTypeCount(Furniture.COUCH);
    int countC = this.getTypeCount(Furniture.TABLE);
    total = countA + countB + countC;
    return total;
  }
}
