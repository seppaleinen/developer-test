package se.comhem.web.test.repositories;

import org.springframework.data.repository.CrudRepository;
import se.comhem.web.test.domain.MarvelHero;

import java.util.List;

public interface HeroFileBasedRepository extends CrudRepository<MarvelHero, String> {
    List<MarvelHero> findAll();
    MarvelHero findByName(String name);
}
