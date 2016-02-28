package se.comhem.web.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import se.comhem.web.test.domain.Hero;
import se.comhem.web.test.domain.MarvelHero;
import se.comhem.web.test.services.HeroService;

import java.util.Map;

@Controller
@EnableWebMvc
@RequestMapping("heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<HttpStatus> saveMarvelHero(@RequestBody MarvelHero hero) {
        heroService.save(hero);
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map<Integer,Hero>> listHeroes() {

        return new ResponseEntity<Map<Integer,Hero>>(heroService.list(), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Hero> getHero(@PathVariable String id) {

        try {
            Hero hero = heroService.get(Integer.parseInt(id));

            if(hero == null) {
                return new ResponseEntity<Hero>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Hero>(hero, HttpStatus.OK);
            }

        } catch (NumberFormatException nfe) {

            return new ResponseEntity<Hero>(HttpStatus.BAD_REQUEST);

        }
    }
}
