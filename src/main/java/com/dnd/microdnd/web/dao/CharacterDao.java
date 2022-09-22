package com.dnd.microdnd.web.dao;

import com.dnd.microdnd.model.Hero;

import java.util.List;

public interface CharacterDao {

    //Interface qui dit que la classe qui va l'implémenter va devoir ecrire ces méthodes.
    List<Hero> findAll();
    Hero findById(int id);
    Hero save(Hero hero);

    Hero update(int id, Hero hero);

    Hero delete(int id);


}
