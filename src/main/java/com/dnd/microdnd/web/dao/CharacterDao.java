package com.dnd.microdnd.web.dao;

import com.dnd.microdnd.model.Character;

import java.util.List;

public interface CharacterDao {

    //Interface qui dit que la classe qui va l'implémenter va devoir ecrire ces méthodes.
    List<Character> findAll();
    Character findById(int id);
    Character save(Character character);

    Character update(int id, Character character);

    Character delete(int id);


}
