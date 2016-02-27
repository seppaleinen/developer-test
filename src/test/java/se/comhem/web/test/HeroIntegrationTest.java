package se.comhem.web.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import se.comhem.web.test.controllers.HeroController;
import se.comhem.web.test.controllers.PongController;
import se.comhem.web.test.domain.Gender;
import se.comhem.web.test.domain.Hero;
import se.comhem.web.test.domain.MarvelHero;
import se.comhem.web.test.services.HeroService;

import java.util.HashMap;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HeroIntegrationTest {

    MockMvc mockMvc;

    @Mock
    private HeroService heroService;
    @InjectMocks
    HeroController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void testGetHeroList() {
        try {
            when(heroService.list()).thenReturn(new HashMap<Integer, Hero>());

            this.mockMvc.perform(get("/heroes").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(content().string("{}"));

        } catch(Exception e) {

            fail("Exception: " + e);

        }
    }

    @Test
    public void testSaveHero() {
        try {
            MarvelHero hero = new MarvelHero("name", "weakness", Gender.MAN);
            ObjectMapper objectMapper = new MappingJackson2HttpMessageConverter().getObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            String bytes = objectMapper.writeValueAsString(hero);

            this.mockMvc.perform(post("/heroes/save").
                    accept(MediaType.APPLICATION_JSON).
                    contentType(MediaType.APPLICATION_JSON).
                    content(bytes)).
                    andExpect(status().isCreated());

        } catch(Exception e) {

            fail("Exception: " + e);

        }
    }

}
