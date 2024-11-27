package com.bc2405p.pokemon;

import java.util.Set;
import com.bc2405p.pokemon.Moves.FireMove;

public class FirePokemon extends Pokemon<FireMove> {

  private Set<FireMove> moves;

  public FirePokemon(String name) {
    super(name, PokemonType.FIRE);
    // super.getName();
  }

  @Override
  public boolean learnMove(FireMove move) {
    if (moves.size() < 4 && !moves.contains(move)) {
      moves.add(move);
      return true;
    }
    return false;
  }

  @Override
  public boolean forgetMove(FireMove move) {
    return this.moves.remove(move);
  }

  @Override
  public Set<FireMove> getMoves() {
    return this.moves;
  }

  @Override
  public String specialAbility() {
    return this.getName() + " heat up the area";
  }

  @Override
  public String attack() {
    return this.getName() + "uses Flame Thrower";
  }

  public static void main(String[] args) {
    FirePokemon pokemon1 = new FirePokemon("TEST");
    System.out.println(pokemon1.getName());
    System.out.println(pokemon1.getPokemonType());
    // Pokemon p2 = new Pokemon<Moves>() {

    // };
  }
}
