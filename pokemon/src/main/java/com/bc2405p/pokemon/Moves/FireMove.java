package com.bc2405p.pokemon.Moves;

import lombok.Getter;

@Getter
public enum FireMove implements Moves {
  ARMOR_CANNON("Armor Cannon", MoveCategory.SPECIAL, 120, 9), //
  BITTER_BLADE("Bitter Blade", MoveCategory.PHYSICAL, 90, 9), //
  BLAST_BURN("Blast Burn", MoveCategory.SPECIAL, 150, 3), //
  BLAZE_KICK("Blaze Kick", MoveCategory.PHYSICAL, 85, 3), //
  BLAZING_TORQUE("Blazing Torque", MoveCategory.PHYSICAL, 80, 9), //
  BLUE_FLARE("Blue Flare", MoveCategory.SPECIAL, 130, 5), //
  BURN_UP("Burn Up", MoveCategory.SPECIAL, 150, 7), //
  BURNING_BULWARK("Burning Bulwark", MoveCategory.OTHER, 0, 9);

  private final String name;
  private final MoveCategory moveCategory;
  private final int power;
  private final int generation;

  private FireMove(String name, MoveCategory moveCategory, int power,
      int generation) {
    this.name = name;
    this.moveCategory = moveCategory;
    this.power = power;
    this.generation = generation;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getPower() {
    return this.power;
  }

  @Override
  public int getGeneration() {
    return this.generation;
  }

  @Override
  public MoveCategory getMoveCategory() {
    return this.moveCategory;
  }
}
