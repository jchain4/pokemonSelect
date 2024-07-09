package com.juan.pokemonSelect.controller;

import com.juan.pokemonSelect.config.SecurityConfig;
import com.juan.pokemonSelect.model.Pokemon;
import com.juan.pokemonSelect.service.PokeDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PokemonController.class)
@Import(SecurityConfig.class)
public class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokeDataService pokemonDataService;

    @Test
    public void testGetTopHeaviestPokemons() throws Exception {
        // Crea algunos Pokemon de prueba
        Pokemon pokemon1 = new Pokemon("pokemon1", 1, 1, 1);
        Pokemon pokemon2 = new Pokemon("pokemon2", 2, 2, 2);

        // Configura el mock para devolver los Pokemon de prueba cuando se llama al método
        when(pokemonDataService.getTopHeaviestPokemons(2)).thenReturn(Arrays.asList(pokemon1, pokemon2));

        // Realiza una solicitud GET y verifica la respuesta
        mockMvc.perform(get("/pokemons/top-heaviest?limit=2"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"pokemon1\",\"height\":1,\"weight\":1,\"base_experience\":1},{\"name\":\"pokemon2\",\"height\":2,\"weight\":2,\"base_experience\":2}]"));
    }

    @Test
    public void testGetTopTallestPokemons() throws Exception {
        // Crea algunos Pokemon de prueba
        Pokemon pokemon1 = new Pokemon("pokemon1", 1, 1, 1);
        Pokemon pokemon2 = new Pokemon("pokemon2", 2, 2, 2);

        // Configura el mock para devolver los Pokemon de prueba cuando se llama al método
        when(pokemonDataService.getTopTallestPokemons(2)).thenReturn(Arrays.asList(pokemon1, pokemon2));

        // Realiza una solicitud GET y verifica la respuesta
        mockMvc.perform(get("/pokemons/top-tallest?limit=2"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"pokemon1\",\"height\":1,\"weight\":1,\"base_experience\":1},{\"name\":\"pokemon2\",\"height\":2,\"weight\":2,\"base_experience\":2}]"));
    }

    @Test
    public void testGetTopMostExperiencedPokemons() throws Exception {
        // Crea algunos Pokemon de prueba
        Pokemon pokemon1 = new Pokemon("pokemon1", 1, 1, 1);
        Pokemon pokemon2 = new Pokemon("pokemon2", 2, 2, 2);

        // Configura el mock para devolver los Pokemon de prueba cuando se llama al método
        when(pokemonDataService.getTopMostExperiencedPokemons(2)).thenReturn(Arrays.asList(pokemon1, pokemon2));

        // Realiza una solicitud GET y verifica la respuesta
        mockMvc.perform(get("/pokemons/top-most-experienced?limit=2"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"pokemon1\",\"height\":1,\"weight\":1,\"base_experience\":1},{\"name\":\"pokemon2\",\"height\":2,\"weight\":2,\"base_experience\":2}]"));
    }



}
