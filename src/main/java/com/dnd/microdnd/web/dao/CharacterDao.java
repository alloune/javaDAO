package com.dnd.microdnd.web.dao;

import com.dnd.microdnd.model.Character;

import java.util.List;

public interface CharacterDao {
    List<Character> findAll();
    Character findById(int id);
    Character save(Character character);

    Character update(Character character);

    Character delete(Character character);


}
