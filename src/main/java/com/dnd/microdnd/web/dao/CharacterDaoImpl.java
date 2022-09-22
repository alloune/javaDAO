package com.dnd.microdnd.web.dao;

import java.util.ArrayList;
import com.dnd.microdnd.model.Hero;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public class CharacterDaoImpl implements CharacterDao {


    public static List<Hero> charList = new ArrayList<Hero>();

    static {

        charList.add(new Hero(0, "Allan", "Voleuse", 12));
        charList.add(new Hero(1, "Loris", "Démoniste", 7));
        charList.add(new Hero(2, "Martin", "Assassin", 2));

    }
    // Vu qu'on implémente le DAO, on écrit les methodes
    @Override
    public List<Hero> findAll(){
        return charList;
    }
    @Override
    public Hero findById(int id){
        Hero showChar = charList.stream().filter(elt -> elt.getId() == id).findFirst().orElse(null);
        return showChar;
    }
    @Override
    public Hero save(Hero hero){
        charList.add(hero);
        return hero;
    }
    @Override
    public Hero update(int id, @RequestBody Hero hero){

        Hero charToUpdate = charList.stream().filter(elt -> elt.getId() ==  id).findFirst().orElse(null);

        charToUpdate.setName(hero.getName());
        charToUpdate.setType(hero.getType());

        return charToUpdate;
    }
    @Override
    public Hero delete(int id){
        Hero charToDelete = charList.stream().filter(elt -> elt.getId() ==  id).findFirst().orElse(null);
        charList.remove(charToDelete);
        return charToDelete;

    }
}
