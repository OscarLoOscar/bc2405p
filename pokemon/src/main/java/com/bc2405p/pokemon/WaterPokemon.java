package com.bc2405p.pokemon;

import java.util.HashSet;
import java.util.Set;
import com.bc2405p.pokemon.Moves.WaterMove;

public class WaterPokemon extends Pokemon<WaterMove> {
  private Set<WaterMove> moves;

  public WaterPokemon(String name) {
    super(name, PokemonType.WATER);
    this.moves = new HashSet<>();
  }

    @Override
  public boolean learnMove(WaterMove move) {
    if (moves.size() < 4 && !moves.contains(move)) {
      moves.add(move);
      return true;
    }
    return false;
  }

  @Override
  public boolean forgetMove(WaterMove move) {
    return moves.remove(move);
  }

  @Override
  public Set<WaterMove> getMoves() {
    return this.moves;
  }

  @Override
  public String attack() {
    return this.getName() + " uses Water Gun";
  }

  @Override
  public String specialAbility() {
    return this.getName() + " creates a tidal wave!";
  }
}
