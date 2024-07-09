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
        // Creas una lista vacía para almacenar los Pokemon que obtendrás de la API
        List<Pokemon> pokemons = new ArrayList<>();

        // La URL inicial para obtener la lista de Pokemon de la API
        String url = POKEAPI_URL;

        // Mientras la URL no sea nula ni esté vacía...
        while (url != null && !url.isEmpty()) {
            // Haces una solicitud GET a la API de Pokemon y obtienes una respuesta
            PokemonListResponse response = restTemplate.getForObject(url, PokemonListResponse.class);

            // Obtienes la lista de resultados de la respuesta. Si la lista de resultados es nula, usas una lista vacía en su lugar
            List<PokemonResult> results = Optional.ofNullable(response.getResults()).orElse(Collections.emptyList());

            // Para cada resultado, haces una solicitud GET a la URL del Pokemon y obtienes un CompletableFuture<Pokemon>
            // Luego, recoges todos los CompletableFuture en una lista
            List<CompletableFuture<Pokemon>> futures = results.stream().map(result -> getPokemon(result.getUrl())).collect(Collectors.toList());

            // Esperas a que todos los CompletableFuture se completen y recoges los resultados en una lista
            List<Pokemon> resultList = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());

            // Añades todos los Pokemon de la lista de resultados a tu lista de Pokemon
            pokemons.addAll(resultList);

            // Actualizas la URL con la URL de la próxima página de resultados de la API
            url = response.getNext();
        }

        // Devuelves la lista de Pokemon
        return pokemons;
    }

    public List<Pokemon> getTopHeaviestPokemons(int limit) {
        return getAllPokemons().stream()
                .sorted(Comparator.comparing(Pokemon::getWeight).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Pokemon> getTopTallestPokemons(int limit) {
        return getAllPokemons().stream()
                .sorted(Comparator.comparing(Pokemon::getHeight).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Pokemon> getTopMostExperiencedPokemons(int limit) {
        return getAllPokemons().stream()
                .sorted(Comparator.comparing(Pokemon::getBaseExperience).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }
}
