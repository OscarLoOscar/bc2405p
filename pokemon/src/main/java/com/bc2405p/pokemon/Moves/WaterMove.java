package com.bc2405p.pokemon.Moves;

public enum WaterMove implements Moves{
  AQUA_CUTTER("Aqua Cutter", MoveCategory.PHYSICAL, 70, 9),//
  AQUA_JET("Aqua Jet", MoveCategory.PHYSICAL, 40, 4),//
  AQUA_RING("Aqua Ring", MoveCategory.OTHER, 0, 4),//
  AQUA_STEP("Aqua Step", MoveCategory.PHYSICAL, 80, 9),//
  AQUA_TAIL("Aqua Tail", MoveCategory.PHYSICAL, 90, 4),//
  BOUNCY_BUBBLE("Bouncy Bubble", MoveCategory.SPECIAL, 90, 7),//
  BRINE("Brine", MoveCategory.SPECIAL, 65, 4),//
  BUBBLE("Bubble", MoveCategory.SPECIAL, 40, 1);

  private final String name;
  private final MoveCategory category;
  private final int power;
  private final int generation;

  WaterMove(String name, MoveCategory category, int power, int generation) {
    this.name = name;
    this.category = category;
    this.power = power;
    this.generation = generation;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public MoveCategory getMoveCategory() {
    return category;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public int getGeneration() {
    return generation;
  }


}