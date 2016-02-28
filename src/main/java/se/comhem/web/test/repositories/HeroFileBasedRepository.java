package se.comhem.web.test.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.comhem.web.test.domain.Hero;

import java.util.Map;

@Repository
public interface HeroFileBasedRepository extends CrudRepository<Hero, Integer> {
    Map<Integer, Hero> list();
    Hero get(Integer id);
}
