package com.dnd.microdnd.web.dao;

import java.util.ArrayList;
import com.dnd.microdnd.model.Character;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacterDaoImpl implements CharacterDao {


    public static List<Character> charList = new ArrayList<Character>();

    static {

        charList.add(new Character(0, "Allan", "Voleur", 12));
        charList.add(new Character(1, "Loris", "Démoniste", 7));
        charList.add(new Character(2, "Martin", "Assassin", 2));

    }

    @Override
    public List<Character> findAll(){
        return charList;
    }
    @Override
    public Character findById(int id){
        Character showChar = charList.stream().filter(elt -> elt.getId() == id).findFirst().orElse(null);
        return showChar;
    }
    @Override
    public Character save(Character character){
        charList.add(character);
        return character;
    }
    @Override
    public Character update(Character character){

        return character;
    }
    @Override
    public Character delete(Character character){
        charList.remove(character);
        return character;
    }
}
