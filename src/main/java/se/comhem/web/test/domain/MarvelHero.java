package se.comhem.web.test.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MarvelHero implements Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String weakness;
    private Gender gender;

    public MarvelHero() { }

    public MarvelHero(String name, String weakness, Gender gender) {
        this.name = name;
        this.weakness = weakness;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWeakness() {
        return weakness;
    }

    public Gender getGender() {
        return gender;
    }
}
