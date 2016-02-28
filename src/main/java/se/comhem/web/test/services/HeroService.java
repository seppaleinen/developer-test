package se.comhem.web.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.comhem.web.test.domain.Hero;
import se.comhem.web.test.domain.MarvelHero;
import se.comhem.web.test.repositories.HeroFileBasedRepository;

import java.util.List;
import java.util.Map;

@Service
public class HeroService {

    @Autowired
    private HeroFileBasedRepository heroFileBasedRepository;

    public List<MarvelHero> list() {
        return heroFileBasedRepository.findAll();
    }

    public MarvelHero get(String name) {
        return heroFileBasedRepository.findByName(name);
    }

    public void save(MarvelHero hero) {
        heroFileBasedRepository.save(hero);
    }

}
