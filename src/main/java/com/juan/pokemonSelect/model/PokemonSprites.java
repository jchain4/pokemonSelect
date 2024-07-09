package com.juan.pokemonSelect.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PokemonSprites {

    @JsonProperty("front_default")
    private String frontDefault;

}
