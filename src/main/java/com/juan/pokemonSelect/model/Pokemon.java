package com.juan.pokemonSelect.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    private String name;
    private int weight;
    private int height;
    @JsonProperty("base_experience")
    private int baseExperience;


}
