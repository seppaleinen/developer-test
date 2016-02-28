package se.comhem.web.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import se.comhem.web.test.controllers.HeroController;
import se.comhem.web.test.domain.Gender;
import se.comhem.web.test.domain.MarvelHero;
import se.comhem.web.test.repositories.HeroFileBasedRepository;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
// NOTE!! order is important
@WebAppConfiguration
@IntegrationTest("server.port:0")
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class HeroIntegrationTest {
    @Autowired
    HeroFileBasedRepository repository;

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setup() {
        RestAssured.port = port;
        repository.deleteAll();
    }

    @Test
    public void testGetHeroList() {
        try {
            Response response = given().accept(ContentType.JSON).when().get("/developer-test/heroes");
            assertEquals(HttpStatus.OK.value(), response.statusCode());
            assertEquals("[]", response.getBody().asString());

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

            Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(bytes).when().post("/developer-test/heroes/save");
            assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

            /**
            this.mockMvc.perform(post("/heroes/save").
                    accept(MediaType.APPLICATION_JSON).
                    contentType(MediaType.APPLICATION_JSON).
                    content(bytes)).
                    andExpect(status().isCreated());
             **/

        } catch(Exception e) {

            fail("Exception: " + e);

        }
    }

}
