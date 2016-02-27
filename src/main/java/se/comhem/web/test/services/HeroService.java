package se.comhem.web.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.comhem.web.test.domain.Hero;
import se.comhem.web.test.repositories.HeroFileBasedRepository;

import java.util.Map;

@Service
public class HeroService {

    @Autowired
    HeroFileBasedRepository repository;

    public Map<Integer, Hero> list() {
        return repository.list();
    }

    public Hero get(Integer id) {
        return repository.get(id);
    }

    public void save(Hero hero) {
        repository.save(hero);
    }

}
