package se.comhem.web.test.repositories;

import se.comhem.web.test.domain.Hero;
import se.comhem.web.test.domain.MarvelHero;

import java.util.Map;

public interface HeroRepository {

    Map<Integer, MarvelHero> list();
    MarvelHero get(Integer id);
    void save(MarvelHero hero);

}
