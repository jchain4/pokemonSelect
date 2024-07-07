package com.juan.pokemonSelect.service;

import com.juan.pokemonSelect.model.Pokemon;
import com.juan.pokemonSelect.model.PokemonResult;
import com.juan.pokemonSelect.model.PokemonListResponse;
import com.juan.pokemonSelect.service.PokeDataService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static javax.management.Query.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PokeDataServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testGetHighestPokemons() {
        // Crea un mock de PokeDataService
        PokeDataService pokeDataService = new PokeDataService(restTemplate);

        // Crea algunos PokemonResult de prueba
        PokemonResult pokemonResult1 = new PokemonResult("pokemon1", "https://pokeapi.co/api/v2/pokemon/1/");
        PokemonResult pokemonResult2 = new PokemonResult("pokemon2", "https://pokeapi.co/api/v2/pokemon/2/");
        PokemonResult pokemonResult3 = new PokemonResult("pokemon3", "https://pokeapi.co/api/v2/pokemon/3/");

        // Crea una lista de PokemonResult
        List<PokemonResult> pokemonResults = Arrays.asList(pokemonResult1, pokemonResult2, pokemonResult3);

        // Crea pokemonListResponse
        PokemonListResponse pokemonListResponse = new PokemonListResponse();
        pokemonListResponse.setResults(pokemonResults);

        when(restTemplate.getForObject(anyString(), any())).thenReturn(pokemonListResponse);

        // Llama al método que estás probando
        List<Pokemon> highestPokemons = pokeDataService.getHighestPokemons(2);

        // Comprueba que el método devuelve los Pokémons correctos
        assertEquals(2, highestPokemons.size());
    }
}
