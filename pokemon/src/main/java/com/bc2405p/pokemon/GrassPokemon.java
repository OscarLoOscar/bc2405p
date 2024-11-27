package com.bc2405p.pokemon;

import java.util.HashSet;
import java.util.Set;
import com.bc2405p.pokemon.Moves.GrassMove;

public class GrassPokemon extends Pokemon<GrassMove>{

  private Set<GrassMove> moves;
  
  public GrassPokemon(String name) {
    super(name, PokemonType.GRASS);
    this.moves=new HashSet<>();
  }
  @Override
  public boolean learnMove(GrassMove move) {
    if (moves.size() < 4 && !moves.contains(move)) {
      moves.add(move);
      return true;
    }
    return false;
  }

  @Override
  public boolean forgetMove(GrassMove move) {
    return moves.remove(move);
  }

  @Override
  public Set<GrassMove> getMoves() {
    return this.moves;
  }

  @Override
  public String attack() {
    return this.getName() + " uses Vine Whip";
  }

  @Override
  public String specialAbility() {
    return this.getName() + " uses Solar Beam";
  }
}