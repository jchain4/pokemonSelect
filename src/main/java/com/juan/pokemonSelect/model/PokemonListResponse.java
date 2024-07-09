package com.juan.pokemonSelect.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonListResponse {
    private int count;
    private String next;
    private String previous;
    private List<PokemonResult> results;

}
