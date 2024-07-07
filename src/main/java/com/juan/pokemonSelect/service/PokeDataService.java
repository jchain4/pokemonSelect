package com.juan.pokemonSelect.service;

import com.juan.pokemonSelect.model.Pokemon;
import com.juan.pokemonSelect.model.PokemonListResponse;
import com.juan.pokemonSelect.model.PokemonResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PokeDataService {

    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon";

    private final RestTemplate restTemplate;

    public PokeDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<Pokemon> getPokemon(String url) {
        Pokemon pokemon = restTemplate.getForObject(url, Pokemon.class);
        return CompletableFuture.completedFuture(pokemon);
    }

    public List<Pokemon> getAllPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        String url = POKEAPI_URL;
        while (url != null) {
            PokemonListResponse response = restTemplate.getForObject(url, PokemonListResponse.class);
            List<PokemonResult> results = Optional.ofNullable(response.getResults()).orElse(Collections.emptyList());
            List<CompletableFuture<Pokemon>> futures = results.stream()
                    .map(result -> getPokemon(result.getUrl()))
                    .collect(Collectors.toList());
            List<Pokemon> resultList = futures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());
            pokemons.addAll(resultList);
            url = response.getNext();
        }
        return pokemons;
    }

    public List<Pokemon> getHighestPokemons(int limit) {
        List<Pokemon> allPokemons = getAllPokemons();
        return allPokemons.stream()
                .sorted(Comparator.comparing(Pokemon::getHeight).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Pokemon> getHeaviestPokemons(int limit) {
        List<Pokemon> allPokemons = getAllPokemons();
        return allPokemons.stream()
                .sorted(Comparator.comparing(Pokemon::getWeight).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }


    public List<Pokemon> getMostExperiencedPokemons(int limit) {
        List<Pokemon> allPokemons = getAllPokemons();
        return allPokemons.stream()
                .sorted(Comparator.comparing(Pokemon::getBaseExperience).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }
}
