package com.juan.pokemonSelect.controller;

import com.juan.pokemonSelect.model.Pokemon;
import com.juan.pokemonSelect.service.PokeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {
    private final PokeDataService pokeDataService;

    @Autowired
    public PokemonController(PokeDataService pokeDataService) {
        this.pokeDataService = pokeDataService;
    }

    @GetMapping("/top-heaviest")
    public List<Pokemon> getTopHeaviestPokemons(@RequestParam int limit) {
        return pokeDataService.getTopHeaviestPokemons(limit);
    }

    @GetMapping("/top-tallest")
    public List<Pokemon> getTopTallestPokemons(@RequestParam int limit) {
        return pokeDataService.getTopTallestPokemons(limit);
    }

    @GetMapping("/top-most-experienced")
    public List<Pokemon> getTopMostExperiencedPokemons(@RequestParam int limit) {
        return pokeDataService.getTopMostExperiencedPokemons(limit);
    }
}
