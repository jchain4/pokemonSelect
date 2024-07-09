package com.juan.pokemonSelect.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pokemon {
    private String name;
    private int weight;
    private int height;
    @JsonProperty("base_experience")
    private int baseExperience;


}
