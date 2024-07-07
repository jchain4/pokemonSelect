package com.juan.pokemonSelect.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonSprites {

    @JsonProperty("front_default")
    private String frontDefault;

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }
}
