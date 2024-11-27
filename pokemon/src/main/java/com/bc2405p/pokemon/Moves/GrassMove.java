package com.bc2405p.pokemon.Moves;

public enum GrassMove implements Moves {
  ABSORB("Absorb", MoveCategory.SPECIAL, 20, 1), //
  APPLE_ACID("Apple Acid", MoveCategory.SPECIAL, 80, 8), //
  AROMATHERAPY("Aromatherapy", MoveCategory.OTHER, 0, 3), //
  BLOOM_DOOM("Bloom Doom", MoveCategory.OTHER, 0, 7), //
  BRANCH_POKE("Branch Poke", MoveCategory.PHYSICAL, 40, 8), //
  BULLET_SEED("Bullet Seed", MoveCategory.PHYSICAL, 25, 3), //
  CHLOROBLAST("Chloroblast", MoveCategory.SPECIAL, 150, 8), //
  COTTON_GUARD("Cotton Guard", MoveCategory.OTHER, 0, 5);

  private final String name;
  private final MoveCategory category;
  private final int power;
  private final int generation;

  GrassMove(String name, MoveCategory category, int power, int generation) {
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