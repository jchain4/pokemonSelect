package com.juan.pokemonSelect.service;

import com.juan.pokemonSelect.model.Pokemon;
import com.juan.pokemonSelect.model.PokemonResult;
import com.juan.pokemonSelect.model.PokemonListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PokeDataServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private PokeDataService pokeDataService;

    @BeforeEach
    public void setUp() {
// Crea algunos Pokemon de prueba
        Pokemon pokemon1 = new Pokemon("Bulbasaur", 1, 1, 1);
        Pokemon pokemon2 = new Pokemon("Ivysaur", 4, 2, 22);
        Pokemon pokemon3 = new Pokemon("Venusaur", 5, 4, 21);
        Pokemon pokemon4 = new Pokemon("Squirtle", 2, 2, 42);
        Pokemon pokemon5 = new Pokemon("Wartotle", 3, 3, 13);

        // Crea una respuesta de prueba
        PokemonListResponse response = new PokemonListResponse();
        response.setResults(Arrays.asList(
                new PokemonResult("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
                new PokemonResult("Ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
                new PokemonResult("Venusaur", "https://pokeapi.co/api/v2/pokemon/3/"),
                new PokemonResult("Squirtle", "https://pokeapi.co/api/v2/pokemon/4/"),
                new PokemonResult("Wartotle", "https://pokeapi.co/api/v2/pokemon/5/")
        ));

        // Configura el mock para devolver la respuesta de prueba cuando se llama a getForObject
        when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon", PokemonListResponse.class)).thenReturn(response);

        // Configura el mock para devolver diferentes Pokemon dependiendo de la URL
        when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/1/", Pokemon.class)).thenReturn(pokemon1);
        when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/2/", Pokemon.class)).thenReturn(pokemon2);
        when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/3/", Pokemon.class)).thenReturn(pokemon3);
        when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/4/", Pokemon.class)).thenReturn(pokemon4);
        when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/5/", Pokemon.class)).thenReturn(pokemon5);
    }

    @Test
    public void testGetAllPokemons() {

        // Llama al método que estás probando
        List<Pokemon> allPokemons = pokeDataService.getAllPokemons();

        // Comprueba que el método devuelve los Pokémons correctos
        assertEquals(5, allPokemons.size());
        assertEquals("Bulbasaur", allPokemons.get(0).getName());
        assertEquals("Ivysaur", allPokemons.get(1).getName());
    }

    @Test
    public void testGetTopHeaviestPokemons() {
        // Llama al método que estás probando
        List<Pokemon> topHeaviestPokemons = pokeDataService.getTopHeaviestPokemons(2);

        // Comprueba que el método devuelve los Pokémons correctos
        assertEquals(2, topHeaviestPokemons.size());
        assertEquals("Venusaur", topHeaviestPokemons.get(0).getName()); // pokemon2 es más pesado que pokemon1
        assertEquals("Ivysaur", topHeaviestPokemons.get(1).getName());
    }

    @Test
    public void testGetTopTallestPokemons() {
        // Llama al método que estás probando
        List<Pokemon> topTallestPokemons = pokeDataService.getTopTallestPokemons(2);

        // Comprueba que el método devuelve los Pokémons correctos
        assertEquals(2, topTallestPokemons.size());
        assertEquals("Venusaur", topTallestPokemons.get(0).getName()); // pokemon2 es más alto que pokemon1
        assertEquals("Wartotle", topTallestPokemons.get(1).getName());
    }

    @Test
    public void testGetTopMostExperiencedPokemons() {
        // Llama al método que estás probando
        List<Pokemon> topMostExperiencedPokemons = pokeDataService.getTopMostExperiencedPokemons(2);

        // Comprueba que el método devuelve los Pokémons correctos
        assertEquals(2, topMostExperiencedPokemons.size());
        assertEquals("Squirtle", topMostExperiencedPokemons.get(0).getName()); // pokemon2 tiene más experiencia base que pokemon1
        assertEquals("Ivysaur", topMostExperiencedPokemons.get(1).getName());
    }
}
