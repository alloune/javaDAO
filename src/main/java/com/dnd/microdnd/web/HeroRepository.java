package com.dnd.microdnd.web;

import com.dnd.microdnd.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

    Hero save(Hero hero);

}
