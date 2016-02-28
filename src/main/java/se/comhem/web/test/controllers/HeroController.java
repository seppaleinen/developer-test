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

import java.util.List;
import java.util.Map;

@Controller
@EnableWebMvc
@RequestMapping("heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<HttpStatus> saveMarvelHero(@RequestBody MarvelHero hero) {
        try {
            heroService.save(hero);
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MarvelHero>> listHeroes() {
        return new ResponseEntity<List<MarvelHero>>(heroService.list(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public ResponseEntity<MarvelHero> getHero(@PathVariable String name) {
        try {
            MarvelHero hero = heroService.get(name);
            if(hero == null) {
                return new ResponseEntity<MarvelHero>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<MarvelHero>(heroService.get(name), HttpStatus.OK);
            }
        } catch (NumberFormatException nfe) {
            return new ResponseEntity<MarvelHero>(HttpStatus.BAD_REQUEST);
        }
    }
}
